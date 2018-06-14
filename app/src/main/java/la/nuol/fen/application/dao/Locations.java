package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Locations implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("location")      private List<LocationDAO> location;

    protected Locations(Parcel in) {
        error = in.readByte() != 0;
        location = in.createTypedArrayList(LocationDAO.CREATOR);
    }

    public static final Creator<Locations> CREATOR = new Creator<Locations>() {
        @Override
        public Locations createFromParcel(Parcel in) {
            return new Locations(in);
        }

        @Override
        public Locations[] newArray(int size) {
            return new Locations[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<LocationDAO> getLocation() {
        return location;
    }

    public void setLocation(List<LocationDAO> location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(location);
    }
}
