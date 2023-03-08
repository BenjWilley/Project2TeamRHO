import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;

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
                manager.add(backButton2, BorderLayout.SOUTH);  // Add the back button to the bottom of the frame
                
                // Adding text for inventory
                JLabel inventory = new JLabel("Inventory");
                Font invFont = inventory.getFont();
                inventory.setFont(new Font(invFont.getName(),invFont.getStyle(),24));
                manager.add(inventory, BorderLayout.NORTH);
                manager.add(new JLabel(), BorderLayout.EAST);
                manager.getContentPane();


                // adding the scrolling list
                JPanel inventoryList = new JPanel(new BorderLayout());
                // JTextArea inventoryArea = new JTextArea(10, 30);
                DefaultListModel<String> itemList = new DefaultListModel<>();
                itemList.addElement("TallCups");
                itemList.addElement("GrandeCups");
                itemList.addElement("VentiCups");
                itemList.addElement("ShortCups");
                itemList.addElement("Napkins");
                itemList.addElement("LightGrounds(oz.)");
                itemList.addElement("MediumGrounds(oz.)");
                itemList.addElement("DarkGrounds(oz.)");
                itemList.addElement("Teabags");
                itemList.addElement("WholeMilk(oz.)");
                itemList.addElement("2%(oz.)");
                itemList.addElement("NonFatMilk(oz.)");
                itemList.addElement("SoyMilk(oz.)");
                itemList.addElement("AlmondMilk(oz.)");
                itemList.addElement("OatMilk(oz.)");
                itemList.addElement("CoconutMilk(oz.)");
                itemList.addElement("CaramelSyrupPumps");
                itemList.addElement("CaramelSyrupPumps");
                itemList.addElement("No-sugarVanillaSyrup");
                itemList.addElement("PeppermintSyrupPumps");
                itemList.addElement("WhiteMochaSauce");
                itemList.addElement("CaramelDrizzle");
                itemList.addElement("StrawberryAcaiBase");
                itemList.addElement("MangoDragonfruitBase");
                itemList.addElement("FrappuccinoCremeBase");
                itemList.addElement("FrappuccinoCoffeeBase");
                itemList.addElement("ChocolateChips");
                itemList.addElement("Half-n-HalfCups");
                itemList.addElement("HeavyCreamCups");
                JList<String> list = new JList<>(itemList);
                JScrollPane inventoryScroll = new JScrollPane(list);
                inventoryList.add(inventoryScroll, BorderLayout.WEST);
                //manager.getContentPane().add(inventoryList);

                // Restock Bar
                JPanel restock = new JPanel(new BorderLayout());
                DefaultListModel<String> RestockList = new DefaultListModel<>();
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("out");
                RestockList.addElement("good");
                RestockList.addElement("out");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("out");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("out");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("low");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                JList<String> RestockListJ = new JList<>(RestockList);
                JScrollPane RestockScroll = new JScrollPane(RestockListJ);
                restock.add(RestockScroll, BorderLayout.WEST);

                JPanel itemRestock = new JPanel(new GridLayout(1,1));
                itemRestock.add(inventoryList);
                itemRestock.add(restock);
                manager.add(itemRestock);
                //manager.getContentPane().add(restock);



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