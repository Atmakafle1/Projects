package edu.gatech.seclass.gradescalc;

import javax.swing.JOptionPane;

public class GradeFormulaException extends RuntimeException {

	public GradeFormulaException(String message) {
        super(message);
	}

	public GradeFormulaException() {
		JOptionPane.showMessageDialog(null, "Fix your formula");
	}

}
