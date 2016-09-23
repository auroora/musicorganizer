package musicPlayer;

import java.util.ArrayList;

public class Main {
	public static ArrayList<Album> allAlbums = new ArrayList<Album>();
	public static Album allSongs = new Album("All Sound Clips");

	public static void main (String[] args) {
		Organizer org = new Organizer();
		allSongs.setParent(null);

		
	}

}
