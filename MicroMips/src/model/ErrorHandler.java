package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ErrorHandler {
	private ArrayList<String> labelList;

	public ErrorHandler() {
		labelList = new ArrayList<String>();
	}

	public boolean check(String l) {
		if (l.equals("")) {
			JOptionPane.showMessageDialog(new JPanel(), "Code area empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (l.contains("DADDIU") || l.contains("ORI") || l.contains("DADDU") || l.contains("SD")
				|| l.contains("LD") || l.contains("BC") || l.contains("BNEZC")) {
			String[] temp = l.split(" ");
			int x = 0;
			if (temp[0].contains(":")) {
				for (String s : labelList) {
					if (s.equalsIgnoreCase(temp[0])) {
						JOptionPane.showMessageDialog(new JPanel(), "Invalid instruction label!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
				x = 1;
				labelList.add(temp[0]);
			}

			if (l.contains("DADDIU") || l.contains("ORI") || l.contains("DADDU")) {
				if (temp.length != (4 + x)) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid syntaxd!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				} else if (Integer.parseInt(getDig(temp[1 + x])) < 0 || Integer.parseInt(getDig(temp[1 + x])) > 31
						|| Integer.parseInt(getDig(temp[2 + x])) < 0 || Integer.parseInt(getDig(temp[2 + x])) > 31) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid register found!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				} else if (temp[3 + x].length() < 7) {
					try {
						System.out.println("hex ->" + getHex(temp[3 + x]));
						if (Integer.parseInt(getHex(temp[3 + x]), 16) > Integer.parseInt("FFFF", 16)) {
							JOptionPane.showMessageDialog(new JPanel(), "Immediate too big!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return false;
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(new JPanel(), "Invalid immediate found!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

				}
			} else if (l.contains("SD") || l.contains("LD")) {
				if (temp.length != (3 + x)) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid syntax!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				} else if (Integer.parseInt(getDig(temp[1 + x])) < 0 || Integer.parseInt(getDig(temp[1 + x])) > 31) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid register found!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
				if (Integer.parseInt(getHex(temp[2 + x].substring(0, 4)), 16) > Integer.parseInt("FF", 16)) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid data address! Too big", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (l.contains("BNEZC")) {
				if (temp.length != (3 + x)) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid syntax!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				} else if (Integer.parseInt(getDig(temp[1 + x])) < 0 || Integer.parseInt(getDig(temp[1 + x])) > 31) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid register found!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} else if (l.contains("BC")) {
				if (temp.length != (2 + x)) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid syntax!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}

		} else if (false) {
			return false;
		}

		return true;
	}

	public String getDig(String s) {
		return s.replaceAll("[^\\.-0123456789]", "");
	}

	public String getHex(String s) {
		return s.replaceAll("[^\\.0123456789ABCDEFabcdef]", "");
	}

	public String checkImm(String s) {
		return s.replaceAll("[^\\.0123456789ABCDEFabcdef]", "");
	}
}
