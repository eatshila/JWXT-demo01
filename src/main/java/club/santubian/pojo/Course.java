package club.santubian.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    @JsonProperty("id")
    private int id;
    @JsonProperty("nameZh")
    private String namezh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamezh() {
        return namezh;
    }

    public void setNamezh(String namezh) {
        this.namezh = namezh;
    }
}
