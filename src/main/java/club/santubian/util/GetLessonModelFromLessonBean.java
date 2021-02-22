package club.santubian.util;

import club.santubian.model.LessonModel;
import club.santubian.pojo.LessonBean;
import club.santubian.pojo.Lessons;

import java.util.ArrayList;
import java.util.List;

public class GetLessonModelFromLessonBean {
    public static List<LessonModel> getLessonModel(LessonBean lessonBean){
        List<LessonModel> list = new ArrayList<LessonModel>();
        GetParamFromText tool = new GetParamFromText();
        for(Lessons lesson : lessonBean.getLessons()){
            LessonModel lessonModel = new LessonModel();
            if(lesson.getScheduletext().getDatetimeplacetext().getText() == null){
                lessonModel.setInfo("暂未安排课程表");
            }else{
                lessonModel.setPublish(true);
                lessonModel.setNameZh(lesson.getCourse().getNamezh());
                lessonModel.setTeacherName("");
                lessonModel.setDate(tool.getDate(lesson.getScheduletext().getDatetimeplacetext().getText()));
            }
            list.add(lessonModel);
        }
        return list;
    }


}
