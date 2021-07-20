package uia.dapp1.entitdad;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Sector {
    private Integer idsector;
    private String nomSector;

    /**
     * Constructor del Sector
     * @param idsector ID del sector económico y llave primaria
     * @param nomSector Nombre del sector económico
     */
    public Sector(Integer idsector, String nomSector) {
        this.idsector = idsector;
        this.nomSector = nomSector;
    }

    public Integer getIdsector() {
        return idsector;
    }

    public void setIdsector(Integer idsector) {
        this.idsector = idsector;
    }

    public String getNomSector() {
        return nomSector;
    }

    public void setNomSector(String nomSector) {
        this.nomSector = nomSector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sector sector = (Sector) o;
        return Objects.equals(idsector, sector.idsector) &&
                Objects.equals(nomSector, sector.nomSector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsector, nomSector);
    }

    @Override
    public String toString() {
        return nomSector;
    }

    /**
     * Instrucción que servirá para añadir información al combobox
     * @param connection Permite establecer la conexión con la base de datos para las instrucciones
     * @param lista Interfaz que permite la sincronización de los datos de la lista
     * @throws SQLException
     */
    public static void cargarInfo(Connection connection, ObservableList<Sector> lista) throws SQLException {
            Statement stmt = connection.createStatement();
            String sql = "SELECT id_sector_economico, tipo_sector_economico FROM sector_economico";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Sector(rs.getInt("id_sector_economico"), rs.getString("tipo_sector_economico")));
            }
            stmt.close();
            rs.close();
    }
}
