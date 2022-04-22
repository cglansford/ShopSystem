package entities;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame {

	//private ArrayList<Customer> customerList = new ArrayList<Customer>();
	CustomerList customerList = new CustomerList();
	private int position = 0;
	private String password;
	private Customer editCustomer = null;
	
	JFrame f, f1;
	JLabel firstNameLabel, surnameLabel, addressLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, addressTextField;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	Container content;
	Customer loggedInCustomer;

	JPanel panel2;
	JButton add;
	String firstName, surname, address, CustomerID;

	public static void main(String[] args) {
		Menu driver = new Menu();
		driver.menuStart();
	}

	public void menuStart() {
		/*
		 * The menuStart method asks the user if they are a new customer, an existing
		 * customer or an admin. It will then start the create customer process if they
		 * are a new customer, or will ask them to log in if they are an existing
		 * customer or admin.
		 */

		f = new JFrame("User Type");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		JPanel userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand();

				// if user selects NEW
				// CUSTOMER--------------------------------------------------------------------------------------
				if (user.equals("New Customer")) {
					f.dispose();
					f1 = new JFrame("Create New Customer");
					f1.setSize(400, 300);
					f1.setLocation(200, 200);
					f1.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					Container content = f1.getContentPane();
					content.setLayout(new BorderLayout());

					firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
					surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
					addressLabel = new JLabel("Address:", SwingConstants.RIGHT);
					
					firstNameTextField = new JTextField(20);
					surnameTextField = new JTextField(20);
					addressTextField = new JTextField(20);
					JPanel panel = new JPanel(new GridLayout(6, 2));
					panel.add(firstNameLabel);
					panel.add(firstNameTextField);
					panel.add(surnameLabel);
					panel.add(surnameTextField);
					panel.add(addressLabel);
					panel.add(addressTextField);


					panel2 = new JPanel();
					add = new JButton("Add");

					add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							
							firstName = firstNameTextField.getText();
							surname = surnameTextField.getText();
							address = addressTextField.getText();
							password = "";

							CustomerID = "ID" + firstName;

							add.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									f1.dispose();

									boolean loop = true;
									while (loop) {
										password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

										if (password.length() != 7)// Making sure password is 7 characters
										{
											JOptionPane.showMessageDialog(null, null,
													"Password must be 7 charatcers long", JOptionPane.OK_OPTION);
										} else {
											loop = false;
										}
									}

									ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
									Customer customer = new Customer(firstName,surname,  address, CustomerID, password,
											accounts);

									customerList.getCustomerList().add(customer);

									JOptionPane.showMessageDialog(f,
											"CustomerID = " + CustomerID + "\n Password = " + password,
											"Customer created.", JOptionPane.INFORMATION_MESSAGE);
									f.dispose();
									menuStart();
									
								}
							});
						}
					});
					JButton cancel = new JButton("Cancel");
					cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							f1.dispose();
							menuStart();
						}
					});

					panel2.add(add);
					panel2.add(cancel);

					content.add(panel, BorderLayout.CENTER);
					content.add(panel2, BorderLayout.SOUTH);

					f1.setVisible(true);

				}

				// ------------------------------------------------------------------------------------------------------------------
				// if user select
				// ADMIN----------------------------------------------------------------------------------------------
				if (user.equals("Administrator")) {
					f.dispose();
					boolean loop = true, loop2 = true;
					boolean cont = false;
					while (loop) {
						Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

						if (!adminUsername.equals("admin"))// search admin list for admin with matching admin username
						{
							int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							} else if (reply == JOptionPane.NO_OPTION) {
								f1.dispose();
								loop = false;
								loop2 = false;
								menuStart();
							}
						} else {
							loop = false;
						}
					}

					while (loop2) {
						Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

						if (!adminPassword.equals("admin"))// search admin list for admin with matching admin password
						{
							int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {

							} else if (reply == JOptionPane.NO_OPTION) {
								f1.dispose();
								loop2 = false;
								menuStart();
							}
						} else {
							loop2 = false;
							cont = true;
						}
					}

					if (cont) {
						
						loop = false;
						admin();
					}
				}
				
				// -----------------------------------------------------------------------------------------------------------------------
			}
		});
		f.setVisible(true);
	}

	
	public void admin() {
		dispose();

		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);
		
		JPanel newStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton newStockButton = new JButton("Add New Stock Item");
		newStockButton.setPreferredSize(new Dimension(250, 20));
		newStockPanel.add(newStockButton);

		
		JPanel viewStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton viewStockButton = new JButton("View Stock");
		viewStockButton.setPreferredSize(new Dimension(250, 20));
		viewStockPanel.add(viewStockButton);

		JPanel addStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton addStockButton = new JButton("Add Stock");
		addStockButton.setPreferredSize(new Dimension(250, 20));
		addStockPanel.add(addStockButton);
		
		JPanel searchCustomersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton searchCustomersButton = new JButton("Search For A Customer");
		searchCustomersButton.setPreferredSize(new Dimension(250, 20));
		searchCustomersPanel.add(searchCustomersButton);

		JPanel viewCustomersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton viewCustomersButton = new JButton("View All Customers");
		viewCustomersButton.setPreferredSize(new Dimension(250, 20));
		viewCustomersPanel.add(viewCustomersButton);


		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(7, 1));
		content.add(label1);
		content.add(newStockPanel);
		content.add(viewStockPanel);
		content.add(addStockPanel);
		content.add(searchCustomersPanel);
		content.add(viewCustomersPanel);
		
		// content.add(deleteAccountPanel);
		content.add(returnPanel);
		
		newStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});
		
		viewStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});
		
		addStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});
		
		searchCustomersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});
		
		viewCustomersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});

		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				menuStart();
			}
		});
	}
	
}
