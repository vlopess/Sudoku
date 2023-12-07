package sudoku;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
/**
 * The Cell class model the cells of the Sudoku puzzle, by customizing (subclass)
 * the javax.swing.JTextField to include row/column, puzzle number and status.
 */
public class Cell extends JTextField {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

// Define named constants for JTextField's colors and fonts
   //  to be chosen based on CellStatus
   public static final Color BG_GIVEN = new Color(240, 240, 240); // RGB
   public static final Color FG_GIVEN = Color.BLACK;
   public static final Color FG_NOT_GIVEN = Color.GRAY;
   public static final Color BG_TO_GUESS  = new Color(187, 255, 255);
   public static final Color FG_TO_GUESS  = new Color(50, 90, 175);
   public static final Color BG_CORRECT_GUESS = new Color(0, 216, 0);
   public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
   public static final Font FONT_NUMBERS = new Font("OCR A Extended", Font.PLAIN, 28);

   // Define properties (package-visible)
   /** The row and column number [0-8] of this cell */
   int row, col;
   /** The puzzle number [1-9] for this cell */
   int number;
   /** The status of this cell defined in enum CellStatus */
   CellStatus status;
   /** Para identificar que grupo a célula faz parte*/
   int group;
   
   private static int qtd_erros;
   
	public int getErros() {
		return Cell.qtd_erros;
	}
	public static void resetErros() {
		Cell.qtd_erros = 0;
		Error.getInstance().updateErros(0);
	}

   /** Constructor */
   public Cell(int row, int col) {
      super();   // JTextField
      setBorder(null);
      this.row = row;
      this.col = col;
      // Inherited from JTextField: Beautify all the cells once for all
      super.setHorizontalAlignment(JTextField.CENTER);
      super.setFont(FONT_NUMBERS);
   }

   /** Reset this cell for a new game, given the puzzle number and isGiven */
   public void newGame(int number, boolean isGiven) {
      this.number = number;
      status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
      paint();    // paint itself
   }

   /** This Cell (JTextField) paints itself based on its status */
   public void paint() {
      if (status == CellStatus.GIVEN) { 
         // Inherited from JTextField: Set display properties
         super.setText(number + "");
         super.setEditable(false);
         super.setBackground(BG_GIVEN);
         super.setForeground(FG_GIVEN);
      } else if (status == CellStatus.TO_GUESS) {
         // Inherited from JTextField: Set display properties
         super.setText("");
         super.setEditable(true);
         super.setForeground(FG_GIVEN);
         super.setBackground(BG_TO_GUESS);
         super.setForeground(FG_NOT_GIVEN);
      } else if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
         //super.setBackground(BG_CORRECT_GUESS);
     	 MusicPlayer.playSong(Song.CORRECTSOUND);
         super.setForeground(FG_TO_GUESS);
         super.setEditable(false);
      } else if (status == CellStatus.WRONG_GUESS) {
    	 // from TO_GUESS
    	 MusicPlayer.playSong(Song.ERRORSOUND);
    	 Cell.qtd_erros++;
    	 Error.getInstance().updateErros(Cell.qtd_erros);
         super.setForeground(BG_WRONG_GUESS);
    
      }
   }

   @Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", number=" + number + ", status=" + status + "]";
	}

}