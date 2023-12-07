package sudoku;
/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
   // All variables have package access
   // The numbers on the puzzle
   int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
   // The clues - isGiven (no need to guess) or need to guess
   boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

   // Constructor
   public Puzzle() {
      super();
   }

   // Generate a new puzzle given the number of cells to be guessed, which can be used
   //  to control the difficulty level.
   // This method shall set (or update) the arrays numbers and isGiven
   public void newPuzzle(Levels level) {
	   
      // I hardcode a puzzle here for illustration and testing.
      int[][] hardcodedNumbers = Games.getRandomGame();

      // Copy from hardcodedNumbers into the array "numbers"
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            numbers[row][col] = hardcodedNumbers[row][col];
         }
      }

      // Need to use input parameter cellsToGuess!
      // Hardcoded for testing, only 2 cells of "8" is NOT GIVEN
      boolean[][] hardcodedIsGiven = Games.getRandomPuzzle(level);

      // Copy from hardcodedIsGiven into array "isGiven"
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            isGiven[row][col] = hardcodedIsGiven[row][col];
         }
      }
   }

   //(For advanced students) use singleton design pattern for this class
}