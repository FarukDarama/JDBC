import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    public static void main(String[] args) {





    }

    private static  Connection connection;
    private  static  Statement statement;
    //1.Adım driver a kaydol
    // 2. adım Database baglan
    public static Connection connectToDataBase(String hostName,String dbName,String username,String password){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
           connection = DriverManager.getConnection("jdbc:postgresql//"+hostName+":5432/"+dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection!=null){
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Fail");
        }
        return connection;
    }

    //3. adım Statement olustur
    public static Statement createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return  statement;
    }

    //4. Adım: Query çalıştır
    public  static  Boolean execute(String sql){
        Boolean isExecute;
        try {
          isExecute =  statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return isExecute;
    }
    //5. adım baglantı ve  statemnet ı kapat
    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed()&& statement.isClosed()){
                System.out.println("Connection and Statement closed");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Table olusturan method
    public static void createTable(String tableName,String... columnNmae_dataType  ){

        StringBuilder columnNmae_dataValue = new StringBuilder();
        for (String w: columnNmae_dataType){
        columnNmae_dataValue.append(w).append(",");
        }
        columnNmae_dataValue.deleteCharAt(columnNmae_dataValue.length()-1);


        try {
            statement.execute("Create table "+tableName+"("+columnNmae_dataValue+")");
            System.out.println("Table "+tableName+" succesfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }













}
