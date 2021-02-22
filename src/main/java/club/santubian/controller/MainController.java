package club.santubian.controller;

import club.santubian.View.TextAreaStyle;
import club.santubian.model.LessonDate;
import club.santubian.model.LessonModel;
import club.santubian.model.LessonsModel;
import club.santubian.model.UserModel;
import club.santubian.pojo.LessonBean;
import club.santubian.util.GetLessonModelFromLessonBean;
import club.santubian.util.GetParamFromText;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainController extends Controller{

    @FXML
    private AnchorPane topPane;

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private JFXButton btnTitle_;

    @FXML
    private JFXButton btnTitleX;

    @FXML
    private GridPane gridTable;

//    @FXML
//    private JFXListView listView;

    private ArrayList<JFXTextArea> textList;

    private List<LessonModel> lessonList;

    private boolean isClosed = true;

    @Override
    public void init() {
        textList = new ArrayList<JFXTextArea>();
//        LessonModel lesson1 = new LessonModel("数据结构","灵风",
//                new LessonDate(new int[]{2},new int[]{6},new int[]{8},1,17,1,new String[]{"2030203"}));
//        addLesson(lesson1);
        init(rootBorderPane,btnTitle_,btnTitleX,topPane);
        try {
            testDataBind();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(UserModel.msg);

        stage.setOnHiding((e)->{
            System.out.println(e);
            if(isClosed == true){
                new Thread(()->{
                    UserModel.logout();
                    System.out.println("MainController.init():退出登录2");
                }).start();
            }
        });
    }

    private void addLesson(LessonModel lesson){
        if(!lesson.isPublish()){
            return;
        }

        for (int i=0;i< lesson.getDate().getWeekCount();i++){
            int day = lesson.getDate().getWeekday()[i];
            int startTime = lesson.getDate().getStartTime()[i];
            int endTime = lesson.getDate().getEndTime()[i];
            int rowSpan = endTime - startTime + 1;
            JFXTextArea text = new JFXTextArea();
            text.setEditable(false);
            text.setStyle(TextAreaStyle.STYLE1);
            text.setText(lesson.toString(i));

            if(startTime > 0 && startTime <= 5){
                int tableRow = startTime;
                int tableCol = day;
                gridTable.add(text,tableCol,tableRow,1,rowSpan);
            }else if(startTime > 5 && startTime <= 10){
                int tableRow = startTime + 1;
                int tableCol = day;
                gridTable.add(text,tableCol,tableRow,1,rowSpan);
            }else if(startTime > 10 && startTime <= 13){
                int tableRow = startTime + 2;
                int tableCol = day;
                gridTable.add(text,tableCol,tableRow,1,rowSpan);
            }else{
                System.err.println("MainController:addLesson() 错误的时间!");
            }
            textList.add(text);
        }

    }



    private void testDataBind() throws IOException {
//        LessonBean lessonBean = null;
        ObjectMapper objectMapper = new ObjectMapper();
//        lessonBean = objectMapper.readValue(new File(getClass().getResource("/json/response.json").getPath()),LessonBean.class);
        lessonList = GetLessonModelFromLessonBean.getLessonModel(LessonsModel.getLessonBean());
//        lessonList = GetLessonModelFromLessonBean.getLessonModel(lessonBean);
        Iterator i = lessonList.iterator();
        while(i.hasNext()){
            LessonModel lessonModelt = (LessonModel)i.next();
            addLesson(lessonModelt);
        }
    }
}
