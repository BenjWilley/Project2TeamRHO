import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class RestaurantPOS extends JFrame {

    private JButton serverButton;
    private JButton managerButton;
    private Connection conn;

    public RestaurantPOS() {
        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_rho",
            "csce315331_rho_master", "RHO");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }

        // Initialize the buttons
        serverButton = new JButton("Server Menu");
        managerButton = new JButton("Manager Menu");

        // Set the preferred size of the buttons
        serverButton.setPreferredSize(new Dimension(100, 50));
        managerButton.setPreferredSize(new Dimension(100, 50));

        // Add an ActionListener to the first button
        serverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new frame for the next page 1
                JFrame server = new JFrame("Server");
                server.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                // Add a back button to the next page 1
                JButton backButton1 = new JButton("Back");
                backButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        server.dispose();  // Close the next page 1 frame
                        setVisible(true);  // Show the main frame
                    }
                });

        //create the server(order) page here
                


                server.add(backButton1, BorderLayout.SOUTH);  // Add the back button to the bottom of the frame
                
                server.setVisible(true);
                server.setSize(1000, 600);
                server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                server.setLocationRelativeTo(null);
                server.setVisible(true);
                
                setVisible(false);  // Hide the main frame
            }
        });

        // Add an ActionListener to the second button
        managerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new frame for the next page 2
                JFrame manager = new JFrame("Manager");
                manager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                // Add a back button to the next page 2
                JButton backButton2 = new JButton("Back");
                backButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        manager.dispose();  // Close the next page 2 frame
                        setVisible(true);  // Show the main frame
                    }
                });


        //create the manager(inventory) page here        
                manager.add(backButton2, BorderLayout.SOUTH);  // Add the back button to the bottom of the frame
                
                manager.setVisible(true);
                manager.setSize(1000, 600);
                manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                manager.setLocationRelativeTo(null);
                manager.setVisible(true);
                
                setVisible(false);  // Hide the main frame
            }
        });

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // Set the layout of the panel to GridLayout
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Add the buttons to the panel
        buttonPanel.add(serverButton);
        buttonPanel.add(managerButton);

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
