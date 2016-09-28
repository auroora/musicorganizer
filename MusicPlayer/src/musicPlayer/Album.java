package musicPlayer;


import java.util.ArrayList;

public class Album {
	private Album parentAlbum;
	private String name;
	public ArrayList<Album> childrenAlbums;
	ArrayList<SoundClip> songList;
	
	
	
	
	public Album(String albumName) {
		name = albumName;
		songList = new ArrayList<SoundClip>();
		childrenAlbums = new ArrayList<Album>();
		Main.allAlbums.add(this);
	}
	
	public void setParent(Album album){
		parentAlbum = album;
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
	
	public String getName(){
		return name;
	}
	
	public ArrayList<SoundClip> getSongs(){
		return songList;
	}
	
	public void addToAlbum(SoundClip sound){
		{
//			sound.albumList.add(this);
			songList.add(sound);
		}
	}
	
	public void removeFromAlbum(SoundClip sound){		//Tar bort en l�t fr�n albumet
//		sound.albumList.remove(this);
		int songIndex = songList.indexOf(sound);
		if (songIndex != -1) {
		songList.remove(songIndex);
		}
		if (getChildrenAlbums().size()>0) {		//Tar bort l�ten �ven fr�n alla children album
			ArrayList<Album> subAlbums = new ArrayList<Album>();
			subAlbums = getAllChildren(this, subAlbums);
//				if (sound.albumList.contains(getChildrenAlbums().get(i))) {
//					sound.albumList.remove(getChildrenAlbums().get(i));
//				}
				for (int j = 0;j<subAlbums.size();j++){
					subAlbums.get(j).songList.remove(sound);
				}
			}
			
		}
	
	public void deleteAlbum() {			//Tar bort ett helt album
		ArrayList<Album> subAlbums = new ArrayList<Album>();
		subAlbums = getAllChildren(this, subAlbums);
		for(int i = 0;i<songList.size();i++){
			removeFromAlbum(songList.get(i));
			if (subAlbums.size()>0) {
			for (int j = 0;j<Main.allAlbums.size();j++){		//Tar bort alla children albums fr�n listan med alla album
				for (int k = 0; k<subAlbums.size();k++){
					Main.allAlbums.remove(subAlbums.get(k));
				}
			}
			}
		}
		if (getParent() != null) {
		getParent().getChildrenAlbums().remove(this);
			Main.allAlbums.remove(this);
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
	
	public String toString(){
		return name;
		
	}
	
}
