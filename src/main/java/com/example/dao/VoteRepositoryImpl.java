package com.example.dao;

import com.example.connection.PostgreSqlConnection;
import com.example.entity.VoteEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoteRepositoryImpl implements VoteRepository {

//    private Connection connection;
//
//    {
//        connection = PostgreSqlConnection.getConnection();
//    }

    private String dbName = "dfn9v3pq2trvcn";

    @Override
    public void saveVote(VoteEntity vote) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "INSERT INTO " + dbName + ".public.vote VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Optional.ofNullable(vote.getQuestion()).orElse(""));
            preparedStatement.setString(2, Optional.ofNullable(vote.getAnswerOne()).orElse(""));
            preparedStatement.setInt(3, Optional.ofNullable(vote.getCountOne()).orElse(0));
            preparedStatement.setString(4, Optional.ofNullable(vote.getAnswerTwo()).orElse(""));
            preparedStatement.setInt(5, Optional.ofNullable(vote.getCountTwo()).orElse(0));
            preparedStatement.setString(6, Optional.ofNullable(vote.getAnswerThree()).orElse(""));
            preparedStatement.setInt(7, Optional.ofNullable(vote.getCountThree()).orElse(0));
            preparedStatement.setString(8, Optional.ofNullable(vote.getAnswerFour()).orElse(""));
            preparedStatement.setInt(9, Optional.ofNullable(vote.getCountFour()).orElse(0));
            preparedStatement.setString(10, Optional.ofNullable(vote.getAnswerFive()).orElse(""));
            preparedStatement.setInt(11, Optional.ofNullable(vote.getCountFive()).orElse(0));
            preparedStatement.setBoolean(12, Optional.ofNullable(vote.getActive()).orElse(false));
            preparedStatement.setString(13, Optional.ofNullable(vote.getVote()).orElse(""));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVote(VoteEntity vote) {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.vote SET question=?, answer_one=?, count_one=?, answer_two=?, count_two=?, answer_three=?, count_three=?," +
                    "answer_four=?, count_four=?, answer_five=?, count_five=?, active=?, vote_ser=? WHERE id=" + vote.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Optional.ofNullable(vote.getQuestion()).orElse(""));
            preparedStatement.setString(2, Optional.ofNullable(vote.getAnswerOne()).orElse(""));
            preparedStatement.setInt(3, Optional.ofNullable(vote.getCountOne()).orElse(0));
            preparedStatement.setString(4, Optional.ofNullable(vote.getAnswerTwo()).orElse(""));
            preparedStatement.setInt(5, Optional.ofNullable(vote.getCountTwo()).orElse(0));
            preparedStatement.setString(6, Optional.ofNullable(vote.getAnswerThree()).orElse(""));
            preparedStatement.setInt(7, Optional.ofNullable(vote.getCountThree()).orElse(0));
            preparedStatement.setString(8, Optional.ofNullable(vote.getAnswerFour()).orElse(""));
            preparedStatement.setInt(9, Optional.ofNullable(vote.getCountFour()).orElse(0));
            preparedStatement.setString(10, Optional.ofNullable(vote.getAnswerFive()).orElse(""));
            preparedStatement.setInt(11, Optional.ofNullable(vote.getCountFive()).orElse(0));
            preparedStatement.setBoolean(12, Optional.ofNullable(vote.getActive()).orElse(false));
            preparedStatement.setString(13, Optional.ofNullable(vote.getVote()).orElse(""));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<VoteEntity> getAllVote() {
        Connection connection = PostgreSqlConnection.getConnection();
        List<VoteEntity> voteEntities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, question, answer_one, count_one, answer_two, count_two, answer_three, count_three," +
                    " answer_four, count_four, answer_five, count_five, active, vote_ser FROM " + dbName + ".public.vote ORDER BY id ASC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String question = resultSet.getString("question");
                String answerOne = resultSet.getString("answer_one");
                String answerTwo = resultSet.getString("answer_two");
                String answerThree = resultSet.getString("answer_three");
                String answerFour = resultSet.getString("answer_four");
                String answerFive = resultSet.getString("answer_five");
                Integer countOne = resultSet.getInt("count_one");
                Integer countTwo = resultSet.getInt("count_two");
                Integer countThree = resultSet.getInt("count_three");
                Integer countFour = resultSet.getInt("count_four");
                Integer countFive = resultSet.getInt("count_five");
                Boolean active = resultSet.getBoolean("active");
                String voteSer = resultSet.getString("vote_ser");
                VoteEntity vote = new VoteEntity(id, question, answerOne, countOne, answerTwo, countTwo, answerThree, countThree,
                        answerFour, countFour, answerFive, countFive, active, voteSer);
                voteEntities.add(vote);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voteEntities;
    }

    @Override
    public VoteEntity getVote(String question) {
        Connection connection = PostgreSqlConnection.getConnection();
        VoteEntity vote = null;
        try {
            String sql = "SELECT id, question, answer_one, count_one, answer_two, count_two, answer_three, count_three, " +
                    "answer_four, count_four, answer_five, count_five, active, vote_ser FROM " + dbName + ".public.vote WHERE question = '" + question + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                question = resultSet.getString("question");
                String answerOne = resultSet.getString("answer_one");
                String answerTwo = resultSet.getString("answer_two");
                String answerThree = resultSet.getString("answer_three");
                String answerFour = resultSet.getString("answer_four");
                String answerFive = resultSet.getString("answer_five");
                Integer countOne = resultSet.getInt("count_one");
                Integer countTwo = resultSet.getInt("count_two");
                Integer countThree = resultSet.getInt("count_three");
                Integer countFour = resultSet.getInt("count_four");
                Integer countFive = resultSet.getInt("count_five");
                Boolean active = resultSet.getBoolean("active");
                String voteSer = resultSet.getString("vote_ser");
                vote = new VoteEntity(id, question, answerOne, countOne, answerTwo, countTwo, answerThree, countThree,
                        answerFour, countFour, answerFive, countFive, active, voteSer);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vote;
    }

    @Override
    public VoteEntity getActiveVote() {
        Connection connection = PostgreSqlConnection.getConnection();
        VoteEntity vote = null;
        try {
            String sql = "SELECT id, question, answer_one, count_one, answer_two, count_two, answer_three, count_three, " +
                    "answer_four, count_four, answer_five, count_five, active, vote_ser FROM " + dbName + ".public.vote WHERE active = true";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String question = resultSet.getString("question");
                String answerOne = resultSet.getString("answer_one");
                String answerTwo = resultSet.getString("answer_two");
                String answerThree = resultSet.getString("answer_three");
                String answerFour = resultSet.getString("answer_four");
                String answerFive = resultSet.getString("answer_five");
                Integer countOne = resultSet.getInt("count_one");
                Integer countTwo = resultSet.getInt("count_two");
                Integer countThree = resultSet.getInt("count_three");
                Integer countFour = resultSet.getInt("count_four");
                Integer countFive = resultSet.getInt("count_five");
                Boolean active = resultSet.getBoolean("active");
                String voteSer = resultSet.getString("vote_ser");
                vote = new VoteEntity(id, question, answerOne, countOne, answerTwo, countTwo, answerThree, countThree,
                        answerFour, countFour, answerFive, countFive, active, voteSer);
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vote;
    }

    @Override
    public void disableAllVotes() {
        Connection connection = PostgreSqlConnection.getConnection();
        try {
            String sql = "UPDATE " + dbName + ".public.vote SET active = false";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
