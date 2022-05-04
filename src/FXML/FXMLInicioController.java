/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import DBAccess.NavegacionDAOException;
import IrA.IrA;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author Dabelo
 */
public class FXMLInicioController implements Initializable {

    @FXML
    private Button BIdentificarse;
    @FXML
    private Button BRegistrarse;
    @FXML
    private Text TUsuario;
    @FXML
    private ImageView IVAvatar;
    @FXML
    private Button BModificar;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void IrALogin(ActionEvent event) {
        IrA.IrALogin((Stage) BIdentificarse.getScene().getWindow(), getClass());
    }

    @FXML
    private void IrARegistrarse(ActionEvent event) {
        IrA.IrARegistrarse((Stage) BRegistrarse.getScene().getWindow(), getClass());
    }
    
    
}
