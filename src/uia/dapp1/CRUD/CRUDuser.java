package uia.dapp1.CRUD;

import javafx.collections.ObservableList;
import uia.dapp1.entitdad.Dependencia;
import uia.dapp1.entitdad.Usuario;

import java.sql.*;

public class CRUDuser {
    /**
     * SELECT - Usuario
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param listaU Interfaz que permite la sincronización de los datos de la lista
     * @throws SQLException
     */
    public static void llenarTablaU(Connection connection, ObservableList<Usuario> listaU) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql ="SELECT T2.id_usuario, " +
                "T2.nombre, " +
                "T2.nombre_dos, " +
                "T2.paterno, " +
                "T2.materno, " +
                "T2.telefono, " +
                "T2.correo, " +
                "T2.edad, " +
                "T2.sexo, " +
                "T1.id_dependencia, " +
                "T1.nombre_dependencia, " +
                "T1.rfc_dependencia " +
                "FROM dir_gral_fest_cdmx AS T1 " +
                "INNER JOIN usuario AS T2 " +
                "ON T1.id_dependencia=T2.id_dependencia_fk;";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            listaU.add(new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("nombre_dos"),
                    rs.getString("paterno"),
                    rs.getString("materno"),
                    rs.getLong("telefono"),
                    rs.getString("correo"),
                    rs.getDate("edad"),
                    rs.getString("sexo"),
                    new Dependencia(rs.getInt("id_dependencia"), rs.getString("nombre_dependencia"),rs.getString("rfc_dependencia"))
            ));
        }
        stmt.close();
        rs.close();
    }

    /**
     * INSERT - Usuario
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param usuario Clase de tipo Usuario
     * @return
     */
    public int guardar(Connection connection, Usuario usuario)  {
        try {
            String sql = "INSERT INTO usuario (nombre, nombre_dos, paterno, materno, telefono, correo, edad,sexo, id_dependencia_fk) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,usuario.getNombreU());
            pstmt.setString(2,usuario.getNombre2U());
            pstmt.setString(3,usuario.getApellidoP());
            pstmt.setString(4,usuario.getApellidoM());
            pstmt.setLong(5,usuario.getTelefonoU());
            pstmt.setString(6,usuario.getEmail());
            pstmt.setDate(7,usuario.getFechaNac());
            pstmt.setString(8,usuario.getGenero());
            pstmt.setInt(9,usuario.getDep().getIdDep());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * UPDATE - Usuario
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param usuario Clase de tipo Usuario
     * @return
     */
    public int actualizar(Connection connection, Usuario usuario) {
        try {
            String sql = "UPDATE usuario " +
                    "SET nombre=?, " +
                    "nombre_dos=?, " +
                    "paterno=?, " +
                    "materno=?, " +
                    "telefono=?, " +
                    "correo=?, " +
                    "edad=?, " +
                    "sexo=?, " +
                    "id_dependencia_fk=? " +
                    "WHERE id_usuario=?";
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombreU());
            pstmt.setString(2, usuario.getNombre2U());
            pstmt.setString(3, usuario.getApellidoP());
            pstmt.setString(4, usuario.getApellidoM());
            pstmt.setLong(5, usuario.getTelefonoU());
            pstmt.setString(6,usuario.getEmail());
            pstmt.setDate(7,usuario.getFechaNac());
            pstmt.setString(8,usuario.getGenero());
            pstmt.setInt(9,usuario.getDep().getIdDep());
            pstmt.setInt(10,usuario.getIdUsuario());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * DELETE - Usuario
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param usuario Clase de tipo Usuario
     * @return
     */
    public int eliminar(Connection connection, Usuario usuario) {
        try {
            PreparedStatement pstmt;
            String sql = "DELETE FROM usuario WHERE id_usuario=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,usuario.getIdUsuario());
            return pstmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return 0;
        }
    }
}
