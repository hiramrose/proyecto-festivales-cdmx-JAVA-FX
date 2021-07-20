package uia.dapp1.entitdad;

import java.util.Objects;

public class Institucionalidad {
    private Integer idInstitucion;
    private String nombreInst;
    private String rfcInst;
    private Double presupuesto;
    private String permisos;
    private String contratos;
    private Sector sector;
    private Dependencia dependencia;

    /**
     * Constructor de la Institucionalidad
     * @param idInstitucion Identificador autoincremental y llave primaria de la institucionalidad
     * @param nombreInst Nombre de la institucionalidad
     * @param rfcInst RFC de la institucionalidad
     * @param presupuesto Presupuesto de la institucionalidad
     * @param permisos Permisos de la institucionalidad
     * @param contratos Contratos de la institucionalidad
     * @param sector ID Llave foránea de Sector económico de la institucionalidad
     * @param dependencia ID Llave foránea de la institucionalidad
     */
    public Institucionalidad(Integer idInstitucion, String nombreInst, String rfcInst, Double presupuesto, String permisos, String contratos, Sector sector, Dependencia dependencia) {
        this.idInstitucion = idInstitucion;
        this.nombreInst = nombreInst;
        this.rfcInst = rfcInst;
        this.presupuesto = presupuesto;
        this.permisos = permisos;
        this.contratos = contratos;
        this.sector = sector;
        this.dependencia = dependencia;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInst() {
        return nombreInst;
    }

    public void setNombreInst(String nombreInst) {
        this.nombreInst = nombreInst;
    }

    public String getRfcInst() {
        return rfcInst;
    }

    public void setRfcInst(String rfcInst) {
        this.rfcInst = rfcInst;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getContratos() {
        return contratos;
    }

    public void setContratos(String contratos) {
        this.contratos = contratos;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institucionalidad that = (Institucionalidad) o;
        return Objects.equals(idInstitucion, that.idInstitucion) &&
                Objects.equals(nombreInst, that.nombreInst) &&
                Objects.equals(rfcInst, that.rfcInst) &&
                Objects.equals(presupuesto, that.presupuesto) &&
                Objects.equals(permisos, that.permisos) &&
                Objects.equals(contratos, that.contratos) &&
                Objects.equals(sector, that.sector) &&
                Objects.equals(dependencia, that.dependencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstitucion, nombreInst, rfcInst, presupuesto, permisos, contratos, sector, dependencia);
    }

    @Override
    public String toString() {
        return "institucionalidad{" +
                "idInstitucion=" + idInstitucion +
                ", nombreInst='" + nombreInst + '\'' +
                ", rfcInst='" + rfcInst + '\'' +
                ", presupuesto='" + presupuesto + '\'' +
                ", permisos='" + permisos + '\'' +
                ", contratos='" + contratos + '\'' +
                ", sector=" + sector +
                ", dependencia=" + dependencia +
                '}';
    }
}
