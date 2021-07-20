package uia.dapp1.CRUD;

import javafx.collections.ObservableList;
import uia.dapp1.entitdad.TipoFestival;

import java.sql.*;

public class CrudTF {
    /**
     * SELECT - Tipo de Festival
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param listaTF Interfaz que permite la sincronización de los datos de la lista
     * @throws SQLException
     */
    public static void llenarTabla(Connection connection, ObservableList<TipoFestival> listaTF) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql ="SELECT id_tipo_festival, tipo_festival FROM tipo_festival";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            listaTF.add(new TipoFestival(rs.getInt("id_tipo_festival"), rs.getString("tipo_festival")));
        }
        stmt.close();
        rs.close();
    }

    /**
     * INSERT - Tipo de Festival
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param tipoFestival Clase de tipo TipoFestival
     * @return
     */
    public int guardar(Connection connection, TipoFestival tipoFestival)  {
        try {
            String sql = "INSERT INTO tipo_festival (tipo_festival) VALUES (?)";
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tipoFestival.getTipoFestival());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * UPDATE - Tipo de Festival
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param tipoFestival Clase de tipo TipoFestival
     * @return
     */
    public int actualizar(Connection connection, TipoFestival tipoFestival)  {
        try {
            String sql = "UPDATE tipo_festival SET tipo_festival=? WHERE id_tipo_festival=?";
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,tipoFestival.getTipoFestival());
            pstmt.setInt(2,tipoFestival.getIdTF());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * DELETE - Tipo de Festival
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param tipoFestival Clase de tipo TipoFestival
     * @return
     */
    public int eliminar(Connection connection, TipoFestival tipoFestival) {
        try {
            PreparedStatement pstmt;
            String sql = "DELETE FROM tipo_festival WHERE id_tipo_festival=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,tipoFestival.getIdTF());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }
}
