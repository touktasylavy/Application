package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgramBachelor implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("bachelor")      private List<ProgramBachelorDAO> Bachelor;

    protected ProgramBachelor(Parcel in) {
        error = in.readByte() != 0;
        Bachelor = in.createTypedArrayList(ProgramBachelorDAO.CREATOR);
    }

    public static final Creator<ProgramBachelor> CREATOR = new Creator<ProgramBachelor>() {
        @Override
        public ProgramBachelor createFromParcel(Parcel in) {
            return new ProgramBachelor(in);
        }

        @Override
        public ProgramBachelor[] newArray(int size) {
            return new ProgramBachelor[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ProgramBachelorDAO> getBachelor() {
        return Bachelor;
    }

    public void setBachelor(List<ProgramBachelorDAO> bachelor) {
        Bachelor = bachelor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(Bachelor);
    }
}
