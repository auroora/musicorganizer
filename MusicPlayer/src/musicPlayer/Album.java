package musicPlayer;


import java.util.ArrayList;

public class Album {
	private static int counter = 0;
	private int albumID;
	private Album parentAlbum;
	private String name;
	public ArrayList<Album> childrenAlbums;
	ArrayList<SoundClip> songList;
	
	
	
	
	public Album(String albumName) {
		albumID = counter;
		counter++;
		name = albumName;
		songList = new ArrayList<SoundClip>();
		childrenAlbums = new ArrayList<Album>();
		Main.allAlbums.add(this);
	}
	
	public void setParent(Album album){
		if (album == null) {
			parentAlbum = null;
		}
		else {
		parentAlbum = album;
		}
	}
	
	public Album getParent(){
		return parentAlbum;
	}
	
	public void setchildrenAlbums(Album album){
		if (!childrenAlbums.contains(album)){
			childrenAlbums.add(album);
		}
	}
	
	public ArrayList<Album> getChildrenAlbums(){
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
