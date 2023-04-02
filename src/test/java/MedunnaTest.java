import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaTest {


    @Test
    public void medunnaTest() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();

        String sql1 = "Select created_by from room";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<String> created_byList = new ArrayList<>();
        while (resultSet1.next()) {
            created_byList.add(resultSet1.getString(1));
        }
        System.out.println("created_byList = " + created_byList);

        //Assertion
        Assert.assertTrue(created_byList.contains("john_doe"));


    }
}
