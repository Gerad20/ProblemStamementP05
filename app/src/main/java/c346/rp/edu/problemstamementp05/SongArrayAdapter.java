package c346.rp.edu.problemstamementp05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongArrayAdapter extends ArrayAdapter {
    private ArrayList<Song> song;
    private Context context;
    private TextView tvDisplayYear;
    private TextView tvDisplaytitle;
    private TextView tvDisplaySinger;
    private ImageView displayedSongImage;



    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;

    public SongArrayAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);
        song = objects;
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        tvDisplayYear = (TextView)rowView.findViewById(R.id.tvYear);
        tvDisplaytitle = (TextView)rowView.findViewById(R.id.tvSongName);
        tvDisplaySinger = (TextView) rowView.findViewById(R.id.tvSingername);
        displayedSongImage = (ImageView)rowView.findViewById(R.id.imageView) ;
        displayedSongImage.setImageResource(R.drawable.ic_library_music);

        Song songObj = song.get(position);

        tvDisplayYear.setText(songObj.getYear() + "");
        tvDisplaytitle.setText(songObj.getTitle());
        tvDisplaySinger.setText(songObj.getSinger() + "");
        iv1 = rowView.findViewById(R.id.imageView1star);
        iv2 = rowView.findViewById(R.id.imageView2star);
        iv3 = rowView.findViewById(R.id.imageView3star);
        iv4 = rowView.findViewById(R.id.imageView4star);
        iv5 = rowView.findViewById(R.id.imageView5star);
        int rating =  songObj.getStars();
        if(rating >= 1){
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
            if(rating >= 2){
                iv2.setImageResource(android.R.drawable.btn_star_big_on);
             if(rating >= 3){
                 iv3.setImageResource(android.R.drawable.btn_star_big_on);
                 if(rating >= 4){
                     iv4.setImageResource(android.R.drawable.btn_star_big_on);
                     if(rating == 5){
                         iv5.setImageResource(android.R.drawable.btn_star_big_on);
                     }

                 }
             }
            }
        }

        return rowView;

    }
}