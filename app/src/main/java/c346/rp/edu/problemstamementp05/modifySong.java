package c346.rp.edu.problemstamementp05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class modifySong extends AppCompatActivity {
    TextView tvID;
    EditText etSongName;
    EditText etSingerName;
    EditText etYear;
    RadioGroup ratingss;
    RadioButton r1,r2,r3,r4,r5;
    Song song;

    Button update;
    Button delete;
    RadioGroup ratings;
    RadioButton chosenRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_song);
        tvID = findViewById(R.id.id);
        etSongName = findViewById(R.id.songName);
        etSingerName = findViewById(R.id.singerName);
        etYear = findViewById(R.id.year);
        ratings = findViewById(R.id.ratings);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);
        r4 = findViewById(R.id.radioButton4);
        r5 = findViewById(R.id.radioButton5);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("data");
        tvID.setText(song.get_id() + "");
        etSongName.setText(song.getTitle());
        etSingerName.setText(song.getSinger());
        etYear.setText(song.getYear() + "");
        if(song.getStars() == 1){
            r1.setChecked(true);
        }else if (song.getStars() == 2){
            r2.setChecked(true);
        } else if (song.getStars() == 3){
            r3.setChecked(true);
        } else if (song.getStars() == 4){
            r4.setChecked(true);
        }else if (song.getStars() == 5){
            r5.setChecked(true);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfSong = etSongName.getText().toString();
                String nameOfSinger = etSingerName.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                //for the star value
                int chosentratingId = ratings.getCheckedRadioButtonId();
                chosenRating = (RadioButton)findViewById(chosentratingId);


                int valueOfRating = Integer.parseInt(chosenRating.getText().toString());
                Song newObj = new Song(nameOfSong,nameOfSinger,year,valueOfRating);
                newObj.set_id(song.get_id());
                DBHelper dbh = new DBHelper(modifySong.this);
                dbh.updateSong(newObj);
                dbh.close();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(modifySong.this);
                dbh.deleteSong(song.get_id());


            }
        });

    }
}
