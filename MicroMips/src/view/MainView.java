package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.CodeExecutor;
import model.CodeParser;
import model.Cycle;
import model.Data;
import model.DataModel;
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
	private JTextArea txtAreaCode;
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
	public static DataModel dataModel;
	private static RegisterModel rModel;
	private DefaultTableModel cycleModel;
	private ArrayList<Instruction> instructList;
	private CodeParser cP;
	private CodeExecutor cE;
	private InputRegisterView iRV;
	private InputDataView iDV;
	private JTable tblCycles;
	private JTable tblData;
	private JScrollPane scrlPaneCycle;
	private JPanel pnlBtnRun1;
	private JLabel lblRun;
	private JPanel pnlButtonGoTo;

	public MainView() {
		setTitle("MicroMIPSers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 675);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		iRV = new InputRegisterView();
		iDV = new InputDataView();
		instructList = new ArrayList<Instruction>();
		cP = new CodeParser(instructList);
		cE = new CodeExecutor();

		initializeComponents();
		generateListeners();
	}

	public void initializeComponents() {
		System.out.println("Initializing main components...");

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 228, 225));
		mainPanel.setBounds(10, 11, 1199, 624);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		separator = new JSeparator();
		separator.setBounds(447, 29, 84, 9);
		mainPanel.add(separator);

		lblOpcodes = new JLabel("OpCodes");
		lblOpcodes.setBounds(666, 10, 74, 19);
		mainPanel.add(lblOpcodes);
		lblOpcodes.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lblCode = new JLabel("Please enter code here");
		lblCode.setBounds(24, 10, 254, 19);
		mainPanel.add(lblCode);
		lblCode.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lblGpRegisters = new JLabel("GP Registers");
		lblGpRegisters.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblGpRegisters.setBounds(447, 10, 98, 19);
		mainPanel.add(lblGpRegisters);

		separator_1 = new JSeparator();
		separator_1.setBounds(24, 29, 162, 9);
		mainPanel.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(434, 10, 12, 603);
		mainPanel.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(655, 10, 12, 603);
		mainPanel.add(separator_3);

		separator_4 = new JSeparator();
		separator_4.setBounds(666, 29, 74, 9);
		mainPanel.add(separator_4);

		ocModel = new OpCodeModel();

		scrlPaneOpCode = new JScrollPane();
		scrlPaneOpCode.setBounds(666, 38, 511, 264);
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
		tblOpCode.getTableHeader().setResizingAllowed(false);
		tblOpCode.getTableHeader().setReorderingAllowed(false);

		scrlPaneRegister = new JScrollPane();
		scrlPaneRegister.setBounds(447, 38, 198, 264);
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
		tblRegister.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblRegister.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tblRegister.getTableHeader().setResizingAllowed(false);
		tblRegister.getTableHeader().setReorderingAllowed(false);

		pnlBtnLoad = new JPanel();
		pnlBtnLoad.setBackground(new Color(203, 201, 201));
		pnlBtnLoad.setBounds(75, 583, 74, 30);
		mainPanel.add(pnlBtnLoad);

		lblLoad = new JLabel("Load");
		lblLoad.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnLoad.add(lblLoad);

		pnlBtnReset = new JPanel();
		pnlBtnReset.setBackground(new Color(203, 201, 201));
		pnlBtnReset.setBounds(174, 583, 74, 30);
		mainPanel.add(pnlBtnReset);

		lblReset = new JLabel("Reset");
		lblReset.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnReset.add(lblReset);

		pnlBtnClear = new JPanel();

		pnlBtnClear.setBackground(new Color(203, 201, 201));
		pnlBtnClear.setBounds(275, 583, 74, 30);
		mainPanel.add(pnlBtnClear);

		lblClear = new JLabel("Clear");
		lblClear.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnClear.add(lblClear);

		JScrollPane scrlPaneData = new JScrollPane();
		scrlPaneData.setBounds(447, 349, 198, 264);
		scrlPaneData.getViewport().setBackground(Color.decode("#f6e6dc"));
		mainPanel.add(scrlPaneData);

		dataModel = new DataModel();
		tblData = new JTable();
		tblData.setForeground(Color.BLACK);
		scrlPaneData.setViewportView(tblData);
		tblData.setModel(dataModel);
		tblData.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		tblData.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblData.setRowHeight(23);
		tblData.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblData.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tblData.getTableHeader().setResizingAllowed(false);
		tblData.getTableHeader().setReorderingAllowed(false);

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblData.setBounds(447, 319, 40, 19);
		mainPanel.add(lblData);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(447, 339, 40, 9);
		mainPanel.add(separator_5);

		JLabel lblCycles = new JLabel("Cycles");
		lblCycles.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblCycles.setBounds(666, 319, 74, 19);
		mainPanel.add(lblCycles);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(666, 339, 49, 9);
		mainPanel.add(separator_6);

		cycleModel = new DefaultTableModel(new Object[] { "Cycle" }, 12);
		scrlPaneCycle = new JScrollPane();
		// scrlPaneCycle.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrlPaneCycle.setBounds(666, 349, 511, 264);
		scrlPaneCycle.getViewport().setBackground(Color.decode("#f6e6dc"));
		mainPanel.add(scrlPaneCycle);
		tblCycles = new JTable();
		scrlPaneCycle.setViewportView(tblCycles);
		tblCycles.setModel(cycleModel);
		tblCycles.setRowHeight(20);
		tblCycles.setFont(new Font("Century Gothic", Font.PLAIN, 11));

		tblCycles.getColumnModel().getColumn(0).setPreferredWidth(150);
		tblCycles.getTableHeader().setReorderingAllowed(false);
		tblCycles.getTableHeader().setResizingAllowed(false);
		tblCycles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		cycleModel.setValueAt("Instruction", 0, 0);
		cycleModel.setValueAt("IR", 1, 0);
		cycleModel.setValueAt("NPC", 2, 0);
		cycleModel.setValueAt("A", 3, 0);
		cycleModel.setValueAt("B", 4, 0);
		cycleModel.setValueAt("IMM", 5, 0);
		cycleModel.setValueAt("ALUOUTPUT", 6, 0);
		cycleModel.setValueAt("COND", 7, 0);
		cycleModel.setValueAt("PC", 8, 0);
		cycleModel.setValueAt("LMD", 9, 0);
		cycleModel.setValueAt("Range aff.", 10, 0);
		cycleModel.setValueAt("Rn", 11, 0);

		pnlButtonGoTo = new JPanel();
		pnlButtonGoTo.setBackground(new Color(203, 201, 201));
		pnlButtonGoTo.setBounds(545, 313, 100, 30);
		mainPanel.add(pnlButtonGoTo);

		JLabel lblGoTo = new JLabel("GOTO Memory");
		lblGoTo.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		pnlButtonGoTo.add(lblGoTo);

		JScrollPane scrlPaneCodeArea = new JScrollPane();
		scrlPaneCodeArea.setBounds(24, 40, 400, 532);
		mainPanel.add(scrlPaneCodeArea);

		txtAreaCode = new JTextArea();
		scrlPaneCodeArea.setViewportView(txtAreaCode);

		pnlBtnRun1 = new JPanel();
		pnlBtnRun1.setBackground(new Color(203, 201, 201));
		pnlBtnRun1.setBounds(1019, 313, 74, 30);
		mainPanel.add(pnlBtnRun1);

		lblRun = new JLabel("Run all");
		lblRun.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		pnlBtnRun1.add(lblRun);
	}

	public void generateListeners() {
		System.out.println("Generating listeners...");

		pnlBtnRun1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cE.execute(instructList);
				int ctr = 1;

				for(int i = 0; i < CodeExecutor.cycleList.size(); i++) {
					String head = ctr + " - " + (ctr + 4);
					System.out.print(ctr);
					Cycle c = CodeExecutor.cycleList.get(i);
					cycleModel.addColumn(head,
							new Object[] { c.getInstr(), c.getiR(), c.getnPC(), c.getA(), c.getA(), c.getimm(),
									c.getaluOutput(), c.getcond(), c.getpC(), c.getlMD(), c.getRange(), c.getrN() });
					ctr += 5;
				}
				
				for(int i = 0; i <tblCycles.getColumnCount(); i++) {
					tblCycles.getColumnModel().getColumn(i).setPreferredWidth(150);
				}

			}
		});

		pnlBtnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] temp = txtAreaCode.getText().split("\n");
				instructList.clear();
				ocModel.clear();
				for (String line : temp)
					instructList.add(new Instruction(line));

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
				dataModel.reset();
				cE.getCycleList().clear();
				DefaultTableModel model = (DefaultTableModel) tblCycles.getModel();
				model.setColumnCount(1);

			}
		});

		pnlBtnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtAreaCode.setText("");
			}
		});

		tblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				iRV.getLblReg()
						.setText(Integer.toString((int) tblRegister.getValueAt(tblRegister.getSelectedRow(), 0)));
				iRV.setVisible(true);
			}
		});

		tblData.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				iDV.getlblData().setText((String) tblData.getValueAt(tblData.getSelectedRow(), 0));
				iDV.setVisible(true);
			}
		});
		
		pnlButtonGoTo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				String a = JOptionPane.showInputDialog(new JFrame("Go To"), "Input address");
				a = zeroExtend(a,4);
				String  r = "";
				for(Data d: DataModel.dataList) {
					if (d.getAddress().equalsIgnoreCase(a)) {
						r = d.getRepresentation();
					}
				}
				
				JOptionPane.showMessageDialog(null, "A address at " + a + " is " + r.toUpperCase());
			}
		});
	}

	public static RegisterModel getrModel() {
		return rModel;
	}
	
	public String zeroExtend(String s, int n) {
		StringBuilder sb = null;

		if (s.length() < n) {
			sb = new StringBuilder();
			int diff = n - s.length();

			for (int i = 0; i < diff; i++) {
				sb.append("0");
			}

			return sb.toString() + "" + s;
		} else {
			return s.substring(s.length() - n, s.length());
		}
	}
}
