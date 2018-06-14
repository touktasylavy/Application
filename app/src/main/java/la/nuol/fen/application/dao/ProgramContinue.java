package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgramContinue implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("continue")      private List<ProgramContinueDAO> Continue;

    protected ProgramContinue(Parcel in) {
        error = in.readByte() != 0;
        Continue = in.createTypedArrayList(ProgramContinueDAO.CREATOR);
    }

    public static final Creator<ProgramContinue> CREATOR = new Creator<ProgramContinue>() {
        @Override
        public ProgramContinue createFromParcel(Parcel in) {
            return new ProgramContinue(in);
        }

        @Override
        public ProgramContinue[] newArray(int size) {
            return new ProgramContinue[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ProgramContinueDAO> getContinue() {
        return Continue;
    }

    public void setContinue(List<ProgramContinueDAO> aContinue) {
        Continue = aContinue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(Continue);
    }
}
