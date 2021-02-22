package club.santubian.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lessons {
    @JsonProperty("nameZh")
    private String namezh;

    @JsonProperty("scheduleText")
    private Scheduletext scheduletext;

    @JsonProperty("course")
    private Course course;

    @JsonProperty("remark")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNamezh() {
        return namezh;
    }

    public void setNamezh(String namezh) {
        this.namezh = namezh;
    }

    public Scheduletext getScheduletext() {
        return scheduletext;
    }

    public void setScheduletext(Scheduletext scheduletext) {
        this.scheduletext = scheduletext;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
