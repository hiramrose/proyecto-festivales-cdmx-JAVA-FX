package uia.dapp1.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private Button btnInstituciones;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button tipoFestival;

    @FXML
    private Button btnSalir;


    /**
     * Stage de Institucionalidad del CRUD relacional
     * @param event Permite acceder al stage
     */
    @FXML
    void handleButtonInstituciones(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/uia/dapp1/vistas/institucion.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage principal = new Stage();
        principal.setTitle("Instituciones - Dirección general de festivales comunitarios de la CDMX");
        principal.setScene(new Scene(root));
        // Establece el icono de la aplicación.
        principal.getIcons().add(new Image("file:resources/imagenes/logoFestivalesCDMX.jpg"));
        principal.show();
    }

    /**
     * Stage de Usuarios del segundo CRUD relacional
     * @param event Permite acceder al stage
     */
    @FXML
    void handleButtonUsuarios(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/uia/dapp1/vistas/usuarios.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage principal = new Stage();
        principal.setTitle("Usuarios - Dirección general de festivales comunitarios de la CDMX");
        principal.setScene(new Scene(root));
        // Establece el icono de la aplicación.
        principal.getIcons().add(new Image("file:resources/imagenes/logoFestivalesCDMX.jpg"));
        principal.show();
    }

    /**
     * Stage de Tipo de Festival del tercer CRUD (normal)
     * @param event Permite acceder al stage
     */
    @FXML
    void handleButtonTipoFestival(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/uia/dapp1/vistas/tipoFestival.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage principal = new Stage();
        principal.setTitle("Tipos de Festivales - Dirección general de festivales comunitarios de la CDMX");
        principal.setScene(new Scene(root));
        // Establece el icono de la aplicación.
        principal.getIcons().add(new Image("file:resources/imagenes/logoFestivalesCDMX.jpg"));
        principal.show();
    }

    /**
     * Cerrar Sesión
     * @param event Permite salir del programa
     */
    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
