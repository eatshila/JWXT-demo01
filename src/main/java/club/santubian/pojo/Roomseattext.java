package club.santubian.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roomseattext {

    @JsonProperty("textZh")
    private String textzh;
    @JsonProperty("textEn")
    private String texten;
    private String text;
    public void setTextzh(String textzh) {
         this.textzh = textzh;
     }
     public String getTextzh() {
         return textzh;
     }

    public void setTexten(String texten) {
         this.texten = texten;
     }
     public String getTexten() {
         return texten;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

}