package uia.dapp1.CRUD;

import javafx.collections.ObservableList;
import uia.dapp1.entitdad.Dependencia;
import uia.dapp1.entitdad.Institucionalidad;
import uia.dapp1.entitdad.Sector;

import java.sql.*;

public class CRUDIns {
    /**
     * SELECT - Institucionalidad
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param listaT Interfaz que permite la sincronización de los datos de la lista
     * @throws SQLException
     */
    public static void hacerTabla(Connection connection, ObservableList<Institucionalidad> listaT) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql ="SELECT T2.id_institucion, " +
                "T2.nombre_organizacion, " +
                "T2.rfc_institucion, " +
                "T2.presupuesto, " +
                "T2.permisos, " +
                "T2.contratos, " +
                "T1.id_sector_economico, " +
                "T1.tipo_sector_economico, " +
                "T3.id_dependencia, " +
                "T3.nombre_dependencia, " +
                "T3.rfc_dependencia " +
                "FROM sector_economico AS T1 " +
                "INNER JOIN institucionalidad AS T2 " +
                "ON T1.id_sector_economico=T2.id_sector_economico_fk " +
                "INNER JOIN dir_gral_fest_cdmx AS T3 " +
                "ON T3.id_dependencia=T2.id_dependencia_fk";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            listaT.add(new Institucionalidad(
                    rs.getInt("id_institucion"),
                    rs.getString("nombre_organizacion"),
                    rs.getString("rfc_institucion"),
                    rs.getDouble("presupuesto"),
                    rs.getString("permisos"),
                    rs.getString("contratos"),
                    new Sector(rs.getInt("id_sector_economico"), rs.getString("tipo_sector_economico")),
                    new Dependencia(rs.getInt("id_dependencia"), rs.getString("nombre_dependencia"),rs.getString("rfc_dependencia"))
            ));
        }
        stmt.close();
        rs.close();
    }

    /**
     * INSERT - Institucionalidad
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param institucionalidad Clase de tipo Institucionalidad
     * @return
     */
    public int guardar(Connection connection, Institucionalidad institucionalidad)  {
        try {
            String sql = "INSERT INTO institucionalidad (nombre_organizacion, rfc_institucion, presupuesto, permisos, contratos, id_sector_economico_fk, id_dependencia_fk) " +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,institucionalidad.getNombreInst());
            pstmt.setString(2,institucionalidad.getRfcInst());
            pstmt.setDouble(3,institucionalidad.getPresupuesto());
            pstmt.setString(4,institucionalidad.getPermisos());
            pstmt.setString(5,institucionalidad.getContratos());
            pstmt.setInt(6,institucionalidad.getSector().getIdsector());
            pstmt.setInt(7,institucionalidad.getDependencia().getIdDep());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * UPDATE - Institucionalidad
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param institucionalidad Clase de tipo Institucionalidad
     * @return
     */
    public int actualizar(Connection connection, Institucionalidad institucionalidad)  {
        try {
            String sql = "UPDATE institucionalidad " +
                    "SET nombre_organizacion=?, " +
                    "rfc_institucion=?, " +
                    "presupuesto=?, " +
                    "permisos=?, " +
                    "contratos=?, " +
                    "id_sector_economico_fk=?, " +
                    "id_dependencia_fk=? " +
                    "WHERE id_institucion=?";
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,institucionalidad.getNombreInst());
            pstmt.setString(2,institucionalidad.getRfcInst());
            pstmt.setDouble(3,institucionalidad.getPresupuesto());
            pstmt.setString(4,institucionalidad.getPermisos());
            pstmt.setString(5,institucionalidad.getContratos());
            pstmt.setInt(6,institucionalidad.getSector().getIdsector());
            pstmt.setInt(7,institucionalidad.getDependencia().getIdDep());
            pstmt.setInt(8,institucionalidad.getIdInstitucion());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * DELETE - Institucionalidad
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param institucionalidad Clase de tipo Institucionalidad
     * @return
     */
    public int eliminar(Connection connection, Institucionalidad institucionalidad) {
        try {
            PreparedStatement pstmt;
            String sql = "DELETE FROM institucionalidad WHERE id_institucion=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,institucionalidad.getIdInstitucion());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }
}
