package musicPlayer;

public class CommandHistory {
	Command[] undoCommands;
	Command[] redoCommands;
	Command noCommand = new NoCommand();
	static int currentSlot;
	static int currentSlot2;
	
	public CommandHistory() {
		undoCommands = new Command[10];
		redoCommands = new Command[10];
	for(int i=0;i<10;i++){
		undoCommands[i] = noCommand;
		redoCommands[i] = noCommand;
		}
	currentSlot = 0;
	currentSlot2 = 0;
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
			currentSlot = currentSlot+1;
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
		System.out.println("Current slot: " + currentSlot);
		
	}
	
	public void setRedoCommand(Command command) {
		boolean emptySlots = false;
		for(int i = 0;i<10;i++) {
			if (redoCommands[i].equals(noCommand)) {
				emptySlots = true;
			}
			else {
				emptySlots = false;
			}
		}
		if (emptySlots = true) {
			redoCommands[currentSlot2] = command;
			if (currentSlot2 <8) {
			currentSlot2 = currentSlot2+1;
			}
			else {
				currentSlot2 = 9;
			}
		}
		else {
			for (int i = 0;i<9;i++) {
				redoCommands[i] = redoCommands[i+1];
			}
			redoCommands[9] = command;
		}
		
	}
	
	public void undoPressed(){
		if (currentSlot == 0) {
			return;
		}
		undoCommands[currentSlot-1].undo();
		setRedoCommand(undoCommands[currentSlot-1]);
		undoCommands[currentSlot-1] = noCommand;
		if(currentSlot>0) {
		currentSlot = currentSlot -1;
		}
		else{
			currentSlot = 0;
		}
	}
	
	public void redoPressed(){
		if (currentSlot2 == 0) {
			return;
		}
		redoCommands[currentSlot2-1].execute();
		setUndoCommand(redoCommands[currentSlot2-1]);
		redoCommands[currentSlot2-1] = noCommand;
		if(currentSlot2>0) {
		currentSlot2 = currentSlot2 -1;
		}
		else{
			currentSlot2 = 0;
		}
	}
	
}
