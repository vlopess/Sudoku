package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.MatteBorder;

public class GameBoardPanel extends JPanel {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for UI sizes
   public static final int CELL_SIZE = 50;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;
                                             // Board width/height in pixels

   // Define properties
   /** The game board composes of 9x9 Cells (customized JTextFields) */
   private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
   /** It also contains a Puzzle with array numbers and isGiven */
   private Puzzle puzzle = new Puzzle();

   /** Constructor */
   public GameBoardPanel() {
      super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel  

      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            cells[row][col].group = setGroup(row, col);
            cells[row][col].setBorder(Borda.getStandardBorder());
            if(row == 2 || row == 5) {           
                cells[row][col].setBorder(Borda.getRowBorder());            
            }
            if(col == 3 || col == 6) {
            	if(row == 2 || row == 5) {
                    cells[row][col].setBorder(Borda.getCornerBorder());            
            	}else {
                    cells[row][col].setBorder(Borda.getColumnBorder());            
            	}
            }

            super.add(cells[row][col]);  
            // JPanel
         }
         
      }

      // [TODO 3] Allocate a common listener as the ActionEvent listener for all the
      //  Cells (JTextFields)
      // .........
      CellInputListener listener = new CellInputListener();

      // [TODO 4] Adds this common listener to all editable cells
      // .........
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
          for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
        	  if (cells[row][col].isEditable()) {
        	     cells[row][col].addActionListener(listener);   // For all editable rows and cols
        	  }  
          }
       }

      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }

   private int setGroup(int row, int col) {
	   	if(col < 3) {
	   		if(row < 3) return Group.NORTHWEST; 
	   		if(row < 6) return Group.WEST;
	   		return Group.SOUTHWEST;
	   	}else if(col < 6) {
	   		if(row < 3) return Group.NORTH; 
	   		if(row < 6) return Group.CENTER;
	   		return Group.SOUTH;
	   	}else {
	   		if(row < 3) return Group.NORTHEAST; 
	   		if(row < 6) return Group.EAST;
	   		return Group.SOUTHEAST;
	   	}
   }

/**
    * Generate a new puzzle; and reset the gameboard of cells based on the puzzle.
    * You can call this method to start a new game.
    */
   public void newGame(Levels l) {
      // Generate a new puzzle
      puzzle.newPuzzle(l);

      // Initialize all the 9x9 cells, based on the puzzle.
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
         }
      }
   }

   /**
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
    */
   public boolean isSolved() {
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }
   
   public void clearCells() {
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            if (cells[row][col].getBackground() == Color.RED) {
            	cells[row][col].setBackground(new Color(240, 240, 240));  
            }
         }
      }
   }
   
   public void verificarCells(Cell cell, int numberIn) {
	   int col = cell.col;
	   int row = cell.row;
	   int linha = cell.group / 10;
	   int coluna = cell.group % 10;
	   for (int c = 0; c < SudokuConstants.GRID_SIZE; ++c) {
           if (cells[c][col].number == numberIn && cells[c][col].status != CellStatus.TO_GUESS) {
        	   cells[c][col].setBackground(Color.RED);
           }           
       }
	   for (int i = 0; i < SudokuConstants.GRID_SIZE; ++i) {
	       if (cells[row][i].number == numberIn && cells[row][i].status != CellStatus.TO_GUESS) {
	    	   cells[row][i].setBackground(Color.RED);
	       }
	   }
	   for (int l = linha; l < linha + 3; ++l) {
		   for (int c = coluna; c < coluna + 3; ++c) {
			   if (cells[l][c].number == numberIn && cells[l][c].status != CellStatus.TO_GUESS) {
				   cells[l][c].setBackground(Color.RED);
			   }
		   }
	   }
   }
	   
   
   public static void newGame() {
	   
   }
   // [TODO 2] Define a Listener Inner Class for all the editable Cells
   // .........
   private class CellInputListener implements ActionListener {
	   @Override
	   public void actionPerformed(ActionEvent e) {
	      // Get a reference of the JTextField that triggers this action event
		 Cell sourceCell = (Cell)e.getSource();
	 	 try {
		 	 
		 	 // Retrieve the int entered
	 		 String numberInput = sourceCell.getText();
	 		 if(numberInput.length() > 1 || numberInput.equals("0"))  throw new NumberFormatException();
		 	 int numberIn = Integer.parseInt(numberInput);
		 	 // For debugging
		 	 //System.out.println(numberIn + " == " + sourceCell.number + " ?");
		 	clearCells();
		 	 if (numberIn == sourceCell.number) {
		 		if(sourceCell.status != CellStatus.GIVEN) sourceCell.status = CellStatus.CORRECT_GUESS;
		 	 } else {
		 		sourceCell.status = CellStatus.WRONG_GUESS; 		 		
		 		// here
		 		verificarCells(sourceCell, numberIn);
		 	 }
		 	 sourceCell.paint();   

		 	 if(isSolved()) {
		    	MusicPlayer.playSong(Song.WINNERSOUND);
		 		Countdown.getInstance().stopTimer();		 		 
		 		Object[] options = {"Sair do Jogo",
				"Novo Jogo"};
		 		int reply = JOptionPane.showOptionDialog(null,
		 				"Tempo : "+Countdown.getTime()+"\nVocê venceu!", "Jogo Finalizado",JOptionPane.YES_NO_OPTION,
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,     //do not use a custom Icon
	                    options,  //the titles of buttons
	                    options[0]);
	            if (reply == JOptionPane.YES_OPTION)
	                System.exit(0);
	            //NO_OPTION
	            if (reply == JOptionPane.NO_OPTION) {
	            	SudokuMain.getInstance().initGame(false);
	            	MusicPlayer.restartSong();
	            }
	            Cell.resetErros();
		 	 }
	 	 }catch (NumberFormatException nf) {
	 		 JOptionPane.showMessageDialog(null, "Digite Corretamente!");
	 	 }
	   }
	 }
}