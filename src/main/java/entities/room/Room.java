package entities.room;

public class Room {
    private int roomid;
    private String roomName;
    private String type;
    private boolean accessible;
    private String image;
    private String description;
    private String[] features;
    private int roomPrice;

    // Constructors
    public Room() {}

    public Room(String roomName, String type, boolean accessible, String image, 
                String description, String[] features, int roomPrice) {
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
    public void setRoomPrice(int roomPrice) { this.roomPrice = roomPrice; }
}