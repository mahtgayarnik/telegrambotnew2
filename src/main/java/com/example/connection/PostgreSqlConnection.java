package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSqlConnection {
//    private static final String DB_DRIVER = "org.postgresql.Driver";
//    private static final String DB_URL_POSTGRES = "jdbc:postgresql://localhost:5432/postgres?useSSL=false";
//    private static final String DB_URL_MY_DB = "jdbc:postgresql://localhost:5432/dd0b7o2h1193lh?useSSL=false";
//    private static final String DB_USERNAME = "postgres";
//    private static final String DB_PASSWORD = "password";

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL_MY_DB = "jdbc:postgresql://ec2-18-203-62-227.eu-west-1.compute.amazonaws.com:5432/d980dp5ga1d5tc";
    private static final String DB_USERNAME = "dnfmtoounexufu";
    private static final String DB_PASSWORD = "faf8ba5f670ee61631818502d7efd97bceae3269d8f1dead4fe8046b0fc21d51";

//    private static final String DB_DRIVER = "org.postgresql.Driver";
//    private static final String DB_URL_MY_DB = "jdbc:postgresql://localhost:5432/telegram";
//    private static final String DB_USERNAME = "postgres";
//    private static final String DB_PASSWORD = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL_MY_DB, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static Connection getConnection() {
//        Connection connection = null;
//        boolean haveBase;
//        try {
//            Class.forName(DB_DRIVER);
//            connection = DriverManager.getConnection(DB_URL_MY_DB, DB_USERNAME, DB_PASSWORD);
//            return connection;
//        } catch (ClassNotFoundException | SQLException e) {
//            haveBase = true;
//        }
//        if (haveBase) {
//            try {
//                connection = DriverManager.getConnection(DB_URL_POSTGRES, DB_USERNAME, DB_PASSWORD);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                Statement statement = connection.createStatement();
//                String sql = "CREATE DATABASE dc5gqtot08ek3t";
//                statement.execute(sql);
//                connection = DriverManager.getConnection(DB_URL_MY_DB, DB_USERNAME, DB_PASSWORD);
//                statement = connection.createStatement();
//                sql = "create table users\n" +
//                        "(\n" +
//                        "    id               serial not null\n" +
//                        "        constraint users_pk\n" +
//                        "            primary key,\n" +
//                        "    telegram_id      bigint,\n" +
//                        "    first_name       varchar(255),\n" +
//                        "    last_name        varchar(255),\n" +
//                        "    level_user       integer,\n" +
//                        "    wallet           varchar(255),\n" +
//                        "    balance          double precision,\n" +
//                        "    referral_link    varchar(255),\n" +
//                        "    language_user    varchar(255),\n" +
//                        "    sponsor_id       bigint,\n" +
//                        "    invite_left_id   bigint,\n" +
//                        "    invite_right_id  bigint,\n" +
//                        "    paid_bot         boolean,\n" +
//                        "    paid_sponsor     boolean,\n" +
//                        "    inviting_id      bigint,\n" +
//                        "    telephone_number varchar,\n" +
//                        "    is_answer        boolean,\n" +
//                        "    answer           varchar(255),\n" +
//                        "    user_name        varchar(255)\n" +
//                        ");\n" +
//                        "\n" +
//                        "alter table users\n" +
//                        "    owner to postgres;\n" +
//                        "\n" +
//                        "create unique index users_id_uindex\n" +
//                        "    on users (id);";
//                statement.executeUpdate(sql);
//                sql = "create table language\n" +
//                        "(\n" +
//                        "    id          bigserial not null\n" +
//                        "        constraint language_pk\n" +
//                        "            primary key,\n" +
//                        "    telegram_id bigint,\n" +
//                        "    language    varchar(255)\n" +
//                        ");\n" +
//                        "\n" +
//                        "alter table language\n" +
//                        "    owner to postgres;\n" +
//                        "\n" +
//                        "create unique index language_id_uindex\n" +
//                        "    on language (id);";
//                statement.executeUpdate(sql);
//                sql = "create table vote\n" +
//                        "(\n" +
//                        "    id           bigserial not null\n" +
//                        "        constraint vote_pk\n" +
//                        "            primary key,\n" +
//                        "    question     varchar(255),\n" +
//                        "    answer_one   varchar(255),\n" +
//                        "    count_one    integer,\n" +
//                        "    answer_two   varchar(255),\n" +
//                        "    count_two    integer,\n" +
//                        "    answer_three varchar(255),\n" +
//                        "    count_three  integer,\n" +
//                        "    answer_four  varchar(255),\n" +
//                        "    count_four   integer,\n" +
//                        "    answer_five  varchar(255),\n" +
//                        "    count_five   integer,\n" +
//                        "    active       boolean,\n" +
//                        "    vote_ser     varchar(255)\n" +
//                        ");\n" +
//                        "\n" +
//                        "alter table vote\n" +
//                        "    owner to postgres;\n" +
//                        "\n" +
//                        "create unique index vote_id_uindex\n" +
//                        "    on vote (id);";
//                statement.executeUpdate(sql);
//                sql = "create table time\n" +
//                        "(\n" +
//                        "    id         bigserial not null\n" +
//                        "        constraint time_pk\n" +
//                        "            primary key,\n" +
//                        "    name_time  varchar(255),\n" +
//                        "    start_time varchar(255),\n" +
//                        "    end_time   varchar(255)\n" +
//                        ");\n" +
//                        "\n" +
//                        "alter table time\n" +
//                        "    owner to postgres;\n" +
//                        "\n" +
//                        "create unique index time_id_uindex\n" +
//                        "    on time (id);";
//                statement.executeUpdate(sql);
//                sql = "INSERT INTO dc5gqtot08ek3t.public.users VALUES(DEFAULT, 1, 'admin1', null, 1, '1111111111111111', 0, null, null, 1, 0, 0, true, true, 0, null, false, null, null),\n" +
//                        "                                           (DEFAULT, 2, 'admin1', null, 2, '2222222222222222', 0, null, null, 2, 0, 0, true, true, 0, null, false, null, null),\n" +
//                        "                                           (DEFAULT, 3, 'admin1', null, 3, '3333333333333333', 0, null, null, 3, 0, 0, true, true, 0, null, false, null, null),\n" +
//                        "                                           (DEFAULT, 4, 'admin1', null, 4, '4444444444444444', 0, null, null, 4, 0, 0, true, true, 0, null, false, null, null),\n" +
//                        "                                           (DEFAULT, 5, 'admin1', null, 5, '5555555555555555', 0, null, null, 5, 0, 0, true, true, 0, null, false, null, null);";
//                statement.executeUpdate(sql);
//                sql="INSERT INTO dc5gqtot08ek3t.public.time VALUES(DEFAULT, 'timer', '00:00', '23:59');";
//                statement.executeUpdate(sql);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//
//    private static Connection getConnection() throws URISyntaxException, SQLException {
//        String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        return DriverManager.getConnection(dbUrl);
//    }
//
//    public static Connection getConnection() throws URISyntaxException, SQLException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//
//        Connection connection = DriverManager.getConnection(dbUrl, username, password);
//
//        Statement statement = connection.createStatement();
//        String sql = "CREATE SCHEMA telegram_bot";
//        statement.execute(sql);
//        statement = connection.createStatement();
//        sql = "create table users\n" +
//                "(\n" +
//                "    id               serial not null\n" +
//                "        constraint users_pk\n" +
//                "            primary key,\n" +
//                "    telegram_id      bigint,\n" +
//                "    first_name       varchar(255),\n" +
//                "    last_name        varchar(255),\n" +
//                "    level_user       integer,\n" +
//                "    wallet           varchar(255),\n" +
//                "    balance          double precision,\n" +
//                "    referral_link    varchar(255),\n" +
//                "    language_user    varchar(255),\n" +
//                "    sponsor_id       bigint,\n" +
//                "    invite_left_id   bigint,\n" +
//                "    invite_right_id  bigint,\n" +
//                "    paid_bot         boolean,\n" +
//                "    paid_sponsor     boolean,\n" +
//                "    inviting_id      bigint,\n" +
//                "    telephone_number varchar,\n" +
//                "    is_answer        boolean,\n" +
//                "    answer           varchar(255),\n" +
//                "    user_name        varchar(255)\n" +
//                ");\n" +
//                "\n" +
//                "alter table users\n" +
//                "    owner to postgres;\n" +
//                "\n" +
//                "create unique index users_id_uindex\n" +
//                "    on users (id);";
//        statement.executeUpdate(sql);
//        sql = "create table language\n" +
//                "(\n" +
//                "    id          bigserial not null\n" +
//                "        constraint language_pk\n" +
//                "            primary key,\n" +
//                "    telegram_id bigint,\n" +
//                "    language    varchar(255)\n" +
//                ");\n" +
//                "\n" +
//                "alter table language\n" +
//                "    owner to postgres;\n" +
//                "\n" +
//                "create unique index language_id_uindex\n" +
//                "    on language (id);";
//        statement.executeUpdate(sql);
//        sql = "create table vote\n" +
//                "(\n" +
//                "    id           bigserial not null\n" +
//                "        constraint vote_pk\n" +
//                "            primary key,\n" +
//                "    question     varchar(255),\n" +
//                "    answer_one   varchar(255),\n" +
//                "    count_one    integer,\n" +
//                "    answer_two   varchar(255),\n" +
//                "    count_two    integer,\n" +
//                "    answer_three varchar(255),\n" +
//                "    count_three  integer,\n" +
//                "    answer_four  varchar(255),\n" +
//                "    count_four   integer,\n" +
//                "    answer_five  varchar(255),\n" +
//                "    count_five   integer,\n" +
//                "    active       boolean,\n" +
//                "    vote_ser     varchar(255)\n" +
//                ");\n" +
//                "\n" +
//                "alter table vote\n" +
//                "    owner to postgres;\n" +
//                "\n" +
//                "create unique index vote_id_uindex\n" +
//                "    on vote (id);";
//        statement.executeUpdate(sql);
//        sql = "create table time\n" +
//                "(\n" +
//                "    id         bigserial not null\n" +
//                "        constraint time_pk\n" +
//                "            primary key,\n" +
//                "    name_time  varchar(255),\n" +
//                "    start_time varchar(255),\n" +
//                "    end_time   varchar(255)\n" +
//                ");\n" +
//                "\n" +
//                "alter table time\n" +
//                "    owner to postgres;\n" +
//                "\n" +
//                "create unique index time_id_uindex\n" +
//                "    on time (id);";
//        statement.executeUpdate(sql);
//        return connection;
//    }

}
