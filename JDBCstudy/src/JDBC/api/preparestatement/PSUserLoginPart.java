package JDBC.api.preparestatement;
import java.sql.*;
import java.util.Scanner;
public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号");
        String account = scanner.nextLine();
        System.out.println("请输入密码");
        String password=scanner.nextLine();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        String sql="select * from t_user where account = ? and password = ?;";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setObject(1,account);
        preparedStatement.setObject(2,password);
        ResultSet resultSet= preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("登录成功");
        } else {
            System.out.println("登陆失败");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
