package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProgramBachelorDAO implements Parcelable {

    @SerializedName("id")                   private String id;
    @SerializedName("programme_name")       private String programmeName;
    @SerializedName("period")               private String period;
    @SerializedName("department_name")      private String departmentName;

    protected ProgramBachelorDAO(Parcel in) {
        id = in.readString();
        programmeName = in.readString();
        period = in.readString();
        departmentName = in.readString();
    }

    public static final Creator<ProgramBachelorDAO> CREATOR = new Creator<ProgramBachelorDAO>() {
        @Override
        public ProgramBachelorDAO createFromParcel(Parcel in) {
            return new ProgramBachelorDAO(in);
        }

        @Override
        public ProgramBachelorDAO[] newArray(int size) {
            return new ProgramBachelorDAO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(programmeName);
        dest.writeString(period);
        dest.writeString(departmentName);
    }
}
