package com.example.dao;

import com.example.connection.PostgreSqlConnection;
import com.example.entity.LanguageEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LanguageRepositoryImpl implements LanguageRepository {

//    private Connection connection;
//
//    {
//        connection = PostgreSqlConnection.getConnection();
//    }

    private String dbName = "d980dp5ga1d5tc";

    @Override
    public void saveLanguage(LanguageEntity language) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "INSERT INTO " + dbName + ".public.language VALUES (DEFAULT, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, Optional.ofNullable(language.getUserTelegramId()).orElse(0L));
            preparedStatement.setString(2, Optional.ofNullable(language.getLanguage()).orElse(""));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLanguage(LanguageEntity language) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.language SET telegram_id=?, language=? WHERE telegram_id=" + language.getUserTelegramId();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, Optional.ofNullable(language.getUserTelegramId()).orElse(0L));
            preparedStatement.setString(2, Optional.ofNullable(language.getLanguage()).orElse(""));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LanguageEntity getLanguageEntity(Long userTelegramId) {
        Connection connection = PostgreSqlConnection.getConnection();
        LanguageEntity language = null;
        try {
            String sql = "SELECT id, telegram_id, language.language FROM " + dbName + ".public.language WHERE telegram_id =" + userTelegramId;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long telegramId = resultSet.getLong("telegram_id");
                String lang = resultSet.getString("language");
                language = new LanguageEntity(id, telegramId, lang);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return language;
    }
}
