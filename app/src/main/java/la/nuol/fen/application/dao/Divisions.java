package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Divisions implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("divisionbuild")      private List<DivisionDAO> divisionBuild;

    protected Divisions(Parcel in) {
        error = in.readByte() != 0;
        divisionBuild = in.createTypedArrayList(DivisionDAO.CREATOR);
    }

    public static final Creator<Divisions> CREATOR = new Creator<Divisions>() {
        @Override
        public Divisions createFromParcel(Parcel in) {
            return new Divisions(in);
        }

        @Override
        public Divisions[] newArray(int size) {
            return new Divisions[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DivisionDAO> getDivisionBuild() {
        return divisionBuild;
    }

    public void setDivisionBuild(List<DivisionDAO> divisionBuild) {
        this.divisionBuild = divisionBuild;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(divisionBuild);
    }
}
