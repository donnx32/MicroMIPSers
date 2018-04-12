package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DataModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private ArrayList<RowData> data;
	public static ArrayList<Data> dataList;

	public DataModel() {
		header = new String[] { "Address", "Representation" };
		data = new ArrayList<RowData>();
		dataList = new ArrayList<Data>();
		initializeDefaultData();
	}

	public void initializeDefaultData() {
		System.out.println("Initializing Default Register model...");

		for (int i = 0; i < 256; i++) {
			Data d = new Data();
			dataList.add(d);
			addRowWithData(d);
		}
	}
	
	public void reset() {
		for (int i = 0; i < dataList.size(); i++) {
			dataList.get(i).setRepresentation("00");
			setValueAt(dataList.get(i).getRepresentation(), i, 1);
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

	public void addRowWithData(Data r) {
		addRow();
		setValueAt(r.getAddress(), getRowCount() - 1, 0);
		setValueAt(r.getRepresentation(), getRowCount() - 1, 1);
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
