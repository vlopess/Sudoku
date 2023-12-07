package sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Error extends JLabel {

	private static final long serialVersionUID = 1L;
	private static Error instance;

	private Error() {
		updateErros(0);				
	}
	
	public static Error getInstance() {
		if(instance == null)
			instance = new Error();
		return instance;
	} 
	public void updateErros(int qtd_erros) {
		super.setText("Erros : "+qtd_erros+" / 3 ");
		if(qtd_erros > 2) {
	    	MusicPlayer.playSong(Song.FAILEDSOUND);
			Countdown.getInstance().stopTimer();
			JOptionPaneCustom.showErroPane();
		}					
	}
	
	
}
