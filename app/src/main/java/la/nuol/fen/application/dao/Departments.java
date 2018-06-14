package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Departments implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("departbuild")      private List<DepartmentDAO> departBuild;

    protected Departments(Parcel in) {
        error = in.readByte() != 0;
        departBuild = in.createTypedArrayList(DepartmentDAO.CREATOR);
    }

    public static final Creator<Departments> CREATOR = new Creator<Departments>() {
        @Override
        public Departments createFromParcel(Parcel in) {
            return new Departments(in);
        }

        @Override
        public Departments[] newArray(int size) {
            return new Departments[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DepartmentDAO> getDepartBuild() {
        return departBuild;
    }

    public void setDepartBuild(List<DepartmentDAO> departBuild) {
        this.departBuild = departBuild;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(departBuild);
    }
}
