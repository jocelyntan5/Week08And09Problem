package sg.edu.rp.c346.id22043453.week08problem;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }



@NonNull
    @Override
    public String toString() {
        return id + "\n" + title + "\n" + singers + "\n" + year + "\n" + stars;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }
 public void setTitle(String title) {this.title=title;}

    public void setSinger(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStar(int stars) {
        this.stars = stars;
    }
}
