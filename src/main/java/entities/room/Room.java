package entities.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreType
public class Room {
    private int roomid;
    private String roomName;
    private String type;
    private boolean accessible;
    private String image;
    private String description;
    private String[] features;
    private int roomPrice;
    private List<Room> rooms;

    // Constructors
    public Room() {}

    public Room(int roomid, String roomName, String type, boolean accessible, String image,
                String description, String[] features, int roomPrice) {
        this.roomid = roomid;
        this.roomName = roomName;
        this.type = type;
        this.accessible = accessible;
        this.image = image;
        this.description = description;
        this.features = features;
        this.roomPrice = roomPrice;
    }

    // Getters and Setters
    public int getRoomid() { return roomid; }
    public void setRoomid(int roomid) { this.roomid = roomid; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isAccessible() { return accessible; }
    public void setAccessible(boolean accessible) { this.accessible = accessible; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String[] getFeatures() { return features; }
    public void setFeatures(String[] features) { this.features = features; }

    public int getRoomPrice() { return roomPrice; }
    public Room setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
        return this;
    }
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomid=" + roomid +
                ", roomName='" + roomName + '\'' +
                ", type='" + type + '\'' +
                ", accessible=" + accessible +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", features=" + Arrays.toString(features) +
                ", roomPrice=" + roomPrice +
                '}';
    }
}