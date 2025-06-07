package sudoku;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public abstract class StandardColor {
	
	private static final Color standard = new Color(187, 222, 251);
	
	public static Border getTop() {
		return BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(187, 222, 251));
	}
	public static Border getLeft() {
		return BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(187, 222, 251));
	}
	public static Border getBottom() {
		return BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(187, 222, 251));
	}
	public static Border getRight() {
		return BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(187, 222, 251));
	}
}