package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class RegisterModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private ArrayList<RowData> data;
	public static ArrayList<Register> regList;

	public RegisterModel() {
		header = new String[] { "Register", "Value" };
		data = new ArrayList<RowData>();
		regList = new ArrayList<Register>();
		initializeDefaultRegisters();
	}

	public void initializeDefaultRegisters() {
		System.out.println("Initializing Default Register model...");

		for (int i = 0; i < 32; i++) {
			Register r = new Register(i, "0000000000000000");
			regList.add(r);
			addRowWithData(r);
		}
	}

	public void reset() {
		for (Register r : regList) {
			r.setRegisterValue("0000000000000000");
			setValueAt(r.getRegisterValue(), r.getRegisterNumber(), 1);
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

	public void addRowWithData(Register reg) {
		addRow();
		setValueAt(reg.getRegisterNumber(), getRowCount() - 1, 0);
		setValueAt(reg.getRegisterValue(), getRowCount() - 1, 1);
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
