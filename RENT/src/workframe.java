import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Panel;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class workframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	DefaultTableModel model_1;
	Mangement man = new Mangement();
	private JTextField start_date;
	private JTextField username;
	private JPasswordField passwordField;
	private JTextField car_brand;
	private JTextField model_;
	private JTextField Year;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					workframe frame = new workframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void fillTable(List<Information> list) throws SQLException {
		for (Information car : list) {
			model.addRow(new Object[] { car.getCar_id(), car.getCar_brand(), car.getModel(), car.getYear(),
					car.getStstus() });

		}

	}
	
	public void fillTable_1(ArrayList<Details> list1) throws SQLException {
	    model_1.setRowCount(0); 
	    for (Details data : list1) {
	        model_1.addRow(new Object[] { 
	            data.getCar_id(), 
	            data.getStart_date(), 
	            data.getDuration(), 
	            data.getCost() 
	        });
	    }
	}
	public workframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-11, 32, 342, 231);
		contentPane.add(scrollPane);

		model = new DefaultTableModel();
		model = new DefaultTableModel(new String[] { "car_id", "car_brand", "model", "Year", "Status" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				setHorizontalAlignment(JLabel.CENTER);

				return c;
			}
		});

		scrollPane.setViewportView(table);
		table.setModel(model);
		try {
			fillTable(man.getCars());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(330, 32, 272, 231);
		contentPane.add(layeredPane);

		CardLayout cardLayout = new CardLayout();
		layeredPane.setLayout(cardLayout);

		Panel panel = new Panel();
		layeredPane.add(panel, "rent");
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Start:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 10, 45, 21);
		panel.add(lblNewLabel);

		start_date = new JTextField();
		start_date.setBounds(60, 14, 76, 19);
		panel.add(start_date);
		start_date.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 13, 71, 21);
		panel.add(comboBox);
		comboBox.addItem("3 Days");
		comboBox.addItem("Week");
		comboBox.addItem("Month");

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(workframe.this, "Please select a car first.", "No Selection",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				 String status = table.getValueAt(selectedRow, 4).toString();
				    if ("Rented".equalsIgnoreCase(status)) {
				        JOptionPane.showMessageDialog(workframe.this,
				                "This car is already rented. Please select another car.",
				                "Already Rented", JOptionPane.WARNING_MESSAGE);
				        return;
				    }
				String yearStr = table.getValueAt(selectedRow, 3).toString();
				int year = Integer.parseInt(yearStr);

				String startDate = start_date.getText().trim();
				

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf.setLenient(false);

				try {
				    sdf.parse(startDate);
				} catch (ParseException pe) {
				    JOptionPane.showMessageDialog(workframe.this,
				            "Invalid date format! Please enter date as yyyy-MM-dd.",
				            "Date Format Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
			        
				String durationStr = comboBox.getSelectedItem().toString();

				int days = switch (durationStr) {
				case "3 Days" -> 3;
				case "Week" -> 7;
				case "Month" -> 30;
				default -> 0;
				};

				int pricePerDay = switch (year) {
				case 2019 -> 600;
				case 2020 -> 800;
				case 2021 -> 1000;
				case 2022 -> 1400;
				case 2023 -> 2000;
				default -> {
					JOptionPane.showMessageDialog(workframe.this, "Unsupported car year.", "Error",
							JOptionPane.ERROR_MESSAGE);
					yield 0;
				}
				};

				int totalCost = pricePerDay * days;

				int confirm = JOptionPane.showConfirmDialog(workframe.this,
						"Start Date: " + startDate + "\nDuration: " + durationStr + " (" + days + " days)" + "\nYear: "
								+ year + "\nPrice per Day: " + pricePerDay + " TL" + "\nTotal Cost: " + totalCost
								+ " TL" + "\n\nConfirm payment?",
						"Confirm Rental", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {

					try {
						int carId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
						man.rentCar(carId, startDate, durationStr, totalCost);
						model.setValueAt("Rented", selectedRow, 4);
						model_1.setRowCount(0);
						fillTable_1(man.getDetails());
						JOptionPane.showMessageDialog(workframe.this, "Car rented successfully!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(workframe.this, "Error while saving to database.",
								"Database Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnNewButton.setBounds(60, 133, 109, 43);
		panel.add(btnNewButton);

		Panel panel_1 = new Panel();
		layeredPane.add(panel_1, "sales");
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 24, 90, 21);
		panel_1.add(lblNewLabel_1);

		username = new JTextField();
		username.setBounds(97, 28, 96, 19);
		panel_1.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 74, 82, 21);
		panel_1.add(lblNewLabel_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(97, 78, 96, 19);
		panel_1.add(passwordField);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String user = username.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (user.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "Username or password cannot be empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (man.logCheck(user, password)) {
					JOptionPane.showMessageDialog(panel_1, "Login successful!", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					cardLayout.show(layeredPane, "info");

				} else {
					JOptionPane.showMessageDialog(panel_1, "Invalid credentials", "Login Failed",
							JOptionPane.ERROR_MESSAGE);
				}
				username.setText("");
				passwordField.setText("");

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(67, 143, 85, 36);
		panel_1.add(btnNewButton_1);

		Panel panel_2 = new Panel();
		layeredPane.add(panel_2, "info");
		panel_2.setLayout(null);

		JButton btnNewButton_2 = new JButton("Add car");
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(layeredPane, "car");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2.setBounds(73, 41, 106, 29);
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Details");
		btnNewButton_2_1.setFocusable(false);
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(layeredPane, "Details");
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2_1.setBounds(73, 118, 106, 29);
		panel_2.add(btnNewButton_2_1);

		Panel car = new Panel();
		layeredPane.add(car, "car");
		car.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Car_brand:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 10, 76, 28);
		car.add(lblNewLabel_2);

		car_brand = new JTextField();
		car_brand.setBounds(96, 17, 96, 19);
		car.add(car_brand);
		car_brand.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Model:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 42, 76, 28);
		car.add(lblNewLabel_2_1);

		model_ = new JTextField();
		model_.setColumns(10);
		model_.setBounds(96, 46, 96, 19);
		car.add(model_);

		Year = new JTextField();
		Year.setBounds(96, 86, 96, 19);
		car.add(Year);
		Year.setColumns(10);

		JLabel lblNewLabel_2_1_1 = new JLabel("Year:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(10, 80, 76, 28);
		car.add(lblNewLabel_2_1_1);

		JButton btnNewButton_3 = new JButton("Add");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = car_brand.getText();
				String mode_l = model_.getText();
				String yea = Year.getText();
				int year_ = Integer.parseInt(yea);

				if (brand.isEmpty() || mode_l.isEmpty() || yea.isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "brand or mode_l or yea cannot be empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					man.add_new_car(brand, mode_l, year_);
					model.setRowCount(0);
					fillTable(man.getCars());
					JOptionPane.showMessageDialog(panel_1, "Car added successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					car_brand.setText("");
					model_.setText("");
					Year.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_3.setBounds(64, 139, 85, 37);
		car.add(btnNewButton_3);

		Panel Details = new Panel();
		layeredPane.add(Details, "Details");
		Details.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 272, 231);
		Details.add(scrollPane_1);
		
		model_1 = new DefaultTableModel();
		model_1 = new DefaultTableModel(new String[] { "Car_id", "Start_date", "Duration","Cost"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table_1 = new JTable();
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				setHorizontalAlignment(JLabel.CENTER);

				return c;
			}
		});
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(model_1);
		try {
			fillTable_1(man.getDetails());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 602, 35);
		contentPane.add(menuBar);

		Button rent = new Button("Rent");
		rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(layeredPane, "rent");

			}
		});

		rent.setFont(new Font("Dialog", Font.ITALIC, 17));
		menuBar.add(rent);

		Button sales = new Button("Admin");
		sales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(layeredPane, "sales");

			}
		});
		sales.setFont(new Font("Dialog", Font.ITALIC, 17));
		menuBar.add(sales);
	}
}
