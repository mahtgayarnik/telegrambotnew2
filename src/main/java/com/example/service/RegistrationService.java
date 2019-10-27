package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRepositoryImpl;
import com.example.entity.UserEntity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class RegistrationService {

    private UserRepository userRepository = new UserRepositoryImpl();

    public void registration(Message message, Long invitingId) {
        UserEntity user = new UserEntity();
        User telegramUser = message.getFrom();
        user.setTelegramId(Long.valueOf(telegramUser.getId()));
        user.setFirstName(telegramUser.getFirstName());
        user.setLastName(telegramUser.getLastName());
        user.setBalance(0.0);
        user.setLevel(0);
        user.setInvitingId(invitingId);
        user.setInvitedLeftId(0L);
        user.setInvitedRightId(0L);
        user.setPaidSponsor(false);
        user.setPaidBot(false);
        user.setIsAnswer(false);
        user.setUserName(telegramUser.getUserName());
        userRepository.saveUser(user);
    }

    public void updateNumberOfWallet(Message message, String wallet) {
        User telegramUser = message.getFrom();
        UserEntity user = userRepository.getUserByTelegramId((long) telegramUser.getId());
        user.setWallet(wallet);
        userRepository.updateUser(user);
    }

}
