package musicPlayer;

import javax.swing.JOptionPane;

public class addnewAlbumCommand implements Command {
	
	Album album;
	MusicOrganizerController controller = new MusicOrganizerController();
	MusicOrganizerWindow view = new MusicOrganizerWindow(controller);
	
	public addnewAlbumCommand(Album albumInput) {
		album = albumInput;
	}

	@Override
	public void execute() {
try {
		
			album=new Album(view.promptForAlbumName());
			album.setParent(view.getSelectedAlbum());
			view.getSelectedAlbum().setchildrenAlbums(album);
			view.onAlbumAdded(album);
			
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
		view.onAlbumRemoved(album);
	}
	
	
	
	
	

}
