package com.vivek;

import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}
class StudentManagementSystem {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        saveStudents();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found. Starting with an empty list.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }

    public static boolean validateInput(String name, int rollNumber, String grade) {
        return name != null && !name.trim().isEmpty() && rollNumber > 0 && grade != null && !grade.trim().isEmpty();
    }
}


public class Task5 extends JFrame implements ActionListener {
    private JButton addButton, removeButton, searchButton, displayButton;
    private StudentManagementSystem sms;

    public Task5() {
        // Initialize the StudentManagementSystem
        sms = new StudentManagementSystem();

        // Set up the frame
        setTitle("Raisoni High School");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addButton = new JButton("Add Student");
        addButton.setBounds(30, 30, 200, 50);
        addButton.addActionListener(this);

        removeButton = new JButton("Remove Student");
        removeButton.setBounds(250, 30, 200, 50);
        removeButton.addActionListener(this);

        searchButton = new JButton("Search Student");
        searchButton.setBounds(30, 100, 200, 50);
        searchButton.addActionListener(this);

        displayButton = new JButton("Display All Students");
        displayButton.setBounds(250, 100, 200, 50);
        displayButton.addActionListener(this);

        add(addButton);
        add(removeButton);
        add(searchButton);
        add(displayButton);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = JOptionPane.showInputDialog(this, "Enter Student Name:");
            String rollNumberStr = JOptionPane.showInputDialog(this, "Enter Student Roll Number:");
            String grade = JOptionPane.showInputDialog(this, "Enter Student Grade:");
            if (name != null && rollNumberStr != null && grade != null) {
                try {
                    int rollNumber = Integer.parseInt(rollNumberStr);
                    if (StudentManagementSystem.validateInput(name, rollNumber, grade)) {
                        sms.addStudent(new Student(name, rollNumber, grade));
                        JOptionPane.showMessageDialog(this, "Student added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid roll number. Please enter a number.");
                }
            }
        } else if (e.getSource() == removeButton) {
            String rollNumberStr = JOptionPane.showInputDialog(this, "Enter Student Roll Number to Remove:");
            if (rollNumberStr != null) {
                try {
                    int rollNumber = Integer.parseInt(rollNumberStr);
                    sms.removeStudent(rollNumber);
                    JOptionPane.showMessageDialog(this, "Student removed successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid roll number. Please enter a number.");
                }
            }
        } else if (e.getSource() == searchButton) {
            String rollNumberStr = JOptionPane.showInputDialog(this, "Enter Student Roll Number to Search:");
            if (rollNumberStr != null) {
                try {
                    int rollNumber = Integer.parseInt(rollNumberStr);
                    Student student = sms.searchStudent(rollNumber);
                    if (student != null) {
                        JOptionPane.showMessageDialog(this, "Student found: " + student);
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid roll number. Please enter a number.");
                }
            }
        } else if (e.getSource() == displayButton) {
            java.util.List<Student> students = sms.getAllStudents();
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students found.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Student student : students) {
                    sb.append(student).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task5().setVisible(true));
    }
}