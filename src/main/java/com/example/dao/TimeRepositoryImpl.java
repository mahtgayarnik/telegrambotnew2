package com.example.dao;

import com.example.connection.PostgreSqlConnection;
import com.example.entity.TimeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TimeRepositoryImpl implements TimeRepository {

//    private Connection connection;
//
//    {
//        connection = PostgreSqlConnection.getConnection();
//    }

    private String dbName = "dc38qhgpk1vokm";

    @Override
    public TimeEntity getTime(String nameTime) {
        Connection connection = PostgreSqlConnection.getConnection();
        TimeEntity time = null;
        try {
            String sql = "SELECT id, name_time, start_time, end_time FROM " + dbName + ".public.time WHERE name_time = '" + nameTime + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name_time");
                String start = resultSet.getString("start_time");
                String end = resultSet.getString("end_time");
                time = new TimeEntity(id, name, start, end);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Override
    public void updateTime(String nameTime) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.time SET start_time=?, end_time=? WHERE name_time='timer'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Optional.ofNullable(nameTime.split(" ")[1]).orElse("00:00"));
            preparedStatement.setString(2, Optional.ofNullable(nameTime.split(" ")[2]).orElse("23:59"));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
