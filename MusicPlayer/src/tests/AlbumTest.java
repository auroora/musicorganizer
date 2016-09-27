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
	private SoundClip file;


	@Test
	public void testRemoveSounds() {
		file = new SoundClip(new File("song.waw"));
		a = new Album("myAlbum");
		a.addToAlbum(file);
		a.removeFromAlbum(file);
		assertFalse(a.getSongs().contains(file));
	}
	
	@Test
	public void testAddToAlbum() {
		a = new Album("myAlbum");
		file = new SoundClip(new File("song.waw"));
		a.addToAlbum(file);
		assertTrue(a.getSongs().contains(file));
	}

	@Test
	public void testRemoveFromAlbum() {
		a = new Album("myAlbum");
		file = new SoundClip(new File("song.waw"));
		a.addToAlbum(file);
		a.removeFromAlbum(file);
		assertFalse(a.getSongs().contains(file));
		
	}

//	@Test
//	public void testDeleteAlbum() {
//		a = new Album("myAlbum");
//		b = new Album("Album2");
//		file = new SoundClip(new File("song.waw"));
//		b.addToAlbum(file);
//		a.addToAlbum(file);
//		Main.allSongs.addToAlbum(file);
//		Main.allAlbums.add(a);
//		Main.allAlbums.add(b);
//		a.setchildrenAlbums(b);
//		b.setParent(a);
//		a.setParent(null);
//		Main.allAlbums.add(a);
//		a.deleteAlbum();
//		assertTrue(Main.allAlbums.contains(a));
//	}

}
