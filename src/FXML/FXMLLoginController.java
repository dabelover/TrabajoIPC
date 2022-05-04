/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import DBAccess.NavegacionDAOException;
import IrA.IrA;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Navegacion;

/**
 * FXML Controller class
 *
 * @author Dabelo
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private Button BSalir;
    @FXML
    private TextField TFUsuario;
    @FXML
    private PasswordField PFContraseña;
    @FXML
    private Button BRegistrarse;
    @FXML  
    private Button BIdentificarse;
    
    private String usuario;
    private String contraseña;
    @FXML
    private Text TError;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void IrAInicio(ActionEvent event) {
        IrA.IrAInicio((Stage) BSalir.getScene().getWindow(), getClass());
    }

    @FXML
    private void IrARegistrarse(ActionEvent event) {
        IrA.IrARegistrarse((Stage) BRegistrarse.getScene().getWindow(), getClass());
    }
    
    private void IrADocument() {
        IrA.IrARegistrarse((Stage) BIdentificarse.getScene().getWindow(), getClass());
    }

    @FXML
    private void Login(ActionEvent event) throws NavegacionDAOException  {
        usuario = TFUsuario.getText();
        contraseña = PFContraseña.getText();
        Navegacion navegacion = Navegacion.getSingletonNavegacion();
        navegacion.loginUser(usuario, usuario);
        if(!navegacion.exitsNickName(usuario)) {
            TError.setVisible(true);
            TError.setText("El usuario no existe");
        } else {
            IrADocument();
        }
    }
    
    
    private void refreshData(){
        
    }
}
