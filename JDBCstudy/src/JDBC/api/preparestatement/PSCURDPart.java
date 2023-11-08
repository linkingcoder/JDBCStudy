package JDBC.api.preparestatement;
import org.junit.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCURDPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        String  sql = "insert into t_user(account,password,nickname) values(?,?,?);";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setObject(1,"test");
        preparedStatement.setObject(2,"test");
        preparedStatement.setObject(3,"lisi");
        int rows = preparedStatement.executeUpdate();
        if (rows>0){
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        String  sql = "update t_user set nickname=? where id=?;";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setObject(1,"zs");
        preparedStatement.setObject(2,3);
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        String  sql = "delete from t_user where id=?;";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setObject(1,3);
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        String sql="select id,account,password,nickname from t_user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();


        List<Map> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()){
            Map map = new HashMap<>();
           // map.put("id",resultSet.getInt("id"));
            for (int i = 1; i <=columnCount; i++) {
                Object value = resultSet.getObject(i);
                String columnLabel = metaData.getColumnLabel(i);
                map.put( columnLabel,value);
            }
            list.add(map);
        }
        System.out.println("list =" + list);
    }

    public static void main(String[] args) {
    }
}
