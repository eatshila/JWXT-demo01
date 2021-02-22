package club.santubian.View;
import club.santubian.controller.Controller;
import club.santubian.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class LoginForm extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        URL resource = getClass().getResource("");
//        System.out.println(resource.toString());
//        System.exit(0);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/loginform.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("学生端教务系统登录");
        Scene scene = new Scene(root,600,400);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        Controller controller = fxmlLoader.getController();
        controller.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
