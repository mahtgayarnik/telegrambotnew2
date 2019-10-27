package com.example.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;
import java.util.function.Consumer;

public class SendMessageService {

    private KeyboardService keyboardService = new KeyboardService();

    public SendMessage sendMsg(Message message, String text) {
        SendMessage sendMessage = initMsg(message);
        sendMessage.setText(text);
        return sendMessage;
    }

    public SendMessage sendMsg(Update update, String text) {
        SendMessage sendMessage = initMsgInline(update);
        sendMessage.setText(text);
        return sendMessage;
    }

    public SendMessage sendMsgWithInLine(Message message, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        return new SendMessage().setChatId(message.getChatId()).setText(text).setReplyMarkup(inlineKeyboardMarkup);
    }

    public SendMessage sendMsgWithInLine(Update update, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        return new SendMessage().setChatId(String.valueOf(update.getCallbackQuery().getFrom().getId())).setText(text).setReplyMarkup(inlineKeyboardMarkup);
    }

    public SendMessage getConfirmUserKeyboard(Message message, String text, List<Long> ids) {
        SendMessage sendMessage = initMsg(message);
        sendMessage.setText(text);
        keyboardService.setButtonsConfirmUser(sendMessage, ids);
        return sendMessage;
    }

    public SendMessage getKeyboardLanguage(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsLanguage);
    }

    public SendMessage getKeyboardOrder(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsOrder);
    }

    public SendMessage getKeyboardResultVotes(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsResultVotes);
    }

    public SendMessage getKeyboardAdminConfirmPay(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsConfirmAdminsSubscribers);
    }

    public SendMessage getKeyboardRegistration(Message message, String text) {
       return sendMsgKeyboard(message, text, keyboardService::setButtonsStartRegistration);
    }

    public SendMessage getKeyboardRegistration(Update update, String text) {
        return sendMsgKeyboardInline(update, text, keyboardService::setButtonsStartRegistration);
    }

    public SendMessage getKeyboardAdminMainMenu(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsAdminMainMenu);
    }

    public SendMessage getKeyboardMainMenu(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsMainMenu);
    }

    public SendMessage getKeyboardMyCabinet(Message message, String text) {
        return sendMsgKeyboard(message, text, keyboardService::setButtonsMyTeam);
    }

    private SendMessage initMsg(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }


    private SendMessage sendMsgKeyboard(Message message, String text, Consumer<SendMessage> getKeyBoard) {
        SendMessage sendMessage = initMsg(message);
        sendMessage.setText(text);
        getKeyBoard.accept(sendMessage);
        return sendMessage;
    }

    private SendMessage sendMsgKeyboardInline(Update update, String text, Consumer<SendMessage> getKeyBoard) {
        SendMessage sendMessage = initMsgInline(update);
        sendMessage.setText(text);
        getKeyBoard.accept(sendMessage);
        return sendMessage;
    }

    private SendMessage initMsgInline(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
        return sendMessage;
    }

}
