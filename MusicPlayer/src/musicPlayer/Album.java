package musicPlayer;

import java.util.ArrayList;

public class Album {
	private static int counter = 0;
	private int albumID;
	private int parentAlbum;
	private String name;
	public ArrayList<Integer> childrenAlbums;
	ArrayList<SoundClip> songList;
	
	
	
	
	public Album(String albumName) {
		albumID = counter;
		counter++;
		name = albumName;
		songList = new ArrayList<SoundClip>();
		childrenAlbums = new ArrayList<Integer>();
		Main.allAlbums.add(this);
	}
	
	public void setParent(Album album){
		if (album == null) {
			parentAlbum = 0;
		}
		else {
		parentAlbum = album.getID();
		}
	}
	
	public int getParent(){
		return parentAlbum;
	}
	
	public void setchildrenAlbums(Album album){
		if (!childrenAlbums.contains(album.getID())){
			childrenAlbums.add(album.getID());
		}
	}
	
	public ArrayList<Integer> getChildrenAlbums(){
		return childrenAlbums;
	}
	
	public int getID(){
		return albumID;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<SoundClip> getSongs(){
		return songList;
	}
	
	public void addSounds(SoundClip sound){
		songList.add(sound);
	}
	
	public void removeSounds(SoundClip sound) {
		int songIndex = songList.indexOf(sound);
		if (songIndex != -1) {
		songList.remove(songIndex);
		}
	}
	

	
}
