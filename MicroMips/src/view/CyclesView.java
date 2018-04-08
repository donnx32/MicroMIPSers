package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class CyclesView {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CyclesView window = new CyclesView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CyclesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 631, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 616, 468);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCycles = new JLabel("Cycles");
		lblCycles.setBounds(269, 11, 69, 31);
		panel.add(lblCycles);
		lblCycles.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(510, 426, 96, 31);
		panel.add(btnBack);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(259, 38, 89, 2);
		panel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 596, 377);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), "IR", "DC41 1000", null, new Integer(6), "IR", "DC41"},
				{null, "NPC", "0000 0000 0000 0004", null, null, "NPC", "0000 0000 000 0004"},
				{null, null, null, null, null, null, null},
				{new Integer(2), "A", "0000 0000 0000 0008", null, new Integer(7), "A", null},
				{null, "B", "0000 0000 0000 0002", null, null, "B", null},
				{null, "IMM", null, null, null, "IMM", null},
				{null, null, null, null, null, null, null},
				{new Integer(3), "ALUOUTPUT", "0000 0000 0000 1008", null, new Integer(8), "ALUOUTPUT", null},
				{null, "COND", "0", null, null, "COND", null},
				{null, null, null, null, null, null, null},
				{new Integer(4), "PC", "0000 0000 0000 0004", null, new Integer(9), "PC", null},
				{null, "LMD", "5544 3322 11EF CDAB", null, null, "LMD", null},
				{null, "Range", "N/A", null, null, "Range", null},
				{null, null, null, null, null, null, null},
				{new Integer(5), "Rn", "5544 3322 11EF CDAB", null, new Integer(10), "Rn", null},
			},
			new String[] {
				"Cycle No.", "Name", "Result", "", "Cycle No.", "Name", "Result"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, Object.class, Integer.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(61);
		table.getColumnModel().getColumn(2).setPreferredWidth(123);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(21);
		table.getColumnModel().getColumn(4).setPreferredWidth(61);
		table.getColumnModel().getColumn(5).setPreferredWidth(88);
		table.getColumnModel().getColumn(6).setPreferredWidth(114);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	
	}
}
