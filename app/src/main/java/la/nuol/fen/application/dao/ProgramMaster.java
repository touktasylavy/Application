package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgramMaster implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("programmaster")      private List<ProgramMasterDAO> ProgramMaster;

    protected ProgramMaster(Parcel in) {
        error = in.readByte() != 0;
        ProgramMaster = in.createTypedArrayList(ProgramMasterDAO.CREATOR);
    }

    public static final Creator<ProgramMaster> CREATOR = new Creator<ProgramMaster>() {
        @Override
        public ProgramMaster createFromParcel(Parcel in) {
            return new ProgramMaster(in);
        }

        @Override
        public ProgramMaster[] newArray(int size) {
            return new ProgramMaster[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ProgramMasterDAO> getProgramMaster() {
        return ProgramMaster;
    }

    public void setProgramMaster(List<ProgramMasterDAO> programMaster) {
        ProgramMaster = programMaster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(ProgramMaster);
    }
}
