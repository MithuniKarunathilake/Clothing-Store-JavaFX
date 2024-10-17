package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleFormController implements Initializable {

    @FXML
    private Label lblBackMenu;

    @FXML
    private Label lblMenu;

    @FXML
    private AnchorPane slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(-176);

        lblMenu.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished(ActionEvent ->{
                lblMenu.setVisible(false);
                lblBackMenu.setVisible(true);
            });
        });

        lblBackMenu.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished(ActionEvent ->{
                lblMenu.setVisible(true);
                lblBackMenu.setVisible(false);
            });
        });

    }
}

