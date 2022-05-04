/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IrA;

//import controller.FXMLHomeController;
//import controller.FXMLSignInController;
//import controller.FXMLSignUpController;
//import nav.Navigation;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import model.Player;
/**
 *
 * @author Dabelo
 */
public class IrA {
    
    private static void IrA(Stage stage, Class classType, String res) {
        try {
            // Reoslución no variable
            Parent root = FXMLLoader.load(classType.getResource(res));
            
            double h = stage.getHeight();
            double w = stage.getWidth();
            
            Scene scene = new Scene(root, w - 18, h - 32);
            /*String style = isDark ? "/view/styleB.css" : "/view/style.css";
            String styleB = classType.getResource(style).toExternalForm();
            System.out.println("style: " + styleB);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(styleB); 
            */
            stage.setScene(scene);
            stage.show();
        } catch(IOException err) {
            System.out.println(err);
        }
    }
    
    public static void IrALogin(Stage stage, Class classType){
        IrA(stage, classType, "/FXML/FXMLLogin.fxml");
    }

    public static void IrARegistrarse(Stage stage, Class classType){
        IrA(stage, classType, "/FXML/FXMLRegistrarse.fxml");
    }
    
    public static void IrAInicio(Stage stage, Class classType){
        IrA(stage, classType, "/FXML/FXMLInicio.fxml");
    }
    
    public static void IrADocument(Stage stage, Class classType){
        IrA(stage, classType, "/poiupv/FXMLDocument.fxml");
    }
}