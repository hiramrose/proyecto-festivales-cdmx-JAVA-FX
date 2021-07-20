package uia.dapp1.entitdad;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Dependencia {
    private Integer idDep;
    private String nombreDep;
    private String rfcDep;

    /**
     * Constructor de la Dependencia
     * @param idDep Identificador autoincremental y llave primaria de la dependencia
     * @param nombreDep Nombre de la dependencia
     * @param rfcDep RFC de la dependencia
     */
    public Dependencia(Integer idDep, String nombreDep, String rfcDep) {
        this.idDep = idDep;
        this.nombreDep = nombreDep;
        this.rfcDep = rfcDep;
    }

    public Integer getIdDep() {
        return idDep;
    }

    public void setIdDep(Integer idDep) {
        this.idDep = idDep;
    }

    public String getNombreDep() {
        return nombreDep;
    }

    public void setNombreDep(String nombreDep) {
        this.nombreDep = nombreDep;
    }

    public String getRfcDep() {
        return rfcDep;
    }

    public void setRfcDep(String rfcDep) {
        this.rfcDep = rfcDep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependencia that = (Dependencia) o;
        return Objects.equals(idDep, that.idDep) &&
                Objects.equals(nombreDep, that.nombreDep) &&
                Objects.equals(rfcDep, that.rfcDep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDep, nombreDep, rfcDep);
    }

    @Override
    public String toString() {
        return nombreDep;
    }

    /**
     * Instrucción que servirá para añadir información al combobox
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param lista Interfaz que permite la sincronización de los datos de la lista
     * @throws SQLException
     */
    public static void cargarInfo(Connection connection, ObservableList<Dependencia> lista) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "SELECT id_dependencia, nombre_dependencia, rfc_dependencia FROM dir_gral_fest_cdmx";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Dependencia(rs.getInt("id_dependencia"),rs.getString("nombre_dependencia"),rs.getString("rfc_dependencia")));
        }
        stmt.close();
        rs.close();
    }
}
