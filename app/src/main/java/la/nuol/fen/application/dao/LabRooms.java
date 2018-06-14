package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LabRooms implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("lab")      private List<LabRoomDAO> lab;

    protected LabRooms(Parcel in) {
        error = in.readByte() != 0;
        lab = in.createTypedArrayList(LabRoomDAO.CREATOR);
    }

    public static final Creator<LabRooms> CREATOR = new Creator<LabRooms>() {
        @Override
        public LabRooms createFromParcel(Parcel in) {
            return new LabRooms(in);
        }

        @Override
        public LabRooms[] newArray(int size) {
            return new LabRooms[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<LabRoomDAO> getLab() {
        return lab;
    }

    public void setLab(List<LabRoomDAO> lab) {
        this.lab = lab;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(lab);
    }
}
