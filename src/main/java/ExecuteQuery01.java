import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.Adım driver a kaydol
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql//localhost:5432/postgres", "postgres", "1234");
        Statement st = con.createStatement();
       //SORU: region id'si 1 olan country_name degerlerini cagırın
        String sql1= "select country_name from countries where region_id=1;";
       boolean r1=  st.execute(sql1);
        System.out.println("r1 = " + r1);

        //RECORDLARI görmek için executeQuery() methodu kullanılmalı
        ResultSet resultSet1 = st.executeQuery(sql1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString(1));

        }

        //SORU: region id'si 2'den buyuk  olan country_id ve country_name degerlerini cagırın
        String sql2 ="select country_name,country_id from countries where region_id>2;";
        ResultSet resultSet2= st.executeQuery(sql2);
        System.out.println("-----------------------");
        while (resultSet2.next()){
            resultSet2.getString("country_name"+"--"+resultSet2.getString("country_id"));
        }

        //SORU: "number_of_employees" degeri en dusuk olan satırın tum degerlerini

          String sql3 ="select * from companies where number_of_employees=(select min(number_of_employees) from companies);";
     ResultSet resultSet3 = st.executeQuery(sql3);
       while (resultSet3.next()){
           System.out.println(resultSet3.getInt(1)+"--"+resultSet3.getString(2)+"---"+resultSet3.getInt(3));
           st.close();
           con.close();

       }





    }
}
