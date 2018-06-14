package la.nuol.fen.application.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsDAO implements Parcelable {

    @SerializedName("id")                   private String id;
    @SerializedName("news_title")           private String newsTitle;
    @SerializedName("datepost")             private String datePost;
    @SerializedName("image")                private String image;
    @SerializedName("description")          private String description;
    @SerializedName("news_type")          private String newsType;
    @SerializedName("posted_by")            private String postedBy;

    protected NewsDAO(Parcel in) {
        id = in.readString();
        newsTitle = in.readString();
        datePost = in.readString();
        image = in.readString();
        description = in.readString();
        newsType = in.readString();
        postedBy = in.readString();
    }

    public static final Creator<NewsDAO> CREATOR = new Creator<NewsDAO>() {
        @Override
        public NewsDAO createFromParcel(Parcel in) {
            return new NewsDAO(in);
        }

        @Override
        public NewsDAO[] newArray(int size) {
            return new NewsDAO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(newsTitle);
        dest.writeString(datePost);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(newsType);
        dest.writeString(postedBy);
    }
}
