import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountriesTest {

    @Test
    public void countryTest() throws SQLException {
        //user connects to database
        JdbcUtils.connectToDataBase("localhost", "postgres", "postgres", "1234");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the region ids from "countries" table
        String sql1 = "Select region_id from countries";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<Integer> ids = new ArrayList<>();
        while (resultSet1.next()) {
            ids.add(resultSet1.getInt(1));
        }
        System.out.println("ids = " + ids);
        List<Integer> idsGreaterThan1 = new ArrayList<>();
        for (int w : ids) {
            if (w > 1) {
                idsGreaterThan1.add(w);
            }
        }

        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);

        Assert.assertEquals(17, idsGreaterThan1.size());
         //User closes the connection
        JdbcUtils.closeConnectionAndStatement();

    }
}
