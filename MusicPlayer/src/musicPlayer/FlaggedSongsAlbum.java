package musicPlayer;

import java.util.Iterator;

public class FlaggedSongsAlbum extends Album {
	
	public FlaggedSongsAlbum(String albumName) {
		super(albumName);
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
