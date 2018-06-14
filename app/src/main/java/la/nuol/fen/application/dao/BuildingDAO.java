package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BuildingDAO implements Parcelable {

    @SerializedName("id")                   private String id;
    @SerializedName("building_name")        private String buildingName;
    @SerializedName("location")             private String location;
    @SerializedName("latitude")             private String latitude;
    @SerializedName("longtitude")           private String longtitude;

    protected BuildingDAO(Parcel in) {
        id = in.readString();
        buildingName = in.readString();
        location = in.readString();
        latitude = in.readString();
        longtitude = in.readString();
    }

    public static final Creator<BuildingDAO> CREATOR = new Creator<BuildingDAO>() {
        @Override
        public BuildingDAO createFromParcel(Parcel in) {
            return new BuildingDAO(in);
        }

        @Override
        public BuildingDAO[] newArray(int size) {
            return new BuildingDAO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(buildingName);
        dest.writeString(location);
        dest.writeString(latitude);
        dest.writeString(longtitude);
    }
}
