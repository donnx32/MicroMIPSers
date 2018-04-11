package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CycleModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<RowData> header;
	private ArrayList<RowData> data;
	
	public CycleModel() {
		initializeDefaultModel();
	}

	public void initializeDefaultModel() {
		System.out.println("Initializing Default Cycle model...");

		header = new ArrayList<RowData>();
		data = new ArrayList<RowData>();
	}

	public String getColumnName(int col) {
		return header.get(col).toString();
	}

	public int getColumnCount() {
		return header.size();
	}

	public int getRowCount() {
		return data.size();
	}

	public void addRow() {
		data.add(new RowData());
		fireTableRowsInserted(getRowCount(), getRowCount());
	}

	public void addRowWithData(Instruction instruct) {
		addRow();package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.CodeParser;
import model.Instruction;
import model.OpCodeModel;
import model.RegisterModel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel mainPanel;
	private JPanel pnlBtnLoad;
	private JPanel pnlBtnReset;
	private JPanel pnlBtnClear;
	private JLabel lblLoad;
	private JLabel lblReset;
	private JLabel lblClear;
	private JLabel lblOpcodes;
	private JLabel lblCode;
	private JLabel lblGpRegisters;
	private JTextArea textArea;
	private JTable tblOpCode;
	private JTable tblRegister;
	private JScrollPane scrlPaneOpCode;
	private JScrollPane scrlPaneRegister;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private OpCodeModel ocModel;
	private static RegisterModel rModel;
	private  ArrayList<Instruction> instructList;
	private CodeParser cP;
	private InputRegisterView iRV;

	public MainView() {
		setTitle("MicroMIPSers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		iRV = new InputRegisterView();
		instructList = new ArrayList<Instruction>();
		cP = new CodeParser(instructList);

		initializeComponents();
		generateListeners();
	}

	public void initializeComponents() {
		System.out.println("Initializing main components...");

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 228, 225));
		mainPanel.setBounds(10, 11, 1189, 489);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		separator = new JSeparator();
		separator.setBounds(447, 29, 74, 9);
		mainPanel.add(separator);

		lblOpcodes = new JLabel("OpCodes");
		lblOpcodes.setBounds(445, 10, 74, 19);
		mainPanel.add(lblOpcodes);
		lblOpcodes.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lblCode = new JLabel("Please enter code here");
		lblCode.setBounds(24, 10, 254, 19);
		mainPanel.add(lblCode);
		lblCode.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lblGpRegisters = new JLabel("GP Registers");
		lblGpRegisters.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblGpRegisters.setBounds(969, 10, 98, 19);
		mainPanel.add(lblGpRegisters);

		textArea = new JTextArea();
		textArea.setBounds(24, 40, 400, 400);
		mainPanel.add(textArea);

		separator_1 = new JSeparator();
		separator_1.setBounds(24, 29, 162, 9);
		mainPanel.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(434, 10, 12, 469);
		mainPanel.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(957, 10, 12, 469);
		mainPanel.add(separator_3);

		separator_4 = new JSeparator();
		separator_4.setBounds(972, 29, 84, 9);
		mainPanel.add(separator_4);

		ocModel = new OpCodeModel();

		scrlPaneOpCode = new JScrollPane();
		scrlPaneOpCode.setBounds(447, 40, 500, 400);
		mainPanel.add(scrlPaneOpCode);

		tblOpCode = new JTable();
		tblOpCode.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		tblOpCode.setForeground(Color.BLACK);
		scrlPaneOpCode.setViewportView(tblOpCode);
		scrlPaneOpCode.getViewport().setBackground(Color.decode("#f6e6dc"));
		tblOpCode.setBackground(Color.WHITE);
		tblOpCode.setModel(ocModel);
		tblOpCode.getColumnModel().getColumn(0).setPreferredWidth(230);
		tblOpCode.getColumnModel().getColumn(7).setPreferredWidth(125);
		tblOpCode.setRowHeight(23);

		scrlPaneRegister = new JScrollPane();
		scrlPaneRegister.setBounds(970, 40, 198, 400);
		mainPanel.add(scrlPaneRegister);

		rModel = new RegisterModel();
		tblRegister = new JTable();
		tblRegister.setModel(rModel);
		rModel.initializeDefaultRegisters();
		tblRegister.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblRegister.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		scrlPaneRegister.setViewportView(tblRegister);
		scrlPaneRegister.getViewport().setBackground(Color.decode("#f6e6dc"));
		tblRegister.setBackground(Color.WHITE);
		tblRegister.setRowHeight(23);

		pnlBtnLoad = new JPanel();
		pnlBtnLoad.setBackground(new Color(203, 201, 201));
		pnlBtnLoad.setBounds(60, 449, 100, 30);
		mainPanel.add(pnlBtnLoad);

		lblLoad = new JLabel("Load");
		lblLoad.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnLoad.add(lblLoad);

		pnlBtnReset = new JPanel();
		pnlBtnReset.setBackground(new Color(203, 201, 201));
		pnlBtnReset.setBounds(170, 449, 100, 30);
		mainPanel.add(pnlBtnReset);

		lblReset = new JLabel("Reset");
		lblReset.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnReset.add(lblReset);

		pnlBtnClear = new JPanel();

		pnlBtnClear.setBackground(new Color(203, 201, 201));
		pnlBtnClear.setBounds(280, 449, 100, 30);
		mainPanel.add(pnlBtnClear);

		lblClear = new JLabel("Clear");
		lblClear.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnClear.add(lblClear);
	}

	public void generateListeners() {
		System.out.println("Generating listeners...");

		pnlBtnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] temp = textArea.getText().split("\n");
				instructList.clear();
				ocModel.clear();
				
				for (String line : temp) {
					System.out.println(line);
					// ERROR HANDLING HERE
					instructList.add(new Instruction(line));
				}

				cP.parseCode();
				
				for (Instruction i : instructList) {
					ocModel.addRowWithData(i);
				}
			}
		});

		pnlBtnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ocModel.clear();
				rModel.reset();
			}
		});

		pnlBtnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
			}
		});

		tblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				iRV.getLblReg().setText(Integer.toString((int)tblRegister.getValueAt(tblRegister.getSelectedRow(), 0)));
				iRV.setVisible(true);
			}
		});
	}
	
	public static RegisterModel getrModel() {
		return rModel;
	}
}
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
