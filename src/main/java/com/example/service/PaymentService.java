package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRepositoryImpl;
import com.example.dto.ConfirmPaid;
import com.example.entity.Transaction;
import com.example.entity.TransactionsRequest;
import com.example.entity.TransactionsResponse;
import com.example.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentService {

    private ValidationService validationService = new ValidationService();
    private MatrixService matrixService = new MatrixService();
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
//    private MyProperties properties = MyProperties.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private LanguageService lng = new LanguageService();

    public void updatePaidBotStatus(String cardPan, Integer lvl) {
        userRepository.updatePaidBotStatus(cardPan, lvl);
    }

    public void processTransactions(Long dateBegin, Long endDate) {
        TransactionsRequest req = new TransactionsRequest();
        try {
            TransactionsResponse resp = getTransactionResponse(req, dateBegin, endDate);
//            Double amount = Double.valueOf(properties.getProperties().getProperty("contrib"));
            Double amount = Double.valueOf("2");
            Set<String> cards = resp.getTransactionList()
                    .stream()
                    .filter(transaction -> ("Approved").equals(transaction.getTransactionStatus()) && ("card").equals(transaction.getPaymentSystem()) /* test && amount.equals(transaction.getAmount())*/)
                    .map(Transaction::getCardPan).map(cardPan -> cardPan.replace("****", "%")).collect(Collectors.toSet());

            for (int i = 1; i < 6; i++) {
                int finalI = i;
                Objects.requireNonNull(cards).forEach(card -> {
                    updatePaidBotStatus(card, finalI - 1);
                    Long telegramId = userRepository.getTelegramId(card, finalI - 1);
                    sendConfirmPaid(telegramId, "Оплата добровольного взноса подтверждена/Approved \nНажмите сделать заказ/click make an order");
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TransactionsResponse getTransactionResponse(TransactionsRequest req, Long dateBegin, Long dateEnd) throws IOException {
        req.setDateBegin(dateBegin);
        req.setDateEnd(dateEnd);
        String sign = req.getMerchantAccount() + ";" + req.getDateBegin() + ";" + req.getDateEnd() + ";";
        //String key = properties.getProperties().getProperty(/*"merchantSignature"*/"origMerchantSignatureKey");
        String key ="0228b4051b60498637d0813e347a764f3e75250d";
        String hash = HmacUtils.hmacMd5Hex(sign, key);
        req.setMerchantSignature(hash);

        String request = objectMapper.writeValueAsString(req);
        HttpEntity<String> httpEntity = new HttpEntity<>(request);
        String json = null;
        try {
//            json = restTemplate.exchange("properties.getProperties().getProperty("mainUrl")", HttpMethod.POST, httpEntity, String.class).getBody();
            json = restTemplate.exchange("https://api.wayforpay.com/api", HttpMethod.POST, httpEntity, String.class).getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " RUNNING");
        }
        return objectMapper.readValue(Optional.ofNullable(json).orElse(""), TransactionsResponse.class);
    }

    public void removeAllUserWhoDoNotPaid() {
        List<Long> iDs = userRepository.removeAllUserWhoDoNotPaid();
        iDs.forEach(id -> userRepository.updateUserByReferralId(id));
    }

    public void sendConfirmPaid(Long telegramId, String text) {
//        String url = properties.getProperties().getProperty("baseUrl");
        String url = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
//        url = String.format(url, properties.getProperties().getProperty("botToken"), String.valueOf(telegramId), text);
        url = String.format(url, "967287812:AAEAjnZ6gIVczLECLc9J99KAz9oYWORaE9Q", String.valueOf(telegramId), text);
        try {
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(""), String.class);
        } catch (HttpClientErrorException | ResourceAccessException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " RUNNING");
        }
    }

    public void askedToUserConfirm(Update update, String text) {
        Long parentId = matrixService.getSponsorsSponsorTelegramId((long) update.getCallbackQuery().getFrom().getId());
//        String url = properties.getProperties().getProperty("baseUrl");
        String url = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
//        url = String.format(url, properties.getProperties().getProperty("botToken"), String.valueOf(parentId), text);
        url = String.format(url, "967287812:AAEAjnZ6gIVczLECLc9J99KAz9oYWORaE9Q", String.valueOf(parentId), text);
        try {
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(""), String.class);
        } catch (HttpClientErrorException | ResourceAccessException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " RUNNING");
        }
    }

    public void askedToAdminConfirm(Update update, String text) {
//        String url = properties.getProperties().getProperty("baseUrl");
        String url = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
//        url = String.format(url, properties.getProperties().getProperty("botToken"), String.valueOf(properties.getProperties().getProperty("adminId")), text);
//        url = String.format(url, properties.getProperties().getProperty("botToken"), String.valueOf("01010101"), text);
        url = String.format(url, "967287812:AAEAjnZ6gIVczLECLc9J99KAz9oYWORaE9Q", String.valueOf("01010101"), text);
        try {
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(""), String.class);
        } catch (HttpClientErrorException | ResourceAccessException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " RUNNING");
        }
    }

    public List<Long> getConfirmUsersPaid(Long telegramId) {
        UserEntity user = userRepository.getUserByTelegramId(telegramId);
        List<Long> telegramIds = new ArrayList<>();
        UserEntity leftUser = Optional.ofNullable(userRepository.getUserByTelegramId(user.getInvitedLeftId())).orElse(new UserEntity());
        UserEntity rightUser = Optional.ofNullable(userRepository.getUserByTelegramId(user.getInvitedRightId())).orElse(new UserEntity());
        telegramIds.add(Optional.ofNullable(leftUser.getInvitedLeftId()).orElse(0L));
        telegramIds.add(Optional.ofNullable(leftUser.getInvitedRightId()).orElse(0L));
        telegramIds.add(Optional.ofNullable(rightUser.getInvitedLeftId()).orElse(0L));
        telegramIds.add(Optional.ofNullable(rightUser.getInvitedRightId()).orElse(0L));
        return telegramIds;
    }

    public void confirmUser(ConfirmPaid confirmPaid) {
        UserEntity confirmUser = userRepository.getUserByTelegramId(confirmPaid.getTelegramId());
        confirmUser.setPaidSponsor(true);
        int lvl = validationService.getUserLvl(confirmUser.getTelegramId());
        confirmUser.setLevel(lvl != 5 ? (lvl + 1) : 1);
        //confirmUser.setBalance(confirmUser.getBalance() + -2.0); //todo подставить переменную из проперти
        userRepository.updateUser(confirmUser);
        sendConfirmPaid(confirmUser.getTelegramId(), KeyboardService.emoji(":white_check_mark:") + lng.getLng(confirmUser.getTelegramId()).get("Покупка уровня подтверждена"));
    }

    public String checkoutWallet(Message message) {
        UserEntity user = userRepository.getUserByTelegramId((long) message.getFrom().getId());
        String[] msg = message.getText().split(" ");
        if (msg[1].length() == 16 && msg[1].matches("\\d{16}")) {
            user.setWallet(msg[1]);
            userRepository.updateUser(user);
            getConfirmUsersPaid((long) message.getFrom().getId()).forEach(id -> sendConfirmPaid(id, KeyboardService.emoji(":exclamation:") + lng.getLng(Long.valueOf(message.getFrom().getId())).get("Спонсор спонсора изменил номер карты \nПроизведите оплату на эту карту:"
                    + " " + user.getWallet())));
            return KeyboardService.emoji(":white_check_mark:") + lng.getLng(Long.valueOf(message.getFrom().getId())).get("Номер карты успешно изменен!");
        }
        return KeyboardService.emoji(":x:") + lng.getLng(Long.valueOf(message.getFrom().getId())).get("Номер карты не был изменен, попробуйте еще раз!");
    }
    //test
//    public static void main(String[] args) throws IOException {
//        MyProperties properties = MyProperties.getInstance();
//        ObjectMapper objectMapper = new ObjectMapper();
//        RestTemplate restTemplate = new RestTemplate();
//        TransactionsRequest req = new TransactionsRequest();
//        req.setDateBegin(/*1454277600L*/Instant.now().getEpochSecond() - 120L);
//        req.setDateEnd(/*1454364000L*/ Instant.now().getEpochSecond());
//        String sign = req.getMerchantAccount() + ";" + req.getDateBegin() + ";" + req.getDateEnd() + ";";
//        String key = properties.getProperties().getProperty("origMerchantSignatureKey");
//        String hash = HmacUtils.hmacMd5Hex(sign, key);
//        req.setMerchantSignature(hash);
//
//        String request = objectMapper.writeValueAsString(req);
//        HttpEntity<String> httpEntity = new HttpEntity<>(request);
//        String json = restTemplate.exchange(properties.getProperties().getProperty("mainUrl"), HttpMethod.POST, httpEntity, String.class).getBody();
//        TransactionsResponse resp = objectMapper.readValue(json, TransactionsResponse.class);
//        for (Transaction transaction : resp.getTransactionList()) {
//            if (transaction.getPaymentSystem() != null && transaction.getCardPan() != null && "card".equals(transaction.getPaymentSystem()) && "Approved".equals(transaction.getTransactionStatus())) {
//                System.out.println(transaction.getCardPan());
//            }
//        }
//        RestTemplate restTemplate = new RestTemplate();
//       String url = "https://api.interkassa.com/v1/currency/5d96354a1ae1bd0f008b4573";
//       String s = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class).getBody();
//        System.out.println(s);
//    }

}
