package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Register;
import model.RegisterModel;

public class InputRegisterView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPanel pnlBtnOK;
	private JPanel pnlBtnCancel;
	private JLabel lblReg;

	public InputRegisterView() {
		setTitle("Register Input");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 180);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		initializeComponents();
		generateListeners();
	}

	private void initializeComponents() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(10, 11, 274, 129);
		contentPane.add(panel);
		panel.setLayout(null);
		
		pnlBtnOK = new JPanel();
		pnlBtnOK.setBackground(new Color(203, 201, 201));
		pnlBtnOK.setBounds(25, 88, 100, 30);
		panel.add(pnlBtnOK);

		JLabel lblOkay = new JLabel("OK");
		lblOkay.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnOK.add(lblOkay);

		pnlBtnCancel = new JPanel();

		pnlBtnCancel.setBackground(new Color(203, 201, 201));
		pnlBtnCancel.setBounds(139, 88, 100, 30);
		panel.add(pnlBtnCancel);

		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		pnlBtnCancel.add(lblCancel);

		JLabel lblEnter = new JLabel("Enter the content of Register");
		lblEnter.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblEnter.setBounds(25, 11, 201, 14);
		panel.add(lblEnter);

		JSeparator separator = new JSeparator();
		separator.setBounds(25, 27, 214, 8);
		panel.add(separator);

		textField = new JTextField();
		textField.setBounds(10, 36, 254, 30);
		panel.add(textField);
		textField.setColumns(10);

		lblReg = new JLabel();
		lblReg.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblReg.setBounds(225, 11, 29, 14);
		panel.add(lblReg);
	}

	public void generateListeners() {
		pnlBtnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register r = RegisterModel.regList.get(Integer.parseInt(lblReg.getText()));
				r.setRegisterValue(textField.getText());
				MainView.getrModel().setValueAt(r.getRegisterValue(), r.getRegisterNumber(), 1);
				
				lblReg.setText("");
				textField.setText("");
				setVisible(false);
			}
		});

		pnlBtnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblReg.setText("");
				textField.setText("");
				setVisible(false);
			}
		});
	}

	public JLabel getLblReg() {
		return lblReg;
	}
}
