package com.example.dao;

import com.example.connection.PostgreSqlConnection;
import com.example.dto.Cell;
import com.example.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

//    private Connection connection;
//
//    {
//            connection = PostgreSqlConnection.getConnection();
//    }

    private String dbName = "d980dp5ga1d5tc";

    @Override
    public void saveUser(UserEntity user) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "INSERT INTO " + dbName + ".public.users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, Optional.ofNullable(user.getTelegramId()).orElse(0L));
            preparedStatement.setString(2, Optional.ofNullable(user.getFirstName()).orElse(""));
            preparedStatement.setString(3, Optional.ofNullable(user.getLastName()).orElse(""));
            preparedStatement.setInt(4, Optional.ofNullable(user.getLevel()).orElse(0));
            preparedStatement.setString(5, Optional.ofNullable(user.getWallet()).orElse(""));
            preparedStatement.setDouble(6, Optional.ofNullable(user.getBalance()).orElse(0.0));
            preparedStatement.setString(7, Optional.ofNullable(user.getReferralLink()).orElse(""));
            preparedStatement.setString(8, Optional.ofNullable(user.getLanguage()).orElse(""));
            preparedStatement.setLong(9, Optional.ofNullable(user.getSponsorId()).orElse(0L));
            preparedStatement.setLong(10, Optional.ofNullable(user.getInvitedLeftId()).orElse(0L));
            preparedStatement.setLong(11, Optional.ofNullable(user.getInvitedRightId()).orElse(0L));
            preparedStatement.setBoolean(12, Optional.ofNullable(user.getPaidBot()).orElse(false));
            preparedStatement.setBoolean(13, Optional.ofNullable(user.getPaidSponsor()).orElse(false));
            preparedStatement.setLong(14, Optional.ofNullable(user.getInvitingId()).orElse(0L));
            preparedStatement.setString(15, Optional.ofNullable(user.getTelephoneNumber()).orElse(""));
            preparedStatement.setBoolean(16, Optional.ofNullable(user.getIsAnswer()).orElse(false));
            preparedStatement.setString(17, Optional.ofNullable(user.getAnswer()).orElse(""));
            preparedStatement.setString(18, Optional.ofNullable(user.getUserName()).orElse(""));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserEntity user) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.users SET telegram_id=?, first_name=?, last_name=?, level_user=?, wallet=?," +
                    " balance=?, referral_link =?, language_user=?, sponsor_id=?, invite_left_id=?, invite_right_id=?, paid_bot=?," +
                    " paid_sponsor=?, inviting_id=?, telephone_number=?, is_answer=?, answer=?, user_name=? WHERE id=" + user.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, Optional.ofNullable(user.getTelegramId()).orElse(0L));
            preparedStatement.setString(2, Optional.ofNullable(user.getFirstName()).orElse(""));
            preparedStatement.setString(3, Optional.ofNullable(user.getLastName()).orElse(""));
            preparedStatement.setInt(4, Optional.ofNullable(user.getLevel()).orElse(0));
            preparedStatement.setString(5, Optional.ofNullable(user.getWallet()).orElse(""));
            preparedStatement.setDouble(6, Optional.ofNullable(user.getBalance()).orElse(0.0));
            preparedStatement.setString(7, Optional.ofNullable(user.getReferralLink()).orElse(""));
            preparedStatement.setString(8, Optional.ofNullable(user.getLanguage()).orElse(""));
            preparedStatement.setLong(9, Optional.ofNullable(user.getSponsorId()).orElse(0L));
            preparedStatement.setLong(10, Optional.ofNullable(user.getInvitedLeftId()).orElse(0L));
            preparedStatement.setLong(11, Optional.ofNullable(user.getInvitedRightId()).orElse(0L));
            preparedStatement.setBoolean(12, Optional.ofNullable(user.getPaidBot()).orElse(false));
            preparedStatement.setBoolean(13, Optional.ofNullable(user.getPaidSponsor()).orElse(false));
            preparedStatement.setLong(14, Optional.ofNullable(user.getInvitingId()).orElse(0L));
            preparedStatement.setString(15, Optional.ofNullable(user.getTelephoneNumber()).orElse(""));
            preparedStatement.setBoolean(16, Optional.ofNullable(user.getIsAnswer()).orElse(false));
            preparedStatement.setString(17, Optional.ofNullable(user.getAnswer()).orElse(""));
            preparedStatement.setString(18, Optional.ofNullable(user.getUserName()).orElse(""));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePaidBotStatus(String cardPan, Integer lvl) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.users SET paid_bot = true WHERE wallet LIKE '" + cardPan + "' AND paid_bot = false AND level_user = " + lvl;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdminCards(String cardNumber) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.users SET wallet = " + cardNumber + " WHERE telegram_id IN (1, 2, 3, 4, 5)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDefaultAnswerForAllUsers() {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.users SET is_answer = false, answer=''";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateUserByReferralId(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.users SET invite_left_id = CASE WHEN invite_left_id = " + telegramId + " THEN 0 ELSE invite_left_id END," +
                    "                                 invite_right_id = CASE WHEN invite_right_id = " + telegramId + " THEN 0 ELSE invite_right_id END" +
                    "                            WHERE level_user = (SELECT level_user FROM telegram.public.users WHERE telegram_id = " + telegramId + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Long getTelegramId(String cardPan, Integer lvl) {
        Connection connection = PostgreSqlConnection.getConnection();
        Long telegramId = null;
        try {
            String sql = "SELECT telegram_id FROM  " + dbName + ".public.users WHERE wallet LIKE '" + cardPan + "' AND paid_bot = true AND level_user = " + lvl;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                telegramId = resultSet.getLong("telegram_id");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telegramId;
    }

    @Override
    public List<Long> removeAllUserWhoDoNotPaid() {
        Connection connection = PostgreSqlConnection.getConnection();
        List<Long> iDs = new ArrayList<>();
        Long telegramId;
        try {
            String sql = "UPDATE " + dbName + ".public.users SET sponsor_id = 0 WHERE sponsor_id != 0 AND paid_sponsor = false";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                telegramId = resultSet.getLong("telegram_id");
                iDs.add(telegramId);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iDs;
    }

    @Override
    public UserEntity getUserByTelegramId(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        UserEntity user = null;
        try {
            String sql = "SELECT id, telegram_id, first_name, last_name, level_user," +
                    "wallet, balance, referral_link, language_user, sponsor_id, invite_left_id, invite_right_id, paid_bot, " +
                    "paid_sponsor, inviting_id, telephone_number, is_answer, answer, user_name" +
                    " FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                telegramId = resultSet.getLong("telegram_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer levelUser = resultSet.getInt("level_user");
                String wallet = resultSet.getString("wallet");
                Double balance = resultSet.getDouble("balance");
                String referralLink = resultSet.getString("referral_link");
                String language = resultSet.getString("language_user");
                Long sponsorId = resultSet.getLong("sponsor_id");
                Long inviteLeftId = resultSet.getLong("invite_left_id");
                Long inviteRightId = resultSet.getLong("invite_right_id");
                Boolean paidBot = resultSet.getBoolean("paid_bot");
                Boolean paiSponsor = resultSet.getBoolean("paid_sponsor");
                Long invitingId = resultSet.getLong("inviting_id");
                String telNumber = resultSet.getString("telephone_number");
                Boolean isAnswer = resultSet.getBoolean("is_answer");
                String answer = resultSet.getString("answer");
                String userName = resultSet.getString("user_name");
                user = new UserEntity(id, telegramId, firstName, lastName, telNumber, levelUser, wallet, balance, referralLink, language,
                        sponsorId, inviteLeftId, inviteRightId, paidBot, paiSponsor, invitingId, isAnswer, answer, userName);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserEntity getUserById(Long id) {
        Connection connection = PostgreSqlConnection.getConnection();
        UserEntity user = null;
        try {
            String sql = "SELECT id, telegram_id, first_name, last_name, level_user," +
                    "wallet, balance, referral_link, language_user, sponsor_id, invite_left_id, invite_right_id, paid_bot," +
                    " paid_sponsor, inviting_id, telephone_number, is_answer, answer, user_name" +
                    " FROM " + dbName + ".public.users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("id");
                Long telegramId = resultSet.getLong("telegram_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer levelUser = resultSet.getInt("level_user");
                String wallet = resultSet.getString("wallet");
                Double balance = resultSet.getDouble("balance");
                String referralLink = resultSet.getString("referral_link");
                String language = resultSet.getString("language_user");
                Long sponsorId = resultSet.getLong("sponsor_id");
                Long inviteLeftId = resultSet.getLong("invite_left_id");
                Long inviteRightId = resultSet.getLong("invite_right_id");
                Boolean paidBot = resultSet.getBoolean("paid_bot");
                Boolean paidSponsor = resultSet.getBoolean("paid_sponsor");
                Long invitingId = resultSet.getLong("inviting_id");
                String telNumber = resultSet.getString("telephone_number");
                Boolean isAnswer = resultSet.getBoolean("is_answer");
                String answer = resultSet.getString("answer");
                String userName = resultSet.getString("user_name");
                user = new UserEntity(id, telegramId, firstName, lastName, telNumber, levelUser, wallet, balance, referralLink, language,
                        sponsorId, inviteLeftId, inviteRightId, paidBot, paidSponsor, invitingId, isAnswer, answer, userName);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean isAnswer(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        Boolean answer = null;
        try {
            String sql = "SELECT is_answer FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                answer = resultSet.getBoolean("is_answer");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public Integer getCountUser(Integer lvl) {
        Connection connection = PostgreSqlConnection.getConnection();
        Integer count = null;
        try {
            String sql = "SELECT count(*) FROM " + dbName + ".public.users WHERE level_user=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, lvl);;
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer getCountUserOutOfMatrix() {
        Connection connection = PostgreSqlConnection.getConnection();
        Integer count = null;
        try {
            String sql = "SELECT count(*) FROM " + dbName + ".public.users WHERE paid_sponsor = false AND paid_bot = false";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer getCountUserWithoutSponsorPay() {
        Connection connection = PostgreSqlConnection.getConnection();
        Integer count = null;
        try {
            String sql = "SELECT count(*) FROM " + dbName + ".public.users WHERE paid_sponsor = false AND paid_bot = true AND sponsor_id != 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Long getSponsorId(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        Long sponsorId = null;
        try {
            String sql = "SELECT sponsor_id FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sponsorId = resultSet.getLong("sponsor_id");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sponsorId;
    }

    @Override
    public Boolean getBotPaid(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        Boolean paid = null;
        try {
            String sql = "SELECT paid_bot FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                paid = resultSet.getBoolean("paid_bot");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paid;
    }

    @Override
    public Boolean getSponsorPaid(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        Boolean paid = null;
        try {
            String sql = "SELECT paid_sponsor FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                paid = resultSet.getBoolean("paid_sponsor");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paid;
    }

    @Override
    public String getWallet(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        String wallet = null;
        try {
            String sql = "SELECT wallet FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                wallet = resultSet.getString("wallet");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }

    @Override
    public Integer getLevel(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        Integer lvl = null;
        try {
            String sql = "SELECT level_user FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lvl = resultSet.getInt("level_user");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    @Override
    public String getTelephoneNumber(Long telegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        String tel = null;
        try {
            String sql = "SELECT telephone_number FROM " + dbName + ".public.users WHERE telegram_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tel = resultSet.getString("telephone_number");
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tel;
    }

    @Override
    public List<Long> getAllId() {
        Connection connection = PostgreSqlConnection.getConnection();
        List<Long> ids = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT telegram_id FROM " + dbName + ".public.users WHERE telegram_id NOT IN (1, 2, 3, 4, 5)";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Long telegramId = resultSet.getLong("telegram_id");
                ids.add(telegramId);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    @Override
    public List<Cell> getAllCells() {
        Connection connection = PostgreSqlConnection.getConnection();
        List<Cell> cells = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, telegram_id, invite_left_id, invite_right_id, paid_bot, paid_sponsor FROM " + dbName + ".public.users ORDER BY id ASC";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long leftId = Optional.of(resultSet.getLong("invite_left_id")).orElse(0L);
                Long rightId = Optional.of(resultSet.getLong("invite_right_id")).orElse(0L);
                Long telegramId = resultSet.getLong("telegram_id");
                Boolean paidBot = resultSet.getBoolean("paid_bot");
                Boolean paiSponsor = resultSet.getBoolean("paid_sponsor");
                Cell cell = new Cell(id, leftId, rightId, telegramId, paidBot, paiSponsor);
                cells.add(cell);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cells;
    }

    public List<Cell> getCellsByListId(List<Long> ids) {
        Connection connection = PostgreSqlConnection.getConnection();
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < ids.size(); i++) {
            if (i == (ids.size() - 1)) {
                sb.append(ids.get(i)).append(")");
                break;
            }
            sb.append(ids.get(i)).append(", ");
        }
        List<Cell> cells = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, telegram_id, invite_left_id, invite_right_id, paid_bot, paid_sponsor FROM " + dbName + ".public.users WHERE telegram_id IN " + sb.toString() + " ORDER BY id ASC";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long leftId = Optional.of(resultSet.getLong("invite_left_id")).orElse(0L);
                Long rightId = Optional.of(resultSet.getLong("invite_right_id")).orElse(0L);
                Long telegramId = resultSet.getLong("telegram_id");
                Boolean paidBot = resultSet.getBoolean("paid_bot");
                Boolean paiSponsor = resultSet.getBoolean("paid_sponsor");
                Cell cell = new Cell(id, leftId, rightId, telegramId, paidBot, paiSponsor);
                cells.add(cell);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cells;
    }

}
