package musicPlayer;

import java.util.ArrayList;

public class RemoveSoundClipsCommand implements Command {
	
	MusicOrganizerWindow window;
	Album album;
	MusicOrganizerController controller;
	ArrayList<SoundClip> sounds;
	
	public RemoveSoundClipsCommand(MusicOrganizerWindow view, Album albumInput, ArrayList<SoundClip> selectedSounds){
	window = view;
	album = albumInput;
	sounds = selectedSounds;
	}

	@Override
	public void execute() {
		if (album.getChildrenAlbums().size()>0) {
			ArrayList<Album> subAlbums = new ArrayList<Album>();
			subAlbums = album.getAllChildren(album, subAlbums);
			for (int i=0;i<subAlbums.size();i++){
				for (int j=0;j<sounds.size();j++){
				subAlbums.get(i).removeFromAlbum(sounds.get(j));
				}
				}
		}
		
		for (int i=0;i<window.getSelectedSoundClips().size();i++){
			album.removeFromAlbum(sounds.get(i));
		}
		
		window.onClipsUpdated();
		
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		addSoundClips(album);
	}
	
	public void addSoundClips(Album album) {
		for (int i=0;i<sounds.size();i++){
			if (!album.getParent().getSongs().contains(sounds.get(i))) {
				album.getParent().addToAlbum(sounds.get(i));
			}
		album.addToAlbum(sounds.get(i));
	}
	if (!album.getParent().equals(controller.getRootAlbum())) {
	addSoundClips(album.getParent());
	}
	}

}
