package sg.edu.rp.c346.id22043453.week08problem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {

        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowSong = convertView;

        if (rowSong == null) {
            LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowSong = inflater.inflate(R.layout.row, parent, false);
        }


        TextView textViewSongTitle = rowSong.findViewById(R.id.tvSongTitle);
        TextView textViewSongYear = rowSong.findViewById(R.id.tvSongYear);
        TextView textViewSongRating = rowSong.findViewById(R.id.tvSongRating);
        TextView textViewSongSinger = rowSong.findViewById(R.id.tvSongSinger);

        Song currentSong = songList.get(position);

        String[] songDetails = new String[] {
                "Title: " + currentSong.getTitle(),
                "Singers: " + currentSong.getSingers(),
                "Year Released: " + currentSong.getYear(),
                "Stars: " + getSongStarRating(currentSong.getStars())
        };


        textViewSongTitle.setText(currentSong.getTitle());
        textViewSongYear.setText(String.valueOf(currentSong.getYear()));
        textViewSongRating.setText(getSongStarRating(currentSong.getStars()));
        textViewSongSinger.setText(currentSong.getSingers());

        return rowSong;

    }

    private String getSongStarRating (int songStars) {
        switch (songStars) {
            case 1:
                return "⭐";
            case 2:
                return "⭐⭐";
            case 3:
                return "⭐⭐⭐";
            case 4:
                return "⭐⭐⭐⭐";
            case 5:
                return "⭐⭐⭐⭐⭐";
            default:
                return "Nothing";
        }
    }


}
