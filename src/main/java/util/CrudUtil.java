package util;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String SQL, Object... val) throws SQLException {

        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);

        if(SQL.startsWith("SELECT") || SQL.startsWith("select")){
            return (T)statement.executeQuery();
        }else {
            for (int i = 0; i < val.length; i++) {
                statement.setObject(i+1,val[i]);
            }
        }
        return (T) (Boolean)  (statement.executeUpdate()>0);
    }
}
