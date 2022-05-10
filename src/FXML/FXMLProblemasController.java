/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import DBAccess.NavegacionDAOException;
import DBAccess.NavegacionDAO;
import DBAccess.SqliteConnection;
import IrA.IrA;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Answer;
import model.Navegacion;
import model.Problem;

/**
 * FXML Controller class
 *
 * @author Dabelo
 */
public class FXMLProblemasController implements Initializable {

    @FXML
    private RadioButton BR1;
    @FXML
    private RadioButton BR2;
    @FXML
    private RadioButton BR3;
    @FXML
    private RadioButton BR4;
    @FXML
    private Button BComprobar;
    @FXML
    private Text TTextoProblema;
    
    
    private List<Integer> respuestaProblema;
    private List<Answer> listaRespuestas;
    private boolean RespuestaCorrecta = false;
    int pos;
    @FXML
    private Button BNuevoProblema;
    @FXML
    private Text TError;
    @FXML
    private Button BSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void nuevoProblema() {
        TError.setVisible(false);
        clear();
        try {
            Navegacion navegacion = Navegacion.getSingletonNavegacion();
            respuestaProblema = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                pos = (int) (Math.floor(Math.random() * 4));
                System.out.println(i);
                while (respuestaProblema.contains(pos)) {
                    System.out.println("prev: " + pos);
                    pos = (int) (Math.floor(Math.random() * 4));
                    System.out.println("psot: " + pos);
                }
                respuestaProblema.add(i, pos);
            }
            List<Problem> ListaProblemas = navegacion.getProblems();
            pos = (int) Math.floor(Math.random()*ListaProblemas.size());
            TTextoProblema.setText(ListaProblemas.get(pos).getText());
            listaRespuestas = ListaProblemas.get(pos).getAnswers();
            BR1.setText(listaRespuestas.get(respuestaProblema.get(0)).getText());
            BR2.setText(listaRespuestas.get(respuestaProblema.get(1)).getText());
            BR3.setText(listaRespuestas.get(respuestaProblema.get(2)).getText());
            BR4.setText(listaRespuestas.get(respuestaProblema.get(3)).getText());
        } catch (NavegacionDAOException ex) {
            System.out.println(ex);
        }
    }
    
    private void resolver (ActionEvent event) {
        RespuestaCorrecta = false;
        if (BR1.isSelected()) {
            RespuestaCorrecta = listaRespuestas.get(respuestaProblema.get(0)).getValidity();
        } else if (BR2.isSelected()) {
            RespuestaCorrecta = listaRespuestas.get(respuestaProblema.get(1)).getValidity();
        } else if (BR3.isSelected()) {
            RespuestaCorrecta = listaRespuestas.get(respuestaProblema.get(2)).getValidity();
        } else if (BR4.isSelected()) {
            RespuestaCorrecta = listaRespuestas.get(respuestaProblema.get(3)).getValidity();
        }
        if(RespuestaCorrecta){
            clear();
            nuevoProblema();
        } else {
            TError.setVisible(true);
        }
    }
    
    private void clear() {
        BR1.selectedProperty().set(false);
        BR2.selectedProperty().set(false);
        BR3.selectedProperty().set(false);
        BR4.selectedProperty().set(false);
        
        respuestaProblema.remove(0);
        respuestaProblema.remove(0);
        respuestaProblema.remove(0);
        respuestaProblema.remove(0);
    }

    @FXML
    private void IrAInicio(ActionEvent event) {
        IrA.IrAInicio((Stage) BSalir.getScene().getWindow(), getClass());
    }
    
    
}
