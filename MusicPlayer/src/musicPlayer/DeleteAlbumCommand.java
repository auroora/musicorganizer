package musicPlayer;

public class DeleteAlbumCommand implements Command {
	
	Album album;
	MusicOrganizerWindow window;
//	MusicOrganizerController controller = new MusicOrganizerController();
//	MusicOrganizerWindow view = new MusicOrganizerWindow(controller);
	
	public DeleteAlbumCommand(Album albumInput, MusicOrganizerWindow view) {
		album = albumInput;
		window = view;
	}

	@Override
	public void execute() {
		
		window.onAlbumRemoved(album);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		window.onAlbumAdded(album);
		
	}

}
