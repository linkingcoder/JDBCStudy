package JDBC.api.preparestatement;

import org.junit.Test;

import java.sql.*;

public class PSOtherPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true&user=root&password=123456");
        String sql = "insert into t_user(account,password,nickname) values (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
       long start = System.currentTimeMillis();
        for (int i = 0; i < 10000 ; i++) {
            statement.setObject(1,"ddd"+i);
            statement.setObject(2,"ddd"+i);
            statement.setObject(3,"zs"+i);
            statement.addBatch();
        }
        statement.executeUpdate();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        statement.close();
        connection.close();

    }
    @Test
    public void returnPrimaryKey() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        String sql = "insert into t_user(account,password,nickname) values (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1,"test1");
        statement.setObject(2,"123456");
        statement.setObject(3,"ls");
        int i = statement.executeUpdate();
        if (i>0){
            System.out.println("插入成功");
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            System.out.println("id"+id);
        } else {
            System.out.println("插入失败");
        }
        statement.close();
        connection.close();

    }
}
