package entities;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;

import DAOs.CustomerDAO;
import DAOs.StockDAO;

import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame {


	ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<StockItem> stockList = new ArrayList<>();
	private int position = 0;
	private String password;
	private Customer editCustomer = null;
	
	JFrame f, f1;

	
	JLabel	stockIDLabel,stockNameLabel, stockPriceLabel, stockQuantityLabel, stockCategoryLabel, stockManufacturerLabel;
	JTextField stockIDText, stockNameText, stockPriceText, stockQuantityText, stockCategoryText, stockManufacturerText;
	JLabel customerIDLabel, passwordLabel, firstNameLabel, surnameLabel, addressLabel;
	JTextField customerIDText, passwordText, firstNameText, surnameText, addressText;
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
		userTypePanel.add(radioButton = new JRadioButton("Customer Login"));
		radioButton.setActionCommand("Customer Login");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Admin Login"));
		radioButton.setActionCommand("Admin Login");
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
					
					firstNameText = new JTextField(20);
					surnameText = new JTextField(20);
					addressText = new JTextField(20);
					JPanel panel = new JPanel(new GridLayout(6, 2));
					panel.add(firstNameLabel);
					panel.add(firstNameText);
					panel.add(surnameLabel);
					panel.add(surnameText);
					panel.add(addressLabel);
					panel.add(addressText);


					panel2 = new JPanel();
					add = new JButton("Add");

					add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							
							firstName = firstNameText.getText();
							surname = surnameText.getText();
							address = addressText.getText();
							password = "";



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


								

									CustomerDAO customerDAO = new CustomerDAO();
									customerDAO.persistObject(new Customer(firstName,surname,  address,password));

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
				
				// ----------------------------------------------------------------------------------------------------------------

				// if user selects CUSTOMER
				// ----------------------------------------------------------------------------------------
				if (user.equals("Customer Login")) {
					boolean userNotFound = true, inCorrectPassword = true;
					Customer aCustomer = null;
					CustomerDAO customerDAO = new CustomerDAO();
					while (userNotFound) {
						String customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

						 aCustomer = customerDAO.getSingleCustomer(Integer.parseInt(customerID));

						if (aCustomer == null) {
							int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								userNotFound = true;
							} else if (reply == JOptionPane.NO_OPTION) {
								f.dispose();
								userNotFound = false;
								inCorrectPassword = false;
								menuStart();
							}
						} else {
							userNotFound = false;
						}

					}

					while (inCorrectPassword) {
						Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

						if (!aCustomer.getPassword().equals(customerPassword))// check if custoemr password is correct
						{
							int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {

							} else if (reply == JOptionPane.NO_OPTION) {
								f.dispose();
								inCorrectPassword = false;
								menuStart();
							}
						} else {
							inCorrectPassword = false;
							
							f.dispose();
							userNotFound = false;
							customer(aCustomer);
						}
					}
					
				}
				// -----------------------------------------------------------------------------------------------------------------------

				// ------------------------------------------------------------------------------------------------------------------
				// if user select
				// ADMIN----------------------------------------------------------------------------------------------
				if (user.equals("Admin Login")) {
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
				f.dispose();
				
				f = new JFrame("Add New Stock Item");
				f.setSize(400, 400);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}
				});
				f.setVisible(true);
				
				Container content = f.getContentPane();
				content.setLayout(new BorderLayout());
				
				stockNameLabel = new JLabel("Title:", SwingConstants.RIGHT);
				stockPriceLabel = new JLabel("Price:", SwingConstants.RIGHT);
				stockCategoryLabel = new JLabel("Category:", SwingConstants.RIGHT);
				stockManufacturerLabel = new JLabel("Manufacturer", SwingConstants.RIGHT);
				
				stockNameText = new JTextField(20);
				stockPriceText = new JTextField(20);
				stockCategoryText = new JTextField(20);
				stockManufacturerText = new JTextField(20);
				
				JPanel panel = new JPanel(new GridLayout(8, 2));
				panel.add(stockNameLabel);
				panel.add(stockNameText);
				panel.add(stockPriceLabel);
				panel.add(stockPriceText);
				panel.add(stockCategoryLabel);
				panel.add(stockCategoryText);
				panel.add(stockManufacturerLabel);
				panel.add(stockManufacturerText);

				panel2 = new JPanel();
				add = new JButton("Add");

				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						
						String stockName = stockNameText.getText();
						int stockPrice = Integer.parseInt(stockPriceText.getText());
						String stockCategory = stockCategoryText.getText();
						String stockManufacturer = stockManufacturerText.getText();
						

						add.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
									StockDAO stockDAO = new StockDAO();
									
									stockDAO.persistObject(new StockItem(stockName, stockPrice, stockCategory, stockManufacturer));
							}
						});
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						admin();
					}
				});

				panel2.add(add);
				panel2.add(cancel);

				content.add(panel, BorderLayout.CENTER);
				content.add(panel2, BorderLayout.SOUTH);

				f.setVisible(true);

			}
		});
		
		viewStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StockDAO stockDAO = new StockDAO();
				
				stockList = stockDAO.getAllItems();
				f.dispose();
				
				JButton first, previous, next, last, cancel, edit, save;
				JPanel gridPanel, buttonPanel, actionPanel;

				f = new JFrame("View All Stock");
				f.setSize(400, 400);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}
				});
				f.setVisible(true);
				
				Container content = f.getContentPane();
				content.setLayout(new BorderLayout());

				buttonPanel = new JPanel();
				gridPanel = new JPanel(new GridLayout(12, 2));
				actionPanel = new JPanel();
				
				stockIDLabel = new JLabel("ID:", SwingConstants.LEFT);
				stockNameLabel = new JLabel("Title:", SwingConstants.LEFT);
				stockPriceLabel = new JLabel("Price:", SwingConstants.LEFT);
				stockCategoryLabel = new JLabel("Category:", SwingConstants.LEFT);
				stockManufacturerLabel = new JLabel("Manufacturer", SwingConstants.LEFT);
				stockQuantityLabel = new JLabel("Quantity", SwingConstants.LEFT);
				
				stockIDText = new JTextField(20);
				stockNameText = new JTextField(20);
				stockPriceText = new JTextField(20);
				stockCategoryText = new JTextField(20);
				stockManufacturerText = new JTextField(20);
				stockQuantityText = new JTextField(20);
				
				first = new JButton("First");
				previous = new JButton("Previous");
				next = new JButton("Next");
				last = new JButton("Last");
				
				edit = new JButton("Edit Item");
				save = new JButton("Save");
				cancel = new JButton("Cancel");

				displayStockInfo(stockList,0);
				
				stockIDText.setEditable(false);
				stockNameText.setEditable(false);
				stockPriceText.setEditable(false);
				stockCategoryText.setEditable(false);
				stockManufacturerText.setEditable(false);
				stockQuantityText.setEditable(false);
				
				gridPanel.add(stockIDLabel);
				gridPanel.add(stockIDText);
				gridPanel.add(stockNameLabel);
				gridPanel.add(stockNameText);
				gridPanel.add(stockPriceLabel);
				gridPanel.add(stockPriceText);
				gridPanel.add(stockCategoryLabel);
				gridPanel.add(stockCategoryText);
				gridPanel.add(stockManufacturerLabel);
				gridPanel.add(stockManufacturerText);
				gridPanel.add(stockQuantityLabel);
				gridPanel.add(stockQuantityText);
				
				buttonPanel.add(first);
				buttonPanel.add(previous);
				buttonPanel.add(next);
				buttonPanel.add(last);

				actionPanel.add(edit);
				actionPanel.add(save);
				actionPanel.add(cancel);
				
				
				content.add(gridPanel, BorderLayout.NORTH);
				content.add(buttonPanel, BorderLayout.CENTER);
				content.add(actionPanel, BorderLayout.AFTER_LAST_LINE);
				first.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						position = 0;
						displayStockInfo(stockList, position);
					}
				});
				
				previous.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						if (position < 1) {
							// don't do anything
						} else {
							position = position - 1;

							displayStockInfo(stockList, position);
						}
					}
				});

				next.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						if (position == stockList.size() - 1) {
							// don't do anything
						} else {
							position = position + 1;

							displayStockInfo(stockList, position);
						}

					}
				});

				last.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						position = stockList.size() - 1;

						displayStockInfo(stockList, position);
					}
				});
				
				edit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						stockNameText.setEditable(true);
						stockPriceText.setEditable(true);
						stockCategoryText.setEditable(true);
						stockManufacturerText.setEditable(true);
					}
				});
				
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						int stockItemID = Integer.parseInt(stockIDText.getText());
						String stockName = stockNameText.getText();
						int stockPrice = Integer.parseInt(stockPriceText.getText());
						String stockCategory = stockCategoryText.getSelectedText();
						String stockManufacturer = stockManufacturerText.getText();
						
						stockDAO.updateItem(stockItemID, stockName, stockPrice, stockCategory, stockManufacturer);
						
						stockNameText.setEditable(false);
						stockPriceText.setEditable(false);
						stockCategoryText.setEditable(false);
						stockManufacturerText.setEditable(false);
						stockList = stockDAO.getAllItems();	
					}
				});

				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						f.dispose();
						admin();
					}
				});
				setContentPane(content);
				setSize(400, 300);
				setVisible(true);
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
				
				CustomerDAO customerDAO = new CustomerDAO();
				customerList = customerDAO.getAllCustomers();
				f.dispose();
				
				f = new JFrame("Add New Stock Item");
				f.setSize(400, 400);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}
				});
				f.setVisible(true);
				
				Container customerContent = f.getContentPane();

				customerContent.setLayout(new BorderLayout());
				
				JButton firstCustomer, previousCustomer, nextCustomer, lastCustomer, cancelCustomer;
				JPanel gridPanel, buttonPanel, actionPanel;

				

				buttonPanel = new JPanel();
				gridPanel = new JPanel(new GridLayout(8, 2));
				actionPanel = new JPanel();
				
				customerIDLabel = new JLabel("ID:", SwingConstants.LEFT);
				firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
				surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
				addressLabel = new JLabel("Address:", SwingConstants.LEFT);

				
				customerIDText = new JTextField(20);
				firstNameText = new JTextField(20);
				surnameText = new JTextField(20);
				addressText = new JTextField(20);
	
				firstCustomer = new JButton("First");
				previousCustomer = new JButton("Previous");
				nextCustomer = new JButton("Next");
				lastCustomer = new JButton("Last");
				

				cancelCustomer = new JButton("Cancel");

				displayCustomerInfo(customerList, 0);
				
				customerIDText.setEditable(false);
				firstNameText.setEditable(false);
				surnameText.setEditable(false);
				addressText.setEditable(false);

				
				gridPanel.add(customerIDLabel);
				gridPanel.add(customerIDText);
				gridPanel.add(firstNameLabel);
				gridPanel.add(firstNameText);
				gridPanel.add(surnameLabel);
				gridPanel.add(surnameText);
				gridPanel.add(addressLabel);
				gridPanel.add(addressText);

				
				buttonPanel.add(firstCustomer);
				buttonPanel.add(previousCustomer);
				buttonPanel.add(nextCustomer);
				buttonPanel.add(lastCustomer);

				actionPanel.add(cancelCustomer);
				
				
				customerContent.add(gridPanel, BorderLayout.NORTH);
				customerContent.add(buttonPanel, BorderLayout.CENTER);
				customerContent.add(actionPanel, BorderLayout.AFTER_LAST_LINE);
				firstCustomer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						position = 0;
						displayCustomerInfo(customerList, position);
					}
				});
				
				previousCustomer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						if (position < 1) {
							// don't do anything
						} else {
							position = position - 1;

							displayCustomerInfo(customerList, position);
						}
					}
				});

				nextCustomer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						if (position == stockList.size() - 1) {
							// don't do anything
						} else {
							position = position + 1;

							displayCustomerInfo(customerList, position);
						}

					}
				});

				lastCustomer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						position = stockList.size() - 1;

						displayCustomerInfo(customerList, position);
					}
				});
				


				cancelCustomer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						f.dispose();
						admin();
					}
				});
				setContentPane(customerContent);
				setSize(400, 300);
				setVisible(true);
			
				
				

			}
		});

		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				menuStart();
			}
		});
	}
	
	public void customer(Customer aCustomer) {
		
		f.dispose();

		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		JPanel viewStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton viewStockButton = new JButton("View All Products");
		viewStockButton.setPreferredSize(new Dimension(250, 20));
		viewStockPanel.add(viewStockButton);

		JPanel searchStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton searchStockButton = new JButton("Search For a Product");
		searchStockButton.setPreferredSize(new Dimension(250, 20));
		searchStockPanel.add(searchStockButton);

		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Customer Menu");
		returnPanel.add(returnButton);

		JLabel optionLabel = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(4, 1));
		content.add(optionLabel);
		content.add(viewStockPanel);
		content.add(searchStockPanel);
		content.add(returnPanel);
		
	}
	
	public void displayStockInfo(ArrayList<StockItem> stockList, int position) {
		stockIDText.setText(String.valueOf(stockList.get(position).getStockItemID()));
		stockNameText.setText(stockList.get(position).getTitle());
		stockPriceText.setText(String.valueOf(stockList.get(position).getPrice()));
		stockCategoryText.setText(stockList.get(position).getCategory());
		stockManufacturerText.setText(stockList.get(position).getManufacturer());
		stockQuantityText.setText(String.valueOf(stockList.get(position).getQuanitity()));
		
	}
	
	public void displayCustomerInfo(ArrayList<Customer> customerList, int postition) {
		customerIDText.setText(String.valueOf(customerList.get(postition).getCustomerID()));
		firstNameText.setText(customerList.get(postition).getFirstName());
		surnameText.setText(customerList.get(postition).getSurname());
		addressText.setText(customerList.get(postition).getAddress());
	}
	
}
