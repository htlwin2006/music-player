package com.example.testmusic;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	String[] Slist={"Track1","Track2","Track3","Track4"};

	ImageButton btnNext,btnPrevious;
	ToggleButton btnPlay;
	ImageView sicon,artists;
	TextView stitle,showtime;
	SeekBar seek_bar;
	MediaPlayer player;
	Handler seekHandler=new Handler();
	double finaltime,timescape;
	int i;
	private static final int NUM_PAGES=5;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnNext=(ImageButton)findViewById(R.id.imgN);
		btnPrevious=(ImageButton)findViewById(R.id.imgP);
		btnPlay=(ToggleButton)findViewById(R.id.imgS);
		sicon=(ImageView)findViewById(R.id.imgIcon);
		artists=(ImageView)findViewById(R.id.imgArtists);
		stitle=(TextView)findViewById(R.id.txtTitile);
		seek_bar=(SeekBar)findViewById(R.id.seekBar1);
		showtime=(TextView)findViewById(R.id.songCurrentDurationLabel);
		//player=MediaPlayer.create(this, R.drawable.);
		player=MediaPlayer.create(this, R.raw.track0);
		seek_bar.setMax(player.getDuration());
		
		finaltime=player.getDuration();
		
		seekUpdation();
		seek_bar.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				seekChange(arg0);
				return false;
			}
		});
		//mPager=(ViewPager)findViewById(R.id.pager);
		//mPagerAdapter=new ScreenSlidePagerAdapter(getSupportFragmentManager());
		//mPager.setAdapter(mPagerAdapter);	
		
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				i++;
				ChangeSong();
				if(i>4)
				btnNext.setEnabled(false);
			}
		});
		
		btnPrevious.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i--;
				ChangeSong();
				
			}
		});
		btnPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (btnPlay.isChecked()) { // Checked - Pause icon visible
					player.start();
		        } else { // Unchecked - Play icon visible
		            player.pause();
		        }
				
				
			}
		});
	}
	Runnable run= new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			seekUpdation();
			timescape=player.getCurrentPosition();
			double timeRemaining = finaltime - timescape;
			showtime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
			
			//durationHandler.postDelayed(this, 100);
		}
	};
	public void seekChange(View v)
	{
		if(player.isPlaying()){
			SeekBar sb=(SeekBar)v;
			player.seekTo(sb.getProgress());
		}
	}
	public void seekUpdation(){
		seek_bar.setProgress(player.getCurrentPosition());
		seekHandler.postDelayed(run, 1000);
	}
	public void ChangeSong()
	{
		player.reset();
		if(i>=0 && i<4)
		{
			
		Resources res=this.getResources();
		int id=res.getIdentifier("track"+i , "drawable","com.example.testmusic");
		 sicon.setImageResource(id);
		 sicon.refreshDrawableState();
		 sicon.invalidate();
		 
		 artists.setImageResource(id);
		artists.refreshDrawableState();
		artists.invalidate();
		 stitle.setText(Slist[i]);
		 int soundid=res.getIdentifier("track"+i, "raw", "com.example.testmusic");
		 player=MediaPlayer.create(this, soundid);
		 player.start();
		 if(i<0)
				btnPrevious.setEnabled(false);
		 
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		super.onDestroy();
		player.stop();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
