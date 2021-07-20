package uia.dapp1;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uia.dapp1.conexion.Conexion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField usuarioIngresado;

    @FXML
    private PasswordField passwordIngresado;

    Conexion a = new Conexion();
    Connection conn = a.conecta();
    PreparedStatement pstLogin;
    ResultSet rsLogin;

    public Controller() throws SQLException {
    }

    @FXML
    void handleButtonLogin(ActionEvent event) {
        String username = usuarioIngresado.getText();
        String contrasena = passwordIngresado.getText();

        if(username.equals("") || contrasena.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¯\\_( ͡❛ ͜ʖ ͡❛)_/¯  Faltan datos");
            alert.setHeaderText("No podemos procesar la información para loguearte.");
            alert.setContentText("No ingresaste tu usuario o tal vez la contraseña. Intenta de nuevo.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else {
            try {
                String sql = "select * from personal where username=? and contrasena=?";
                pstLogin = conn.prepareStatement(sql);
                pstLogin.setString(1, username);
                pstLogin.setString(2, contrasena);

                rsLogin = pstLogin.executeQuery();
                if(rsLogin.next()) {
                    //Si el login es exitoso, se mostrará el siguiente stage
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/uia/dapp1/vistas/principal.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage principal = new Stage();
                    principal.setTitle("Administrador: panel global - Dirección general de festivales comunitarios de la CDMX");
                    principal.setScene(new Scene(root));
                    // Establece el icono de la aplicación.
                    principal.getIcons().add(new Image("file:resources/imagenes/logoFestivalesCDMX.jpg"));
                    principal.show();
                } else { // Si no es exitoso el login, se muestra esta alerta:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("❎ Datos incorrectos.");
                    alert.setHeaderText("Los datos que ingresaste fueron incorrectos.");
                    alert.setContentText("No podemos procesar la información para loguearte. Por favor, verifíca tus datos.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    usuarioIngresado.setText("");
                    passwordIngresado.setText("");
                    usuarioIngresado.requestFocus();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * "¿Olvidaste la contraseña?"
     * @param event permite al usuario (como ejemplo solamente) ingresar su correo para reestablecer su contraseña
     */
    @FXML
    void handleRecordarPassword(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("ejemplo@correo.com");
        dialog.setTitle("✉ Reestablece tu contraseña.");
        dialog.setHeaderText("Te ayudamos a reestablecer tu contraseña.\nRevisa tu correo después de ingresarlo y de dar click en Aceptar.");
        dialog.setContentText("Ingresa tu e-mail:");
        dialog.initStyle(StageStyle.UTILITY);
        // Obtiene el correo ingresado, pero no haré nada más, solo es de ejemplo.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Correo ingresado a reestablecer contraseña: " + result.get());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
