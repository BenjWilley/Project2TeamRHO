import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestaurantPOS extends JFrame {

    private JButton nextPageButton1;
    private JButton nextPageButton2;
    private JButton backButton;

    public RestaurantPOS() {
        // Initialize the buttons
        nextPageButton1 = new JButton("Server Menu");
        nextPageButton2 = new JButton("Manager Menu");

        // Set the preferred size of the buttons
        nextPageButton1.setPreferredSize(new Dimension(100, 50));
        nextPageButton2.setPreferredSize(new Dimension(100, 50));

        // Add an ActionListener to the first button
        nextPageButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new frame for the next page 1
                JFrame nextPageFrame1 = new JFrame("Server");
                nextPageFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                // Add a back button to the next page 1
                JButton backButton1 = new JButton("Back");
                backButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nextPageFrame1.dispose();  // Close the next page 1 frame
                        setVisible(true);  // Show the main frame
                    }
                });
                nextPageFrame1.add(backButton1, BorderLayout.SOUTH);  // Add the back button to the bottom of the frame
                
                nextPageFrame1.setVisible(true);
                nextPageFrame1.setSize(1000, 600);
                nextPageFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nextPageFrame1.setLocationRelativeTo(null);
                nextPageFrame1.setVisible(true);
                
                setVisible(false);  // Hide the main frame
            }
        });

        // Add an ActionListener to the second button
        nextPageButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new frame for the next page 2
                JFrame nextPageFrame2 = new JFrame("Manager");
                nextPageFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                // Add a back button to the next page 2
                JButton backButton2 = new JButton("Back");
                backButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nextPageFrame2.dispose();  // Close the next page 2 frame
                        setVisible(true);  // Show the main frame
                    }
                });
                nextPageFrame2.add(backButton2, BorderLayout.SOUTH);  // Add the back button to the bottom of the frame
                
                nextPageFrame2.setVisible(true);
                nextPageFrame2.setSize(1000, 600);
                nextPageFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nextPageFrame2.setLocationRelativeTo(null);
                nextPageFrame2.setVisible(true);
                
                setVisible(false);  // Hide the main frame
            }
        });

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // Set the layout of the panel to GridLayout
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Add the buttons to the panel
        buttonPanel.add(nextPageButton1);
        buttonPanel.add(nextPageButton2);

        // Add the panel to the frame
        this.add(buttonPanel, BorderLayout.CENTER);

        // Set up the main frame
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the RestaurantPOS class
        RestaurantPOS pos = new RestaurantPOS();
    }
}