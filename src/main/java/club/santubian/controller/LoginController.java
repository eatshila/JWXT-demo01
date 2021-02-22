package club.santubian.controller;

import club.santubian.View.MainForm;
import club.santubian.model.LessonsModel;
import club.santubian.model.UserModel;
import club.santubian.pojo.LessonBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.WindowEvent;

import java.util.concurrent.*;


public class LoginController extends Controller {

    @FXML
    private JFXButton btnTitle_;

    @FXML
    private JFXButton btnTitleX;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private AnchorPane topPane;

    @FXML
    private JFXSpinner loginSpinner;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblInfo;

    private boolean isClosed = true;

    private State state;
    private final State initState = new InitState();
    private final State loginState = new LoginState();
    private final State loginFailState = new LoginFailState();
    private final State loginInState = new LoginInState();
    private LessonBean lessonBean;

    public void init(){
        init(rootBorderPane,btnTitle_,btnTitleX,topPane);
        btnLogin.setOnMouseClicked(new MouseClickEventLogin());
        btnLogin.setOnKeyPressed(new KeyPressEnterEventLogin());
        txtPassword.setOnKeyPressed(new KeyPressEnterEventLogin());
//        Platform.setImplicitExit(false);
        stage.setOnCloseRequest((e)->{
//            System.out.println("e1"+e);
        });
        stage.setOnHiding((e)->{
//            System.out.println(e);
            if(isClosed == true){
                new Thread(()->{
                    UserModel.logout();
                    System.out.println("LoginController.init():退出登录");
                }).start();
            }
        });
    }



    private class MouseEnteredEvent implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            JFXButton btn = (JFXButton) event.getTarget();
        }
    }

    private class MouseClickEventLogin implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton() == MouseButton.PRIMARY){
                onGotoLogin();
            }
        }
    }
    private void onGotoLogin(){
        goState(loginState);
        LoginThread loginThread = new LoginThread(txtUsername.getText(),txtPassword.getText());
        loginThread.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println(oldValue+"---"+newValue);
                if(newValue.doubleValue() < 0.1){
                    goState(loginFailState);
                    System.out.println("LoginController.onGotoLogin():没进去");
                }else if(newValue.doubleValue() < 0.2){
                    System.out.println("LoginController.onGotoLogin():进去");
                    goState(loginInState);
                }else if(newValue.doubleValue() < 0.3){
                    goState(initState);
                    System.out.println("LoginController.onGotoLogin():初始化");
                }
            }
        });
        new Thread(loginThread).start();
    }
    private class KeyPressEnterEventLogin implements EventHandler<KeyEvent>{

        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                onGotoLogin();
            }
        }
    }

    class InitState extends State{
        @Override
        void onEntry() {
            txtUsername.setText("");
            txtPassword.setText("");
            btnLogin.setVisible(true);
            loginSpinner.setVisible(false);
            txtUsername.setDisable(false);
            txtPassword.setDisable(false);
            txtUsername.setEditable(true);
            txtPassword.setEditable(true);
            lblInfo.setVisible(false);
        }
    }

    class LoginFailState extends State{
        @Override
        void onEntry() {
            btnLogin.setVisible(true);
            loginSpinner.setVisible(false);
            txtUsername.setDisable(false);
            txtPassword.setDisable(false);
            txtUsername.setEditable(true);
            txtPassword.setEditable(true);
            lblInfo.setText(UserModel.msg);
            lblInfo.setVisible(true);
        }
    }
    class LoginState extends State{
        @Override
        void onEntry() {
            txtUsername.setDisable(true);
            txtPassword.setDisable(true);
            btnLogin.setVisible(false);
            loginSpinner.setVisible(true);
            lblInfo.setVisible(false);
        }
    }

    class LoginInState extends State{
        @Override
        void onEntry() {
            stage.hide();
            MainForm mainForm = new MainForm();
            try {
                mainForm.showWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    abstract class State{
        void onEntry(){}
    }

    private void goState(State state){
        this.state = state;
        state.onEntry();
    }

    private class LoginThread extends Task<Integer> {


        private String username;
        private String password;

        public LoginThread(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void updateProgress(long workDone, long max) {
            super.updateProgress(workDone, max);
        }

        @Override
        protected void updateProgress(double workDone, double max) {
            super.updateProgress(workDone, max);
        }

        @Override
        protected void updateMessage(String message) {
            super.updateMessage(message);
        }

        @Override
        protected void updateTitle(String title) {
            super.updateTitle(title);
        }

        @Override
        protected void updateValue(Integer value) {
            super.updateValue(value);

        }

        @Override
        public Integer call() throws Exception {
            if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("") || username == null || password == null){
                UserModel.msg = "学号或密码不能为空";
                UserModel.state = -1;
                System.out.println("别空着");
                updateProgress(1,100);
                return -1;
            }else{
                if(UserModel.login(username,password)){
                    UserModel.isLogin = true;
                    try {
                        LessonsModel.setLessonBean("61");
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    UserModel.state = 1;
                    isClosed = false;
                    updateProgress(15,100);
                    return 1;

                }else{
                    UserModel.state = -1;
                    System.out.println("LoginController$LoginThread.call():登录出错了吧");
                    updateProgress(1,100);
                    return -1;
                }
            }
        }
    }



}
