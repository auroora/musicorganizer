package musicPlayer;


import java.io.File;
import java.util.ArrayList;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {
	public boolean flagged;
	public int rating;
	private final File file;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	public String toString(){
		return file.getName()+" rating: "+this.rating;
	}
	
	@Override
	public boolean equals(Object obj) {
		return 
			obj instanceof SoundClip
			&& ((SoundClip)obj).file.equals(file);
	}
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}
	

}
