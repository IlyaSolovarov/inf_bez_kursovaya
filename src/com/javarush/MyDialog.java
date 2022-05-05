package com.javarush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog extends JDialog implements ActionListener {

    JLabel label;
    JButton OKButton;
    JPanel jBottom;

    public MyDialog(Frame owner, String message) {
        super(owner);
        this.setSize(600,400);
        this.setModal(true);
        label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        OKButton = new JButton("OK");
        OKButton.setPreferredSize(new Dimension(100, 50));
        jBottom = new JPanel();
        jBottom.setPreferredSize(new Dimension(640, 80));
        jBottom.add(OKButton);
        OKButton.addActionListener(this);
        this.add(label, BorderLayout.CENTER);
        this.add(jBottom, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OKButton) {
            this.dispose();
        }
    }
}
