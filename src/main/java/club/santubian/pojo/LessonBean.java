package club.santubian.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonBean {
    @JsonProperty("lessons")
    private List<Lessons> lessons;

    public List<Lessons> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lessons> lessons) {
        this.lessons = lessons;
    }
}
