package main;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.TextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cipher.Decrypt;
import cipher.Encrypt;

public class GUI {

    JFrame jframe;
    JTabbedPane jTabbedPane;
    Encrypt encrypt = new Encrypt();
    Decrypt decrypt = new Decrypt();

    public void setJframe() {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the red x is pressed, the window exits instead of working in background
        jframe.setTitle("Cryptograph"); // Set title
        jframe.setSize(700, 550); // Set the size 600 width and 400 height
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null); // Center window
        ImageIcon icon = new ImageIcon(getClass().getResource("Encrypt.png"));
        jframe.setIconImage(icon.getImage());
        
        addEDTabs(); // Add tabs for encrypt and decrypt

        jframe.setVisible(true); // Make the window visible
    }

    public void addEDTabs() {
        jTabbedPane = new JTabbedPane();

        // Create panels for each tab
        JPanel encryptPanel = createEncryptPanel();
        JPanel decryptPanel = createDecryptPanel();
        
        // Add panels as tabs
        jTabbedPane.addTab("Encrypt", encryptPanel);
        jTabbedPane.addTab("Decrypt", decryptPanel);

        // Add the tabbed pane to the frame
        jframe.add(jTabbedPane, BorderLayout.CENTER);
    }
    
    public JPanel createEncryptPanel() {
        JPanel encryptPanel = new JPanel(new BorderLayout());
        
        // SLIDER
        
        JSlider jSlider = new JSlider(1, 25); // Set the values of the slider between 1 and 25, since 0 will do nothing, and 26 will put each letter back in its place
        JLabel sliderValue = new JLabel("Key: " + jSlider.getValue()); // Create a JLabel to display the slider value
        
        // Connect the value and slider into one panel
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(sliderValue);
        sliderPanel.add(jSlider);
        
        // Add the sliderPanel and place it at the top
        encryptPanel.add(sliderPanel, BorderLayout.NORTH);
        
        // TEXT AREA
        
        TextArea textArea = new TextArea(5, 20);
        textArea.setEditable(false);
        encryptPanel.add(textArea, BorderLayout.CENTER);
        
        // JTEXTAREA
        
        // Add JTextArea for input
        JTextArea jTextArea = new JTextArea(7, 20); // Create a JTextArea with 7 rows and 20 columns
        jTextArea.setBackground(new Color(170, 170, 170)); // Set the colour to make it more visible
        jTextArea.setText("Input"); // Add dummy text
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add a bottom margin to the text area
        

        // Use a ScrollPane to display the text area
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        encryptPanel.add(scrollPane, BorderLayout.SOUTH); // Add the scrollPane with the textArea to the panel
        
        jTextArea.getDocument().addDocumentListener(new DocumentListener() { // Check for changes to run the encryption
            @Override
            public void insertUpdate(DocumentEvent e) {textArea.setText(encrypt.encrypt(jTextArea.getText(), jSlider.getValue()));}
            @Override
            public void removeUpdate(DocumentEvent e) {textArea.setText(encrypt.encrypt(jTextArea.getText(), jSlider.getValue()));}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        // Add a ChangeListener to update the JLabel and encryption when the slider value changes
        jSlider.addChangeListener(e -> {
            textArea.setText(encrypt.encrypt(jTextArea.getText(), jSlider.getValue()));
            sliderValue.setText("Key: " + jSlider.getValue());
        });
        textArea.setText(encrypt.encrypt(jTextArea.getText(), jSlider.getValue())); // Run once so that it shows up at the start
        
        // Return the JPanel with all the components
        return encryptPanel;
    }
    
    public JPanel createDecryptPanel() {
    	JPanel decryptPanel = new JPanel(new BorderLayout());
        
        // SLIDER
        
        JSlider jSlider = new JSlider(1, 25); // Set the values of the slider between 1 and 25, since 0 will do nothing, and 26 will put each letter back in its place
        JLabel sliderValue = new JLabel("Key: " + jSlider.getValue()); // Create a JLabel to display the slider value
        
        // Connect the value and slider into one panel
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(sliderValue);
        sliderPanel.add(jSlider);
        
        // Add the sliderPanel and place it at the top
        decryptPanel.add(sliderPanel, BorderLayout.NORTH);
        
        // TEXT AREA
        
        TextArea textArea = new TextArea(5, 20);
        textArea.setEditable(false);
        decryptPanel.add(textArea, BorderLayout.CENTER);
        
        // JTEXTAREA
        
        // Add JTextArea for input
        JTextArea jTextArea = new JTextArea(7, 20); // Create a JTextArea with 7 rows and 20 columns
        jTextArea.setBackground(new Color(170, 170, 170)); // Set the colour to make it more visible
        jTextArea.setText("Bhgchg"); // Add dummy text
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add a bottom margin to the text area
        

        // Use a ScrollPane to display the text area
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        decryptPanel.add(scrollPane, BorderLayout.SOUTH); // Add the scrollPane with the textArea to the panel
        
        jTextArea.getDocument().addDocumentListener(new DocumentListener() { // Check for changes to run the decryption
            @Override
            public void insertUpdate(DocumentEvent e) {textArea.setText(decrypt.decrypt(jTextArea.getText(), jSlider.getValue()));}
            @Override
            public void removeUpdate(DocumentEvent e) {textArea.setText(decrypt.decrypt(jTextArea.getText(), jSlider.getValue()));}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        // Add a ChangeListener to update the JLabel and decryption when the slider value changes
        jSlider.addChangeListener(e -> {
            textArea.setText(decrypt.decrypt(jTextArea.getText(), jSlider.getValue()));
            sliderValue.setText("Key: " + jSlider.getValue());
        });
        textArea.setText(decrypt.decrypt(jTextArea.getText(), jSlider.getValue())); // Run once so that it shows up at the start
        
        // Return the JPanel with all the components
        return decryptPanel;
    }
}