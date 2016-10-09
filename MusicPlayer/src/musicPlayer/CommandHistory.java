package musicPlayer;

public class CommandHistory {
	Command[] undoCommands;
	Command[] redoCommands;
//	Command undoCommand
	Command noCommand = new NoCommand();
	static int currentSlot = 0;
	static int currentSlot2 = 0;
	
	public CommandHistory() {
		undoCommands = new Command[10];
		redoCommands = new Command[10];
//	Command noCommand = new NoCommand();
	for(int i=0;i<10;i++){
		undoCommands[i] = noCommand;
		redoCommands[i] = noCommand;
		}
	}
	
	public void setUndoCommand(Command command) {
		boolean emptySlots = false;
		for(int i = 0;i<10;i++) {
			if (undoCommands[i].equals(noCommand)) {
				emptySlots = true;
			}
			else {
				emptySlots = false;
			}
		}
		if (emptySlots = true) {
			undoCommands[currentSlot] = command;
			if (currentSlot <8) {
			currentSlot = currentSlot++;
			}
			else {
				currentSlot = 9;
			}
		}
		else {
			for (int i = 0;i<9;i++) {
				undoCommands[i] = undoCommands[i+1];
			}
			undoCommands[9] = command;
		}
		
	}
	
	public void setRedoCommand(Command command) {
		boolean emptySlots = false;
		for(int i = 0;i<10;i++) {
			if (undoCommands[i].equals(noCommand)) {
				emptySlots = true;
			}
			else {
				emptySlots = false;
			}
		}
		if (emptySlots = true) {
			undoCommands[currentSlot2] = command;
			if (currentSlot2 <8) {
			currentSlot2 = currentSlot2++;
			}
			else {
				currentSlot2 = 9;
			}
		}
		else {
			for (int i = 0;i<9;i++) {
				undoCommands[i] = undoCommands[i+1];
			}
			undoCommands[9] = command;
		}
		
	}
	
	public void undoPressed(){
		undoCommands[currentSlot].undo();
		setRedoCommand(undoCommands[currentSlot]);
		undoCommands[currentSlot] = noCommand;
		if(currentSlot>0) {
		currentSlot = currentSlot -1;
		}
		else{
			currentSlot = 0;
		}
	}
	
	public void redoPressed(){
		redoCommands[currentSlot2].execute();
		setUndoCommand(redoCommands[currentSlot2]);
		redoCommands[currentSlot2] = noCommand;
		if(currentSlot>0) {
		currentSlot = currentSlot -1;
		}
		else{
			currentSlot = 0;
		}
	}
	
}
