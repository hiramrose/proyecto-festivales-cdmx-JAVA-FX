package uia.dapp1.entitdad;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class Usuario {
    private Integer idUsuario;
    private String nombreU;
    private String nombre2U;
    private String apellidoP;
    private String apellidoM;
    private Long telefonoU;
    private String email;
    private Date fechaNac;
    private String genero;
    private Dependencia dep;

    /**
     * Constructor del Usuario
     * @param idUsuario ID y llave primaria del usuario
     * @param nombreU Nombre del usuario
     * @param nombre2U Segundo nombre del usuario
     * @param apellidoP Apellido paterno del usuario
     * @param apellidoM Apellido materno del usuario
     * @param telefonoU Teléfono del usuario
     * @param email Correo electrónico del usuario
     * @param fechaNac Fecha de nacimiento del usuario
     * @param genero Sexo del usuario
     * @param dep ID llave foránea de la dependencia
     */
    public Usuario(Integer idUsuario, String nombreU, String nombre2U, String apellidoP, String apellidoM, Long telefonoU, String email, Date fechaNac, String genero, Dependencia dep) {
        this.idUsuario = idUsuario;
        this.nombreU = nombreU;
        this.nombre2U = nombre2U;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.telefonoU = telefonoU;
        this.email = email;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.dep = dep;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getNombre2U() {
        return nombre2U;
    }

    public void setNombre2U(String nombre2U) {
        this.nombre2U = nombre2U;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public Long getTelefonoU() {
        return telefonoU;
    }

    public void setTelefonoU(Long telefonoU) {
        this.telefonoU = telefonoU;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Dependencia getDep() {
        return dep;
    }

    public void setDep(Dependencia dep) {
        this.dep = dep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) &&
                Objects.equals(nombreU, usuario.nombreU) &&
                Objects.equals(nombre2U, usuario.nombre2U) &&
                Objects.equals(apellidoP, usuario.apellidoP) &&
                Objects.equals(apellidoM, usuario.apellidoM) &&
                Objects.equals(telefonoU, usuario.telefonoU) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(fechaNac, usuario.fechaNac) &&
                Objects.equals(genero, usuario.genero) &&
                Objects.equals(dep, usuario.dep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreU, nombre2U, apellidoP, apellidoM, telefonoU, email, fechaNac, genero, dep);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreU='" + nombreU + '\'' +
                ", nombre2U='" + nombre2U + '\'' +
                ", apellidoP='" + apellidoP + '\'' +
                ", apellidoM='" + apellidoM + '\'' +
                ", telefonoU=" + telefonoU +
                ", email='" + email + '\'' +
                ", fechaNac=" + fechaNac +
                ", genero='" + genero + '\'' +
                ", dep=" + dep +
                '}';
    }
}
