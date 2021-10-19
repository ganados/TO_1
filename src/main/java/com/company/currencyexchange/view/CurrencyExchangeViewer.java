package com.company.currencyexchange.view;

import com.company.currencyexchange.domain.Currencies;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CurrencyExchangeViewer {
    private final JFrame jFrame;
    private final JButton jButton;
    private JTextField jTextFieldIn;
    private JTextField jTextFieldOut;
    private JTextArea logs;
    private JComboBox jComboBoxIn;
    private JComboBox jComboBoxOut;

    public CurrencyExchangeViewer() {
        jFrame = new JFrame();
        jButton = new JButton("Convert");
        jTextFieldOut = new JTextField("result here");
        jTextFieldIn = new JTextField();
        logs = new JTextArea();
    }

    public void createGUI(final Currencies currencies) {

        JLabel jLabelFrom = getLabel("From... ", 50, 10);
        JLabel jLabelTo = getLabel("... to ", 400, 10);

        jComboBoxIn = getComboBox(currencies, 50, 50);
        jComboBoxOut = getComboBox(currencies, 400, 50);

        JLabel jLabelValue = getLabel("How much", 50, 100);

        jTextFieldIn.setBounds(150, 100, 100, 30);
        jTextFieldOut.setEditable(false);
        jTextFieldOut.setBounds(300, 100, 100, 30);
        logs.setBounds(300, 150, 400, 18);
        logs.setEditable(false);


        jButton.setBounds(50, 150, 100, 30);

        jFrame.add(jComboBoxIn);
        jFrame.add(jComboBoxOut);

        jFrame.add(jLabelFrom);
        jFrame.add(jLabelTo);
        jFrame.add(jLabelValue);

        jFrame.add(jTextFieldIn);
        jFrame.add(jTextFieldOut);
        jFrame.add(logs);

        jFrame.add(jButton);

        jFrame.setSize(770, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addActionListener(final ActionListener actionListener) {
        jButton.addActionListener(actionListener);
    }

    public void setTextFieldOut(final String out) {
        jTextFieldOut.setText(out);
    }

    public void updateLogs(String message) {
//        String actualValue = logs.getText();
        logs.setText(String.format("%s\n", message));
    }

    public String getCurrencyIn() {
        return jComboBoxIn.getItemAt(jComboBoxIn.getSelectedIndex()).toString();
    }

    public String getCurrencyOut() {
        return jComboBoxOut.getItemAt(jComboBoxOut.getSelectedIndex()).toString();
    }

    public String getValue() {
        return jTextFieldIn.getText();
    }

    private JLabel getLabel(final String s, final int x, final int y) {
        JLabel jLabelFrom = new JLabel(s);
        jLabelFrom.setBounds(x, y, 100, 30);
        return jLabelFrom;
    }

    private JComboBox getComboBox(final Currencies currencies, final int x, final int y) {
        JComboBox jComboBoxIn = new JComboBox(toTable(currencies));
        jComboBoxIn.setBounds(x, y, 300, 30);
        return jComboBoxIn;
    }

    private String[] toTable(final Currencies currencies) {
        int i = 0;
        String[] accessibleCurrencies = new String[currencies.getCurrencies().size()];
        for (String code : currencies.getCurrencies().keySet()) {
            accessibleCurrencies[i++] = currencies.getCurrency(code).getName();
        }
        return accessibleCurrencies;
    }
}
