package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LocationDAO implements Parcelable {

    @SerializedName("id")                   private String id;
    @SerializedName("location_name")        private String locationName;
    @SerializedName("Latitude")             private String latitude;
    @SerializedName("Longtitude")           private String longtitude;

    protected LocationDAO(Parcel in) {
        id = in.readString();
        locationName = in.readString();
        latitude = in.readString();
        longtitude = in.readString();
    }

    public static final Creator<LocationDAO> CREATOR = new Creator<LocationDAO>() {
        @Override
        public LocationDAO createFromParcel(Parcel in) {
            return new LocationDAO(in);
        }

        @Override
        public LocationDAO[] newArray(int size) {
            return new LocationDAO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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
        dest.writeString(locationName);
        dest.writeString(latitude);
        dest.writeString(longtitude);
    }
}
