package club.santubian.View;

import club.santubian.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainForm extends Application {
    Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainform.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("课表");
        Scene scene = new Scene(root,1600,900);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        Controller controller = fxmlLoader.getController();
        controller.init();
        primaryStage.show();
    }

    public void showWindow() throws Exception {

        start(stage);
    }
}
