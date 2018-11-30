import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    Connection con;
    Statement statement;
    ResultSet rs;

    public Connection getCon() {
        return con;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getRs() {
        return rs;
    }

    public Main(Connection con) {
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        String sql = "create table if not exists test(id int,name varchar(100))";
        statement.executeUpdate(sql);
    }

    //增
    public void insert() throws SQLException {
        String sql1 = "insert into test values(1,'tom')";
        String sql2 = "insert into test values(2,'张三')";
        String sql3 = "insert into test values(3,'999')";
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        int[] results = statement.executeBatch();
    }

    //查
    public void select() throws SQLException {
        String sql = "select id,name from test";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            System.out.println("id:"+id + "\t" +"name:"+ name);
        }
    }

    //删
    public void delete() throws SQLException {
        String sql1 = "delete from test where id=1";
        String sql2 = "delete from test where id=2";
        String sql3 = "delete from test where id=3";
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        int[] results = statement.executeBatch();
    }

    //改
    public void update() throws SQLException {
        String sql1 = "update test set name='lily' where id=1";
        String sql2 = "update test set name='李四' where id=2";
        String sql3 = "update test set name='666' where id=3";
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        int[] results = statement.executeBatch();
    }

    public static void main(String[] args) {
        Connection con = JDBCUtil.getConnection();
        Main main = new Main(con);
        try {
            main.createTable();
            main.insert();
            System.out.println("修改前的数据:");
            main.select();
            main.update();
            System.out.println("更新后的数据:");
            main.select();
            System.out.println("以上数据最终都将被删除!");
            main.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(main.getRs(), main.getStatement(), main.getCon());
        }
    }
}