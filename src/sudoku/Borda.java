package sudoku;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public abstract class Borda {
    
    private static final Border borderTop = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK);
    private static final Border borderLeft = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
    private static final Border borderBottom = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
    private static final Border borderRight = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
    
	public static Border getBordertop() {
		return borderTop;
	}

	public static Border getBorderleft() {
		return borderLeft;
	}

	public static Border getBorderbottom() {
		return borderBottom;
	}

	public static Border getBorderright() {
		return borderRight;
	}

	public static Border getStandardBorder() {		
	    Border patrao = new CompoundBorder(StandardColor.getTop(), StandardColor.getBottom());
	    patrao = new CompoundBorder(patrao, StandardColor.getLeft());
	    patrao = new CompoundBorder(patrao, StandardColor.getRight());
	    return patrao;
	}
	
	public static Border getRowBorder() {		
		Border rowborder = new CompoundBorder(StandardColor.getTop(), borderBottom);
		rowborder = new CompoundBorder(rowborder, StandardColor.getLeft());
		rowborder = new CompoundBorder(rowborder, StandardColor.getRight());
		return rowborder;
	}

	public static Border getColumnBorder() {	    
	    Border colunmborder = new CompoundBorder(StandardColor.getTop(), StandardColor.getBottom());
	    colunmborder = new CompoundBorder(colunmborder, borderLeft);
	    return colunmborder;
	}
    
	public static Border getCornerBorder() {		
		Border corner = new CompoundBorder(StandardColor.getTop(), borderBottom);
		corner = new CompoundBorder(corner, borderLeft);  
		return corner;
	}

}


