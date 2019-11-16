package com.example.controller;

import com.example.dao.TimeRepository;
import com.example.dao.TimeRepositoryImpl;
import com.example.dto.ConfirmPaid;
import com.example.entity.LanguageEntity;
import com.example.service.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalTime;

public class BotController extends TelegramLongPollingBot {

    //    private MyProperties properties = MyProperties.getInstance();
    private RegistrationService registrationService = new RegistrationService();
    private KeyboardService keyboardService = new KeyboardService();
    private SendMessageService sendMessageService = new SendMessageService();
    private ValidationService validationService = new ValidationService();
    private PaymentService paymentService = new PaymentService();
    private MatrixService matrixService = new MatrixService();
    private StatService statService = new StatService();
    private AdminService adminService = new AdminService();
    private LanguageService lng = new LanguageService();
    private TimeRepository timeRepository = new TimeRepositoryImpl();

    private boolean sendMsg = false;
    private boolean sendMsgForOne = false;
    private boolean createVote = false;
    private boolean confirmBotPay = false;
    private boolean timer = false;


    @Override
    public void onUpdateReceived(Update update) {
        LocalTime timeStart = LocalTime.parse(timeRepository.getTime("timer").getStartTime());
        LocalTime timeEnd = LocalTime.parse(timeRepository.getTime("timer").getEndTime());
        LocalTime now = LocalTime.now();


        Long userTelegramId;
        Message message = update.getMessage();

        if (update.hasCallbackQuery()) {
            userTelegramId = (long) update.getCallbackQuery().getFrom().getId();
        } else {
            userTelegramId = (long) message.getFrom().getId();
        }

        if (update.hasCallbackQuery()) {
            executeMessage(new SendMessage()
                    .setText(update.getCallbackQuery().getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));

//            if (update.getCallbackQuery().getData().equals("Pay / Оплатить")) {
//                executeMessage(sendMessageService.sendMsg(update, properties.getProperties().getProperty("contribUrl")));
//            }

            if (update.getCallbackQuery().getData().equals("Confirm/Подтвердить")) {
//                executeMessage(sendMessageService.sendMsgWithInLine(update, properties.getProperties().getProperty("contribUrl"), keyboardService.setButtonAdminReminder(userTelegramId)));
                if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                    adminService.confirmUserBotPayAuto(userTelegramId);

                    paymentService.askedToAdminConfirm(update, KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:") +
                            lng.getLng(userTelegramId).get("У Вас новый заказ!") + KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:")
                            + (" Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ")
                            + userTelegramId);
                    executeMessage(sendMessageService.sendMsg(update, lng.getLng(userTelegramId).get("Оплата добровольного взноса подтверждена! Теперь Вы можете приобрести уровень.")));
                }
            }

            if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                if (update.getCallbackQuery().getData().equals(lng.getLng(userTelegramId).get("Попросить подтвердить оплату"))) {
                    if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                        if (matrixService.getSponsorTelegramId(userTelegramId) != 0) {
                            paymentService.askedToUserConfirm(update, KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:") +
                                    lng.getLng(userTelegramId).get("У Вас новый заказ!") + KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:")
                                    + (" Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ")
                                    + userTelegramId);
                            executeMessage(sendMessageService.sendMsg(update, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Ваш заказ отправлен Вашему спонсору. После подтверждения Вашим спонсором получения денег Вам будет присвоен Уровень.")));
                        }
                    }
                }
            }

//            if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
//                if (update.getCallbackQuery().getData().equals(lng.getLng(userTelegramId).get("Подтвердить перевод"))) {
//                    if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
//                        adminService.confirmUserBotPayAuto(userTelegramId);
//
//                        paymentService.askedToAdminConfirm(update, KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:") +
//                                lng.getLng(userTelegramId).get("У Вас новый заказ!") + KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:")
//                                + (" Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ")
//                                + userTelegramId);
//                        executeMessage(sendMessageService.sendMsg(update, lng.getLng(userTelegramId).get("Оплата добровольного взноса подтверждена! Теперь Вы можете приобрести уровень.")));
//                    }
//                }
//            }

            if (update.getCallbackQuery().getData().matches("[0-9]+") && !validationService.isAnswer(userTelegramId)) {
                System.out.println(update.getCallbackQuery().getData()); //test
                adminService.giveAnswer(userTelegramId, update.getCallbackQuery().getData());
            }

            if (update.getCallbackQuery().getData().equals("English")) {
                LanguageEntity lang = new LanguageEntity();
                lang.setUserTelegramId(userTelegramId);
                lang.setLanguage("e");
                if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                    lng.updateLang(lang);
                    executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Добро пожаловать!")));
                } else {
                    lng.saveLang(lang);
                    executeMessage(sendMessageService.sendMsgWithInLine(update, statService.getStartMessage(userTelegramId), keyboardService.setButtonInlineRegistration()));
                }
            }

            if (update.getCallbackQuery().getData().equals("Русский")) {
                LanguageEntity lang = new LanguageEntity();
                lang.setUserTelegramId(userTelegramId);
                lang.setLanguage("r");
                if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                    lng.updateLang(lang);
                    executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Добро пожаловать!")));
                } else {
                    lng.saveLang(lang);
                    executeMessage(sendMessageService.sendMsgWithInLine(update, statService.getStartMessage(userTelegramId), keyboardService.setButtonInlineRegistration()));
                }
            }

            if (update.getCallbackQuery().getData().equals("Registration/Регистрация")) {
                if (!validationService.userIsExist(userTelegramId)) {
                    executeMessage(sendMessageService.sendMsgWithInLine(update, KeyboardService.emoji(":busts_in_silhouette:") + lng.getLng(userTelegramId).get("Введите ID пригласившего Вас пользователя!"),
                            keyboardService.setButtonNextStepRegistration()));
                } else {
                    executeMessage(sendMessageService.getKeyboardMainMenu(message, KeyboardService.emoji(":exclamation:") + lng.getLng(userTelegramId).get("Вы уже зарегистрированы в системе")));
                }
            }

        }

        if (message.hasText()) {

            boolean showMsgSponsorCard = true;

//            if (!userTelegramId.equals(Long.valueOf(properties.getProperties().getProperty("adminId")))) {
            if (!userTelegramId.equals(Long.valueOf("0000121"))) {
                if (now.isAfter(timeStart) && now.isBefore(timeEnd)) {

                    if (message.getText().equalsIgnoreCase("/start")) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Добро пожаловать!")));
                        } else {
                            executeMessage(sendMessageService.sendMsgWithInLine(message, "Choose language / Выберите язык",
                                    keyboardService.setButtonsLanguageStart()));
                        }
                    }

                    if (message.getText().contains("English")) {
                        LanguageEntity lang = new LanguageEntity();
                        lang.setUserTelegramId(userTelegramId);
                        lang.setLanguage("e");
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            lng.updateLang(lang);
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Добро пожаловать!")));
                        } else {
                            lng.saveLang(lang);
                            executeMessage(sendMessageService.sendMsgWithInLine(message, statService.getStartMessage(userTelegramId), keyboardService.setButtonInlineRegistration()));
                        }
                    }

                    if (message.getText().contains("Русский")) {
                        LanguageEntity lang = new LanguageEntity();
                        lang.setUserTelegramId(userTelegramId);
                        lang.setLanguage("r");
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            lng.updateLang(lang);
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Добро пожаловать!")));
                        } else {
                            lng.saveLang(lang);
                            executeMessage(sendMessageService.sendMsgWithInLine(message, statService.getStartMessage(userTelegramId), keyboardService.setButtonInlineRegistration()));
                        }
                    }

                    if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                        if (!validationService.isAnswer(userTelegramId) && validationService.isVoteExist()) {
                            if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                                executeMessage(adminService.createVotingForUser(message, adminService.getTextActiveVote()));
                            }
                        }
                    }

                    if (message.getText().equalsIgnoreCase(lng.getLng(userTelegramId).get("Назад"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Главное меню")));
                        }
                    }


                    if (message.getText().contains(lng.getLng(userTelegramId).get("Язык"))) {
                        executeMessage(sendMessageService.getKeyboardLanguage(message, "Выберите язык / Choose language"));
                    }

                    if (message.getText().equalsIgnoreCase(lng.getLng(userTelegramId).get("Регистрация"))) {
                        if (!validationService.userIsExist(userTelegramId)) {
                            //executeMessage(sendMessageService.getKeyboardRegistration(message, lng.getLng(userTelegramId).get("Приветствую! Я телеграм хайп-бот! \nЗарегистрируйтесь!")));
                            executeMessage(sendMessageService.getKeyboardRegistration(update, statService.getStartMessage(userTelegramId)));
                        } else {
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Добро пожаловать!")));
                        }
                    }

                    if (message.getText().toLowerCase().equalsIgnoreCase(lng.getLng(userTelegramId).get("Registration/Регистрация"))) {
                        if (!validationService.userIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsgWithInLine(message, KeyboardService.emoji(":busts_in_silhouette:") + lng.getLng(userTelegramId).get("Введите ID пригласившего Вас пользователя!"),
                                    keyboardService.setButtonNextStepRegistration()));
                        } else {
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Вы уже зарегистрированы в системе")));
                        }
                    }

                    if (message.getText().matches("[0-9]+") && !message.getText().matches("\\d{16}")) {
                        if (!validationService.userIsExist(Long.valueOf(message.getText()))) {
                            executeMessage(sendMessageService.getKeyboardRegistration(update, KeyboardService.emoji(":x:") + lng.getLng(userTelegramId).get("Пользователь с таким ID не найден! \nЗарегистрируйтесь!")));
                        } else {
                            if (!validationService.userIsExist(userTelegramId)) {
                                registrationService.registration(message, Long.valueOf(message.getText()));
                                executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":credit_card:") + lng.getLng(userTelegramId).get("Введите номер Вашей кредитной карты, 16 цифр!")));
                            } else {
                                executeMessage(sendMessageService.getKeyboardMainMenu(message, lng.getLng(userTelegramId).get("Вы уже зарегистрированы в системе")));
                            }
                        }
                    }

                    if (message.getText().matches("\\d{16}")) {
                        if (!validationService.walletIsExist(userTelegramId)) {
                            if (!validationService.userIsExist(userTelegramId)) {
                                registrationService.registration(message, 0L);
                                registrationService.updateNumberOfWallet(message, message.getText());
                                executeMessage(sendMessageService.getKeyboardMainMenu(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Вы успешно зарегистрированы! Ваш ID: ") + message.getFrom().getId()));
                            } else {
                                registrationService.updateNumberOfWallet(message, message.getText());
                                executeMessage(sendMessageService.getKeyboardMainMenu(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Вы успешно зарегистрированы! Ваш ID: ") + message.getFrom().getId()));
                            }
                        } else {
                            executeMessage(sendMessageService.getKeyboardMainMenu(message, KeyboardService.emoji(":exclamation:") + lng.getLng(userTelegramId).get("У вас уже есть кредитная карта!")));
                        }
                    }
                    // смена номера карты
                    if (message.getText().matches("new \\d{16}")) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsg(message, paymentService.checkoutWallet(message)));
                        }
                    }

//                if (message.getText().contains(lng.getLng(userTelegramId).get("Оформить заказ"))) {
//                    if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
//                        executeMessage(sendMessageService.getKeyboardOrder(message, lng.getLng(userTelegramId).get("Сделайте заказ")));
//                    }
//                }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Сделать заказ"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            if (!validationService.getBotPaid(userTelegramId)) {
                                executeMessage(sendMessageService.sendMsgWithInLine(message, KeyboardService.emoji(":moneybag:") + lng.getLng(userTelegramId).get("Сделайте добровольный взнос. Переведите 10 UAH участнику проекта на его карту 0000 0000 0000 0000. После перевода средств нажмите кнопку \"подтвердить перевод\". Если Вы нажали кнопку \"подтвердить перевод\", но не перевели средства, то в течении суток Ваш аккаунт будет удален!"), keyboardService.setButtonPayToBot()));
                            }

                            if (!validationService.getSponsorPaid(userTelegramId) && validationService.getBotPaid(userTelegramId) && matrixService.getSponsorTelegramId(userTelegramId) == 0) {
                                matrixService.addUserToMatrix(message, userTelegramId);
                                if (matrixService.getSponsorTelegramId(userTelegramId) != 0L) {
                                    showMsgSponsorCard = false;
                                    executeMessage(sendMessageService.sendMsgWithInLine(message, KeyboardService.emoji(":exclamation:") + lng.getLng(userTelegramId).get("Чтобы приобрести уровень, переведите средства Вашему спонсору (цену за каждый уровень можно узнать в разделе FAQ).\nВ примечании к платежу напишите свой ID. После перевода средств нажмите кнопку \"Попросить подтвердить оплату\" \nНомер карты спонсора для оплаты: ")
                                            + matrixService.getWalletForPaid(userTelegramId), keyboardService.setButtonReminder(userTelegramId)));
                                } else {
                                    executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":warning:") + lng.getLng(userTelegramId).get("В данный момент нет места в матрице, ожидаются оплаты пользователей \nПопробуйте позже")));
                                }
                            }

                            if (validationService.getSponsorPaid(userTelegramId) && validationService.getBotPaid(userTelegramId) && validationService.getUserLvl(userTelegramId) > 0) {
                                if (matrixService.transitionToNextLevel(message)) {
                                    executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Матрица закрылась!")));
                                } else {
                                    executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":exclamation:") + lng.getLng(userTelegramId).get("Ваша матрица еще не закрылась!")));
                                }
                            }

                            if (!validationService.getSponsorPaid(userTelegramId) && validationService.getBotPaid(userTelegramId) && matrixService.getSponsorTelegramId(userTelegramId) != 0 && showMsgSponsorCard) {
                                executeMessage(sendMessageService.sendMsgWithInLine(message, KeyboardService.emoji(":exclamation:") + lng.getLng(userTelegramId).get("Чтобы приобрести уровень, переведите средства Вашему спонсору (цену за каждый уровень можно узнать в разделе FAQ).\nВ примечании к платежу напишите свой ID. После перевода средств нажмите кнопку \"Попросить подтвердить оплату\" \nНомер карты спонсора для оплаты: ")
                                        + matrixService.getWalletForPaid(userTelegramId), keyboardService.setButtonReminder(userTelegramId)));
                            }

                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Подтвердить оплату")) && message.getText().contains("ID")) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            ConfirmPaid confirmPaid = matrixService.isParentOfUser(userTelegramId, message.getText());
                            if (confirmPaid.getPaidConfirm()) {
                                paymentService.confirmUser(confirmPaid);
//                                statService.updateBalance(userTelegramId, Double.valueOf(properties.getProperties().getProperty("contrib")));
                                statService.updateBalance(userTelegramId, Double.valueOf("2"));
                                executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Вы подтвердили платеж пользователя ID ") + confirmPaid.getTelegramId()));
                            } else {
                                executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Платеж этого пользователя уже подтвержден!")));
                            }
                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Покупка уровня подтверждена"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            if (validationService.getSponsorPaid(userTelegramId) && validationService.getBotPaid(userTelegramId)) {
                                executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Теперь у вас уровень ") + validationService.getUserLvl(userTelegramId)));
                            }
                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Моя команда"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.getKeyboardMyCabinet(message, KeyboardService.emoji(":busts_in_silhouette:") + lng.getLng(userTelegramId).get("Тут ваша команда")));

                        }
                    }

//                if (message.getText().equalsIgnoreCase(lng.getLng(userTelegramId).get("Попросить подтвердить оплату"))) {
//                    if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
//                        if (matrixService.getSponsorTelegramId(userTelegramId) != 0) {
//                            paymentService.askedToUserConfirm(message, KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:") +
//                                    lng.getLng(userTelegramId).get("У Вас новый заказ!") + KeyboardService.emoji(":heavy_exclamation_mark:") + KeyboardService.emoji(":heavy_exclamation_mark:")
//                                    + (" Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ")
//                                    + userTelegramId);
//                            executeMessage(sendMessageService.sendMsg(message, lng.getLng(userTelegramId).get("Ваш заказ отправлен Вашему спонсору. После подтверждения Вашим спонсором получения денег Вам будет присвоен Уровень.")));
//                        }
//                    }
//                }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Подтвердить оплату"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.getConfirmUserKeyboard(message, KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Подтвердите"), paymentService.getConfirmUsersPaid(userTelegramId)));
                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Состав моей команды"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsg(message, statService.sendInfoAboutMyTeam(userTelegramId)));
                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Сменить карту"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsg(message, KeyboardService.emoji(":pencil2:") + lng.getLng(userTelegramId).get("Введите новый номер карты \nФормат ввода: new 0000000000000000")));
                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Аккаунт"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsg(message, statService.sendStat(userTelegramId)));
                        }
                    }

                    if (message.getText().contains("FAQ")) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsg(message, lng.getLang(userTelegramId).getLanguage().equals("e") ? statService.getEngFaq() : statService.getRusFaq()));
                        }
                    }

                    if (message.getText().contains(lng.getLng(userTelegramId).get("Поддержка"))) {
                        if (validationService.userIsExist(userTelegramId) && validationService.walletIsExist(userTelegramId)) {
                            executeMessage(sendMessageService.sendMsg(message, "email: voitec1515@gmail.com"));
                        }
                    }

                }
                //admin account
            } else {

                if (timer) {
                    timer = false;
                    adminService.setTimer(message.getText());
                    executeMessage(sendMessageService.sendMsg(message, "Установлено новое время работы бота " + message.getText()));
                }

                if (confirmBotPay) {
                    confirmBotPay = false;
                    executeMessage(sendMessageService.sendMsg(message, adminService.confirmUserBotPay(Long.valueOf(message.getText()))));
                }

                if (sendMsg) {
                    sendMsg = false;
                    adminService.sendToAll(message.getText());
                }

                //format id*text
                if (sendMsgForOne) {
                    sendMsgForOne = false;
                    adminService.sendToOne(message.getText());
                }
                //format question*answer*answer
                if (createVote) {
                    createVote = false;
                    adminService.sendToAll("Создан новый опрос, пройдите его пожалуйста");
                    executeMessage(adminService.createVoting(message, message.getText()));
                }

                if (message.getText().equalsIgnoreCase("/start")) {
                    executeMessage(sendMessageService.getKeyboardAdminMainMenu(message, "Аккаунт администратора"));
                }

                if (message.getText().equalsIgnoreCase("Назад")) {
                    executeMessage(sendMessageService.getKeyboardAdminMainMenu(message, "Главное меню"));
                }

                if (message.getText().equalsIgnoreCase("Подтверждение оплат")) {
                    executeMessage(sendMessageService.getKeyboardAdminConfirmPay(message, "Выберите уровень"));
                }

                if (message.getText().matches("Уровень \\d{1}")) {
                    executeMessage(sendMessageService.getConfirmUserKeyboard(message, "Подтвердите",
                            adminService.getUserToConfirmForAdmin(Long.valueOf(message.getText().split(" ")[1]))));
                }

                if (message.getText().equalsIgnoreCase("Подтвердить обязательные оплаты")) {
                    confirmBotPay = true;
                }


                if (message.getText().contains("Подтвердить оплату") && message.getText().contains("ID")) {
                    String[] text = message.getText().split(" ");
                    ConfirmPaid confirmPaid = matrixService.isParentOfUser((long) validationService.getUserLvl(Long.valueOf(text[text.length - 1])), message.getText());
                    if (confirmPaid.getPaidConfirm()) {
                        paymentService.confirmUser(confirmPaid);
                        executeMessage(sendMessageService.sendMsg(message, "Вы подтвердили платеж пользователя ID " + confirmPaid.getTelegramId()));
                    } else {
                        executeMessage(sendMessageService.sendMsg(message, "Платеж этого пользователя уже подтвержден!"));
                    }

                }

                if (message.getText().equalsIgnoreCase("Удалить неактивных")) {
                    executeMessage(sendMessageService.getKeyboardAdminMainMenu(message, "Введите: delete"));
                }

                if (message.getText().equalsIgnoreCase("delete")) {
                    paymentService.removeAllUserWhoDoNotPaid();
                    executeMessage(sendMessageService.getKeyboardAdminMainMenu(message, "Все пользователи не оплатившие покупку уровня были удалены!"));
                }

                if (message.getText().equalsIgnoreCase("Написать всем")) {
                    sendMsg = true;
                }

                if (message.getText().equalsIgnoreCase("Написать одному")) {
                    sendMsgForOne = true;
                }

                if (message.getText().equalsIgnoreCase("Создать опрос")) {
                    createVote = true;
                }

                if (message.getText().equalsIgnoreCase("Установить таймер")) {
                    timer = true;
                }


                if (message.getText().equalsIgnoreCase("Статистика")) {
                    executeMessage(sendMessageService.getKeyboardResultVotes(message, statService.sendInfoAboutBot(userTelegramId)));
                }

                if (message.getText().equalsIgnoreCase("Результаты опросов")) {
                    executeMessage(sendMessageService.sendMsg(message, statService.getResultVotes(userTelegramId)));
                }

            }
        }
    }


    private void executeMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //    @Override
//    public String getBotUsername() {
//        return "Test29BotValBot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "967287812:AAEAjnZ6gIVczLECLc9J99KAz9oYWORaE9Q";
//    }
    @Override
    public String getBotUsername() {
        return "dating_zp_bot";
    }

    @Override
    public String getBotToken() {
        return "920411958:AAEjN4VyKGZYkoKZalvdIfhD0PROnFGtwL0";
    }
}

