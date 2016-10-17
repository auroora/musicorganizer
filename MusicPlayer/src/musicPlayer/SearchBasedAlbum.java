package musicPlayer;

import java.util.HashSet;

public abstract class SearchBasedAlbum {
	
	public abstract String toString();
	abstract void getSounds(Album album);
	abstract void setParent(Album album);
	abstract Album getParent();
	

}
