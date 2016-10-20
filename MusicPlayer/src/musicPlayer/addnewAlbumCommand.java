package musicPlayer;

import javax.swing.JOptionPane;

public class addnewAlbumCommand implements Command {
	
	Album album;
	MusicOrganizerWindow window;
	
	public addnewAlbumCommand(Album albumInput, MusicOrganizerWindow view) {
		album = albumInput;
		window = view;
	}

	@Override
	public void execute() {
try {
		
			album.setParent((Album) window.getSelectedAlbum());
			((Album) window.getSelectedAlbum()).setchildrenAlbums(album);
			window.onAlbumAdded(album);
			
			//save information
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Please select folder");
			
			
			return;
		}
	}
	
	@Override
	public void undo() {
		window.onAlbumRemoved(album);
	}
	
	
	
	
	

}
