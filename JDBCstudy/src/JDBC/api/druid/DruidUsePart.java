package JDBC.api.druid;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUsePart {
    public static void main(String[] args) {

    }
    public void testHard() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/atguigu");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        Connection connection = dataSource.getConnection();
        connection.close();
    }
    public void testSoft() throws Exception {
        Properties properties = new Properties();
        InputStream ips = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
