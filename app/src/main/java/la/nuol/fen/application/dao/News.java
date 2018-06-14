package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News implements Parcelable {

    @SerializedName("error")         private boolean error;
    @SerializedName("news")      private List<NewsDAO> news;

    protected News(Parcel in) {
        error = in.readByte() != 0;
        news = in.createTypedArrayList(NewsDAO.CREATOR);
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<NewsDAO> getNews() {
        return news;
    }

    public void setNews(List<NewsDAO> news) {
        this.news = news;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeTypedList(news);
    }
}
