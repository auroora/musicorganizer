package musicPlayer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MusicOrganizerButtonPanel extends JPanel {

	private MusicOrganizerController controller;
	private MusicOrganizerWindow view;
	
	private JButton newAlbumButton;
	private JButton deleteAlbumButton;
	private JButton addSoundClipsButton;
	private JButton removeSoundClipsButton;	
	private JButton playButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton flagButton;
	private JComboBox<String> ratingList;

	
	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view){
		super(new BorderLayout());

		controller = contr;
		
		this.view = view;
		
		JToolBar toolbar = new JToolBar();
		
		newAlbumButton = createNewAlbumButton();
		toolbar.add(newAlbumButton);

		deleteAlbumButton = createDeleteAlbumButton();
		toolbar.add(deleteAlbumButton);

		addSoundClipsButton = createAddSoundClipsButton();
		toolbar.add(addSoundClipsButton);

		removeSoundClipsButton = createRemoveSoundClipsButton();
		toolbar.add(removeSoundClipsButton);

		playButton = createPlayButton();
		toolbar.add(playButton);
		
		undoButton = createUndoButton();
		toolbar.add(undoButton);
		
		redoButton = createRedoButton();
		toolbar.add(redoButton);
		
		flagButton = createFlagButton();
		toolbar.add(flagButton);
		
		ratingList = createRatingList();
		toolbar.add(ratingList);
		
		this.add(toolbar);

	}
	
	public JButton getNewAlbumButton() {
		return newAlbumButton;
	}


	public JButton getDeleteAlbumButton() {
		return deleteAlbumButton;
	}


	public JButton getAddSoundClipsButton() {
		return addSoundClipsButton;
	}


	public JButton getRemoveSoundClipsButton() {
		return removeSoundClipsButton;
	}
	

	/**
	 * Note: You can replace the text strings in the instantiations of the JButtons
	 * below with ImageIcons if you prefer to have buttons with icons instead of
	 * buttons with text strings
	 * 
	 *  Example:
	 *  ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
	 *  JButton newAlbumButton = new JButton(newAlbumIcon);
	 *  
	 *  will put the imageIcon on the button, instead of the text "New Album", as 
	 *  done below
	 * 
	 */
	
	private JButton createNewAlbumButton() {
		//ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton("New Album");
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addNewAlbum();
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		//ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton("Remove Album");
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteAlbum();
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
		//ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
		JButton addSoundClipButton = new JButton("Add Sound Clips");
		addSoundClipButton.setToolTipText("Add Selected Sound Clips To Selected Album");
		addSoundClipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				controller.addSoundClips((Album) view.getSelectedAlbum());

			}
		});
		return addSoundClipButton;
	}
	
	private JButton createRemoveSoundClipsButton() {
		//ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton("Remove Sound Clips");
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeSoundClips((Album) view.getSelectedAlbum());
			}
		});
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		//ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton("Play");
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
			}
		});
		return playButton;
	}
	
	private JButton createUndoButton() {
		//ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton undoButton = new JButton("Undo");
		undoButton.setToolTipText("Undo last edit");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
//				controller.undoPressed(slot);
			}
		});
		return undoButton;
	}
	
	private JButton createRedoButton() {
		//ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton redoButton = new JButton("Redo");
		redoButton.setToolTipText("Redo last edit");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		return redoButton;
	}
	private JButton createFlagButton(){
		JButton flagButton = new JButton("Flag/Unflag"); 
		flagButton.setToolTipText("Flag selected music");
		flagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.flag();
				view.onClipsUpdated();
			}
		});
		return flagButton;
	}

	private JComboBox<String> createRatingList() {
		// TODO Auto-generated method stub
		String[] ratingListIntegers = {"Set rating","*","**","***","****","*****"};
		JComboBox<String> ratingList = new JComboBox<String>(ratingListIntegers);
		ratingList.setSelectedIndex(0);
		ratingList.setToolTipText("Select rating");
		ratingList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rate((Integer)ratingList.getSelectedIndex());
				view.onClipsUpdated();
				ratingList.setSelectedIndex(0);
			}
		});
		return ratingList;
	}
}
