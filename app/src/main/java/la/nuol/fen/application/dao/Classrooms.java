package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classrooms implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("classroom")      private List<ClassroomDAO> classRoom;

    protected Classrooms(Parcel in) {
        error = in.readByte() != 0;
        classRoom = in.createTypedArrayList(ClassroomDAO.CREATOR);
    }

    public static final Creator<Classrooms> CREATOR = new Creator<Classrooms>() {
        @Override
        public Classrooms createFromParcel(Parcel in) {
            return new Classrooms(in);
        }

        @Override
        public Classrooms[] newArray(int size) {
            return new Classrooms[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ClassroomDAO> getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(List<ClassroomDAO> classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(classRoom);
    }
}
