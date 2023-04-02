import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        //1.Adım driver a kaydol
        // 2. adım Database baglan
        Connection connection = JdbcUtils.connectToDataBase("localhost", "postgres", "postgres", "1234");

        //3. adım Statement olustur
        Statement statement = JdbcUtils.createStatement();
        //4.Adım : Query calıstır
        JdbcUtils.execute("Create Table students (name VARCHAR(20),id INT,address VARCHAR(80))");

        JdbcUtils.createTable("School","classes VARCHAR(20)","teacher_Name VARCHAR(20)","id INT");




    }
}
