package musicPlayer;

public class DeleteAlbumCommand implements Command {
	
	Album album;
	MusicOrganizerWindow window;
	
	public DeleteAlbumCommand(Album albumInput, MusicOrganizerWindow view) {
		album = albumInput;
		window = view;
	}

	@Override
	public void execute() {
		window.onAlbumRemoved(album);
		
	}

	@Override
	public void undo() {
		window.onAlbumAdded(album);
		
	}

}
