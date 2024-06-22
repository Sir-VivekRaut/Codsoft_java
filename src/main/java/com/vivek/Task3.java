package com.vivek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task3 extends JFrame implements ActionListener {

    private JTextField display;
    private JButton depositButton, withdrawButton, checkBalanceButton, transferButton;
    private double balance;

    public Task3() {
        // Initialize balance
        balance = 0.0;

        // Set up the frame
        setTitle("ATM Interface");
        getContentPane().setBackground(Color.CYAN);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 60, 160, 50);
        depositButton.addActionListener(this);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 140, 160, 50);
        withdrawButton.addActionListener(this);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(10, 210, 160, 50);
        checkBalanceButton.addActionListener(this);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(10, 280, 160, 50);
        transferButton.addActionListener(this);

        // Set layout to null for absolute positioning
        setLayout(null);

        // Add components to the frame
        add(display);
        display.setBounds(10, 10, 460, 40); // Positioning the display field
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(transferButton);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount :₹");
            if (amountStr != null && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                balance += amount;
                display.setText("Deposited: ₹" + amount);
            }
        } else if (e.getSource() == withdrawButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter withdrawal amount :₹");
            if (amountStr != null && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                if (amount <= balance) {
                    balance -= amount;
                    display.setText("Withdrew: ₹" + amount);
                } else {
                    display.setText("Insufficient balance");
                }
            }
        } else if (e.getSource() == checkBalanceButton) {
            display.setText("Balance: ₹" + balance);
        } else if (e.getSource() == transferButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter transfer amount :₹");
            if (amountStr != null && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                if (amount <= balance) {
                    balance -= amount;
                    display.setText("Transferred: ₹" + amount);
                } else {
                    display.setText("Insufficient balance");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task3().setVisible(true));
    }
}
