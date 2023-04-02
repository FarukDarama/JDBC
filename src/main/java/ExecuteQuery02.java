import java.sql.*;


public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql//localhost:5432/postgres", "postgres", "1234");
        Statement st = con.createStatement();

        //SORU: conpanies tablosundan en yuksek ikinci number_of_employees degeri olan company ve number_of_employees degerini cagırın
       String sql1="Select company,number_of_employees  from companies order by number_of_employees DESC OFfSET BY 1 ROW FETCH NEXT 1 ROW ONLY;";
      ResultSet resultSet1= st.executeQuery(sql1);
      while (resultSet1.next()){
      System.out.println(resultSet1.getString("company")+"---"+resultSet1.getInt("number_of_employees"));//index 1 ve 2 seklinde de kullanabiliriz
      }
     //EN BUYUK 2. VEREN DİGER BİR QUERY
        //SELECT MAX(number_of_employees) FROM companies where number_of_employees < (SELECT MAX(number_of_employees) FROM companies);



      con.close();
      resultSet1.close();
      st.close();

    }


}
