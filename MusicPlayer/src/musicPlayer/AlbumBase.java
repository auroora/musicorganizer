package musicPlayer;

import java.util.HashSet;

public abstract class AlbumBase {
	
	protected Album parentAlbum;
	protected String name;
	protected HashSet<SoundClip> songList;
	
	public String getName(){
		return name;
	}
	
	public HashSet<SoundClip> getSongs(){
		return songList;
	}
	
	public void setParent(Album albumBase){
		parentAlbum = albumBase;
	}
	
	public Album getParent(){
		return parentAlbum;
	}
	
	@Override
	public String toString() {
	    return this.name;
	}
	

}
