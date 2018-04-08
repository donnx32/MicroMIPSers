package model;

import javax.swing.table.AbstractTableModel;

public class OpCodeModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private Object[][] data;

	public OpCodeModel() {
		initializeDefaultModel();
	}

	public void initializeDefaultModel() {
		System.out.println("Initializing Default OpCode model...");

		header = new String[] { "Instruction", "B: 31-26", "B: 25-21", "B: 20-16", "B: 15-11", "B: 10-6", "B: 5-0",
				"Hex" };
		data = new Object[][] {};

		for (int row = 0; row < getRowCount(); row++) {
			for (int col = 0; col < getColumnCount(); col++)
				fireTableCellUpdated(row, col);
		}
	}

	public String getColumnName(int col) {
		return header[col];
	}

	public int getColumnCount() {
		return header.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
