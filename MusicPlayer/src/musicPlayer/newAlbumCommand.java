package musicPlayer;

public class newAlbumCommand implements Command {
	public  Album album;
//	public newAlbumCommand(Album album) {
//		// TODO Auto-generated constructor stub
//		this.album=album;
//	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		MusicOrganizerController.addNewAlbum();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		MusicOrganizerController.view.onAlbumRemoved(album);
	}


	@Override
	public void redo() {
		// TODO Auto-generated method stub
		MusicOrganizerController.view.onAlbumAdded(album);
	}
	
	public void saveHistory(Album album){
		this.album=album;
	}
	
	public String toString(){
		return album.toString();
		
	}
}
