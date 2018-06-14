package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Office implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("office")      private List<OfficeDAO> office;

    protected Office(Parcel in) {
        error = in.readByte() != 0;
        office = in.createTypedArrayList(OfficeDAO.CREATOR);
    }

    public static final Creator<Office> CREATOR = new Creator<Office>() {
        @Override
        public Office createFromParcel(Parcel in) {
            return new Office(in);
        }

        @Override
        public Office[] newArray(int size) {
            return new Office[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<OfficeDAO> getOffice() {
        return office;
    }

    public void setOffice(List<OfficeDAO> office) {
        this.office = office;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(office);
    }
}
