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
                JButton backButton1 = new JButton("Home");
                backButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        server.dispose();  // Close the next page 1 frame
                        setVisible(true);  // Show the main frame
                    }
                });

        //create the server(order) page here
                setTitle("Server");

                //subtotal
                JTextPane subtotalTextBox = new JTextPane();
                subtotalTextBox.setText("Subtotal: $$$");
                subtotalTextBox.setEditable(false);
                server.add(subtotalTextBox,BorderLayout.WEST);




                // Create a JPanel with a CardLayout
                JPanel itemPanel = new JPanel(new CardLayout());
                
                // Create a JPanel with a CardLayout
                JPanel cardPanel = new JPanel(new CardLayout());


        //Coffee panel
                // Create the panels you want to switch between
                JPanel coffee = new JPanel();
                coffee.setLayout(new GridLayout(0,1));
                
                //adding coffee options
                        JRadioButton coffeeSelect  = new JRadioButton();
                        JRadioButton espressoSelect  = new JRadioButton();
                        JRadioButton frapSelect  = new JRadioButton();

                        //adding customization options
                        JRadioButton tallSelect  = new JRadioButton();
                        JRadioButton grandeSelect  = new JRadioButton();
                        JRadioButton ventiSelect  = new JRadioButton();
                        JCheckBox extraEspressoSelect  = new JCheckBox();
                        
                        // making the order button
                        JButton orderCoffeeButton = new JButton("Order");


                        // grouping the buttons
                        ButtonGroup drinkSelection = new ButtonGroup();
                        ButtonGroup customSelection = new ButtonGroup();


                        class Order{
                            String drink;
                            String size;
                            boolean customization = false;
                            double price = 0.0;
                        }
                        final Order order = new Order();

                        //assigning text to the buttons
                        coffeeSelect.setText("Freshly Brewed Coffee");
                        coffeeSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = coffeeSelect.getText();
                            }
                        });
                        espressoSelect.setText("Cappuccino");
                        espressoSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = espressoSelect.getText();
                            }
                        });
                        frapSelect.setText("Frappuccino Coffee");
                        frapSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = frapSelect.getText();
                            }
                        });

                        tallSelect.setText("Tall");
                        tallSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.size = "tall";
                            }
                        });
                        grandeSelect.setText("Grande");
                        grandeSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.size = "grande";
                            }
                        });
                        ventiSelect.setText("Venti");
                        ventiSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.size = "venti";
                            }
                        });
                        extraEspressoSelect.setText("Extra Espresso Shot");
                        extraEspressoSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.customization = true;
                            }
                        });

                        //adding all of the selections
                        coffee.add(new JLabel("Coffee Selection"));
                        coffee.add(coffeeSelect);
                        coffee.add(espressoSelect);
                        coffee.add(frapSelect);

                        coffee.add(new JLabel("Customizations"));
                        coffee.add(tallSelect);
                        coffee.add(grandeSelect);
                        coffee.add(ventiSelect);
                        coffee.add(extraEspressoSelect);

                        //adding the buttons to their respective groups
                        drinkSelection.add(coffeeSelect);
                        drinkSelection.add(espressoSelect);
                        drinkSelection.add(frapSelect);

                        customSelection.add(tallSelect);
                        customSelection.add(grandeSelect);
                        customSelection.add(ventiSelect);

                        //adding the order button
                        coffee.add(orderCoffeeButton);
                        
                        String sql = "SELECT * FROM menu where item = " + order.drink;

                        try{
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            if(order.size == "tall"){
                                while(rs.next()){
                                    order.price = rs.getDouble(4);
                                }  
                            }
                            else if(order.size == "grande"){
                                while(rs.next()){
                                    order.price = rs.getDouble(5);
                                } 
                            }
                            else{
                                while(rs.next()){
                                    order.price = rs.getDouble(6);
                                } 
                            }
                            System.out.println(order.price);
                        } catch(SQLException s){
                            s.printStackTrace();
                        }

            //Teas
                JPanel tea = new JPanel();
                tea.setLayout(new GridLayout(0,1));
                    //adding tea options
                        JRadioButton mintSelect  = new JRadioButton();
                        JRadioButton herbalSelect  = new JRadioButton();
                        JRadioButton greenSelect  = new JRadioButton();
                        JRadioButton hotCocoSelect  = new JRadioButton();

                        //adding customization options
                        JRadioButton tallTeaSelect  = new JRadioButton();
                        JRadioButton grandeTeaSelect  = new JRadioButton();
                        JRadioButton ventiTeaSelect  = new JRadioButton();
                        
                        
                        // making the order button
                        JButton orderTeaButton = new JButton("Order");


                        // grouping the buttons
                        ButtonGroup teaSelection = new ButtonGroup();
                        ButtonGroup customTeaSelection = new ButtonGroup();


                        //assigning text to the buttons
                        mintSelect.setText("Peppermint Tea");
                        herbalSelect.setText("Herbal Tea");
                        greenSelect.setText("Green Tea");
                        hotCocoSelect.setText("Hot Chocolate");

                        tallTeaSelect.setText("Tall");
                        grandeTeaSelect.setText("Grande");
                        ventiTeaSelect.setText("Venti");
                        

                        //adding all of the selections
                        tea.add(new JLabel("Drink Selection"));
                        tea.add(mintSelect);
                        tea.add(herbalSelect);
                        tea.add(greenSelect);
                        tea.add(hotCocoSelect);

                        tea.add(new JLabel("Customizations"));
                        tea.add(tallTeaSelect);
                        tea.add(grandeTeaSelect);
                        tea.add(ventiTeaSelect);
                        

                        //adding the buttons to their respective groups
                        teaSelection.add(mintSelect);
                        teaSelection.add(herbalSelect);
                        teaSelection.add(greenSelect);
                        teaSelection.add(hotCocoSelect);

                        customTeaSelection.add(tallTeaSelect);
                        customTeaSelection.add(grandeTeaSelect);
                        customTeaSelection.add(ventiTeaSelect);

                        //adding the order button
                        tea.add(orderTeaButton);
                
            //Breakfast Items    
                JPanel breakfast = new JPanel();
                breakfast.setLayout(new GridLayout(0,1));
                    //adding breakfast options
                        JRadioButton eggSelect  = new JRadioButton();
                        JRadioButton sammySelect  = new JRadioButton();
                        JRadioButton wrapSelect  = new JRadioButton();
                        JRadioButton impossibleSelect  = new JRadioButton();

        
                        // making the order button
                        JButton orderBreakfastButton = new JButton("Order");


                        // grouping the buttons
                        ButtonGroup breakfastSelection = new ButtonGroup();


                        //assigning text to the buttons
                        eggSelect.setText("Egg Bites");
                        sammySelect.setText("Bacon, Egg, and Cheese Sandwich");
                        wrapSelect.setText("Bacon, Sausage, and Egg Wrap");
                        impossibleSelect.setText("ImpossibleTM Breakfast Sandwich");
                       
                        

                        //adding all of the selections
                        breakfast.add(new JLabel("Breakfast Selection"));
                        breakfast.add(eggSelect);
                        breakfast.add(sammySelect);
                        breakfast.add(wrapSelect);
                        breakfast.add(impossibleSelect);

                        //adding the buttons to their respective groups
                        breakfastSelection.add(eggSelect);
                        breakfastSelection.add(sammySelect);
                        breakfastSelection.add(wrapSelect);
                        breakfastSelection.add(impossibleSelect);

                        //adding the order button
                        breakfast.add(orderBreakfastButton);

                JPanel bakery = new JPanel();
                bakery.setLayout(new GridLayout(0,1));
                    //adding breakfast options
                        JRadioButton crosSelect  = new JRadioButton();
                        JRadioButton muffinSelect  = new JRadioButton();
                        JRadioButton loafSelect  = new JRadioButton();
                        JRadioButton bagelSelect  = new JRadioButton();

        
                        // making the order button
                        JButton orderBakeryButton = new JButton("Order");

                        // grouping the buttons
                        ButtonGroup bakerySelection = new ButtonGroup();


                        //assigning text to the buttons
                        crosSelect.setText("Buttered Croissant");
                        muffinSelect.setText("Blueberry Muffin");
                        loafSelect.setText("Banana Nut Loaf");
                        bagelSelect.setText("Everything Bagel");
                       
                        

                        //adding all of the selections
                        bakery.add(new JLabel("Breakfast Selection"));
                        bakery.add(crosSelect);
                        bakery.add(muffinSelect);
                        bakery.add(loafSelect);
                        bakery.add(bagelSelect);

                        //adding the buttons to their respective groups
                        bakerySelection.add(crosSelect);
                        bakerySelection.add(muffinSelect);
                        bakerySelection.add(loafSelect);
                        bakerySelection.add(bagelSelect);

                        //adding the order button
                        bakery.add(orderBakeryButton);



                // Add the panels to the cardPanel using the add() method
                cardPanel.add(coffee, "coffee");
                cardPanel.add(tea, "tea");
                cardPanel.add(breakfast, "breakfast");
                cardPanel.add(bakery, "bakery");

                // Add the cardPanel to your main GUI panel
                itemPanel.add(cardPanel);

                //menu selector
                JPanel menuSelectorPanel = new JPanel();
                menuSelectorPanel.setLayout(new GridLayout(0,1));
                //coffee
                JButton coffeeButton = new JButton("Coffee");
                coffeeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                        cardLayout.show(cardPanel, "coffee");

                    }
                });
                menuSelectorPanel.add(coffeeButton);

                //tea and hot chocolate
                JButton teaButton = new JButton("Tea/Misc");
                teaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                        cardLayout.show(cardPanel, "tea");
                    }
                });
                menuSelectorPanel.add(teaButton);

                //bakery
                JButton bakeryButton = new JButton("Bakery");
                bakeryButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                        cardLayout.show(cardPanel, "bakery");
                    }
                });
                menuSelectorPanel.add(bakeryButton);

                //breakfast
                JButton breakfastButton = new JButton("Breakfast");
                breakfastButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                        cardLayout.show(cardPanel, "breakfast");
                    }
                });
                menuSelectorPanel.add(breakfastButton);


                menuSelectorPanel.setPreferredSize(new Dimension(300, 300));

                JSplitPane divider= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuSelectorPanel, itemPanel);
                server.add(divider, BorderLayout.CENTER);
                
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
                JButton backButton2 = new JButton("Home");
                backButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        manager.dispose();  // Close the next page 2 frame
                        setVisible(true);  // Show the main frame
                    }
                });


        //create the manager(inventory) page here        
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
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
                RestockList.addElement("good");
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
