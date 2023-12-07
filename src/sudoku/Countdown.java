package sudoku;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Countdown extends JLabel {

	private static final long serialVersionUID = 1L;
	private static String time;
	private static Countdown instance;
	private static int counter;


	public static int getCounter() {
		return counter;
	}
	public static String getTime() {
		return time;
	}
	public void setTime(String time) {
		super.setText(time);
	}
	private Countdown() {
		super("00:00:00");
		counter = 0;
	}
	
	public static Countdown getInstance() {
		if(instance == null) {
			instance = new Countdown();
		}
		return instance;
	}
	private Timer t = new Timer(1000, new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
		  counter++; 
          int hours = counter / 3600;
          int minutes = (counter % 3600) / 60;
          int seconds = counter % 60;
          time = String.format("%02d:%02d:%02d", hours, minutes, seconds);                 
          setTime(time);
		}
	});	
	
	public void startTimer() {
		t.start();
	}
	
	public void pararTimer() {
		t.stop();
	}
	
	public void stopTimer() {
		t.stop();
		setTime("00:00:00");
		this.counter = 0;
		
	}
}
