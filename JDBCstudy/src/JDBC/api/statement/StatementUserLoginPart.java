package JDBC.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.util.Properties;
import java.util.Scanner;
import java.sql.*;
public class StatementUserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.获取用户输入信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号");
        String account = scanner.nextLine();
        System.out.println("请输入密码");
        String password=scanner.nextLine();
        //2.注册驱动
        //DriverManager.registerDriver(new Driver());
        //new Driver();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","123456");
        Properties info=new Properties();
        info.put("user","root");
        info.put("password","123456");
        DriverManager.getConnection("jdbc:mysql:///atguigu",info);
        String url="jdbc:mysql:///atguigu?user=root&password=123456";
        DriverManager.getConnection(url);
        //3.创建statement
        Statement statement = connection.createStatement();
        String sql="select * from t_user where account ='"+account+"' and password = '"+password+"';" ;
        ResultSet resultSet= statement.executeQuery(sql);
        //int i= statement.executeUpdate(sql);
       /* while (resultSet.next()){
            int id= resultSet.getInt(1);
            String account1 = resultSet.getString("account");
            String password1 = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(nickname);
        }*/if(resultSet.next()){
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
        resultSet.close();
        statement.close();
        connection.close();


    }
}
