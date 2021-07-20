package uia.dapp1.vistas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import uia.dapp1.CRUD.CRUDuser;
import uia.dapp1.conexion.Conexion;
import uia.dapp1.entitdad.Dependencia;
import uia.dapp1.entitdad.Usuario;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNombre2;
    @FXML
    private TextField txtAP;
    @FXML
    private TextField txtAM;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker datePickerEdad;
    @FXML
    private RadioButton SexoF;
    @FXML
    private RadioButton SexoM;
    @FXML
    private ComboBox<Dependencia> comboDep;

    private ObservableList<Dependencia> listaDep;
    private ObservableList<Usuario> listaUsuarios;

    @FXML
    private TableView<Usuario> tablaUsuario;

    @FXML
    private TableColumn<Usuario, Integer> clmnID;
    @FXML
    private TableColumn<Usuario, String> clmnNom;
    @FXML
    private TableColumn<Usuario, String> clmnNom2;
    @FXML
    private TableColumn<Usuario, String> clmnAP;
    @FXML
    private TableColumn<Usuario, String> clmnAM;
    @FXML
    private TableColumn<Usuario, Long> clmnTel;
    @FXML
    private TableColumn<Usuario, String> clmnEmail;
    @FXML
    private TableColumn<Usuario, Date> clmnEdad;
    @FXML
    private TableColumn<Usuario, String> clmnSexo;
    @FXML
    private TableColumn<Usuario, Dependencia> clmnDep;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    Conexion conn = new Conexion();
    Connection connection;
    CRUDuser cruDuser = new CRUDuser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = conn.conecta();
            listaDep = FXCollections.observableArrayList();
            Dependencia.cargarInfo(connection, listaDep);
            comboDep.setItems(listaDep);

            listaUsuarios = FXCollections.observableArrayList();
            CRUDuser.llenarTablaU(connection, listaUsuarios);
            tablaUsuario.setItems(listaUsuarios);

            clmnID.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idUsuario"));
            clmnNom.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombreU"));
            clmnNom2.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre2U"));
            clmnAP.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidoP"));
            clmnAM.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidoM"));
            clmnTel.setCellValueFactory(new PropertyValueFactory<Usuario, Long>("telefonoU"));
            clmnEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));
            clmnEdad.setCellValueFactory(new PropertyValueFactory<Usuario, Date>("fechaNac"));
            clmnSexo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("genero"));
            clmnDep.setCellValueFactory(new PropertyValueFactory<Usuario, Dependencia>("dep"));
            gestorEventos();
            conn.desconecta(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Permite la interacción para poder seleccionar los campos a guardar, editar o eliminar
     */
    public void gestorEventos() {
        tablaUsuario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {
            @Override
            public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
                if (newValue != null) {
                    txtID.setText(String.valueOf(newValue.getIdUsuario()));
                    txtNombre.setText(newValue.getNombreU());
                    txtNombre2.setText(newValue.getNombre2U());
                    txtAP.setText(newValue.getApellidoP());
                    txtAM.setText(newValue.getApellidoM());
                    txtTel.setText(String.valueOf(newValue.getTelefonoU()));
                    txtEmail.setText(newValue.getEmail());
                    datePickerEdad.setValue(newValue.getFechaNac().toLocalDate());
                    if (newValue.getGenero().equals("F")) {
                        SexoF.setSelected(true);
                        SexoM.setSelected(false);
                    } else if (newValue.getGenero().equals("M")) {
                        SexoF.setSelected(false);
                        SexoM.setSelected(true);
                    }
                    comboDep.setValue(newValue.getDep());

                    btnGuardar.setDisable(true);
                    btnActualizar.setDisable(false);
                    btnEliminar.setDisable(false);
                }
            }
        });
    }

    /**
     * INSERT en el controlador
     * @throws SQLException
     */
    @FXML
    public void guardarInsert() throws SQLException {
        Usuario user = new Usuario(null,
                txtNombre.getText(),
                txtNombre2.getText(),
                txtAP.getText(),
                txtAM.getText(),
                Long.valueOf(txtTel.getText()),
                txtEmail.getText(),
                Date.valueOf(datePickerEdad.getValue()),
                SexoF.isSelected()?"F":"M",
                comboDep.getSelectionModel().getSelectedItem());
        conn.conecta();
        int i = cruDuser.guardar(conn.getConn(),user);
        conn.desconecta(connection);

        if(i == 1) {
            listaUsuarios.add(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje:");
            alert.setHeaderText("Se ha ingresado el registro con éxito.");
            //alert.setContentText("Mensaje de contenido");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error:");
            alert.setHeaderText("No se pudo guardar el registro.\nVerifica ingresar datos que sean válidos.");
            //alert.setContentText("Mensaje de contenido");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

    /**
     * UPDATE en el controlador
     * @throws SQLException
     */
    @FXML
    public void actualizarUpdate() throws SQLException {
        Usuario user = new Usuario(
                Integer.valueOf(txtID.getText()),
                txtNombre.getText(),
                txtNombre2.getText(),
                txtAP.getText(),
                txtAM.getText(),
                Long.valueOf(txtTel.getText()),
                txtEmail.getText(),
                Date.valueOf(datePickerEdad.getValue()),
                SexoF.isSelected()?"F":"M",
                comboDep.getSelectionModel().getSelectedItem());
        conn.conecta();
        int i = cruDuser.actualizar(conn.getConn(),user);
        conn.desconecta(connection);

        if(i == 1) {
            listaUsuarios.set(tablaUsuario.getSelectionModel().getSelectedIndex(),user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje:");
            alert.setHeaderText("Se ha actualizado el registro con éxito.");
            //alert.setContentText("Mensaje de contenido");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error:");
            alert.setHeaderText("No se pudo actualizar el registro.\nVerifica ingresar datos que sean válidos.");
            //alert.setContentText("Mensaje de contenido");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

    /**
     * DELETE en el controlador
     */
    @FXML
    public void eliminarDelete() {
        try {
            conn.conecta();
            int i = cruDuser.eliminar(conn.getConn(),tablaUsuario.getSelectionModel().getSelectedItem());
            conn.desconecta(connection);

            if(i == 1) {
                listaUsuarios.remove(tablaUsuario.getSelectionModel().getSelectedIndex());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje:");
                alert.setHeaderText("Se ha eliminado el registro con éxito.");
                //alert.setContentText("Mensaje de contenido");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error:");
                alert.setHeaderText("No se pudo o no se permite eliminar el registro.");
                //alert.setContentText("Mensaje de contenido");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Vacía los componentes para guardar un nuevo registro
     */
    @FXML
    public void agregarNuevousuario() {
        txtID.setText(null);
        txtNombre.setText(null);
        txtNombre2.setText(null);
        txtAP.setText(null);
        txtAM.setText(null);
        txtTel.setText(null);
        txtEmail.setText(null);
        datePickerEdad.setValue(null);
        SexoF.setSelected(false);
        SexoM.setSelected(false);
        comboDep.setValue(null);

        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }
}
