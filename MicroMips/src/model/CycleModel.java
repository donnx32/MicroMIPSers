package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CycleModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private ArrayList<RowData> data;
	
	public CycleModel() {
		initializeDefaultModel();
	}

	public void initializeDefaultModel() {
		System.out.println("Initializing Default Cycle model...");

		header = new String[] { "" };
		data = new ArrayList<RowData>();
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
	
	public void clear() {
		while(getRowCount() != 0) {
			removeRow(getRowCount() - 1);
		}
		
		while(getColumnCount() != 0) {
			
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
