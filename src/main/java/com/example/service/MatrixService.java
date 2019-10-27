package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRepositoryImpl;
import com.example.dto.Cell;
import com.example.dto.ConfirmPaid;
import com.example.entity.UserEntity;
import com.google.common.collect.Lists;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatrixService {

    private UserRepository userRepository = new UserRepositoryImpl();
//    private MyProperties properties = MyProperties.getInstance();

    public UserEntity getUser(Long telegramId) {
        return userRepository.getUserByTelegramId(telegramId);
    }

    private Long searchPlaceUnderSponsor(Message message, List<Long> telegramIds) {
        List<Cell> cells = userRepository.getCellsByListId(telegramIds);
        List<Long> newIds = new ArrayList<>();
        for (Cell cell : cells) {

            if (cell.getPaidSponsor()) {

                newIds.add(cell.getLeftId());
                newIds.add(cell.getRightId());
                if (cell.getLeftId() == 0L || cell.getRightId() == 0L) {
                    UserEntity user = userRepository.getUserByTelegramId(cell.getTelegramId());
                    if (cell.getLeftId() == 0L) {
                        user.setInvitedLeftId((long) message.getFrom().getId());
                        userRepository.updateUser(user);
                        return user.getTelegramId();
                    }
                    if (cell.getRightId() == 0L) {
                        user.setInvitedRightId((long) message.getFrom().getId());
                        userRepository.updateUser(user);
                        return user.getTelegramId();
                    }
                }

            }
        }

        if (newIds.size() == 0) return null;

        return searchPlaceUnderSponsor(message, newIds);
    }

    private Long subscribe(Message message, Long sponsorTelegramId) {
        return searchPlaceUnderSponsor(message, Lists.newArrayList(sponsorTelegramId));
    }

    public void addUserToMatrix(Message message, Long userTelegramId) {
        UserEntity user = getUser(userTelegramId);

        UserEntity sponsor = Optional.ofNullable(getUser(user.getInvitingId())).orElse(new UserEntity());
        if (user.getInvitingId() != 0L && user.getInvitingId() != null && user.getLevel() == 0 && sponsor.getLevel() == 1) {
            user.setSponsorId(subscribe(message, user.getInvitingId()));
            userRepository.updateUser(user);
        } else {
            if (user.getLevel() == 5) {
                user.setSponsorId(Optional.ofNullable(subscribe(message, 1L)).orElse(0L));
            } else {
                user.setSponsorId(Optional.ofNullable(subscribe(message, (long) user.getLevel() + 1)).orElse(0L));
            }
            userRepository.updateUser(user);
        }
    }

    private Boolean isMatrixReadyToUpdate(Message message) {
        int count = 0;
        List<Long> newIds = new ArrayList<>();
        newIds.add((long) message.getFrom().getId());
        int i = 0;
        while (i < 3) {
            List<Cell> cells = userRepository.getCellsByListId(newIds);
            newIds = new ArrayList<>();
            if (cells.size() == 0) return false;
            for (Cell cell : cells) {
                if (!cell.getPaidBot() || !cell.getPaidSponsor()) {
                    return false;
                }
                if (cell.getPaidBot() && cell.getPaidSponsor()) {
                    count++;
                }
                newIds.add(cell.getLeftId());
                newIds.add(cell.getRightId());
            }
            i++;
        }
        return count == 7;
    }

    public Boolean transitionToNextLevel(Message message) {
        if (isMatrixReadyToUpdate(message)) {
            UserEntity user = userRepository.getUserByTelegramId((long) message.getFrom().getId());

            user.setInvitedLeftId(0L);
            user.setInvitedRightId(0L);
            user.setSponsorId(0L);
            user.setPaidSponsor(false);

            userRepository.updateUser(user);
            return true;
        }
        return false;
    }

    public ConfirmPaid isParentOfUser(Long telegramId, String textWithSubscriberTelegramId) {
        if (telegramId == 0L || telegramId == 5) telegramId = 1L;
        String[] text = textWithSubscriberTelegramId.split(" ");
        //Long subscriberTelegramId = Long.valueOf(text[text.length - 1]);
        UserEntity subscriber = userRepository.getUserByTelegramId(Long.valueOf(text[text.length - 1]));
        Long sponsorTelegramId;
        Long parentTelegramId = 0L;
        if (!subscriber.getPaidSponsor()) {
            sponsorTelegramId = getSponsorTelegramId(subscriber.getTelegramId());
            parentTelegramId = getSponsorTelegramId(sponsorTelegramId);
            return new ConfirmPaid(subscriber.getTelegramId(), telegramId.equals(parentTelegramId));
        } else {
            return new ConfirmPaid(subscriber.getTelegramId(), telegramId.equals(parentTelegramId));
        }
    }

    public Long getSponsorTelegramId(Long telegramId) {
        return userRepository.getSponsorId(telegramId);
    }

    public Long getSponsorsSponsorTelegramId(Long telegramId) {
        UserEntity sponsor = userRepository.getUserByTelegramId(getSponsorTelegramId(telegramId));
        return sponsor.getSponsorId();
    }

    public String getWalletForPaid(Long telegramId) {
        return userRepository.getWallet(getSponsorsSponsorTelegramId(telegramId));
    }

}
