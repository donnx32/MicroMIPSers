package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Data;
import model.DataModel;

public class InputDataView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPanel pnlBtnOK;
	private JPanel pnlBtnCancel;
	private JLabel lblData;

	public InputDataView() {
		setTitle("Data Input");
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

		JLabel lblEnter = new JLabel("Enter the content of Address");
		lblEnter.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblEnter.setBounds(25, 11, 201, 14);
		panel.add(lblEnter);

		JSeparator separator = new JSeparator();
		separator.setBounds(25, 27, 230, 8);
		panel.add(separator);

		textField = new JTextField();
		textField.setBounds(10, 36, 254, 30);
		panel.add(textField);
		textField.setColumns(10);

		lblData = new JLabel();
		lblData.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblData.setBounds(225, 11, 50, 14);
		panel.add(lblData);
	}

	public void generateListeners() {
		pnlBtnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Component frame = null;
				String input = textField.getText().replaceAll("\\s+", "");; 
				int check = 0;
				
				
				if(input.length() > 2) {
					System.out.println("error in length");
					JOptionPane.showMessageDialog(frame, "Error: Maximum Number Reached");
					lblData.setText("");
					textField.setText("");
					setVisible(false);
				}
				
				else { 
					input = zeroExtend(input,2);
					System.out.println("Input after zeroExtend: " + input);
						for(int i = 0; i < input.length(); i++) {
							if(input.charAt(i) > 'F' || input.charAt(i) < '/');
							check += 1;	
						}
					
					

					if(check == 0) {
						System.out.println("error in validity");
						JOptionPane.showMessageDialog(frame, "Error: Input is not Valid");
						lblData.setText("");
						textField.setText("");
						setVisible(false);
					}
					
					else {
						Data d = DataModel.dataList.get(getDataIndex(lblData.getText()));
						d.setRepresentation(input);
						MainView.dataModel.setValueAt(d.getRepresentation(), getDataIndex(lblData.getText()), 1);
						lblData.setText("");
						textField.setText("");
						setVisible(false);
					}
				}
					
//				lblData.setText("");
//				textField.setText("");
//				setVisible(false);
			}
		});

		pnlBtnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblData.setText("");
				textField.setText("");
				setVisible(false);
			}
		});
	}

	public JLabel getlblData() {
		return lblData;
	}
	
	public int getDataIndex(String s) {
		int index = -1;
		
		for(int i = 0; i < DataModel.dataList.size(); i++) {
			if (DataModel.dataList.get(i).getAddress().equalsIgnoreCase(s)) {
				index = i;
				break;
			}
		}
		
		return index;
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
			return s.substring(s.length() - n,s.length());
		}
	}

}
