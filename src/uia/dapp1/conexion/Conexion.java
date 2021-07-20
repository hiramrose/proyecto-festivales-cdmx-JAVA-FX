package uia.dapp1.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection conn;
    public Connection conecta() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_cdmx?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "a2166202");
        if (conn != null) {
            //System.out.println("Conectado con éxito.");
            return conn;
        } else {
            return null;
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void desconecta(Connection conn) throws SQLException {
        conn.close();
    }
}
