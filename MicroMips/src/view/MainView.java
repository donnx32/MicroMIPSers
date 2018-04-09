package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JLabel lblOpcodes;
	private JLabel lblCode;
	private JLabel lblGpRegisters;
	private Button btnLoad;
	private Button btnReset;
	private Button btnClear;
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
	private RegisterModel rModel;
	private static ArrayList<Instruction> instructList;
	private CodeParser cP;

	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

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

		btnLoad = new Button("Load");
		btnLoad.setBounds(56, 449, 90, 30);
		mainPanel.add(btnLoad);
		btnLoad.setActionCommand("");
		btnLoad.setFont(new Font("Arial Black", Font.PLAIN, 14));

		btnReset = new Button("Reset");
		btnReset.setBounds(165, 449, 90, 30);
		mainPanel.add(btnReset);
		btnReset.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnReset.setActionCommand("");

		btnClear = new Button("Clear");
		btnClear.setBounds(270, 449, 90, 30);
		mainPanel.add(btnClear);
		btnClear.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnClear.setActionCommand("");

		separator = new JSeparator();
		separator.setBounds(447, 29, 74, 9);
		mainPanel.add(separator);

		lblOpcodes = new JLabel("OpCodes");
		lblOpcodes.setBounds(445, 10, 74, 19);
		mainPanel.add(lblOpcodes);
		lblOpcodes.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lblCode = new JLabel("Please enter code here");
		lblCode.setBounds(36, 10, 254, 19);
		mainPanel.add(lblCode);
		lblCode.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lblGpRegisters = new JLabel("GP Registers");
		lblGpRegisters.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblGpRegisters.setBounds(967, 10, 98, 19);
		mainPanel.add(lblGpRegisters);

		textArea = new JTextArea();
		textArea.setBounds(24, 40, 400, 400);
		mainPanel.add(textArea);

		separator_1 = new JSeparator();
		separator_1.setBounds(36, 31, 162, 9);
		mainPanel.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(434, 40, 12, 400);
		mainPanel.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(957, 40, 2, 400);
		mainPanel.add(separator_3);

		separator_4 = new JSeparator();
		separator_4.setBounds(965, 31, 84, 9);
		mainPanel.add(separator_4);

		ocModel = new OpCodeModel(instructList);

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
		tblOpCode.getColumnModel().getColumn(0).setPreferredWidth(250);
		tblOpCode.getColumnModel().getColumn(7).setPreferredWidth(95);
		tblOpCode.setRowHeight(23);

		scrlPaneRegister = new JScrollPane();
		scrlPaneRegister.setBounds(969, 40, 198, 400);
		mainPanel.add(scrlPaneRegister);

		rModel = new RegisterModel();
		tblRegister = new JTable();
		tblRegister.setModel(rModel);
		tblRegister.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblRegister.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		scrlPaneRegister.setViewportView(tblRegister);
		scrlPaneRegister.getViewport().setBackground(Color.decode("#f6e6dc"));
		tblRegister.setBackground(Color.WHITE);
		tblRegister.setRowHeight(23);
	}

	public void generateListeners() {
		System.out.println("Generating listeners...");

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] temp = textArea.getText().split("\n");
				instructList.clear();
				
				for (String code : temp)
					instructList.add(new Instruction(code));
				
				cP.parseCode();
				
				for (Instruction i : instructList)
					ocModel.addRowWithData(i);
				
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
	}
}
