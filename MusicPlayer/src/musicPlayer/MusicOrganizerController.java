package musicPlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	
	public MusicOrganizerController() {
		
		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
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
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){ //TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		
		try {
			Album album=new Album(view.promptForAlbumName());
			album.setParent(view.getSelectedAlbum());
			view.getSelectedAlbum().setchildrenAlbums(album);
			view.onAlbumAdded(album);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Please select folder");
			return;
		}
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ //TODO Update parameters if needed
		// TODO: Add your code here
		view.onAlbumRemoved(view.getSelectedAlbum());
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(Album album){ //TODO Update parameters if needed
		// TODO: Add your code here
		for (int i=0;i<view.getSelectedSoundClips().size();i++){
				if (!album.getParent().getSongs().contains(view.getSelectedSoundClips().get(i))) {
					album.getParent().addToAlbum(view.getSelectedSoundClips().get(i));
				}
			view.getSelectedAlbum().addToAlbum(view.getSelectedSoundClips().get(i));
		}
		if (!album.getParent().equals(getRootAlbum())) {
		addSoundClips(album.getParent());
		}
		view.onClipsUpdated();
		
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(Album album){ //TODO Update parameters if needed
		// TODO: Add your code here
		if (album.getChildrenAlbums().size()>0) {
			ArrayList<Album> subAlbums = new ArrayList<Album>();
			subAlbums = album.getAllChildren(album, subAlbums);
			for (int i=0;i<subAlbums.size();i++){
				for (int j=0;j<view.getSelectedSoundClips().size();j++){
				subAlbums.get(i).removeFromAlbum(view.getSelectedSoundClips().get(j));
				}
				}
		}
		
		for (int i=0;i<view.getSelectedSoundClips().size();i++){
		view.getSelectedAlbum().removeFromAlbum(view.getSelectedSoundClips().get(i));
		}
		
		view.onClipsUpdated();
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
}
