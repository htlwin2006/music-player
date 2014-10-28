package com.mysquar.mymusicplayer;

import android.content.res.Resources;

public class MusicTrack {
	private int mp3URI;
	private int thumbnailURI;
	
	public MusicTrack(int thumbnailURI, int mp3URI) {
		this.thumbnailURI = thumbnailURI;
		this.mp3URI = mp3URI;
	}
	
	public int getMp3URI() {
		return this.mp3URI;
	}
	
	public int getThumbnailURI() {
		return this.thumbnailURI;
	}
	public void setMp3URI(int mp3uri) {
		mp3URI = mp3uri;
	}
	public void setThumbnailURI(int thumbnailURI) {
		this.thumbnailURI = thumbnailURI;
	}
}
