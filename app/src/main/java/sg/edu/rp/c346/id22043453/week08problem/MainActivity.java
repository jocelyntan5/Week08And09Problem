package sg.edu.rp.c346.id22043453.week08problem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    RadioButton rbStars1, rbStars2, rbStars3, rbStars4, rbStars5;
    Button btnInsert;
    Button btnShowList;
    ListView lvSong;
    ArrayAdapter<Song> adapter;
    DBHelper DBHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.editTextSongTitle);
        etSingers = findViewById(R.id.editTextSingers); // Corrected variable name
        etYear = findViewById(R.id.editTextYear);
        rgStars = findViewById(R.id.radioGroupStars);
        rbStars1 = findViewById(R.id.radioButton1);
        rbStars2 = findViewById(R.id.radioButton2);
        rbStars3 = findViewById(R.id.radioButton3);
        rbStars4 = findViewById(R.id.radioButton4);
        rbStars5 = findViewById(R.id.radioButton5);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);
        lvSong = findViewById(R.id.listViewSong);
        DBHelp = new DBHelper(this);


        Song s1 = new Song(1, "Hello", "Treasure", 2022, 5);


        ArrayList<Song> sList = new ArrayList<>();
        sList.add(s1);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create the DBHelper object, passing in the
                // activity's Context


                // Insert a task
                String title = etSongTitle.getText().toString().trim();
                String singers = etSingers.getText().toString().trim();
                String yearToString = etYear.getText().toString().trim();

                int year = Integer.parseInt(yearToString);
                int stars = getStarsSelected();



                DBHelp.insertSong(title, singers, year, stars);


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }

    private int getStarsSelected() {

        int starsId = rgStars.getCheckedRadioButtonId();

        if (starsId == R.id.radioButton1) {
            return 1;
        }
        else if (starsId == R.id.radioButton2) {
            return 2;
        }
        else if (starsId == R.id.radioButton3) {
            return 3;
        }
        else if (starsId == R.id.radioButton4) {
            return 4;
        }
        else if (starsId == R.id.radioButton5) {
            return 5;
        }

        return 0;

    }


}