package com.dmd.testinter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PrimaryController implements Initializable {

    private Corredor liebre;
    private Corredor tortuga;

    @FXML private Button btnIniciar;
    @FXML private Label lblLiebre;
    @FXML private ProgressBar barLiebre;
    @FXML private Label lblTortuga;
    @FXML private ProgressBar barTortuga;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        liebre = new Corredor("La Liebre", 30);
        tortuga = new Corredor("La Tortuga", 80);
    }

    @FXML
    private void iniciarCarrera(ActionEvent event) {
        btnIniciar.setDisable(true);

        Thread hiloLiebre = new Thread(new Runnable() {
            @Override
            public void run() {
                for (double i = 0; i <= 1.0; i += 0.05) {
                    final double avance = i;
                    
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            barLiebre.setProgress(avance);
                            if (avance > 0.4 && avance < 0.8) {
                                lblLiebre.setText(liebre.getNombre() + ": ¡Se durmió!");
                            } else {
                                lblLiebre.setText(liebre.getNombre() + ": ¡Está corriendo!");
                            }
                        }
                    });

                    try {
                        if (avance > 0.4 && avance < 0.8) {
                            Thread.sleep(800);
                        } else {
                            Thread.sleep(liebre.getTiempoEspera());
                        }
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        Thread hiloTortuga = new Thread(new Runnable() {
            @Override
            public void run() {
                for (double i = 0; i <= 1.0; i += 0.02) {
                    final double avance = i;
                    
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            barTortuga.setProgress(avance);
                            lblTortuga.setText(tortuga.getNombre() + ": ¡Está corriendo!");
                        }
                    });

                    try {
                        Thread.sleep(tortuga.getTiempoEspera());
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        hiloLiebre.start();
        hiloTortuga.start();
    }
}