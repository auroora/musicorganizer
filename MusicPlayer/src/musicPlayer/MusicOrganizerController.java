package musicPlayer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class MusicOrganizerController {

	public static MusicOrganizerWindow view;//changed from private
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album allSoundClips;
	private FlaggedSongsAlbum flaggedSongs;
	private RatedSongsAlbum greatSongs;
	CommandHistory commandHistory;
	
	public MusicOrganizerController() {
		
		// TODO: Create the root album for all sound clips
		
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		flaggedSongs = new FlaggedSongsAlbum ("Flagged Sound Clips");
		flaggedSongs.setParent(root);
		view.onAlbumAdded(flaggedSongs);
		greatSongs = new RatedSongsAlbum ("Great Sound Clips");
		greatSongs.setParent(root);
		view.onAlbumAdded(greatSongs);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		commandHistory = new CommandHistory();
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album
		for(SoundClip c:clips)
			root.addToAlbum(c);
		System.out.println("root size "+ clips.size());
		return clips;
	}
	
	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	//Return the search based albums
	
	public FlaggedSongsAlbum getFlaggedAlbum() {
		return flaggedSongs;
	}
	
	public RatedSongsAlbum getRatedAlbum() {
		return greatSongs;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){
		//TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		String name = view.promptForAlbumName();
		Album newAlbum = new Album(name);
		addnewAlbumCommand addnewAlbum = new addnewAlbumCommand(newAlbum, view);
		try {
			
			addnewAlbum.execute();
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Please select folder");
			
			
			return;
		}
		commandHistory.setUndoCommand(addnewAlbum);
		
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){
		//TODO Update parameters if needed
		// TODO: Add your code here
		DeleteAlbumCommand deleteAlbum = new DeleteAlbumCommand(view.getSelectedAlbum(), view);
		deleteAlbum.execute();
		commandHistory.setUndoCommand(deleteAlbum);
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(Album album){ //TODO Update parameters if needed
		// TODO: Add your code here
		ArrayList<SoundClip> sounds = (ArrayList<SoundClip>) view.getSelectedSoundClips();
		AddSoundClipsCommand addSoundClips = new AddSoundClipsCommand(view, album, this, sounds);
		addSoundClips.execute();
		commandHistory.setUndoCommand(addSoundClips);
		
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(Album album){ //TODO Update parameters if needed
		// TODO: Add your code here
		ArrayList<SoundClip> sounds = (ArrayList<SoundClip>) view.getSelectedSoundClips();
		RemoveSoundClipsCommand removeSoundClips = new RemoveSoundClipsCommand(view, album, sounds);
		removeSoundClips.execute();
		commandHistory.setUndoCommand(removeSoundClips);
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
	}

	public void undo() {
		commandHistory.undoPressed();
	}

	public void redo() {
		commandHistory.redoPressed();
	}

	public void flag() {
		// TODO Auto-generated method stub
		ArrayList<SoundClip> sounds = (ArrayList<SoundClip>) view.getSelectedSoundClips();
		for(SoundClip i:sounds){
			if(!i.flagged)i.setFlagged(true);
			else i.setFlagged(false);
		}
		flaggedSongs.getSongs(root);
	}

	public void rate(Integer rating) {
		// TODO Auto-generated method stub
		ArrayList<SoundClip> sounds = (ArrayList<SoundClip>) view.getSelectedSoundClips();
		for(SoundClip i:sounds)i.setRating(rating);
		greatSongs.getSongs(root);
	}
}
