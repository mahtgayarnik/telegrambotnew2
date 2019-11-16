package com.example.dao;

import com.example.dto.Cell;
import com.example.entity.UserEntity;

import java.util.List;

public interface UserRepository {

    void saveUser(UserEntity user);

    void updateUser(UserEntity user);

    void updatePaidBotStatus(String cardPan, Integer lvl);

    void updateDefaultAnswerForAllUsers();

    void updateUserByReferralId(Long telegramId);

    UserEntity getUserByTelegramId(Long telegramId);

    UserEntity getUserById(Long id);

    List<Long> removeAllUserWhoDoNotPaid();

    List<Cell> getAllCells();

    List<Cell> getCellsByListId(List<Long> ids);

    List<Long> getAllId();

    Long getSponsorId(Long telegramId);

    Long getTelegramId(String cardPan, Integer lvl);

    String getWallet(Long telegramId);

    Integer getLevel(Long telegramId);

    Boolean getBotPaid(Long telegramId);

    Boolean getSponsorPaid(Long telegramId);

    Boolean isAnswer(Long telegramId);

    String getTelephoneNumber(Long telegramId);

    Integer getCountUser(Integer lvl);

    Integer getCountUserOutOfMatrix();

    Integer getCountUserWithoutSponsorPay();

}
