import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class GymGUI {
    private JFrame frame;
    private ArrayList<GymMember> members = new ArrayList<>();
    private HashMap<String, Double> equipmentCart = new HashMap<>();
    private double cartTotal = 0.0;
    
    // Form components
    private JTextField IDField, nameField, locationField, phoneField, emailField;
    private JTextField ReferralField, TrainerField, paidAmountField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;
    private JComboBox<String> planCombo, dayCombo, monthCombo, yearCombo, startDayCombo, startMonthCombo, startYearCombo;
    
    // Display areas
    private JTextArea regularBox, premiumBox, cartBox;
    private JLabel cartTotalLabel;

    public GymGUI() {
        frame = new JFrame("Modern Gym Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        
        // Create tabbed interface
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Member Management", createMemberManagementPanel());
        tabbedPane.addTab("Equipment Store", createEquipmentStorePanel());
        
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
    
    private JPanel createMemberManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header
        JLabel header = new JLabel("GYM MEMBER MANAGEMENT", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(new Color(70, 130, 180));
        panel.add(header, BorderLayout.NORTH);
        
        // Center content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setLeftComponent(createMemberForm());
        splitPane.setRightComponent(createMemberDisplayArea());
        panel.add(splitPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createMemberForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder("Member Information"));
        
        // Personal Details Section
        JPanel personalPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        personalPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        
        personalPanel.add(new JLabel("Member ID:"));
        IDField = new JTextField();
        personalPanel.add(IDField);
        
        personalPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        personalPanel.add(nameField);
        
        personalPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        personalPanel.add(genderPanel);
        
        personalPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        personalPanel.add(locationField);
        
        personalPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        personalPanel.add(phoneField);
        
        personalPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        personalPanel.add(emailField);
        
        personalPanel.add(new JLabel("DOB:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dayCombo = new JComboBox<>(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"});
        monthCombo = new JComboBox<>(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
        yearCombo = new JComboBox<>(new String[]{"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024"});
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        personalPanel.add(dobPanel);
        
        personalPanel.add(new JLabel("Start Date:"));
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDayCombo = new JComboBox<>(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"});
        startMonthCombo = new JComboBox<>(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
        startYearCombo = new JComboBox<>(new String[]{"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024"});
        startDatePanel.add(startDayCombo);
        startDatePanel.add(startMonthCombo);
        startDatePanel.add(startYearCombo);
        personalPanel.add(startDatePanel);
        
        // Membership Details Section
        JPanel membershipPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        membershipPanel.setBorder(BorderFactory.createTitledBorder("Membership Details"));
        
        membershipPanel.add(new JLabel("Plan:"));
        planCombo = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        membershipPanel.add(planCombo);
        
        membershipPanel.add(new JLabel("Referral:"));
        ReferralField = new JTextField();
        membershipPanel.add(ReferralField);
        
        membershipPanel.add(new JLabel("Trainer:"));
        TrainerField = new JTextField();
        membershipPanel.add(TrainerField);
        
        membershipPanel.add(new JLabel("Paid Amount:"));
        paidAmountField = new JTextField();
        membershipPanel.add(paidAmountField);
        
        // Buttons Section
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        
        JButton addRegularBtn = createStyledButton("Add Regular");
        JButton addPremiumBtn = createStyledButton("Add Premium");
        JButton activateBtn = createStyledButton("Activate");
        JButton deactivateBtn = createStyledButton("Deactivate");
        JButton markAttendanceBtn = createStyledButton("Mark Attendance");
        JButton upgradeBtn = createStyledButton("Upgrade");
        JButton displayBtn = createStyledButton("Display All");
        JButton clearBtn = createStyledButton("Clear");
        
        // Add action listeners
        addRegularBtn.addActionListener(e -> addMember(true));
        addPremiumBtn.addActionListener(e -> addMember(false));
        activateBtn.addActionListener(e -> modifyMembership(true));
        deactivateBtn.addActionListener(e -> modifyMembership(false));
        markAttendanceBtn.addActionListener(this::markAttendance);
        upgradeBtn.addActionListener(this::upgradePlan);
        displayBtn.addActionListener(this::displayMembers);
        clearBtn.addActionListener(e -> clearFields());
        
        buttonPanel.add(addRegularBtn);
        buttonPanel.add(addPremiumBtn);
        buttonPanel.add(activateBtn);
        buttonPanel.add(deactivateBtn);
        buttonPanel.add(markAttendanceBtn);
        buttonPanel.add(upgradeBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(clearBtn);
        
        // Add sections to form panel
        formPanel.add(personalPanel);
        formPanel.add(membershipPanel);
        formPanel.add(buttonPanel);
        
        return formPanel;
    }
    
    private JPanel createMemberDisplayArea() {
        JPanel displayPanel = new JPanel(new BorderLayout());
        
        // Tabbed display for regular/premium members
        JTabbedPane memberTabs = new JTabbedPane();
        
        regularBox = new JTextArea();
        regularBox.setEditable(false);
        JScrollPane regularScroll = new JScrollPane(regularBox);
        regularScroll.setBorder(BorderFactory.createTitledBorder("Regular Members"));
        
        premiumBox = new JTextArea();
        premiumBox.setEditable(false);
        JScrollPane premiumScroll = new JScrollPane(premiumBox);
        premiumScroll.setBorder(BorderFactory.createTitledBorder("Premium Members"));
        
        memberTabs.addTab("Regular", regularScroll);
        memberTabs.addTab("Premium", premiumScroll);
        
        // Additional buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = createStyledButton("Save Data");
        JButton loadBtn = createStyledButton("Load Data");
        JButton revertRegularBtn = createStyledButton("Revert Regular");
        JButton revertPremiumBtn = createStyledButton("Revert Premium");
        JButton payDueBtn = createStyledButton("Pay Due");
        JButton calcDiscountBtn = createStyledButton("Calc Discount");
        
        // Add action listeners
        saveBtn.addActionListener(this::saveToFile);
        loadBtn.addActionListener(this::loadFromFile);
        revertRegularBtn.addActionListener(e -> revertMember(true));
        revertPremiumBtn.addActionListener(e -> revertMember(false));
        payDueBtn.addActionListener(this::payDueAmount);
        calcDiscountBtn.addActionListener(this::calculateDiscount);
        
        bottomPanel.add(saveBtn);
        bottomPanel.add(loadBtn);
        bottomPanel.add(revertRegularBtn);
        bottomPanel.add(revertPremiumBtn);
        bottomPanel.add(payDueBtn);
        bottomPanel.add(calcDiscountBtn);
        
        displayPanel.add(memberTabs, BorderLayout.CENTER);
        displayPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        return displayPanel;
    }
    
    private JPanel createEquipmentStorePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header
        JLabel header = new JLabel("GYM EQUIPMENT STORE", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(new Color(70, 130, 180));
        panel.add(header, BorderLayout.NORTH);
        
        // Center content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(600);
        splitPane.setLeftComponent(createEquipmentCatalog());
        splitPane.setRightComponent(createCartPanel());
        panel.add(splitPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createEquipmentCatalog() {
        JPanel catalogPanel = new JPanel(new BorderLayout());
        catalogPanel.setBorder(BorderFactory.createTitledBorder("Available Equipment"));
        
        // Sample equipment data
        String[][] equipmentData = {
            {"Treadmill", "1999.99", "High-end cardio machine"},
            {"Elliptical", "1499.99", "Low-impact cardio trainer"},
            {"Dumbbell Set", "299.99", "5-50lb rubber coated set"},
            {"Bench Press", "499.99", "Adjustable weight bench"},
            {"Yoga Mat", "39.99", "Premium non-slip mat"},
            {"Resistance Bands", "49.99", "Set of 5 with handles"},
            {"Kettlebell", "79.99", "20lb cast iron"},
            {"Jump Rope", "19.99", "Weighted speed rope"}
        };
        
        String[] columnNames = {"Equipment", "Price", "Description"};
        JTable equipmentTable = new JTable(equipmentData, columnNames);
        equipmentTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        equipmentTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        
        // Add to cart button
        JButton addToCartBtn = createStyledButton("Add to Cart");
        addToCartBtn.addActionListener(e -> {
            int selectedRow = equipmentTable.getSelectedRow();
            if (selectedRow >= 0) {
                String item = (String) equipmentTable.getValueAt(selectedRow, 0);
                double price = Double.parseDouble((String) equipmentTable.getValueAt(selectedRow, 1));
                equipmentCart.put(item, price);
                cartTotal += price;
                updateCartDisplay();
            }
        });
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(addToCartBtn);
        
        catalogPanel.add(scrollPane, BorderLayout.CENTER);
        catalogPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        return catalogPanel;
    }
    
    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createTitledBorder("Shopping Cart"));
        
        cartBox = new JTextArea();
        cartBox.setEditable(false);
        cartBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane cartScroll = new JScrollPane(cartBox);
        
        cartTotalLabel = new JLabel("Total: $0.00");
        cartTotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JButton checkoutBtn = createStyledButton("Checkout");
        checkoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Purchase completed! Total: $" + String.format("%.2f", cartTotal));
            equipmentCart.clear();
            cartTotal = 0.0;
            updateCartDisplay();
        });
        
        JButton removeBtn = createStyledButton("Remove Item");
        removeBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter item name to remove:");
            if (input != null && equipmentCart.containsKey(input)) {
                cartTotal -= equipmentCart.get(input);
                equipmentCart.remove(input);
                updateCartDisplay();
            }
        });
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        bottomPanel.add(cartTotalLabel);
        bottomPanel.add(removeBtn);
        bottomPanel.add(checkoutBtn);
        
        cartPanel.add(cartScroll, BorderLayout.CENTER);
        cartPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        return cartPanel;
    }
    
    private void updateCartDisplay() {
        cartBox.setText("");
        for (String item : equipmentCart.keySet()) {
            cartBox.append(String.format("%-20s $%.2f\n", item, equipmentCart.get(item)));
        }
        cartTotalLabel.setText("Total: $" + String.format("%.2f", cartTotal));
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }
    
    // ================== MEMBER MANAGEMENT LOGIC ==================
    
    private void addMember(boolean isRegular) {
        // Validate required fields
        if (IDField.getText().trim().isEmpty()) {
            showError("Please enter Member ID");
            return;
        }
        
        if (nameField.getText().trim().isEmpty()) {
            showError("Please enter Name");
            return;
        }
        
        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
            showError("Please select Gender");
            return;
        }
        
        if (locationField.getText().trim().isEmpty()) {
            showError("Please enter Location");
            return;
        }
        
        if (phoneField.getText().trim().isEmpty()) {
            showError("Please enter Phone");
            return;
        }
        
        if (emailField.getText().trim().isEmpty()) {
            showError("Please enter Email");
            return;
        }
        
        // Validate member-specific required fields
        if (isRegular && ReferralField.getText().trim().isEmpty()) {
            showError("Please enter Referral for Regular member");
            return;
        }
        if (!isRegular && TrainerField.getText().trim().isEmpty()) {
            showError("Please enter Trainer for Premium member");
            return;
        }
        if (isRegular && !TrainerField.getText().trim().isEmpty()) {
            showError("The Trainer service is only for premium members.");
            return;
        }

        try {
            // Parse input values
            int id = Integer.parseInt(IDField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            
            // Validate phone number format
            if (!phone.matches("[0-9]+")) {
                showError("Please enter a valid numeric phone number");
                return;
            }
            
            if (phone.length() < 10) {
                showError("Phone number must be at least 10 digits long");
                return;
            }

            String email = emailField.getText();
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";
            
            // Format dates from combo box selections
            String dob = dayCombo.getSelectedItem() + "-" + monthCombo.getSelectedItem() + "-" + yearCombo.getSelectedItem();
            String startDate = startDayCombo.getSelectedItem() + "-" + startMonthCombo.getSelectedItem() + "-" + startYearCombo.getSelectedItem();
            
            double paidAmount = 0;
            
            // Process payment amount if provided
            if (!paidAmountField.getText().trim().isEmpty()) {
                paidAmount = Double.parseDouble(paidAmountField.getText());
                
                // Validate payment amounts for member types
                if (isRegular && paidAmount < 6500) {
                    showMessage("The full payment of Rs. 6,500 is required to become a regular member.\nPlease pay full amount to be a member.");
                    return;
                }
                
                // Handle overpayment for regular members
                if (isRegular && paidAmount > 6500) {
                    showMessage("Regular membership maximum is Rs.6,500\nWe have accepted the payment and received Rs. 6,500.\nAny excess amount has been refunded");
                    paidAmount = 6500;
                    paidAmountField.setText("6500"); 
                }
                // Handle overpayment for premium members
                else if (!isRegular && paidAmount > 50000) {
                    showMessage("Premium membership maximum is Rs.50,000\nWe have accepted the payment and received Rs. 50,000.\nAny excess amount has been refunded");
                    paidAmount = 50000;
                    paidAmountField.setText("50000");
                }
            }

            // Check for duplicate member ID
            for (GymMember member : members) {
                if (member.getId() == id) {
                    showError("Member ID " + id + " already exists!");
                    return;
                }
            }

            GymMember newMember;
            
            // Create appropriate member type
            if (isRegular) {
                newMember = new RegularMember(
                    id, name, location, phone, email, gender, dob, startDate, ReferralField.getText()
                );
                
                // Show success message with member details
                showSuccess("Regular Member Added!\n" +"ID: " + id + "\n" +"Name: " + name + "\n" +
                           (paidAmount > 0 ? "Paid: Rs." + paidAmount + "/6,500" : "Payment Due: Rs.6,500"));
            } 
            else {
                // Create premium member
                newMember = new PremiumMember(
                    id, name, location, phone, email, gender, dob, startDate, TrainerField.getText()
                );
                
                // Process payment for premium member
                String paymentStatus = "Payment Due: Rs.50,000";
                if (paidAmount > 0) {
                    paymentStatus = ((PremiumMember)newMember).payDueAmount(paidAmount);
                }
                
                // Show success message with member details
                showSuccess("Premium Member Added!\n" +"ID: " + id + "\n" +"Name: " + name + "\n" +paymentStatus);
            }

            // Add new member to the list and clear form
            members.add(newMember);
            clearFields();

        } catch (NumberFormatException e) {
            showError("Please enter valid numbers in:\n- Member ID or Amount");
        } catch (Exception e) {
            showError("Unexpected error: " + e.getMessage());
        }
    }

    private void modifyMembership(boolean activate) {
        try {
            // Prompt for member ID
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
            
            // Find member and modify status
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (activate) {
                        member.activateMembership();
                        JOptionPane.showMessageDialog(frame, "Membership activated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        member.deactivateMembership();
                        JOptionPane.showMessageDialog(frame, "Membership deactivated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(frame, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAttendance(ActionEvent e) {
        try {
            // Prompt for member ID
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));

            // Find member and mark attendance
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member.isActive()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(frame, 
                            "Attendance marked for ID: " + id, 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, 
                            "Member is inactive!", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(frame, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void upgradePlan(ActionEvent e) {
        try {
            // Prompt for member ID
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
            boolean memberFound = false;
            
            // Find member and upgrade plan
            for (GymMember member : members) {
                if (member instanceof RegularMember && member.getId() == id) {
                    memberFound = true;
                    RegularMember regularMember = (RegularMember) member;
                    
                    // Check if member is active
                    if (!regularMember.isActive()) {
                        JOptionPane.showMessageDialog(frame, "Member is inactive! Activate membership first.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Get selected plan
                    String newPlan = (String) planCombo.getSelectedItem();
                    if (newPlan == null || newPlan.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select a plan.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Perform upgrade
                    String result = regularMember.upgradePlan(newPlan);
                    JOptionPane.showMessageDialog(frame, result, "Upgrade Status", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            
            // Member not found or already premium
            if (!memberFound) {
                JOptionPane.showMessageDialog(frame, "You are already Premium Member", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateDiscount(ActionEvent e) {
        try {
            // Prompt for member ID
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
            
            // Find premium member and calculate discount
            for (GymMember member : members) {
                if (member instanceof PremiumMember && member.getId() == id) {
                    PremiumMember premiumMember = (PremiumMember) member;
                   
                    // Check if full payment was made
                    if (premiumMember.isFullPayment()) {
                        // Show discount information
                        JOptionPane.showMessageDialog(frame, 
                            "10% Discount Applied!\n" +
                            "Discount Amount: Rs." + premiumMember.getDiscountAmount() + "\n" +
                            "Total Paid: Rs.50,000 (Full Payment)",
                            "Discount Calculated", 
                            JOptionPane.INFORMATION_MESSAGE);
                              
                        // Calculate the discount
                        premiumMember.calculateDiscount();
                    } else {
                       showMessage("This service is only available to customers who have made the full payment upfront.");
                    }
                   
                    // Refresh display
                    displayMembers(null);
                    return;
                }
            }
            
            // Premium member not found
            JOptionPane.showMessageDialog(frame, 
                "Premium member not found with ID: " + id, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, 
                "Please enter a valid numeric ID", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void revertMember(boolean isRegular) {
        try {
            // Prompt for member ID
            String input = JOptionPane.showInputDialog("Enter Member ID:");
            if (input == null)
                return;

            int id = Integer.parseInt(input);

            // Prompt for removal reason
            String reason = JOptionPane.showInputDialog("Why is this member being removed?");
            if (reason == null) {
                showError("Must give a reason for removal");
                return;
            }

            boolean found = false;
            GymMember toRemove = null;

            // Find member and mark for removal
            for (GymMember member : members) {
                if (member.getId() == id) {
                    found = true;
                    if (isRegular && member instanceof RegularMember) {
                        ((RegularMember) member).revertRegularMember(reason);
                        toRemove = member; // mark for removal
                        showSuccess("Regular member removed!");
                    } else if (!isRegular && member instanceof PremiumMember) {
                        ((PremiumMember) member).revertPremiumMember(reason);
                        toRemove = member; // mark for removal
                        showSuccess("Premium member removed!");
                    } else {
                        showError("Wrong member type selected");
                    }
                    break; // exit loop once found
                }
            }

            // Remove member if found
            if (toRemove != null) {
                members.remove(toRemove);
            }

            // Member not found
            if (!found) {
                showError("Member not found!");
            }

        } catch (NumberFormatException e) {
            showError("Please enter a valid number for ID");
        }
    }

    private void payDueAmount(ActionEvent e) {
        try {
            // Prompt for member ID and payment amount
            String idInput = JOptionPane.showInputDialog("Enter Member ID:");
            if (idInput == null) 
                return;  // user cancelled
            int id = Integer.parseInt(idInput);

            String amtInput = JOptionPane.showInputDialog("Enter Amount:");
            if (amtInput == null) 
                return; // user cancelled
            double amount = Double.parseDouble(amtInput);

            // Find the premium member and process payment
            for (GymMember gm : members) {
                if (gm instanceof PremiumMember && gm.getId() == id) {
                    PremiumMember premium = (PremiumMember) gm;

                    // Apply payment to dues
                    String result = premium.payDueAmount(amount);
                    JOptionPane.showMessageDialog(frame, result, "Payment", JOptionPane.INFORMATION_MESSAGE);

                    // Refresh the display
                    displayMembers(null);
                    return;
                }
            }

            // No matching premium member found
            JOptionPane.showMessageDialog(
                frame,
                "Premium member not found with ID: " + id,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

        } catch (NumberFormatException ex) {
            // Handle non-numeric input
            JOptionPane.showMessageDialog(
                frame,
                "Invalid input format! Please enter numeric values.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void displayMembers(ActionEvent e) {
        if (members.isEmpty()) {
            regularBox.setText("No members found. Fill out the form to add one!");
            premiumBox.setText("No members found. Fill out the form to add one!");
            return;
        }

        String regularMembersInfo = "";
        String premiumMembersInfo = "";

        for (GymMember member : members) {
            String memberInfo = "";
            memberInfo += "----------------------------------------\n";
            memberInfo += "ID: " + member.getId() + "\n";
            memberInfo += "Name: " + member.getName() + "\n";
            memberInfo += "Date of Birth: " + member.getDOB() + "\n";
            memberInfo += "Membership Start Date: " + member.getMembershipStartDate() + "\n";
            memberInfo += "Status: " + (member.isActive() ? "Active" : "Inactive") + "\n";
            memberInfo += "Attendance: " + member.getAttendance() + "\n";
            memberInfo += "Loyalty Points: " + member.getLoyaltyPoints() + "\n";

            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;
                memberInfo += "Type: Regular\n";
                memberInfo += "Plan: " + regularMember.getPlan() + "\n";
                memberInfo += "Price: Rs." + regularMember.getPrice() + "\n";
                memberInfo += "Referral: " + regularMember.getReferralSource() + "\n\n";
                regularMembersInfo += memberInfo;
            } else if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;
                memberInfo += "Type: Premium\n";
                memberInfo += "Trainer: " + premiumMember.getPersonalTrainer() + "\n";
                memberInfo += "Paid Amount: Rs." + premiumMember.getPaidAmount() + "\n";
                memberInfo += "Discount: Rs." + premiumMember.getDiscountAmount() + "\n\n";
                premiumMembersInfo += memberInfo;
            }
        }

        regularBox.setText(regularMembersInfo.isEmpty() ? "No regular members found." : regularMembersInfo);
        premiumBox.setText(premiumMembersInfo.isEmpty() ? "No premium members found." : premiumMembersInfo);
    }

    private void saveToFile(ActionEvent e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
            writer.println(String.format("%110s", "Gym Members Details"));
            writer.println("=".repeat(150));

            writer.printf("%-5s %-12s %-12s %-15s %-25s %-20s %-10s %-10s %-12s %-14s %-10s %-14s %-10s %-12s\n",
                    "ID", "Name", "Location", "Phone", "Email", "Start Date", "Plan", "Price",
                    "Attendance", "Loyalty Pts", "Status", "Full Pay", "Discount", "Net Paid");
            writer.println("-".repeat(150));

            for (GymMember member : members) {
                String plan = "";
                String price = "";
                String fullPayment = "";
                String discount = "";
                String netPaid = "";

                if (member instanceof RegularMember) {
                    RegularMember rm = (RegularMember) member;
                    plan = rm.getPlan();
                    price = String.format("%.2f", rm.getPrice());
                    fullPayment = "Yes";
                    discount = "N/A";
                    netPaid = String.format("%.2f", rm.getPrice());
                } else if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    plan = "Premium";
                    price = "50000.00";
                    fullPayment = pm.isFullPayment() ? "Yes" : "No";
                    discount = String.format("%.2f", pm.getDiscountAmount());
                    netPaid = String.format("%.2f", pm.getPaidAmount());
                }

                writer.printf("%-5d %-12s %-12s %-15s %-25s %-20s %-10s %-10s %-12d %-14.1f %-10s %-14s %-10s %-12s\n",
                        member.getId(), member.getName(), member.getLocation(), member.getPhone(), member.getEmail(),
                        member.getMembershipStartDate(), plan, price, member.getAttendance(), member.getLoyaltyPoints(),
                        (member.isActive() ? "Active" : "Inactive"), fullPayment, discount, netPaid);
            }

            JOptionPane.showMessageDialog(frame, "Data saved to MemberDetails.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
        }
    }

    private void loadFromFile(ActionEvent e) {
        JFrame displayFrame = new JFrame("Member Details");
        JTextArea textArea = new JTextArea(25, 110);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(textArea);

        try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
            String line;
            StringBuilder allText = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                allText.append(line).append("\n");
            }
            textArea.setText(allText.toString());

            displayFrame.add(scrollPane);
            displayFrame.pack();
            displayFrame.setLocationRelativeTo(frame);
            displayFrame.setVisible(true);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "File not found. Please save data first.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage());
        }
    }

    private void clearFields() {
        IDField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        ReferralField.setText("");
        TrainerField.setText("");
        paidAmountField.setText("");
        genderGroup.clearSelection();
        planCombo.setSelectedIndex(0);
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        startDayCombo.setSelectedIndex(0);
        startMonthCombo.setSelectedIndex(0);
        startYearCombo.setSelectedIndex(0);
        regularBox.setText("To view your information, please click | Display All |.");
        premiumBox.setText("To view your information, please click | Display All |.");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Notice", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GymGUI());
    }
}