package uia.dapp1.vistas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import uia.dapp1.CRUD.CRUDIns;
import uia.dapp1.conexion.Conexion;
import uia.dapp1.entitdad.Dependencia;
import uia.dapp1.entitdad.Institucionalidad;
import uia.dapp1.entitdad.Sector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InstitucionController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombreInst;
    @FXML
    private TextField txtRFC;
    @FXML
    private TextField txtPresupuesto;
    @FXML
    private TextField txtPermisos;
    @FXML
    private TextField txtContratos;
    @FXML
    private ComboBox<Sector> comboSector;
    @FXML
    private ComboBox<Dependencia> comboDep;
    @FXML
    private TableView<Institucionalidad> tableView;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;


    @FXML
    private TableColumn<Institucionalidad, Integer> clmnID;
    @FXML
    private TableColumn<Institucionalidad, String> clmnIns;
    @FXML
    private TableColumn<Institucionalidad, String> clmnRFC;
    @FXML
    private TableColumn<Institucionalidad, Double> clmnPres;
    @FXML
    private TableColumn<Institucionalidad, String> clmnPerm;
    @FXML
    private TableColumn<Institucionalidad, String> clmnContr;
    @FXML
    private TableColumn<Institucionalidad, Sector> clmnSect;
    @FXML
    private TableColumn<Institucionalidad, Dependencia> clmnDep;


    private ObservableList<Sector> listaSectores;
    private ObservableList<Dependencia> listaDep;
    private ObservableList<Institucionalidad> listaIns;

    Conexion conn = new Conexion();
    Connection connection;
    CRUDIns crudIns = new CRUDIns();

    /**
     * Inicializador
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = conn.conecta();
            listaSectores = FXCollections.observableArrayList();
            Sector.cargarInfo(connection, listaSectores);
            comboSector.setItems(listaSectores);

            listaDep = FXCollections.observableArrayList();
            Dependencia.cargarInfo(connection, listaDep);
            comboDep.setItems(listaDep);

            listaIns = FXCollections.observableArrayList();
            CRUDIns.hacerTabla(connection, listaIns);
            tableView.setItems(listaIns);

            clmnID.setCellValueFactory(new PropertyValueFactory<Institucionalidad, Integer>("idInstitucion"));
            clmnIns.setCellValueFactory(new PropertyValueFactory<Institucionalidad, String>("nombreInst"));
            clmnRFC.setCellValueFactory(new PropertyValueFactory<Institucionalidad, String>("rfcInst"));
            clmnPres.setCellValueFactory(new PropertyValueFactory<Institucionalidad, Double>("presupuesto"));
            clmnPerm.setCellValueFactory(new PropertyValueFactory<Institucionalidad, String>("permisos"));
            clmnContr.setCellValueFactory(new PropertyValueFactory<Institucionalidad, String>("contratos"));
            clmnSect.setCellValueFactory(new PropertyValueFactory<Institucionalidad, Sector>("sector"));
            clmnDep.setCellValueFactory(new PropertyValueFactory<Institucionalidad, Dependencia>("dependencia"));
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
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Institucionalidad>() {
            @Override
            public void changed(ObservableValue<? extends Institucionalidad> observable, Institucionalidad oldValue, Institucionalidad newValue) {
                //System.out.println("Se ha seleccionado un registro.");
                if(newValue!= null) {
                    txtID.setText(String.valueOf(newValue.getIdInstitucion()));
                    txtNombreInst.setText(newValue.getNombreInst());
                    txtRFC.setText(newValue.getRfcInst());
                    txtPresupuesto.setText(String.valueOf(newValue.getPresupuesto()));
                    txtPermisos.setText(newValue.getPermisos());
                    txtContratos.setText(newValue.getContratos());
                    comboSector.setValue(newValue.getSector());
                    comboDep.setValue(newValue.getDependencia());

                    btnGuardar.setDisable(true);
                    btnActualizar.setDisable(false);
                    btnEliminar.setDisable(false);
                }
            }
        });
    }

    /**
     * INSERT en controlador
     * @throws SQLException
     */
    @FXML
    public void guardarInsert() throws SQLException {
        Institucionalidad ins = new Institucionalidad(null,
                txtNombreInst.getText(),
                txtRFC.getText(),
                Double.valueOf(txtPresupuesto.getText()),
                txtPermisos.getText(),
                txtContratos.getText(),
                comboSector.getSelectionModel().getSelectedItem(),
                comboDep.getSelectionModel().getSelectedItem());
        conn.conecta();
        int i = crudIns.guardar(conn.getConn(),ins);
        conn.desconecta(connection);

        if(i == 1) {
            listaIns.add(ins);
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
     * UPDATE en controlador
     * @throws SQLException
     */
    @FXML
    public void actualizarUpdate() throws SQLException {
        Institucionalidad ins = new Institucionalidad(
                Integer.valueOf(txtID.getText()),
                txtNombreInst.getText(),
                txtRFC.getText(),
                Double.valueOf(txtPresupuesto.getText()),
                txtPermisos.getText(),
                txtContratos.getText(),
                comboSector.getSelectionModel().getSelectedItem(),
                comboDep.getSelectionModel().getSelectedItem());
        conn.conecta();
        int i = crudIns.actualizar(conn.getConn(),ins);
        conn.desconecta(connection);

        if(i == 1) {
            listaIns.set(tableView.getSelectionModel().getSelectedIndex(),ins);
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
     * DELETE en controlador
     */
    @FXML
    public void eliminarDelete() {
        try {
            conn.conecta();
            int i = crudIns.eliminar(conn.getConn(),tableView.getSelectionModel().getSelectedItem());
            conn.desconecta(connection);

            if(i == 1) {
                listaIns.remove(tableView.getSelectionModel().getSelectedIndex());
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
    public void agregarNuevaInstitucion() {
        txtID.setText(null);
        txtNombreInst.setText(null);
        txtRFC.setText(null);
        txtPresupuesto.setText(null);
        txtPermisos.setText(null);
        txtContratos.setText(null);
        comboSector.setValue(null);
        comboDep.setValue(null);

        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }
}
