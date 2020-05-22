package c346.rp.edu.problemstamementp05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText songName;
    EditText singerName;
    EditText yearReleased;

    RadioGroup ratings;
    RadioButton chosenRating;
    Button Insert;
    Button Show;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songName = findViewById(R.id.songName);
        singerName = findViewById(R.id.singerName);
        yearReleased = findViewById(R.id.year);
        ratings = findViewById(R.id.ratings);
        Insert = findViewById(R.id.btnInsert);
        Show = findViewById(R.id.btnShowList);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chosentratingId = ratings.getCheckedRadioButtonId();
                chosenRating = (RadioButton)findViewById(chosentratingId);


                String nameOfSong = songName.getText().toString();
                String nameOfSinger = singerName.getText().toString();
                int year = Integer.parseInt(yearReleased.getText().toString());
                int valueOfRating = Integer.parseInt(chosenRating.getText().toString());

                DBHelper dbh = new DBHelper(getApplicationContext());
                dbh.addSong(nameOfSong,nameOfSinger,year,valueOfRating);
                Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_LONG).show();



            }
        });
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ShowSong.class);
                startActivity(i);
            }
        });





    }
}
