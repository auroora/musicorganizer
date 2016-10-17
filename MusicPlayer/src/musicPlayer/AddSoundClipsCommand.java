package musicPlayer;

import java.util.ArrayList;

public class AddSoundClipsCommand implements Command {
	
	MusicOrganizerWindow window;
	Album album;
	MusicOrganizerController controller;
	ArrayList<SoundClip> sounds;
	
	public AddSoundClipsCommand(MusicOrganizerWindow view, Album albumInput, MusicOrganizerController controllerInput, ArrayList<SoundClip> selectedSounds){
	window = view;
	album = albumInput;
	controller = controllerInput;
	sounds = selectedSounds;
	}

	@Override
	public void execute() {
		addSoundClips(album);
		window.onClipsUpdated();
		
		
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

	@Override
	public void undo() {
		if (album.getChildrenAlbums().size()>0) {
			ArrayList<Album> subAlbums = new ArrayList<Album>();
			subAlbums = album.getAllChildren(album, subAlbums);
			for (int i=0;i<subAlbums.size();i++){
				for (int j=0;j<sounds.size();j++){
				subAlbums.get(i).removeFromAlbum(sounds.get(j));
				}
				}
		}
		
		for (int i=0;i<sounds.size();i++){
			album.removeFromAlbum(sounds.get(i));
		}
		
		window.onClipsUpdated();
		
	}

}
