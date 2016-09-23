package musicPlayer;

import java.util.ArrayList;

public class Organizer {
	
	public void addToAlbum(Album album, SoundClip sound){
//		if (!albumList.contains(album.getID())) 
		{
			sound.albumList.add(album.getID());
			album.addSounds(sound);
		}
	}
	
	public void removeFromAlbum(Album album, SoundClip sound){
		int IDindex = sound.albumList.indexOf(album.getID());
		if (IDindex != -1) {
		sound.albumList.remove(IDindex);
		if (album.getChildrenAlbums().size()>0) {
			for (int i = 0;i<album.getChildrenAlbums().size();i++){
				if (sound.albumList.contains(album.getChildrenAlbums().get(i))) {
					sound.albumList.remove(album.getChildrenAlbums().get(i));
				}
				for (int j = 0;j<Main.allAlbums.size();j++){
					if (new Integer(Main.allAlbums.get(j).getParent()).equals(album.getChildrenAlbums().get(i))){
						removeFromAlbum(Main.allAlbums.get(j), sound);
					}
					if (Main.allAlbums.get(j).getSongs().isEmpty()){
						Main.allAlbums.remove(j);
					}
				}
			}
			
		}
		album.removeSounds(sound);
		}
	}
	
	public void deleteAlbum(Album album) {
//		if (album.getID() != 0){
		ArrayList<Integer> subAlbums = new ArrayList<Integer>();
		subAlbums = getAllChildren(album, subAlbums);
		for(int i = 0;i<Main.allSongs.songList.size();i++){
			removeFromAlbum(album, Main.allSongs.songList.get(i));
			for (int j = 0;j<Main.allAlbums.size();j++){
				for (int k = 0; k<subAlbums.size();k++){
					if (new Integer(Main.allAlbums.get(j).getID()).equals(subAlbums.get(k))) {
						removeFromAlbum(Main.allAlbums.get(j),Main.allSongs.songList.get(i));
					}
				}
			}
		}
			Main.allAlbums.remove(album);
//		}
	}
	

	public ArrayList<Integer> getAllChildren(Album album, ArrayList<Integer> subAlbums) {
		if (album.childrenAlbums.size()>0){
			for(int i = 0;i<album.childrenAlbums.size();i++){
				subAlbums.add(album.childrenAlbums.get(i));
				for (int j = 0;j<Main.allAlbums.size();j++){
					if (new Integer(Main.allAlbums.get(j).getParent()).equals(album.getChildrenAlbums().get(i)) && !Main.allAlbums.get(j).equals(album)){
						getAllChildren(Main.allAlbums.get(j), subAlbums);
					}
			}
		}
		}
		return subAlbums;
	}

}
