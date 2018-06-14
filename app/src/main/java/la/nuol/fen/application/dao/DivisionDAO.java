package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DivisionDAO implements Parcelable {

    @SerializedName("id")                   private String id;
    @SerializedName("shortname")            private String shortName;
    @SerializedName("fname")                private String fullName;
    @SerializedName("telephone")            private String telephone;
    @SerializedName("email")                private String email;
    @SerializedName("building")             private String building;

    protected DivisionDAO(Parcel in) {
        id = in.readString();
        shortName = in.readString();
        fullName = in.readString();
        telephone = in.readString();
        email = in.readString();
        building = in.readString();
    }

    public static final Creator<DivisionDAO> CREATOR = new Creator<DivisionDAO>() {
        @Override
        public DivisionDAO createFromParcel(Parcel in) {
            return new DivisionDAO(in);
        }

        @Override
        public DivisionDAO[] newArray(int size) {
            return new DivisionDAO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(shortName);
        dest.writeString(fullName);
        dest.writeString(telephone);
        dest.writeString(email);
        dest.writeString(building);
    }
}
