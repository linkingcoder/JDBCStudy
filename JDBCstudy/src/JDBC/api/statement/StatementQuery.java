package JDBC.api.statement;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
public class StatementQuery {
    public static  void main(String[] args) throws SQLException {
        //1.注册驱动
        DriverManager.registerDriver(new Driver());
        //2.获取链接
        Connection conection= DriverManager.
                getConnection("jdbc:mysql://127.0.0.1:3306/atguigu","root","123456");
        //3.创建statement
        Statement statement = conection.createStatement();
        //4.发送sql语句
        String sql = "select * from t_user;";
        ResultSet resultSet= statement.executeQuery(sql);
        //5.进行结果集解析
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"--"+account+"--"+password+"--"+nickname);
        }
        //6.关闭资源
         resultSet.close();
         statement.close();
         conection.close();
    }
}

