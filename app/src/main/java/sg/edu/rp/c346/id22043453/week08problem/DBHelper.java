package sg.edu.rp.c346.id22043453.week08problem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "songs.db";

    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INTEGER," // Add space before INTEGER
                + COLUMN_STARS + " INTEGER)"; // Add space before INTEGER

        db.execSQL(createTableSql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);

    }

    public void insertSong(String title, String singers, int year, int stars) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();
    }


    public ArrayList<Song> getSong() {
        ArrayList<Song> songList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                //int stars = cursor.getInt(cursor.getColumnIndex(COLUMN_STARS));
                Song song = new Song(id, title, singers, year, stars);
                songList.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songList;
    }

    public ArrayList<Song> getFiveStars() {
        ArrayList<Song> songList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = COLUMN_STARS + " ?";
        String[] selectArgs = {"5"};
        Cursor cursor = db.query(TABLE_SONG, null, select, selectArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String singers = cursor.getString(cursor.getColumnIndex(COLUMN_SINGERS));
                int year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR));
                int stars = cursor.getInt(cursor.getColumnIndex(COLUMN_STARS));

                Song song = new Song(id, title, singers, year, stars);
                songList.add(song);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return songList;

    }

    public ArrayList<Integer> getSpinnerYears() {
        ArrayList<Integer> yearsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_YEAR};
        Cursor cursor = db.query(true, TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR));
                yearsList.add(year);
            }while (cursor.moveToNext());
            }
        cursor.close();
        db.close();

        return yearsList;
        }

        public ArrayList<Song> getSongYear(int year) {
        ArrayList<Song> songList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_YEAR + " ?";
        String[] selectionArgs = {String.valueOf(year)};
            Cursor cursor = db.query(TABLE_SONG,null,  selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    String singers = cursor.getString(cursor.getColumnIndex(COLUMN_SINGERS));
                    int stars = cursor.getInt(cursor.getColumnIndex(COLUMN_STARS));

                    Song song = new Song(id, title, singers, year, stars);
                    songList.add(song);
                } while (cursor.moveToNext());

            }
            cursor.close();
            db.close();
            return songList;

        }
        public void updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
            values.put(COLUMN_SINGERS, song.getSingers());
            values.put(COLUMN_YEAR, song.getYear());
            values.put(COLUMN_STARS, song.getStars());
            String selection = COLUMN_ID+ " = ?";
            String[] selectionArgs = {String.valueOf(song.getID())};
            db.update(TABLE_SONG,  values, selection, selectionArgs);
            db.close();

        }
        public void deleteSong(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String selection =  COLUMN_ID + " =?";
            String[] selectionArgs = {String.valueOf(id)};
            db.delete(TABLE_SONG, selection, selectionArgs);
            db.close();

        }

        }

