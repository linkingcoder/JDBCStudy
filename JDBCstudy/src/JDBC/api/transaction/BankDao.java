package JDBC.api.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BankDao {
    public void add(String account,int money,Connection connection) throws ClassNotFoundException, SQLException {
        String sql = "update t_bank set money = money + ? where account = ?; ";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("加钱成功");

    }
    public void sub(String account,int money,Connection connection) throws ClassNotFoundException, SQLException {
        String sql = "update t_bank set money = money - ? where account = ?; ";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("减钱成功");

    }
}
