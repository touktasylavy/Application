package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LabRoomDAO implements Parcelable {

    @SerializedName("id")                   private String id;
    @SerializedName("room_name")            private String roomName;
    @SerializedName("floor")                private String floor;
    @SerializedName("building")             private String building;
    @SerializedName("roomtype")             private String roomType;

    protected LabRoomDAO(Parcel in) {
        id = in.readString();
        roomName = in.readString();
        floor = in.readString();
        building = in.readString();
        roomType = in.readString();
    }

    public static final Creator<LabRoomDAO> CREATOR = new Creator<LabRoomDAO>() {
        @Override
        public LabRoomDAO createFromParcel(Parcel in) {
            return new LabRoomDAO(in);
        }

        @Override
        public LabRoomDAO[] newArray(int size) {
            return new LabRoomDAO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(roomName);
        dest.writeString(floor);
        dest.writeString(building);
        dest.writeString(roomType);
    }
}
