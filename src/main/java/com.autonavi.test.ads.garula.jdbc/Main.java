package com.autonavi.test.ads.garula.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by chichen.cc on 2015/7/21.
 */
public class Main {

    public static void main(String asd[]){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.101.76.98:9999/cctest?useUnicode=true&characterEncoding=UTF-8";

            Properties connectionProps = new Properties();
            connectionProps.put("user", "gq5FDS2IgSWqXzTu");
            connectionProps.put("password", "xNXmuBr4dvn3BNLLzWZEAerpHqREto");

            connection = DriverManager.getConnection(url, connectionProps);
            statement = connection.createStatement();

//            String query = "select count(*) from information_schema.tables";
            String query =  "select tid from tb_test limit 10";
            rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getObject(1));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
