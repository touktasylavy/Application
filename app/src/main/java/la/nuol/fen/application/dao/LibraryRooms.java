package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LibraryRooms implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("library")      private List<LibraryDAO> library;

    protected LibraryRooms(Parcel in) {
        error = in.readByte() != 0;
        library = in.createTypedArrayList(LibraryDAO.CREATOR);
    }

    public static final Creator<LibraryRooms> CREATOR = new Creator<LibraryRooms>() {
        @Override
        public LibraryRooms createFromParcel(Parcel in) {
            return new LibraryRooms(in);
        }

        @Override
        public LibraryRooms[] newArray(int size) {
            return new LibraryRooms[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<LibraryDAO> getLibrary() {
        return library;
    }

    public void setLibrary(List<LibraryDAO> library) {
        this.library = library;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(library);
    }
}
