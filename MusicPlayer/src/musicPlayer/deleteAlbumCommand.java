package musicPlayer;


public class deleteAlbumCommand implements Command {
	public Album album;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		MusicOrganizerController.deleteAlbum();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		MusicOrganizerController.view.onAlbumAdded(album);
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		MusicOrganizerController.view.onAlbumRemoved(album);
	}
	
	public void saveHistory(Album album){
		this.album=album;
	}
}
