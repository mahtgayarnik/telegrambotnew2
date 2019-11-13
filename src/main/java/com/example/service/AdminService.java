package com.example.service;

import com.example.dao.*;
import com.example.entity.UserEntity;
import com.example.entity.VoteEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminService {

//    private MyProperties properties = MyProperties.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private VoteRepository voteRepository = new VoteRepositoryImpl();
    private PaymentService paymentService = new PaymentService();
    private KeyboardService keyboardService = new KeyboardService();
    private SendMessageService sendMessageService = new SendMessageService();
    private TimeRepository timeRepository = new TimeRepositoryImpl();
//    private String adminId = properties.getProperties().getProperty("adminId");
    private String adminId = "01010101";

    public void setTimer(String time) {
        timeRepository.updateTime(time);
    }

    public String confirmUserBotPay(Long userTelegramId) {
        UserEntity user = userRepository.getUserByTelegramId(userTelegramId);
        if (user != null) {
            user.setPaidBot(true);
            userRepository.updateUser(user);
            return "Обязательный платеж для пользователя ID " + userTelegramId + "подтвержден";
        }
        return "Пользователь ID " + userTelegramId + " не найден";
    }

    public void confirmUserBotPayAuto(Long userTelegramId) {
        UserEntity user = userRepository.getUserByTelegramId(userTelegramId);
        if (user != null) {
            user.setPaidBot(true);
            userRepository.updateUser(user);
        }
    }

    public SendMessage createVoting(Message message, String text) {
        userRepository.updateDefaultAnswerForAllUsers();
        voteRepository.disableAllVotes();
        List<String> answers = new ArrayList<>();
        String[] textArr = text.split("\\*");
        VoteEntity vote = new VoteEntity();
        vote.setActive(true);
        vote.setVote(text);
        vote.setQuestion(textArr[0]);
        for (int i = 1; i < textArr.length; i++) {
            answers.add(textArr[i]);
        }
        saveAnswer(vote, answers);
        voteRepository.saveVote(vote);
        return sendMessageService.sendMsgWithInLine(message, textArr[0], keyboardService.setButtonsForVoting(answers));
    }

    public SendMessage createVotingForUser(Message message, String text) {
        List<String> answers = new ArrayList<>();
        String[] textArr = text.split("\\*");
        for (int i = 1; i < textArr.length; i++) {
            answers.add(textArr[i]);
        }
        VoteEntity vote = voteRepository.getVote(textArr[0]);
        List<Integer> answerCounts = new ArrayList<>();
        Optional.ofNullable(vote.getCountOne()).map(answerCounts::add);
        Optional.ofNullable(vote.getCountTwo()).map(answerCounts::add);
        Optional.ofNullable(vote.getCountThree()).map(answerCounts::add);
        Optional.ofNullable(vote.getCountFour()).map(answerCounts::add);
        Optional.ofNullable(vote.getCountFive()).map(answerCounts::add);
        StringBuilder textWithStat = new StringBuilder(textArr[0] + "\n");
        answers.forEach(answer -> textWithStat.append(answer).append(" ответили ").append(answerCounts.get(answers.indexOf(answer))).append("\n"));

        return sendMessageService.sendMsgWithInLine(message, textWithStat.toString(), keyboardService.setButtonsForVoting(answers));
    }

    public String getTextActiveVote() {
        VoteEntity vote = voteRepository.getActiveVote();
        return vote.getVote();
    }

    public void giveAnswer(Long userTelegramId, String answer) {
        VoteEntity vote = voteRepository.getActiveVote();
        UserEntity user = userRepository.getUserByTelegramId(userTelegramId);
        user.setIsAnswer(true);
        user.setAnswer(answer);
        setCountAnswer(vote, answer);
        userRepository.updateUser(user);
        voteRepository.updateVote(vote);
    }

    public List<Long> getUserToConfirmForAdmin(Long id) {
        List<Long> ids = new ArrayList<>();
        UserEntity user = userRepository.getUserByTelegramId(id);
        if (!userRepository.getUserByTelegramId(user.getInvitedLeftId()).getPaidSponsor()) ids.add(user.getInvitedLeftId());
        if (!userRepository.getUserByTelegramId(user.getInvitedRightId()).getPaidSponsor()) ids.add(user.getInvitedRightId());
        UserEntity left = userRepository.getUserByTelegramId(user.getInvitedLeftId());
        UserEntity right = userRepository.getUserByTelegramId(user.getInvitedRightId());
        ids.add(Optional.ofNullable(left).map(UserEntity::getInvitedLeftId).orElse(0L));
        ids.add(Optional.ofNullable(left).map(UserEntity::getInvitedRightId).orElse(0L));
        ids.add(Optional.ofNullable(right).map(UserEntity::getInvitedLeftId).orElse(0L));
        ids.add(Optional.ofNullable(right).map(UserEntity::getInvitedRightId).orElse(0L));
        return ids;
    }

    public void sendToAll(String text) {
        userRepository.getAllId().forEach(id -> paymentService.sendConfirmPaid(id, text));
    }

    public void sendToOne(String text) {
        String[] textArr = text.split("\\*");
        paymentService.sendConfirmPaid(Long.valueOf(textArr[0]), textArr[1]);
    }

    private void setCountAnswer(VoteEntity vote, String answer) {
        if (answer.equals("0")) {
            vote.setCountOne(vote.getCountOne() + 1);
        }
        if (answer.equals("1")) {
            vote.setCountTwo(vote.getCountTwo() + 1);
        }
        if (answer.equals("2")) {
            vote.setCountThree(vote.getCountThree() + 1);
        }
        if (answer.equals("3")) {
            vote.setCountFour(vote.getCountFour() + 1);
        }
        if (answer.equals("4")) {
            vote.setCountFive(vote.getCountFive() + 1);
        }
    }

    private void saveAnswer(VoteEntity vote, List<String> answers) {
        if (answers.size() == 1) {
            vote.setAnswerOne(answers.get(0));
        }
        if (answers.size() == 2) {
            vote.setAnswerOne(answers.get(0));
            vote.setAnswerTwo(answers.get(1));
        }
        if (answers.size() == 3) {
            vote.setAnswerOne(answers.get(0));
            vote.setAnswerTwo(answers.get(1));
            vote.setAnswerThree(answers.get(2));
        }
        if (answers.size() == 4) {
            vote.setAnswerOne(answers.get(0));
            vote.setAnswerTwo(answers.get(1));
            vote.setAnswerThree(answers.get(2));
            vote.setAnswerFour(answers.get(3));
        }
        if (answers.size() == 5) {
            vote.setAnswerOne(answers.get(0));
            vote.setAnswerTwo(answers.get(1));
            vote.setAnswerThree(answers.get(2));
            vote.setAnswerFour(answers.get(3));
            vote.setAnswerFive(answers.get(4));
        }

    }

}
