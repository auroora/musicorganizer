package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import musicPlayer.Album;
import musicPlayer.Main;
import musicPlayer.Organizer;
import musicPlayer.SoundClip;

public class AlbumTest {
	
	private Album a;
	private Album b;
	private Organizer o = new Organizer();
	private SoundClip file =new SoundClip(new File("song.waw"));


	@Test
	public void testRemoveSounds() {
		a = new Album("myAlbum");
		o.addToAlbum(a, file);
		a.removeSounds(file);
		assertFalse(a.getSongs().contains(file));
	}

}
