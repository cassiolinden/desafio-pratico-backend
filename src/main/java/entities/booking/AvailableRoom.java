package entities.booking;

public class AvailableRoom {
    private int roomid;

    // Constructors
    public AvailableRoom() {}

    public AvailableRoom(int roomid) {
        this.roomid = roomid;
    }

    // Getters and Setters
    public int getRoomid() { return roomid; }
    public void setRoomid(int roomid) { this.roomid = roomid; }

    @Override
    public String toString() {
        return "AvailableRoom{" +
                "roomid=" + roomid +
                '}';
    }
}