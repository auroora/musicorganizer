package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import musicPlayer.Album;
import musicPlayer.Main;
import musicPlayer.Organizer;
import musicPlayer.SoundClip;

public class OrganizerTest {
	
	Organizer o = new Organizer();
	private Album c;
	private Album a;
	private Album b;
	private SoundClip file;

	@Test
	public void testAddToAlbum() {
		a = new Album("myAlbum");
		file = new SoundClip(new File("song.waw"));
		o.addToAlbum(a, file);
		assertTrue(a.getSongs().contains(file));
	}

	@Test
	public void testRemoveFromAlbum() {
		a = new Album("myAlbum");
		file = new SoundClip(new File("song.waw"));
		o.addToAlbum(a, file);
		o.removeFromAlbum(a, file);
		assertFalse(a.getSongs().contains(file));
		
	}

	@Test
	public void testDeleteAlbum() {
		c = new Album ("All Songs");
		a = new Album("myAlbum");
		b = new Album("Album2");
		file = new SoundClip(new File("song.waw"));
		o.addToAlbum(c, file);
		o.addToAlbum(b, file);
		o.addToAlbum(a, file);
		Main.allSongs.addSounds(file);
		Main.allAlbums.add(a);
		Main.allAlbums.add(b);
		a.setchildrenAlbums(b);
		b.setParent(a);
		Main.allAlbums.add(a);
		o.deleteAlbum(a);
		assertTrue(Main.allAlbums.contains(a));
	}

}
