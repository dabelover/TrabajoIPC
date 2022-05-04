/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import DBAccess.NavegacionDAOException;
import IrA.IrA;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Navegacion;
import model.User;
import poiupv.PoiUPVApp;

/**
 * FXML Controller class
 *
 * @author Dabelo
 */
public class FXMLRegistrarseController implements Initializable {

    @FXML
    private Button BSalir;
    @FXML
    private TextField TFUsuario;
    @FXML
    private TextField TFCorreo;
    @FXML
    private DatePicker DPFechaNacimiento;
    @FXML
    private ImageView IVAvatar;
    @FXML
    private Button BCambiar;
    @FXML
    private Button BRegistrarse;
    @FXML
    private PasswordField PFContraseña;
    @FXML
    private Text TError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler<ActionEvent> btnLoadEventListener
                = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                File file = fileChooser.showOpenDialog(null);
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    IVAvatar.setImage(image);
                    IVAvatar.setFitHeight(150);
                    IVAvatar.setFitWidth(90);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(PoiUPVApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        };
    }    
    
    private String usuario;
    private String correo;
    private String contraseña;
    private Image avatar;
    private LocalDate fechaNacimiento;
    
    @FXML
    private void IrAInicio(ActionEvent event){
        IrA.IrAInicio((Stage) BSalir.getScene().getWindow(), getClass());
    }
    
    private void IrALogin(ActionEvent event){
        IrA.IrALogin((Stage) BRegistrarse.getScene().getWindow(), getClass());
    }

    @FXML
    private void Registrarse(ActionEvent event) throws NavegacionDAOException {
        usuario = TFUsuario.getText();
        contraseña = PFContraseña.getText();
        correo = TFCorreo.getText();
        avatar = IVAvatar.getImage();
        fechaNacimiento = DPFechaNacimiento.getValue();
        Navegacion navegacion = Navegacion.getSingletonNavegacion();
        User user = navegacion.registerUser( usuario, correo, contraseña,
                avatar, fechaNacimiento);
        
        if(!navegacion.exitsNickName(usuario)) {
            TError.setVisible(true);
            TError.setText("El usuario no ha sido correctamente registrado");
        } else {
            /*Alert output = new Alert(AlertType.CONFIRMATION);
            output.setTitle("Registro Nuevo Usuario");
            output.setHeaderText("Confirmado");
            output.getButtonTypes().setAll(new ButtonType("Iniciar Sesión", ButtonBar.ButtonData.OK_DONE));
            
            output.showAndWait();*/
            TError.setVisible(true);
            TError.setText("Todo ha ido bien");
        }
    }

    @FXML
    private void CambiarAvatar(ActionEvent event) throws FileNotFoundException {
        Stage stage = (Stage) BCambiar.getScene().getWindow();
        FileChooser fChooser = new FileChooser();
        File f = fChooser.showOpenDialog(stage);
        Image i = new Image(new FileInputStream(f));
        IVAvatar.imageProperty().setValue(i);
    }
    
    
     
}
