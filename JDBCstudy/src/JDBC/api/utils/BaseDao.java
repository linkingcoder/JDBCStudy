package JDBC.api.utils;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {

    public int executeUpdate(String sql,Object... params) throws ClassNotFoundException, SQLException {
        JDBCUtilsv2.getConnection();
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root&password=123456");
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i,params[i-1]);
        }
        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtilsv2.freeConnection();
        }
       return rows;
    }
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JDBCUtilsv2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params!=null&&params.length!=0) {
            for (int i = 1; i <=params.length ; i++) {
                preparedStatement.setObject(i,params[i-1]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            T t=clazz.newInstance();
            for (int i = 1; i <=columnCount; i++) {
                Object value = resultSet.getObject(i);
                String propertyName = metaData.getColumnLabel(i);
                Field field = clazz.getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(t,value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtilsv2.freeConnection();
        }
        return list;
    }

}

