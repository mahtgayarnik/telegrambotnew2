package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRepositoryImpl;
import com.example.dao.VoteRepository;
import com.example.dao.VoteRepositoryImpl;
import com.example.entity.UserEntity;

public class ValidationService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private VoteRepository voteRepository = new VoteRepositoryImpl();

    public Boolean payOnlyFirstLvl(Integer code) {
        return code == 0;
    }

    public Boolean userIsExist(Long telegramId) {
        UserEntity user = userRepository.getUserByTelegramId(telegramId);
        return (user != null);
    }

    public Boolean walletIsExist(Long telegramId) {
        String wallet = userRepository.getWallet(telegramId);
        return (wallet != null && !wallet.equals(""));
    }

    public Integer getUserLvl(Long telegramId) {
        return userRepository.getLevel(telegramId);
    }

    public String getTelephoneNumber(Long telegramId) {
        return userRepository.getTelephoneNumber(telegramId);
    }

    public Boolean getBotPaid(Long telegramId) {
        return userRepository.getBotPaid(telegramId);
    }

    public Boolean getSponsorPaid(Long telegramId) {
        return userRepository.getSponsorPaid(telegramId);
    }

    public Boolean isAnswer(Long telegramId) {
        return userRepository.isAnswer(telegramId);
    }

    public Boolean isVoteExist() {
        return voteRepository.getActiveVote() != null;
    }
}
