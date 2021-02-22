package club.santubian.model;

public class LessonModel {
    private String info;
    private String nameZh;
//    private String nameEh;
    private String teacherName;
    private LessonDate date;
    private boolean publish = false;

    public LessonModel() {
    }

    public LessonModel(String nameZh, String teacherName, LessonDate date) {
        this.nameZh = nameZh;
        this.teacherName = teacherName;
        this.date = date;
        this.info = "";
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LessonDate getDate() {
        return date;
    }

    public void setDate(LessonDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                nameZh +"\n"
                ;
    }

    public String toString(int index){
        if(getDate().getWeekCount() > 1){

        }
        return
                nameZh + "\n" +
                date.getTimePlaceStr(index);

    }
}
