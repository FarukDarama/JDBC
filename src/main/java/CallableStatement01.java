import java.sql.*;

public class CallableStatement01 {
    /*
    Java da methodlar return type sahibi olsasda olmasada method olarak adlandırılır.
    SQL de ise data return ediyorsa "function" denir.Return yapömıyorsa "procedure" olarak adlandırılır
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql//localhost:5432/postgres", "postgres", "1234");
        Statement st = con.createStatement();
     //CallableStatement ile function cagırmayı parametrelendirecegiz.
        //1.ADIM: Function kodunu yaz.
        String sql1="CREATE  OR REPLACE FUNCTION toplamaF(x NUMERİC,y NUMERİC\n" +
                "          RETURS NUMERIC\n" +
                "          LANGUAGE plpsql\n" +
                "          AS\n" +
                "          $$\n" +
                "          BEGIN\n" +
                "\n" +
                "          RETURN x+y;\n" +
                "\n" +
                "          END\n" +
                "          $$\n" +
                "\n" +
                "          SELECT * FROM toplamF(4,6) AS toplam ;";
        // 2. ADIM: Funtion'ı calistir.
        st.execute(sql1);
        //3. ADIM: Funtion'ı cagir
       CallableStatement cst1 = con.prepareCall("{?= call toplamaF(?,?)}");
        //4. ADIM: Return icin  regirterOutParameter() methodu,parametreler için iste set().... methodlarını uygula
        cst1.registerOutParameter(1,Types.NUMERIC);
         cst1.setInt(2,6);
         cst1.setInt(3,4);
         //5. ADIM: execute() methodu ile CallableStatement'ı çalıştır.
        cst1.execute();
        //6. ADIM: Sonucu çagırmak icin return data type tipine göre
        System.out.println(cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan  bir funtion yazın
        //1.ADIM: Function kodunu yaz.
        String sql2="CREATE  OR REPLACE FUNCTION koniniHacmiF(r NUMERİC,h NUMERİC\n" +
                "          RETURS NUMERIC\n" +
                "          LANGUAGE plpsql\n" +
                "          AS\n" +
                "          $$\n" +
                "          BEGIN\n" +
                "\n" +
                "          RETURN 3.14*r*rh/3;\n" +
                "\n" +
                "          END\n" +
                "          $$\n" +
                "\n" +
                "          SELECT * FROM koniniHacmiF(4,6) AS toplam ;";
        // 2. ADIM: Funtion'ı calistir.
        st.execute(sql2);
        //3. ADIM: Funtion'ı cagir
        CallableStatement cst2 = con.prepareCall("{?= call konininHacmiF(?,?)}");
        //4. ADIM: Return icin  regirterOutParameter() methodu,parametreler için iste set().... methodlarını uygula
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);
        //5. ADIM: execute() methodu ile CallableStatement'ı çalıştır.
        cst2.execute();
        //6. ADIM: Sonucu çagırmak icin return data type tipine göre
        System.out.printf("%.2f",cst2.getBigDecimal(1));// printf deyip "%.2f" virgulden sonra 2 basamak yazdır seklinde format blirleyebiliriz
        /*CREATE  OR REPLACE FUNCTION toplamaF(x NUMERİC,y NUMERİC
          RETURS NUMERIC
          LANGUAGE plpsql
          AS
          $$
          BEGIN

          RETURN x+y;

          END
          $$

          SELECT * FROM toplamF(4,6) AS toplam ;

         */



    }





}
