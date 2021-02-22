package club.santubian.util;

import club.santubian.model.LessonDate;

import java.util.HashMap;
import java.util.Map;

public class GetParamFromText {
    Map<String,Integer> weekMap = new HashMap<>();
    Map<String,Integer> unitMap = new HashMap<>();

    public LessonDate getDate(String text){
        String aSplitStr = "; \n";
        System.out.println(text);
        String strWeekAll[] = text.split(aSplitStr);
        int weekCount = strWeekAll.length;
        int startTime[] = new int[weekCount];
        int endTime[] = new int[weekCount];
        int weekDay[] = new int[weekCount];
        int startWeek[] = new int[weekCount];
        int endWeek[] = new int[weekCount];
        String room[] = new String[weekCount];
        LessonDate lessonDate = new LessonDate();
        for(int i=0;i<weekCount;i++){
            String bSplitStr = " ";
            String singleCourseInfo[] = strWeekAll[i].split(bSplitStr);
            if(singleCourseInfo.length == 4){
                room[i] = singleCourseInfo[3];
            }else{
                room[i] = singleCourseInfo[4];
            }
            startWeek[i] = getStartWeek(singleCourseInfo[0]);
            endWeek[i] = getEndWeek(singleCourseInfo[0]);
            weekDay[i] = getWeekIndex(singleCourseInfo[1]);
            startTime[i] = getStartTime(singleCourseInfo[2]);
            endTime[i] = getEndTime(singleCourseInfo[2]);
        }
        lessonDate.setRoom(room);
        lessonDate.setWeekday(weekDay);
        lessonDate.setStartTime(startTime);
        lessonDate.setEndTime(endTime);
        lessonDate.setStartWeek(startWeek);
        lessonDate.setEndWeek(endWeek);
        lessonDate.setWeekCount(weekCount);
        String roomStr = room[0] + "\n";
        for (int i=1;i<room.length;i++){
            roomStr += startWeek[i] + "~" + endWeek[i] + " " + room[i] + "\n";
        }
        if(isSameTime(lessonDate)){
            System.out.println("true");
            lessonDate.setWeekCount(1);
            for(int i=0;i<lessonDate.getRoom().length;i++){
                room[i] = roomStr;
            }
            lessonDate.setRoom(room);
        }
        return lessonDate;
    }

    private boolean isSameTime(LessonDate lessonDate) {
        if(lessonDate.getWeekCount() == 1){
            return true;
        }
        int startTime0 = lessonDate.getStartTime()[0];
        int weekDay0 = lessonDate.getWeekday()[0];
        for(int i=0;i<lessonDate.getWeekCount();i++){
            if(startTime0 != lessonDate.getStartTime()[i]){
                return false;
            }
        }
        for(int i=0;i<lessonDate.getWeekCount();i++){
            if(weekDay0 != lessonDate.getWeekday()[i]){
                return false;
            }
        }
        return true;
    }

    //1~17周拆分出startWeek与endWeek
    public int getStartWeek(String str){
        String aSplitStr = "~";
        String weekStr[] = str.split(aSplitStr);
        int weekInt = 0;
        if(weekStr.length == 1){
            String weekStr2[] = weekStr[0].split("周");
            weekInt = Integer.parseInt(weekStr2[0]);
        }else{
            weekInt = Integer.parseInt(weekStr[0]);
        }
        return weekInt;
    }

    //1~17周拆分出startWeek与endWeek
    public static int getEndWeek(String str){
        String aSplitStr = "~";

        String str2[] = str.split(aSplitStr);
        int weekInt = 0;
        if(str2.length == 1){
            String weekStr[] = str2[0].split("周");
            weekInt = Integer.parseInt(weekStr[0]);
        }else {
            String weekStr[] = str2[1].split("周");
            weekInt = Integer.parseInt(weekStr[0]);
        }

        return weekInt;
    }

    private void initWeekMap(){
        weekMap.put("周一",1);
        weekMap.put("周二",2);
        weekMap.put("周三",3);
        weekMap.put("周四",4);
        weekMap.put("周五",5);
        weekMap.put("周六",6);
        weekMap.put("周日",7);
        weekMap.put("周天",7);
        weekMap.put("周七",7);
        weekMap.put("星期一",1);
        weekMap.put("星期二",2);
        weekMap.put("星期三",3);
        weekMap.put("星期四",4);
        weekMap.put("星期五",5);
        weekMap.put("星期六",6);
        weekMap.put("星期天",7);
        weekMap.put("星期日",7);
        weekMap.put("星期七",7);
    }

    private void initUnitMap(){
        unitMap.put("第一节",1);
        unitMap.put("第二节",2);
        unitMap.put("第三节",3);
        unitMap.put("第四节",4);
        unitMap.put("第五节",5);
        unitMap.put("第六节",6);
        unitMap.put("第七节",7);
        unitMap.put("第八节",8);
        unitMap.put("第九节",9);
        unitMap.put("第十节",10);
        unitMap.put("第十一节",11);
        unitMap.put("十一节",11);
        unitMap.put("第十二节",12);
        unitMap.put("十二节",12);
        unitMap.put("第十三节",13);
        unitMap.put("十三节",13);
    }

    public int getStartTime(String str){
        String startTimeStr = str.split("~")[0];
        return getUnitIndex(startTimeStr);
    }

    public int getEndTime(String str){
        String endTimeStr = str.split("~")[1];
        return getUnitIndex(endTimeStr);
    }

    public int getWeekIndex(String str){
        initWeekMap();
        return weekMap.get(str);
    }

    public int getUnitIndex(String str){
        initUnitMap();
        return unitMap.get(str);
    }



}
