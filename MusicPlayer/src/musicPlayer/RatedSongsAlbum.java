package musicPlayer;

import java.util.Iterator;

public class RatedSongsAlbum extends Album{
	
	public RatedSongsAlbum(String albumName) {
		super(albumName);
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
