package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Buildings implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("building")      private List<BuildingDAO> building;

    protected Buildings(Parcel in) {
        error = in.readByte() != 0;
        building = in.createTypedArrayList(BuildingDAO.CREATOR);
    }

    public static final Creator<Buildings> CREATOR = new Creator<Buildings>() {
        @Override
        public Buildings createFromParcel(Parcel in) {
            return new Buildings(in);
        }

        @Override
        public Buildings[] newArray(int size) {
            return new Buildings[size];
        }
    };

    public List<BuildingDAO> getBuilding() {
        return building;
    }

    public void setBuilding(List<BuildingDAO> building) {
        this.building = building;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(building);
    }
}
