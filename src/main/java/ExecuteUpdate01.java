import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql//localhost:5432/postgres", "postgres", "1234");
        Statement st = con.createStatement();
      //SORU: number_of_employees degeri ortalama calısan sayısından az olan number_of_employees degerlerini 16000 olarak update edin

        String sql1 ="UPDATE companies SET number_of_employees =16000 WHERE number_of_employees < SELECT AVG(number_of_employees) FROM companies;";
      int updateEdilenSatirSayisi =  st.executeUpdate(sql1);
    ResultSet resultSet1 = st.executeQuery(sql1);
    while (resultSet1.next()){
        System.out.println(resultSet1.getInt(1) + "---" + resultSet1.getString(2) + "---" + resultSet1.getInt(3));


    }
        //SELECT AVG(number_of_employees) FROM companies;
    con.close();
    st.close();
    resultSet1.close();

    }
}
