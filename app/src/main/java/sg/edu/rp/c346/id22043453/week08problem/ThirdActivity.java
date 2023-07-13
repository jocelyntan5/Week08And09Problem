package sg.edu.rp.c346.id22043453.week08problem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    EditText title, singer, year, star;
    Button updbtn, dtlbtn;
    Song song;
    DBHelper dbHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        title = findViewById(R.id.editTextText);
        singer = findViewById(R.id.editTextText2);
        year = findViewById(R.id.editTextText3);
        star = findViewById(R.id.editTextText4);
        updbtn = findViewById(R.id.button2);
        dtlbtn = findViewById(R.id.button3);
        dbHelper =  new DBHelper(this);

        Intent intent= getIntent();
        song = (Song) intent.getSerializableExtra("song");
        title.setText(song.getTitle());
        singer.setText(song.getSingers());
        year.setText(String.valueOf(song.getYear()));
        star.setText(String.valueOf(song.getStars()));
        updbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title1 =  title.getText().toString().trim();
                String singer1 =  singer.getText().toString().trim();
                int year1 =  Integer.parseInt(year.getText().toString().trim());
                int star1 =  Integer.parseInt(star.getText().toString().trim());
                song.setTitle(title1);
                song.setSinger(singer1);
                song.setYear(year1);
                song.setStar(star1);
                dbHelper.updateSong(song);
            }
        });
        dtlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteSong(song.getID());
                finish();
            }
        });
    }

}