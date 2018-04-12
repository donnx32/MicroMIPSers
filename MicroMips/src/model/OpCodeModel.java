package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class OpCodeModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private ArrayList<RowData> data;

	public OpCodeModel() {
		initializeDefaultModel();
	}

	public void initializeDefaultModel() {
		System.out.println("Initializing Default OpCode model...");

		header = new String[] { "Instruction", "B: 31-26", "B: 25-21", "B: 20-16", "B: 15-11", "B: 10-6", "B: 5-0",
				"Hex" };
		data = new ArrayList<RowData>();

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
		return data.size();
	}

	public void addRow() {
		data.add(new RowData());
		fireTableRowsInserted(getRowCount(), getRowCount());
	}

	public void addRowWithData(Instruction instruct) {
		addRow();
		//System.out.println("outside " +instruct);
		setValueAt(instruct.getValue(), getRowCount() - 1, 0);
		setValueAt(instruct.getBin().substring(0,6), getRowCount() - 1, 1);
		setValueAt(instruct.getBin().substring(6,11), getRowCount() - 1, 2);
		setValueAt(instruct.getBin().substring(11,16), getRowCount() - 1, 3);
		setValueAt(instruct.getBin().substring(16,21), getRowCount() - 1, 4);
		setValueAt(instruct.getBin().substring(21,26), getRowCount() - 1, 5);
		setValueAt(instruct.getBin().substring(26,32), getRowCount() - 1, 6);
		setValueAt(instruct.getHex(), getRowCount() - 1, 7);
	}
	
	public void clear() {
		while(getRowCount() != 0) {
			removeRow(getRowCount() - 1);
		}
	}

	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public Object getValueAt(int row, int col) {
		RowData rData = data.get(row);
		return rData.getValueAtCol(col);
	}

	public void setValueAt(Object value, int row, int col) {
		RowData rData = data.get(row);
		rData.setValueAtCol(value, col);
		fireTableCellUpdated(row, col);
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
