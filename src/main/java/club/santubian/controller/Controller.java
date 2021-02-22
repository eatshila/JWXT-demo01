package club.santubian.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Controller {
    protected double xOffset;
    protected double yOffset;
    protected Stage stage;
    public Stage getStage(){
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    abstract public void init();

    public void init(BorderPane rootBorderPane, JFXButton btnTitle_, JFXButton btnTitleX, AnchorPane topPane){
        stage = (Stage) rootBorderPane.getScene().getWindow();
        btnTitle_.setDisableVisualFocus(true);
        btnTitleX.setDisableVisualFocus(true);
        btnTitleX.setOnMouseClicked(new MouseClickEventX());
        btnTitle_.setOnMouseClicked(new MouseClickEvent_());
        topPane.setOnMousePressed(new DragListener());
        topPane.setOnMouseDragged(new DragListener());
    }

    protected class DragListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            event.consume();
            if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                stage.setX(event.getScreenX() - xOffset);

                if (event.getScreenY() - yOffset < 0) {
                    stage.setY(0);
                } else {
                    stage.setY(event.getScreenY() - yOffset);
                }
            }
        }
    }


    protected class MouseClickEventX implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if(event.getButton() == MouseButton.PRIMARY){
                stage.close();
            }
        }
    }

    protected class MouseClickEvent_ implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if(event.getButton() == MouseButton.PRIMARY){
                stage.setIconified(true);
            }
        }
    }
}
