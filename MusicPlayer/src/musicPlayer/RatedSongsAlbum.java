package musicPlayer;

import java.util.HashSet;
import java.util.Iterator;

public class RatedSongsAlbum extends AlbumBase {
	
	public RatedSongsAlbum(String albumName) {
		name = albumName;
		songList = new HashSet<SoundClip>();
	}

	void getSongs(Album allSongs) {
		songList.clear();
		Iterator <SoundClip> songs = allSongs.getSongs().iterator();
		while (songs.hasNext()) {
			SoundClip temp = songs.next();
			if (temp.getRating() >3) {
				songList.add(temp);
			}
		}
		
	}


}
