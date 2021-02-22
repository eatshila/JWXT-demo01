package club.santubian.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scheduletext {

    @JsonProperty("dateTimeText")
    private Datetimetext datetimetext;
    @JsonProperty("dateTimePlaceText")
    private Datetimeplacetext datetimeplacetext;
    @JsonProperty("dateTimePlacePersonText")
    private Datetimeplacepersontext datetimeplacepersontext;
    @JsonProperty("roomSeatText")
    private Roomseattext roomseattext;
    public void setDatetimetext(Datetimetext datetimetext) {
        this.datetimetext = datetimetext;
    }
    public Datetimetext getDatetimetext() {
        return datetimetext;
    }

    public void setDatetimeplacetext(Datetimeplacetext datetimeplacetext) {
        this.datetimeplacetext = datetimeplacetext;
    }
    public Datetimeplacetext getDatetimeplacetext() {
        return datetimeplacetext;
    }

    public void setDatetimeplacepersontext(Datetimeplacepersontext datetimeplacepersontext) {
        this.datetimeplacepersontext = datetimeplacepersontext;
    }
    public Datetimeplacepersontext getDatetimeplacepersontext() {
        return datetimeplacepersontext;
    }

    public void setRoomseattext(Roomseattext roomseattext) {
        this.roomseattext = roomseattext;
    }
    public Roomseattext getRoomseattext() {
        return roomseattext;
    }

}