package musicPlayer;

import java.util.HashSet;
import java.util.Iterator;

public class FlaggedSongsAlbum extends AlbumBase {
	
	public FlaggedSongsAlbum(String albumName) {
		name = albumName;
		songList = new HashSet<SoundClip>();
	}


	void getSongs(Album allSongs) {
		songList.clear();
		Iterator <SoundClip> songs = allSongs.getSongs().iterator();
		while (songs.hasNext()) {
			SoundClip temp = songs.next();
			if (temp.isFlagged() == true) {
				songList.add(temp);
			}
		}
		
	}


}
