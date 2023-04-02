import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adım driver a kaydol
        Class.forName("org.postgresql.Driver");
        // 2. adım Database baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql//localhost:5432/postgres", "postgres", "1234");
        //3. adım Statement olustur
        Statement st = con.createStatement();
        //4. adım query calıtır
        boolean sql1 = st.execute("Create table workers(worker_id Varchar(20),worker_name Varchar(20),worker_salary INT)");
        //data cagırmadıgımız ıcın sadece table olusturdugumuz ıcın bize false verecek
        String sql2 = "Alter table workers add worker_address VARCHAR(80);";
        st.execute(sql2);
        String sql3 = "Drop table workers;";
        st.execute(sql3);

        //5. adım baglantı ve  statemnet ı kapat
        con.close();
        st.close();


    }
}
