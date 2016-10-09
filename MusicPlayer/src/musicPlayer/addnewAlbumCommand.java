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
		
			album=new Album(MusicOrganizerController.view.promptForAlbumName());
			album.setParent(MusicOrganizerController.view.getSelectedAlbum());
			window.getSelectedAlbum().setchildrenAlbums(album);
			window.onAlbumAdded(album);
			
			//save information
//			saveHistory(album);
//			System.out.println("added album "+MusicOrganizerButtonPanel.addnewAlbumCommand);
//			MusicOrganizerController.addToCommandHistory(MusicOrganizerButtonPanel.addnewAlbumCommand);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Please select folder");
			
			
			return;
		}
	}
	
	@Override
	public void undo() {
//		MusicOrganizerButtonPanel.deleteAlbumCommand.saveHistory(view.getSelectedAlbum());
//		MusicOrganizerController.addToCommandHistory(MusicOrganizerButtonPanel.deleteAlbumCommand);
		window.onAlbumRemoved(album);
	}
	
	
	
	
	

}
