package uia.dapp1.entitdad;

import java.util.Objects;

public class TipoFestival {
    private Integer idTF;
    private String tipoFestival;

    /**
     * Constructor del Tipo de Festival
     * @param idTF Identificador y llave primario del Tipo de Festival
     * @param tipoFestival Categoría del tipo de festival
     */
    public TipoFestival(Integer idTF, String tipoFestival) {
        this.idTF = idTF;
        this.tipoFestival = tipoFestival;
    }

    public Integer getIdTF() {
        return idTF;
    }

    public void setIdTF(Integer idTF) {
        this.idTF = idTF;
    }

    public String getTipoFestival() {
        return tipoFestival;
    }

    public void setTipoFestival(String tipoFestival) {
        this.tipoFestival = tipoFestival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoFestival that = (TipoFestival) o;
        return Objects.equals(idTF, that.idTF) &&
                Objects.equals(tipoFestival, that.tipoFestival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTF, tipoFestival);
    }

    @Override
    public String toString() {
        return "TipoFestival{" +
                "idTF=" + idTF +
                ", tipoFestival='" + tipoFestival + '\'' +
                '}';
    }
}