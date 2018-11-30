import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
    static {//加载JDBC驱动程序
        try {
            String diverName = "com.mysql.jdbc.Driver";
                /*com.mysql.jdbc.Driver 是 mysql‐connector‐java 5中的，
                com.mysql.cj.jdbc.Driver 是 mysql‐connector‐java 6以上的*/
            Class.forName(diverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {//创建数据库连接
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/redrock" + "? serverTimezone=GMT%2B8"+"?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "newpass");
            //北京时间东八区，时区要设置好，不然会出现时差
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs, Statement statement, Connection con) {//关闭数据库连接
        try {
            if (rs != null)
                rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (statement != null)
                statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (con != null)
                con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
