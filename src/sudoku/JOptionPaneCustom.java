package sudoku;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class JOptionPaneCustom {
	
	public static void showErroPane() {
		Object[] options = {"Sair do Jogo",
		"Novo Jogo"};
		int reply = JOptionPane.showOptionDialog(null,
		        "Você errou 3 vezes e perdeu o jogo", "Fim de Jogo",JOptionPane.YES_NO_OPTION,
		        JOptionPane.PLAIN_MESSAGE,
		        null,
		        options,  //the titles of buttons
		        options[0]);
		if (reply == JOptionPane.YES_OPTION)
		    System.exit(0);
		//NO_OPTION
		if (reply == JOptionPane.NO_OPTION) {
			SudokuMain.getInstance().initGame(false);
			Cell.resetErros();
		}
	}

	public static void showPausePane() {
		Object[] options = {"Sair do Jogo",
		"Novo Jogo"};
		int reply = JOptionPane.showOptionDialog(null,
		        "", "Jogo Pausado",JOptionPane.YES_NO_OPTION,
		        JOptionPane.PLAIN_MESSAGE,
		        null,     //do not use a custom Icon
		        options,  //the titles of buttons
		        options[0]);
		if (reply == JOptionPane.YES_OPTION)
		    System.exit(0);
		//NO_OPTION
		if (reply == JOptionPane.NO_OPTION) {
			SudokuMain.getInstance().initGame(false);
			Cell.resetErros();
		}
		if(reply == JOptionPane.DEFAULT_OPTION) {
			if(Countdown.getCounter() != 0)	Countdown.getInstance().startTimer();
		}
	}

	
}
