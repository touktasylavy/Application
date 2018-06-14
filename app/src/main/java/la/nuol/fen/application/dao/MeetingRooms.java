package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MeetingRooms implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("meeting")      private List<MeetingRoomDAO> meeting;

    protected MeetingRooms(Parcel in) {
        error = in.readByte() != 0;
        meeting = in.createTypedArrayList(MeetingRoomDAO.CREATOR);
    }

    public static final Creator<MeetingRooms> CREATOR = new Creator<MeetingRooms>() {
        @Override
        public MeetingRooms createFromParcel(Parcel in) {
            return new MeetingRooms(in);
        }

        @Override
        public MeetingRooms[] newArray(int size) {
            return new MeetingRooms[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MeetingRoomDAO> getMeeting() {
        return meeting;
    }

    public void setMeeting(List<MeetingRoomDAO> meeting) {
        this.meeting = meeting;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(meeting);
    }
}
