package c346.rp.edu.problemstamementp05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowSong extends AppCompatActivity {
    ListView songList;

    ArrayList<Song> al = new ArrayList<>();
    ArrayList<String> YearArray = new ArrayList<>(Arrays.asList(""));
    SongArrayAdapter aa;
    ArrayAdapter spinnerAdapter;
    Button Show5Stars;
    Spinner spinnerYearDisplay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_song);
        songList = findViewById(R.id.songList);
        Show5Stars = findViewById(R.id.Show5Star);
        spinnerYearDisplay = findViewById(R.id.yearSpinner);
        DBHelper dbh = new DBHelper(ShowSong.this);
        al.clear();
        al.addAll(dbh.getAllSongs(""));
        for(int i =0;i < al.size(); i++){
            YearArray.add(al.get(i).getYear() + "");
        }
        //spinnerValues
         spinnerAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,YearArray);
        spinnerYearDisplay.setAdapter(spinnerAdapter);
        dbh.close();
        ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
        songList.setAdapter(aa);
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Song songObj = al.get(position);

                Intent i = new Intent(ShowSong.this,
                        modifySong.class);

                i.putExtra("data", songObj);
                startActivityForResult(i, 9);

            }
        });
        spinnerYearDisplay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String Chosenyear = spinnerYearDisplay.getSelectedItem().toString();
                DBHelper dbh = new DBHelper(ShowSong.this);
                al.clear();
                al.addAll(dbh.getAllSongsForyear(Chosenyear));
                dbh.close();
                ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
                songList.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        Show5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ShowSong.this);
                al.clear();
                al.addAll(dbh.getAllSongs("5"));
                dbh.close();
                ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
                songList.setAdapter(aa);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(ShowSong.this);
            al.clear();
            al.addAll(dbh.getAllSongs(""));
            dbh.close();
            ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
            songList.setAdapter(aa);
            for(int i =0;i < al.size(); i++){
                YearArray.add(al.get(i).getYear() + "");
            }
            //spinnerValues
            spinnerAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,YearArray);
            spinnerYearDisplay.setAdapter(spinnerAdapter);
        }
    }

}
