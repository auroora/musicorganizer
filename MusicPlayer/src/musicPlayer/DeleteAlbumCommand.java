package musicPlayer;

public class DeleteAlbumCommand implements Command {
	
	Album album;
	MusicOrganizerWindow window;
	
	public DeleteAlbumCommand(Object object, MusicOrganizerWindow view) {
		album = (Album) object;
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
