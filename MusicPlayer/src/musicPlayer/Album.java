package musicPlayer;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Album extends AlbumBase {
	private ArrayList<Album> childrenAlbums;
	
	public Album(String albumName) {
		name = albumName;
		songList = new HashSet<SoundClip>();
		childrenAlbums = new ArrayList<Album>();
	}
	
	public void setchildrenAlbums(Album album){
		if (!childrenAlbums.contains(album)){
			childrenAlbums.add(album);
		}
	}
	
	public ArrayList<Album> getChildrenAlbums(){
		return childrenAlbums;
	}
	
	public void addToAlbum(SoundClip sound){
		{
			songList.add(sound);
		}
	}
	
	public void removeFromAlbum(SoundClip sound){		//Tar bort en l�t fr�n albumet
//		sound.albumList.remove(this);
		songList.remove(sound);
		if (getChildrenAlbums().size()>0) {		//Tar bort l�ten �ven fr�n alla children album
			ArrayList<Album> subAlbums = new ArrayList<Album>();
			subAlbums = getAllChildren(this, subAlbums);
				for (int j = 0;j<subAlbums.size();j++){
					subAlbums.get(j).songList.remove(sound);
				}
			}
			
		}

	public ArrayList<Album> getAllChildren(Album album, ArrayList<Album> subAlbums) {
		if (album.getChildrenAlbums().size()>0){
			for(int i = 0;i<album.getChildrenAlbums().size();i++){
				subAlbums.add(album.getChildrenAlbums().get(i));
				getAllChildren(album.getChildrenAlbums().get(i), subAlbums);
		}
	}
		return subAlbums;
	}
	

	
}
