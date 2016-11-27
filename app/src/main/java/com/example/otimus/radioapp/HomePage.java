package com.example.otimus.radioapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {

                // Handle action bar actions click
                super.onOptionsItemSelected(item);
                switch (item.getItemId()){

               case R.id.addstation:
                   // Toast.makeText(this, "Option pressed= facebook",Toast.LENGTH_LONG).show();
                   //startActivity(new Intent(this,ListActivity.class));

                   final Dialog dialog = new Dialog(HomePage.this);
                   dialog.setContentView(R.layout.fab_dialog);
                   dialog.setTitle("Add new Station");



                   Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);
                   // if button is clicked, close the custom dialog
                   dialogButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();
                       }
                   });
                   dialog.show();

                }

            return true;
            }


    private ImageView buttonPlay;
    private ImageView buttonStopPlay;
    private ImageView buttonPrevious;
    private ImageView buttonNext;
    private FloatingActionButton fab_icon;

    private MediaPlayer player;
    CoordinatorLayout coordinatorLayout;
    ActionBar actionBar;
    String urls[]={"",
            "http://broadcast.radiokantipur.com:7248/stream",
            "http://ujyaalo-stream.softnep.com:7710",
            "http://49.236.212.220:88/broadwave.mp3",
            "http://streaming.softnep.net:8037",
            "http://198.27.83.198:5098/stream",
            "http://182.50.64.136:89/broadwave.mp3",
            "http://live.itechnepal.com:8530/stream?type=.mp3",
            "http://live.itech.host:8790/stream"
    };
    TextView home_name, home_id;
    ImageView home_image;
    public int i=1;
    ItemStation itemStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        home_name=(TextView)findViewById(R.id.home_name);
        home_id=(TextView)findViewById(R.id.home_frequency);
        home_image=(ImageView)findViewById(R.id.home_image) ;
        fab_icon=(FloatingActionButton)findViewById(R.id.fab_icon);
        fab_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,ListActivity.class));

            }

        });

        actionBar= getActionBar();
        getSupportActionBar();
        initializeUIElements();
        try{
            Intent intent=getIntent();
            itemStation= (ItemStation) intent.getSerializableExtra("data");
            i=itemStation.getId();


        }catch (Exception e){

        }
        if(i==1){
            home_name.setText("Kantipur");
            home_id.setText(String.valueOf("96.1"));
            home_image.setImageResource(R.drawable.kantipur);
        }else{
            home_name.setText(itemStation.getName());
            home_id.setText(String.valueOf(itemStation.getFrequency()));
            home_image.setImageResource(itemStation.getImage());
            Log.d("name",itemStation.getName());
        }

        initializeMediaPlayer();

    }

    private void initializeUIElements() {
        buttonPlay = (ImageView) findViewById(R.id.btn_play);
        buttonPlay.setOnClickListener(this);
        buttonStopPlay = (ImageView) findViewById(R.id.btn_stop);
        buttonStopPlay.setEnabled(false);
        buttonStopPlay.setOnClickListener(this);
//        buttonPrevious = (ImageView) findViewById(R.id.previous);
//        buttonNext = (ImageView) findViewById(R.id.next);
//
//        buttonPrevious.setOnClickListener(this);
//        buttonNext.setOnClickListener(this);

    buttonStopPlay.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorGray));
    coordinatorLayout= (CoordinatorLayout) findViewById(R.id.coordinator);
}


    @Override
    public void onClick(View view) {
        if (view == buttonPlay) {
            startPlaying();
        } else if(view == buttonStopPlay) {
            stopPlaying();
        }
//        else if(view== buttonNext && i<8){
//            stopPlaying();
//
//
//        }else if(view==buttonPrevious && i>1){

//        }
    }

    private void startPlaying() {

        buttonStopPlay.setEnabled(true);
        buttonPlay.setEnabled(false);
        buttonStopPlay.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));

        buttonPlay.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorGray));


        try {
            player.prepareAsync();
        }catch (Exception e)
        {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Please wait", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }


        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });

    }

    private void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }


        buttonPlay.setEnabled(true);
        buttonStopPlay.setEnabled(false);
        buttonPlay.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
        buttonStopPlay.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorGray));



    }
    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource(urls[i]);
            Log.d("url",String.valueOf(urls[i]));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isPlaying()) {
            player.stop();
        }
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        moveTaskToBack(true);
                    }
                }).setNegativeButton("No", null).show();
    }
}
