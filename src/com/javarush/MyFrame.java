package com.javarush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem parameters;
    JMenuItem fileOperations;
    JMenuItem algorithm;
    JMenuItem aboutProgram;
    JPanel file_screen;
    JPanel param_screen;
    JButton encrypt_button;
    JButton decrypt_button;
    JButton chooseInput;
    JButton chooseOutput;
    JTextArea inputFileName;
    JTextArea OutputFileName;
    JTextArea keyField;
    JLabel keyLabel;
    JLabel infLabel;
    JLabel outfLabel;
    int horizGap = 20;
    int verticGap = 20;

    public MyFrame() {
        this.setTitle("Алгоритм шифрования MISTY1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(625, 400);
        this.setLayout(null);

        file_screen = new JPanel(null);
        param_screen = new JPanel(null);

        keyLabel = new JLabel("128-битный ключ:");
        keyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        keyLabel.setBounds(50,35,200,30);

        keyField = new JTextArea();
        keyField.setText(Resources.default_key);
        keyField.setBounds(50,80,450,30);
        keyField.setFont(new Font("Arial", Font.BOLD, 20));

        param_screen.add(keyField, BorderLayout.CENTER);
        param_screen.add(keyLabel);

        infLabel = new JLabel("Входной файл");
        outfLabel = new JLabel("Выходной файл");
        infLabel.setBounds(10 + horizGap, -15 + verticGap, 100, 100);
        outfLabel.setBounds(10 + horizGap, 60 + verticGap, 100, 100);

        inputFileName = new JTextArea();
        OutputFileName = new JTextArea();
        inputFileName.setFont(new Font("Arial", Font.PLAIN, 18));
        OutputFileName.setFont(new Font("Arial", Font.PLAIN, 18));
        inputFileName.setBounds(10 + horizGap,50 + verticGap,330 + horizGap,30);
        OutputFileName.setBounds(10 + horizGap,125 + verticGap,330 + horizGap,30);
        encrypt_button = new JButton("Зашифровать");
        decrypt_button = new JButton("Расшифровать");
        chooseInput = new JButton("Выбрать файл");
        chooseOutput= new JButton("Выбрать файл");
        chooseInput.setBounds(360 + (horizGap << 1), 50 + verticGap, 150, 30);
        chooseOutput.setBounds(360 + (horizGap << 1), 125 + verticGap, 150, 30);
        encrypt_button.setBounds(10 + horizGap,200 + verticGap,150,30);
        decrypt_button.setBounds(190 + (horizGap << 1),200 + verticGap,150,30);


        file_screen.add(encrypt_button);
        file_screen.add(decrypt_button);
        file_screen.add(inputFileName);
        file_screen.add(OutputFileName);
        file_screen.add(infLabel);
        file_screen.add(outfLabel);
        file_screen.add(chooseInput);
        file_screen.add(chooseOutput);

        encrypt_button.addActionListener(this);
        decrypt_button.addActionListener(this);
        chooseInput.addActionListener(this);
        chooseOutput.addActionListener(this);

        this.setContentPane(file_screen);

        //this.setIconImage();
        //menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        helpMenu = new JMenu("Справка");
        parameters = new JMenuItem("Параметры");
        fileOperations = new JMenuItem("Действия с файлами");
        algorithm = new JMenuItem("Алгоритм ...");
        aboutProgram = new JMenuItem("О программе");

        parameters.addActionListener(this);
        fileOperations.addActionListener(this);
        algorithm.addActionListener(this);
        aboutProgram.addActionListener(this);

        fileMenu.add(parameters);
        fileMenu.add(fileOperations);
        helpMenu.add(algorithm);
        helpMenu.add(aboutProgram);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        this.setVisible(true);
        ImageIcon img = new ImageIcon("resources/logo_m.png");
        this.setIconImage(img.getImage());
    }

    public boolean checkKeyfield() {
        String text = keyField.getText();

        return text.length() == Resources.default_key.length() && text.matches("([0-9a-fA-F]{2} ){15}[0-9a-fA-F]{2}");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == parameters) {
            this.setContentPane(param_screen);
            this.getContentPane().revalidate();
            this.repaint();
        } else if (e.getSource() == fileOperations) {
            this.setContentPane(file_screen);
            this.getContentPane().revalidate();
            this.repaint();
        } else if (e.getSource() == algorithm) {
            new MyDialog(this, Resources.algoDescription);
        } else if (e.getSource() == aboutProgram) {
            new MyDialog(this, Resources.aboutProgramm);
        } else if (e.getSource() == encrypt_button) {
            if (!checkKeyfield()) {
                JOptionPane.showMessageDialog(this, Resources.keyErrorMsg, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Main.encrypt_engine(keyField.getText(), inputFileName.getText(), OutputFileName.getText());
                JOptionPane.showMessageDialog(this, Resources.Encrypt_Successful, "Сообщение", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ошибка!\n" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == decrypt_button) {
            if (!checkKeyfield()) {
                JOptionPane.showMessageDialog(null, Resources.keyErrorMsg, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Main.decrypt_engine(keyField.getText(), inputFileName.getText(), OutputFileName.getText());
                JOptionPane.showMessageDialog(this, Resources.Decrypt_Successful, "Сообщение", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ошибка!\n" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == chooseInput) {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(new File("C:\\test"));
            if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                inputFileName.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == chooseOutput) {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(new File("C:\\test"));
            if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                OutputFileName.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        }

    }
}
