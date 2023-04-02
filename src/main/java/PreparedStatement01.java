import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql//localhost:5432/postgres", "postgres", "1234");
        Statement st = con.createStatement();
      /*
      PreparedStatement interface, birden cok kez calıstırılabilen onceden derlenmiş bir SQL kodunu temsil eder
      Parametrelendirilmiş SQL sorguları ile çalışır.Bu sorguya 0 veya daha fazla parametre ile kullanabilirsiniz
       */

        //1.Örnek : Prepared statement kullanarak adı IBM olan number_of_employees degerini 9999 olarak guncelleyin

        //1.Adım : PreparedStatement  querysini olustur
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company =? ";
        //2. Adım PreparedStatement objectini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);
        //3.Adım setInt(),setString() ... methodlarını kullanarak soru işaretleri yerlerine deger gir.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");
        //4.Adım  Queryi çalışturt
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);//1

        String sql2 = "select * from companies";
        ResultSet rs1 = st.executeQuery(sql2);
        System.out.println("rs1 = " + rs1);
        // UPDATE companies SET number_of_employees =9999 WHERE company ='IBM';

        while (rs1.next()) {
            System.out.println(rs1.getInt(1) + "---" + rs1.getString(2) + "---" + rs1.getInt(3));
        }
        //SORU : Prepared statement kullanarak company adı GOOGLE olan number_of_employees degerini 5555 olarak guncelleyin
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");
        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi2);//1

      ResultSet rs2 =  st.executeQuery(sql2);
      while (rs2.next()){
          System.out.println(rs2.getInt(1)+"---"+rs2.getString(2)+"---"+rs2.getInt(3));
      }
      con.close();
      rs1.close();
      st.close();
      rs2.close();
      pst1.close();






    }


}
