package com.mysquar.mymusicplayer;

import android.support.v7.app.ActionBarActivity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Playlists;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	ImageView imgMusicThumbnail, imgNowPlaying;
	TextView txtSoundTitle, txtTimeStampPlaying;
	ImageButton btnPrevious, btnPlay, btnNext;
	SeekBar seekBar;
	MusicTrack[] playlist = new MusicTrack[4];
	MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
    
    public void initialize() {
    	imgMusicThumbnail = (ImageView)findViewById(R.id.imgMusicThumbnail);
    	imgNowPlaying = (ImageView)findViewById(R.id.imgNowPlaying);
    	txtSoundTitle= (TextView)findViewById(R.id.txtSoundTitle);
    	txtTimeStampPlaying = (TextView)findViewById(R.id.txtTimeStampPlaying);
    	seekBar = (SeekBar)findViewById(R.id.seekBar1);
    	btnPrevious = (ImageButton)findViewById(R.id.imgPrevious);
    	btnPlay = (ImageButton)findViewById(R.id.imgPlay);
    	btnNext = (ImageButton)findViewById(R.id.imgNext);
    	
    	playlist[0] = new MusicTrack(R.drawable.ic_launcher,R.raw.track0);
    	playlist[1] = new MusicTrack(R.drawable.ic_launcher,R.raw.track1);
    	playlist[2] = new MusicTrack(R.drawable.ic_launcher,R.raw.track2);
    	playlist[3] = new MusicTrack(R.drawable.ic_launcher,R.raw.track3);
    	
    	imgMusicThumbnail.setImageResource(playlist[0].getThumbnailURI());
    	player=MediaPlayer.create(this, playlist[0].getMp3URI());
    	player.start();
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
