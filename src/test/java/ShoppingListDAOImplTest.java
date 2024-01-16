import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class ShoppingListDAOImplTest {
    @Test
    void should_connect_to_MySQL_DB() {
        try (Connection con = getConnection(DBConfig.MYSQL_DB_URL,
                DBConfig.MYSQL_DB_USER_NAME, DBConfig.MYSQL_DB_USER_PASS);){

            if (con == null) {
                Assertions.fail("There is a problem to connect to your DB");
            }

        } catch (SQLException e) {
            Assertions.fail("There is a problem to connect to your DB");
            throw new RuntimeException(e);
        }

    }
}
