package club.santubian.model;

import club.santubian.pojo.LessonBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LessonsModel {
    private static LessonBean lessonBean;

    public static LessonBean setLessonBean (String semesterId) throws JsonProcessingException {
        if(UserModel.isLogin==false){
            System.err.println("未登陆");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        lessonBean = objectMapper.readValue(getLessonBeanStr(semesterId),LessonBean.class);
        return lessonBean;
    }

    public static LessonBean getLessonBean(){
        return lessonBean;
    }

    private static String getLessonBeanStr(String semesterId){
        if(UserModel.isLogin==false){
            System.err.println("未登陆");
            return null;
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://jw.ahnu.edu.cn/student/for-std/course-table/get-data?bizTypeId=2&semesterId=" + semesterId)
                .method("GET", null)
                .addHeader("Cookie", "SESSION=" + UserModel.session + "; __pstsid__=" + UserModel.pstsid )
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
