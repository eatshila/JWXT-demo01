package club.santubian.model;

public class LessonDate {
    //星期几
    private int weekday[];

    //第几节课开始
    private int startTime[];

    //第几节课结束
    private int endTime[];

    //第几周开始
    private int startWeek[];

    //第几周结束
    private int endWeek[];

    //一周几次课
    private int weekCount;

    //地点
    private String[] room;

    public LessonDate(int[] weekday, int[] startTime, int[] endTime, int startWeek[], int endWeek[], int weekCount, String[] room) {
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.weekCount = weekCount;
        this.room = room;
    }

    public String[] getRoom() {
        return room;
    }

    public void setRoom(String[] room) {
        this.room = room;
    }

    public int[] getWeekday() {
        return weekday;
    }

    public void setWeekday(int[] weekday) {
        this.weekday = weekday;
    }

    public int getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(int weekCount) {
        this.weekCount = weekCount;
    }

    public LessonDate() {
    }

    public int[] getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int[] startWeek) {
        this.startWeek = startWeek;
    }

    public int[] getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int[] endWeek) {
        this.endWeek = endWeek;
    }

    public int[] getStartTime() {
        return startTime;
    }

    public void setStartTime(int[] startTime) {
        this.startTime = startTime;
    }

    public int[] getEndTime() {
        return endTime;
    }

    public void setEndTime(int[] endTime) {
        this.endTime = endTime;
    }

    public String getTimePlaceStr(){
        String str = "";
        for(int i =0 ;i<weekCount;i++){
            str += startWeek[i]+"~"+endWeek[i]+ " " + room[i] + "\n";
        }
        return str;
    }

    public String getTimePlaceStr(int i){
        String str = startWeek[i]+"~"+endWeek[i]+ " " + room[i] + "\n";
        return str;
    }

}
