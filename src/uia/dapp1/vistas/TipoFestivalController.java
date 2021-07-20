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
import uia.dapp1.conexion.Conexion;
import uia.dapp1.CRUD.CrudTF;
import uia.dapp1.entitdad.TipoFestival;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TipoFestivalController implements Initializable {

    @FXML
    private TextField tipoFestID;
    @FXML
    private TextField tipoFest;

    @FXML
    private TableView<TipoFestival> tablaTF;
    @FXML
    private TableColumn<TipoFestival, Integer> clmnID;
    @FXML
    private TableColumn<TipoFestival, String> clmnTF;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    private ObservableList<TipoFestival> listaTF;

    Conexion conn = new Conexion();
    Connection connection;
    CrudTF TF = new CrudTF();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = conn.conecta();
            listaTF = FXCollections.observableArrayList();
            CrudTF.llenarTabla(connection, listaTF);
            tablaTF.setItems(listaTF);
            clmnID.setCellValueFactory(new PropertyValueFactory<TipoFestival, Integer>("idTF"));
            clmnTF.setCellValueFactory(new PropertyValueFactory<TipoFestival, String>("tipoFestival"));
            gestionEventos();
            conn.desconecta(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Permite la interacción para poder seleccionar los campos a guardar, editar o eliminar
     */
    public void gestionEventos() {
        tablaTF.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TipoFestival>() {
            @Override
            public void changed(ObservableValue<? extends TipoFestival> observable, TipoFestival oldValue, TipoFestival newValue) {
                if(newValue!= null) {
                    tipoFestID.setText(String.valueOf(newValue.getIdTF()));
                    tipoFest.setText(newValue.getTipoFestival());
                    //Deshabilitando y habilitando botones
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
        TipoFestival tf = new TipoFestival(null,
                tipoFest.getText());
        conn.conecta();
        int i = TF.guardar(conn.getConn(),tf);
        conn.desconecta(connection);

        if(i == 1) {
            listaTF.add(tf);
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
     * Update en el controlador
     * @throws SQLException
     */
    @FXML
    public void actualizarUpdate() throws SQLException {
        TipoFestival tf = new TipoFestival(
                Integer.valueOf(tipoFestID.getText()),
                tipoFest.getText());
        conn.conecta();
        int i = TF.actualizar(conn.getConn(),tf);
        conn.desconecta(connection);

        if(i == 1) {
            listaTF.set(tablaTF.getSelectionModel().getSelectedIndex(),tf);
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
            int i = TF.eliminar(conn.getConn(),tablaTF.getSelectionModel().getSelectedItem());
            conn.desconecta(connection);

            if(i == 1) {
                listaTF.remove(tablaTF.getSelectionModel().getSelectedIndex());
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
    public void agregarTF() {
        tipoFestID.setText(null);
        tipoFest.setText(null);
        //Deshabilitando y habilitando botones
        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }
}
