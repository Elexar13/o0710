package exceptions;

import java.sql.SQLException;

public class CantFindCountryException extends RuntimeException {
    public CantFindCountryException(SQLException e) {
        super(e);
    }
}
