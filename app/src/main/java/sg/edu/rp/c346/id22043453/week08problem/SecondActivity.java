package sg.edu.rp.c346.id22043453.week08problem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Spinner spinner;
    Button button;
    ArrayAdapter<Song> adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dbHelper = new DBHelper(this);
        lv = findViewById(R.id.lv);


        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);

        ArrayList<Song> songList = dbHelper.getSong();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song clickSong = (Song) parent.getItemAtPosition(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("song", clickSong);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Song> fivestar = dbHelper.getFiveStars();
                adapter.clear();
                adapter.addAll(fivestar);
                adapter.notifyDataSetChanged();
            }
        });
        ArrayList<Integer> spinneryears = dbHelper.getSpinnerYears();
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneryears);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int mainyear = (int) parent.getItemAtPosition(position);
                ArrayList<Song> list = dbHelper.getSongYear(mainyear);
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void onResume() {
        super.onResume();
        ArrayList<Song> songList = dbHelper.getSong();
        adapter.clear();
        adapter.addAll(songList);
        adapter.notifyDataSetChanged();
    }
}
