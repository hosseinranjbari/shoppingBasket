import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.*;
import static java.sql.DriverManager.getConnection;

public class ShoppingListDAOImplTest {

    public static final String ERROR_MESSAGE = "There is a problem to connect to your DB";
    public static final String INSER_COMMAND = "INSERT INTO item (name, quantity) VALUES (?, ?)";
    public static final String UPDATE_COMMAND = "UPDATE item set quantity = 15 where id = 1";

    @Test
    void should_connect_to_MySQL_DB() {
        try (Connection con = getConnection(DBConfig.MYSQL_DB_URL,
                DBConfig.MYSQL_DB_USER_NAME, DBConfig.MYSQL_DB_USER_PASS);){
            if (con == null) {
                Assertions.fail(ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            Assertions.fail(ERROR_MESSAGE);
            throw new RuntimeException(e);
        }

    }

    @Test
    void should_insert_to_MySQL() {
        try (final Connection con = getConnection(DBConfig.MYSQL_DB_URL, DBConfig.MYSQL_DB_USER_NAME, DBConfig.MYSQL_DB_USER_PASS);
             final PreparedStatement insertCommand = con.prepareStatement(INSER_COMMAND);
        ){
            insertCommand.setString(1, "orange");
            insertCommand.setInt(2, 10);
            insertCommand.executeUpdate();

        } catch (SQLException e) {
            Assertions.fail(ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    @Test
    void should_update_a_row() {
        try (final Connection con = getConnection(DBConfig.MYSQL_DB_URL, DBConfig.MYSQL_DB_USER_NAME, DBConfig.MYSQL_DB_USER_PASS);
             final PreparedStatement updateCommand = con.prepareStatement(UPDATE_COMMAND)
        ) {
            updateCommand.executeUpdate();


        } catch (SQLException e) {
            Assertions.fail(ERROR_MESSAGE);
            throw new RuntimeException(e);

        }
    }

    @Test
    void should_delete_a_row() {
        try (final Connection con = getConnection(DBConfig.MYSQL_DB_URL, DBConfig.MYSQL_DB_USER_NAME, DBConfig.MYSQL_DB_USER_PASS);
             final PreparedStatement deleteCommand = con.prepareStatement("DELETE FROM item where id = 4")
        ) {
            deleteCommand.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
