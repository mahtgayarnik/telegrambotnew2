package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRepositoryImpl;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;

public class KeyboardService {

    private ReplyKeyboardMarkup replyKeyboardMarkup;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    private UserRepository userRepository = new UserRepositoryImpl();
    private LanguageService lng = new LanguageService();

    public static String emoji(String emoji) {
        return EmojiParser.parseToUnicode(emoji);
    }

    public ReplyKeyboardMarkup setButtonsStartRegistration(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Зарегистрируйтесь/Registration"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsResultVotes(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Результаты опросов")));
        keyboardFirstRow.add(new KeyboardButton(lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Назад")));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsMainMenu(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(emoji(":bust_in_silhouette:") + " " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Аккаунт")));
        keyboardFirstRow.add(new KeyboardButton(emoji(":family:") + " " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Моя команда")));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(emoji(":moneybag:") + " " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Сделать заказ")));
        keyboardSecondRow.add(new KeyboardButton(emoji(":credit_card:") + " " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Сменить карту")));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton(emoji(":ru:") + emoji(":gb:") + " " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Язык")));
        keyboardThirdRow.add(new KeyboardButton(emoji(":question:") + " " + "FAQ"));
        keyboardThirdRow.add(new KeyboardButton(emoji(":wrench:") + " " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Поддержка")));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsOrder(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Сделать заказ")));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Попросить подтвердить оплату")));
        KeyboardRow keyboardFourthRow = new KeyboardRow();
        keyboardFourthRow.add(new KeyboardButton(lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Назад")));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardFourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsMyTeam(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(emoji(":white_check_mark:") + "  " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Подтвердить оплату")));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton(emoji(":busts_in_silhouette:") + "  " + lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Состав моей команды")));
        KeyboardRow keyboardFourthRow = new KeyboardRow();
        keyboardFourthRow.add(new KeyboardButton(lng.getLng(Long.valueOf(sendMessage.getChatId())).get("Назад")));
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        keyboardRowList.add(keyboardFourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsConfirmAdminsSubscribers(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardZeroRow = new KeyboardRow();
        keyboardZeroRow.add(new KeyboardButton("Подтвердить обязательные оплаты"));
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Уровень 1"));
        keyboardFirstRow.add(new KeyboardButton("Уровень 2"));
        keyboardFirstRow.add(new KeyboardButton("Уровень 3"));
        KeyboardRow keyboardFourthRow = new KeyboardRow();
        keyboardFourthRow.add(new KeyboardButton("Уровень 4"));
        keyboardFourthRow.add(new KeyboardButton("Уровень 5"));
        keyboardFourthRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(keyboardZeroRow);
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardFourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsAdminMainMenu(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Статистика"));
        keyboardFirstRow.add(new KeyboardButton("Подтверждение оплат"));
        keyboardFirstRow.add(new KeyboardButton("Удалить неактивных"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("Написать всем"));
        keyboardThirdRow.add(new KeyboardButton("Написать одному"));
        keyboardThirdRow.add(new KeyboardButton("Создать опрос"));
        KeyboardRow keyboardFourRow = new KeyboardRow();
        keyboardFourRow.add(new KeyboardButton("Установить таймер"));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardThirdRow);
        keyboardRowList.add(keyboardFourRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsLanguage(SendMessage sendMessage) {
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(emoji(":ru:") + " " + ("Русский")));
        keyboardFirstRow.add(new KeyboardButton(emoji(":gb:") + " " + ("English")));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtonsConfirmUser(SendMessage sendMessage, List<Long> telegramIds) {
        if (telegramIds.size() == 0) return setButtonsMainMenu(new SendMessage());
        initReplyKeyboard();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        telegramIds.stream().filter(Objects::nonNull).filter(id -> id != 0L).filter(id -> userRepository.getUserByTelegramId(id).getPaidSponsor().equals(false)).forEach(id -> {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Подтвердить оплату " + Optional.ofNullable(userRepository.getUserByTelegramId(id).getUserName()).orElse("") + " ID " + id));
            keyboardRowList.add(keyboardRow);
        });
        KeyboardRow lastRow = new KeyboardRow();
        lastRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(lastRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup setButtonsForVoting(List<String> answers) {
        initInlineKeyboard();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        answers.stream().filter(Objects::nonNull).forEach(answer -> {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(new InlineKeyboardButton().setText(answer).setCallbackData(String.valueOf(answers.indexOf(answer))));
            keyboard.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup setButtonsLanguageStart() {
        initInlineKeyboard();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButton = new ArrayList<>();
        inlineKeyboardButton.add(new InlineKeyboardButton().setText(KeyboardService.emoji(":gb:") + " English").setCallbackData("English"));
        inlineKeyboardButton.add(new InlineKeyboardButton().setText(KeyboardService.emoji(":ru:") + " Русский").setCallbackData("Русский"));
        keyboard.add(inlineKeyboardButton);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup setButtonNextStepRegistration() {
        initInlineKeyboard();
        List<InlineKeyboardButton> keyboardButtonsRowOne = new ArrayList<>();
        keyboardButtonsRowOne.add(new InlineKeyboardButton().setText("skip / пропустить")
                .setCallbackData(KeyboardService.emoji(":credit_card:") + "enter card number/введите номер карты"));
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRowOne));
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup setButtonInlineRegistration() {
        initInlineKeyboard();
        List<InlineKeyboardButton> keyboardButtonsRowOne = new ArrayList<>();
        keyboardButtonsRowOne.add(new InlineKeyboardButton().setText("Registration/Регистрация")
                .setCallbackData("Registration/Регистрация"));
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRowOne));
        return inlineKeyboardMarkup;
    }


    public InlineKeyboardMarkup setButtonReminder(Long userTelegramId) {
        initInlineKeyboard();
        List<InlineKeyboardButton> keyboardButtonsRowOne = new ArrayList<>();
        keyboardButtonsRowOne.add(new InlineKeyboardButton().setText(emoji(":white_check_mark:") + " " + lng.getLng(userTelegramId).get("Попросить подтвердить оплату"))
                .setCallbackData(lng.getLng(userTelegramId).get("Попросить подтвердить оплату")));
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRowOne));
        return inlineKeyboardMarkup;
    }


    public InlineKeyboardMarkup setButtonAdminReminder(Long userTelegramId) {
        initInlineKeyboard();
        List<InlineKeyboardButton> keyboardButtonsRowOne = new ArrayList<>();
        keyboardButtonsRowOne.add(new InlineKeyboardButton().setText(emoji(":white_check_mark:")+ " " + lng.getLng(userTelegramId).get("Подтвердить перевод"))
                .setCallbackData(lng.getLng(userTelegramId).get("Подтвердить перевод")));
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRowOne));
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup setButtonPayToBot() {
        initInlineKeyboard();
        List<InlineKeyboardButton> keyboardButtonsRowOne = new ArrayList<>();
//        keyboardButtonsRowOne.add(new InlineKeyboardButton().setText("Pay / Оплатить" + " " + MyProperties.getInstance().getProperties().getProperty("contrib") + " " +
        keyboardButtonsRowOne.add(new InlineKeyboardButton().setText("Pay/Оплатить liqpay 5 UAH")
//                MyProperties.getInstance().getProperties().getProperty("currency"))
                .setCallbackData("Pay / Оплатить"));
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRowOne));
        return inlineKeyboardMarkup;
    }

    private void initReplyKeyboard() {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
    }

    private void initInlineKeyboard() {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
    }

}
