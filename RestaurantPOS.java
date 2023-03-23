import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.table.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;


//added for the z report filename
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;


public class RestaurantPOS extends JFrame {
    /**
     * This is the main class of the POS
     * @author Ashwin Kundeti Ben Willey Emil Agbigay Olen Brown
     * @version 1.0
     */

    /**
     * Server button leads to server page and manager button leads to manager page
     * @author Ashwin Kundeti Ben Willey Emil Agbigay Olen Brown
     */
    private JButton serverButton;
    private JButton managerButton;
    private Connection conn = null;
    private double total = 0.0;
    private String report = "Report \n";
    private double revenue=0;
    
    public RestaurantPOS() {

        //Global variables for the x/z Reports

        




        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_rho",
            "csce315331_rho_master", "RHO");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
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
                subtotalTextBox.setText("Subtotal: $$$\n");
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
                        JRadioButton brewCoffeeSelect  = new JRadioButton();
                        JRadioButton espressoSelect  = new JRadioButton();
                        JRadioButton mochaSelect  = new JRadioButton();
                        JRadioButton carmSelect  = new JRadioButton();
                        JRadioButton latteSelect  = new JRadioButton();
                        JRadioButton iceSelect  = new JRadioButton();
                        JRadioButton coldSelect  = new JRadioButton();
                        JRadioButton hotSelect  = new JRadioButton();

                        //adding customization options
                        JRadioButton tallSelect  = new JRadioButton();
                        JRadioButton grandeSelect  = new JRadioButton();
                        JRadioButton ventiSelect  = new JRadioButton();
                        JCheckBox extraEspressoSelect  = new JCheckBox();


                        // grouping the buttons
                        ButtonGroup drinkSelection = new ButtonGroup();
                        ButtonGroup customSelection = new ButtonGroup();


                        class Order{
                            String drink="Freshly Brewed Coffee";
                            String size="grande";
                            boolean customization = false;
                            double price = 0.0;
                        }
                        final Order order = new Order();

                        //assigning text to the buttons
                        brewCoffeeSelect.setText("Freshly Brewed Coffee");
                        brewCoffeeSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = brewCoffeeSelect.getText();
                            }
                        });
                        espressoSelect.setText("Cappuccino");
                        espressoSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = espressoSelect.getText();
                            }
                        });
                        hotSelect.setText("Hot Chocolate");
                        hotSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = hotSelect.getText();
                            }
                        });

                        mochaSelect.setText("Caffe Mocha");
                        mochaSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = mochaSelect.getText();
                            }
                        });
                        carmSelect.setText("Caramel Macchiato");
                        carmSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = carmSelect.getText();
                            }
                        });
                        latteSelect.setText("Caffe Latte");
                        latteSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = latteSelect.getText();
                            }
                        });

                        iceSelect.setText("Ice Coffee");
                        iceSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = iceSelect.getText();
                            }
                        });
                        coldSelect.setText("Cold Brew Coffee");
                        coldSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                order.drink = coldSelect.getText();
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
                                if(extraEspressoSelect.isSelected()){
                                    order.customization = true;
                                }
                                else{
                                    order.customization = false;
                                }
                            }
                        });

                        //adding all of the selections
                        coffee.add(new JLabel("Coffee Selection"));
                        coffee.add(brewCoffeeSelect);
                        coffee.add(espressoSelect);
                        coffee.add(mochaSelect);
                        coffee.add(carmSelect);
                        coffee.add(latteSelect);
                        coffee.add(iceSelect);
                        coffee.add(coldSelect);
                        coffee.add(hotSelect);


                        coffee.add(new JLabel("Customizations"));
                        coffee.add(tallSelect);
                        coffee.add(grandeSelect);
                        coffee.add(ventiSelect);
                        coffee.add(extraEspressoSelect);

                        //adding the buttons to their respective groups
                        drinkSelection.add(brewCoffeeSelect);
                        drinkSelection.add(espressoSelect);
                        drinkSelection.add(mochaSelect);
                        drinkSelection.add(carmSelect);
                        drinkSelection.add(latteSelect);
                        drinkSelection.add(iceSelect);
                        drinkSelection.add(coldSelect);
                        drinkSelection.add(hotSelect);
                        brewCoffeeSelect.setSelected(true);

                        customSelection.add(tallSelect);
                        customSelection.add(grandeSelect);
                        customSelection.add(ventiSelect);
                        grandeSelect.setSelected(true);

                        // making the order button
                        JButton orderCoffeeButton = new JButton("Order");
                        orderCoffeeButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){


                                //2022-03-01 08:00:09 current Date format

                                String sql = "SELECT * FROM menu where item = '" +order.drink+"'";
                                String editTable = "UPDATE inventory SET quantity = quantity - ? WHERE id = ?";

                                try{
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    PreparedStatement pstmt = conn.prepareStatement(editTable);
                                    int quantSub = 3;
                                    int id = 6;
                                    pstmt.setInt(1, quantSub);
                                    pstmt.setInt(2, id);
                                    int rowsUpdated = pstmt.executeUpdate();

                                    if(rowsUpdated > 0){
                                        System.out.println("Subtracted coffee from inventory");
                                    }
                                    else{
                                        System.out.println("No updates made to inventory");
                                    }

                                    if(order.size == "tall"){
                                        while(rs.next()){
                                            order.price = rs.getDouble(4);
                                        }
                                        quantSub = 1;
                                        id = 1;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted TallCups from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        }
                                    }
                                    else if(order.size == "grande"){
                                        while(rs.next()){
                                            order.price = rs.getDouble(5);
                                        }
                                        quantSub = 1;
                                        id = 2;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted GrandeCups from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        } 
                                    }
                                    else {
                                        while(rs.next()){
                                            order.price = rs.getDouble(6);
                                        }
                                        quantSub = 1;
                                        id = 3;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted VentiCups from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        } 
                                    }

                                    if(order.customization){
                                        order.price += 1;
                                        quantSub = 1;
                                        id = 10;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted Caramel Shot from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        }
                                    }             
                                    
                                } catch(SQLException s){
                                    s.printStackTrace();
                                }



                                System.out.println("Drink: " + order.drink + " Size: " + order.size + " Espresso Shots: " + order.customization + " Price: " + order.price);

                                StyledDocument edit = subtotalTextBox.getStyledDocument();

                                SimpleAttributeSet keyWord = new SimpleAttributeSet();
                                StyleConstants.setForeground(keyWord, Color.BLACK);
                                StyleConstants.setBackground(keyWord, Color.WHITE);
                                StyleConstants.setBold(keyWord, false);

                                try{
                                    edit.insertString(edit.getLength(), "\n" + order.drink + " " + order.size + " " + order.price, keyWord);

                                    //this is for the reports X/Z
                                    report+="\n" + order.drink + " " + order.size + " $" + order.price;
                                    
                                    revenue +=order.price;
                                }
                                catch(Exception d){
                                    System.out.println(d);
                                }
                            }
                        });

                        //adding the order button
                        coffee.add(orderCoffeeButton);
                        
                        

            //Teas
                JPanel tea = new JPanel();
                tea.setLayout(new GridLayout(0,1));
                    //adding tea options
                        JRadioButton mintSelect  = new JRadioButton();
                        JRadioButton chaiSelect  = new JRadioButton();
                        JRadioButton greySelect  = new JRadioButton();
                        JRadioButton royalSelect  = new JRadioButton();
                        JRadioButton peachSelect  = new JRadioButton();
                        JRadioButton blackSelect  = new JRadioButton();
                        JRadioButton seasonSelect  = new JRadioButton();

                        //adding customization options
                        JRadioButton tallTeaSelect  = new JRadioButton();
                        JRadioButton grandeTeaSelect  = new JRadioButton();
                        JRadioButton ventiTeaSelect  = new JRadioButton();
                        
                        
                        // making the order button
                        JButton orderTeaButton = new JButton("Order");
                        JTextField seasonal =  new JTextField(16);


                        // grouping the buttons
                        ButtonGroup teaSelection = new ButtonGroup();
                        ButtonGroup customTeaSelection = new ButtonGroup();


                        class OrderTea{
                            String drink="Mint Majesty";
                            String size="grande";
                            double price = 0.0;
                        }
                        final OrderTea orderTea = new OrderTea();

           
                        //assigning text to the buttons
                        mintSelect.setText("Mint Majesty");
                        mintSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = mintSelect.getText();
                            }
                        });
                        royalSelect.setText("Royal English Breakfast");
                        royalSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = royalSelect.getText();
                            }
                        });
                        chaiSelect.setText("Chai");
                        chaiSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = chaiSelect.getText();
                            }
                        });
                        greySelect.setText("Earl Grey");
                        greySelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = greySelect.getText();
                            }
                        });
                        peachSelect.setText("Peach Tranquility");
                        peachSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = peachSelect.getText();
                            }
                        });
                        
                        blackSelect.setText("Shaken Iced Black Tea");
                        blackSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = blackSelect.getText();
                            }
                        });
                        seasonSelect.setText("Seasonal drink");
                        seasonSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.drink = seasonal.getText();
                            }
                        });



                        tallTeaSelect.setText("Tall");
                        tallTeaSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.size = "tall";
                            }
                        });
                        grandeTeaSelect.setText("Grande");
                        grandeTeaSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.size = "grande";
                            }
                        });
                        ventiTeaSelect.setText("Venti");
                        ventiTeaSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderTea.size = "venti";
                            }
                        });



                        //adding all of the selections
                        tea.add(new JLabel("Tea Selection"));
                        tea.add(mintSelect);
                        tea.add(chaiSelect);
                        tea.add(greySelect);
                        tea.add(peachSelect);
                        tea.add(royalSelect);
                        tea.add(blackSelect);
                        tea.add(new JLabel("Seasonal Drink/Search"));
                        tea.add(seasonSelect);
                        tea.add(seasonal);


                        tea.add(new JLabel("Customizations"));
                        tea.add(tallTeaSelect);
                        tea.add(grandeTeaSelect);
                        tea.add(ventiTeaSelect);
                        

                        //adding the buttons to their respective groups
                        teaSelection.add(mintSelect);
                        teaSelection.add(chaiSelect);
                        teaSelection.add(greySelect);
                        teaSelection.add(peachSelect);
                        teaSelection.add(royalSelect);
                        teaSelection.add(blackSelect);
                        teaSelection.add(seasonSelect);
                        mintSelect.setSelected(true);

                        customTeaSelection.add(tallTeaSelect);
                        customTeaSelection.add(grandeTeaSelect);
                        customTeaSelection.add(ventiTeaSelect);
                        grandeTeaSelect.setSelected(true);


                        orderTeaButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                
                                String sql = "SELECT * FROM menu where item = '" +orderTea.drink+"'";
                                String editTable = "UPDATE inventory SET quantity = quantity - ? WHERE id = ?";

                                try{
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    PreparedStatement pstmt = conn.prepareStatement(editTable);
                                    int quantSub = 1;
                                    int id = 7;
                                    pstmt.setInt(1, quantSub);
                                    pstmt.setInt(2, id);
                                    int rowsUpdated = pstmt.executeUpdate();

                                    if(rowsUpdated > 0){
                                        System.out.println("Subtracted tea from inventory");
                                    }
                                    else{
                                        System.out.println("No updates made to inventory");
                                    }

                                    if(orderTea.size == "tall"){
                                        while(rs.next()){
                                            orderTea.price = rs.getDouble(4);
                                        }  
                                        quantSub = 1;
                                        id = 1;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted TallCups from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        }
                                    }
                                    else if(orderTea.size == "grande"){
                                        while(rs.next()){
                                            orderTea.price = rs.getDouble(5);
                                        } 
                                        quantSub = 1;
                                        id = 2;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted GrandeCups from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        }
                                    }
                                    else {
                                        while(rs.next()){
                                            orderTea.price = rs.getDouble(6);
                                        } 
                                        quantSub = 1;
                                        id = 3;
                                        pstmt.setInt(1, quantSub);  
                                        pstmt.setInt(2, id);
                                        rowsUpdated = pstmt.executeUpdate();
                                        if(rowsUpdated > 0){
                                            System.out.println("Subtracted VentiCups from inventory");
                                        }
                                        else{
                                            System.out.println("No updates made to inventory");
                                        }
                                    }

                                
                                } catch(SQLException s){
                                    System.out.println("HI");
                                    s.printStackTrace();
                                }
                                
                                System.out.println("Drink: " + orderTea.drink + " Size: " + orderTea.size  +" Price: " + orderTea.price);

                                StyledDocument edit = subtotalTextBox.getStyledDocument();

                                SimpleAttributeSet keyWord = new SimpleAttributeSet();
                                StyleConstants.setForeground(keyWord, Color.BLACK);
                                StyleConstants.setBackground(keyWord, Color.WHITE);
                                StyleConstants.setBold(keyWord, false);

                                try{
                                    edit.insertString(edit.getLength(), "\n" + orderTea.drink + " " + orderTea.size + " " + orderTea.price, keyWord);

                                    //this is for the reports X/Z
                                    report+="\n" + orderTea.drink + " " + orderTea.size + " $" + orderTea.price;
                                    
                                    revenue +=orderTea.price;
                                }
                                catch(Exception d){
                                    System.out.println(d);
                                }
                            }
                        });

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

                        class OrderBreak{
                            String drink="Double Smoked Bacon and Cheddar";
                            
                            double price = 0.0;
                        }
                        final OrderBreak orderBreak = new OrderBreak();

                        //assigning text to the buttons
                        eggSelect.setText("Double Smoked Bacon and Cheddar");
                        eggSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderBreak.drink = eggSelect.getText();
                            }
                        });
                        sammySelect.setText("Sausage and Cheddar");
                        sammySelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderBreak.drink = sammySelect.getText();
                            }
                        });
                        wrapSelect.setText("Chicken Sausage and Bacon Biscuit");
                        wrapSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderBreak.drink = wrapSelect.getText();
                            }
                        });
                        impossibleSelect.setText("Spicy Chorizo Monterey Jack and Egg");
                        impossibleSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderBreak.drink = impossibleSelect.getText();
                            }
                        });
                       
                        

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

                        eggSelect.setSelected(true);


                        orderBreakfastButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                
                                String sql = "SELECT * FROM menu where item = '" +orderBreak.drink+"'";
                                String editTable = "UPDATE inventory SET quantity = quantity - ? WHERE item = ?";

                                try{
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    PreparedStatement pstmt = conn.prepareStatement(editTable);
                                    int quantSub = 1;
                                    pstmt.setInt(1, quantSub);
                                    pstmt.setString(2, orderBreak.drink);
                                    int rowsUpdated = pstmt.executeUpdate();

                                    if(rowsUpdated > 0){
                                        System.out.println("Subtracted " + orderBreak.drink + " from inventory");
                                    }
                                    else{
                                        System.out.println("No updates made to inventory");
                                    }
                                    
                                    while(rs.next()){
                                        orderBreak.price = rs.getDouble(4);
                                    }
                                    
                                
                                } catch(SQLException s){
                                    System.out.println("HI");
                                    s.printStackTrace();
                                }
                                
                                System.out.println("Drink: " + orderBreak.drink + "  Price: " + orderBreak.price);

                                StyledDocument edit = subtotalTextBox.getStyledDocument();

                                SimpleAttributeSet keyWord = new SimpleAttributeSet();
                                StyleConstants.setForeground(keyWord, Color.BLACK);
                                StyleConstants.setBackground(keyWord, Color.WHITE);
                                StyleConstants.setBold(keyWord, false);

                                try{
                                    edit.insertString(edit.getLength(), "\n" + orderBreak.drink + " " + orderBreak.price, keyWord);

                                    //this is for the reports X/Z
                                    report+="\n" + orderBreak.drink + "  $" + orderBreak.price;
                                    
                                    revenue +=orderBreak.price;
                                }
                                catch(Exception d){
                                    System.out.println(d);
                                }
                            }
                        });

                        //adding the order button
                        breakfast.add(orderBreakfastButton);
                        class OrderBakery{
                            String drink="Butter Croissant";
    
                            double price = 0.0;
                        }
                        final OrderBakery orderB = new OrderBakery();

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
                        crosSelect.setText("Butter Croissant");
                        crosSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderB.drink = crosSelect.getText();
                            }
                        });
                        muffinSelect.setText("Blueberry Muffin");
                        muffinSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderB.drink = muffinSelect.getText();
                            }
                        });
                        loafSelect.setText("Iced Lemon Loaf Cake");
                        loafSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderB.drink = loafSelect.getText();
                            }
                        });
                        bagelSelect.setText("Banana Nut Bread");
                        bagelSelect.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                orderB.drink = bagelSelect.getText();
                            }
                        });
                       
                        

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
                        crosSelect.setSelected(true);

                        orderBakeryButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                
                                String sql = "SELECT * FROM menu where item = '" +orderB.drink+"'";
                                String editTable = "UPDATE inventory SET quantity = quantity - ? WHERE item = ?";

                                try{
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    PreparedStatement pstmt = conn.prepareStatement(editTable);
                                    int quantSub = 1;
                                    pstmt.setInt(1, quantSub);
                                    pstmt.setString(2, orderB.drink);
                                    int rowsUpdated = pstmt.executeUpdate();

                                    if(rowsUpdated > 0){
                                        System.out.println("Subtracted " + orderB.drink + " from inventory");
                                    }
                                    else{
                                        System.out.println("No updates made to inventory");
                                    }
                                    
                                        while(rs.next()){
                                            orderB.price = rs.getDouble(4);
                                        }
                                    
                                
                                } catch(SQLException s){
                                    System.out.println("HI");
                                    s.printStackTrace();
                                }
                                
                                System.out.println("Drink: " + orderB.drink + "  Price: " + orderB.price);

                                StyledDocument edit = subtotalTextBox.getStyledDocument();

                                SimpleAttributeSet keyWord = new SimpleAttributeSet();
                                StyleConstants.setForeground(keyWord, Color.BLACK);
                                StyleConstants.setBackground(keyWord, Color.WHITE);
                                StyleConstants.setBold(keyWord, false);

                                try{
                                    edit.insertString(edit.getLength(), "\n" + orderB.drink + " " + orderB.price, keyWord);

                                    //this is for the reports X/Z
                                    report+="\n" + orderB.drink + "  $" + orderB.price;
                                    
                                    revenue +=orderB.price;
                                }
                                catch(Exception d){
                                    System.out.println(d);
                                }
                            }
                        });
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
                JButton teaButton = new JButton("Tea/Seasonal");
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


        JTable inventoryTable = new JTable();
        JScrollPane inventoryScroll = new JScrollPane();
        inventoryScroll.setViewportView(inventoryTable);

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
                manager.add(inventory,BorderLayout.NORTH);
                manager.getContentPane();

                // adding the scrolling list
                //JPanel inventoryList = new JPanel(new BorderLayout());
                // JTextArea inventoryArea = new JTextArea(10, 30);
                

                //xReport and zReport///////////////////////////////////////////////////////////////////////////////
                JButton xReportButton = new JButton("X Report");
                        
                xReportButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                        System.out.println("X "+report+"\nrevenue:   $"+revenue+"\n\n");

                    } 
                } );

                
                JTextField restockID = new JTextField("enter item ID");


                JButton restockButton = new JButton("Restock");
                restockButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                        
                        try{
                            String restocknum = restockID.getText();
                            String sql = "UPDATE inventory SET quantity = quantity + 10000 WHERE id = ?";

                            int restockInt = Integer.parseInt(restocknum);
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setInt(1, restockInt);

                            int rowsUpdated = pstmt.executeUpdate();

                            if(rowsUpdated > 0){
                                System.out.println("Updated item id " + restocknum);
                            }
                            else{
                                System.out.println("No updates made to inventory");
                            }

                        } catch (SQLException m) {
                            m.printStackTrace();
                          }
                    } 
                } );

                JButton zReportButton = new JButton("Z Report");
                
                zReportButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 

                        try{
                        FileWriter zReport = new FileWriter("ZReport"+LocalDate.now()+".txt");
                         zReport.write("Z "+report+"\nrevenue:   $"+revenue+"\n");
                        zReport.close();
                        
                        System.out.println("\nZ report stored in file: "+"ZReport"+LocalDate.now()+".txt\n");

                        report= "Report \n";
                        revenue = 0;
                        } catch (IOException m) {
                            System.out.println("Cant Name the z report that");
                            m.printStackTrace();
                          }
                    } 
                } );

                JButton restockReportButton = new JButton("Restock Report");

                restockReportButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String sql = "SELECT * FROM inventory";
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            FileWriter restockReport = new FileWriter("RestockReport"+LocalDate.now()+".txt");
                            restockReport.write("Restock Report for " + LocalDate.now() + "\n");
                            restockReport.write("----------------------------------------\n");
                            restockReport.write("Level  | Item   \n");
                            restockReport.write("----------------------------------------\n");
                            while(rs.next()){
                                int minimum = rs.getInt("restockquantity");
                                int level = rs.getInt("quantity");
                                String item_name = rs.getString("item");

                                
                                if(level > minimum){
                                    restockReport.write("Enough | ");
                                } else{
                                    restockReport.write("Low    | ");
                                }

                                restockReport.write(item_name + "\n");

                            }
                            restockReport.close();

                        } catch(IOException m){
                            System.out.println("Failed to create the restock report : IO error");
                            m.printStackTrace();
                        } catch(SQLException s){
                            System.out.println("Failed to create the restock report : SQL error");
                            s.printStackTrace();
                        }
                    }
                } );
                
                JButton refreshButton = new JButton("Refresh");
                refreshButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                        try{
                            String sql = "SELECT * FROM inventory";
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                            model.setRowCount(0);
                            inventoryTable.setModel(model);
                            int cols = rsmd.getColumnCount();

                            String[] colName=new String[cols];
                                    for(int i =0;i < cols;i++){
                                        colName[i] = rsmd.getColumnName(i+1);
                                    }
                                    model.setColumnIdentifiers(colName);

                                    String id,item, quantity,restockQuantity; 
                                    while(rs.next()){
                                        id = rs.getString(1);
                                        item = rs.getString(2);
                                        quantity = rs.getString(3);
                                        restockQuantity = rs.getString(4);

                                        String[] row = {id,item, quantity,restockQuantity};
                                        model.addRow(row);
                                    }
                                    
                        }
                        catch(SQLException s){
                            System.out.println("HI");
                            s.printStackTrace();
                        }
                    } 
                } );

                 // making the seasonal menu items
                 JButton addSeasonal= new JButton("Add To Menu");
                 JTextField seasonalMenu =  new JTextField("Item Name");
                 JTextField seasonalPrice =  new JTextField("Item Price(double)");
                class OrderSeasonal{
                            String drink="";
                            double price = 0.0;
                        }

                        final OrderSeasonal orderSeason = new OrderSeasonal();


                        addSeasonal.addActionListener(new ActionListener() { 
                            public void actionPerformed(ActionEvent e) { 
                                orderSeason.drink = seasonalMenu.getText();
                                orderSeason.price = Double.parseDouble(seasonalPrice.getText());
                                try{
                                    Statement stmt = conn.createStatement();
                                    
                                    stmt.executeUpdate("INSERT INTO menu " + "VALUES ('drink','seasonal','"+orderSeason.drink+"', "+ orderSeason.price +","+orderSeason.price+","+orderSeason.price+")");
                                                                       
                                } catch(SQLException s){
                                    System.out.println("HI");
                                    s.printStackTrace();
                                }

                                System.out.println("Seasonal Item: "+orderSeason.drink+ " at $"+ orderSeason.price+" has been added to the menu");
                              
                            } 
                        } );

                 


                
                
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////

                //lists added to panel
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new GridLayout(1,1));
                itemPanel.add(inventoryScroll);
                //itemPanel.add(RestockListJ);
                
                ///PHASE 4 Requirement Buttons
                JPanel controlPanel = new JPanel();
                controlPanel.setLayout(new GridLayout(0,1));
                JLabel seasonalLabel = new JLabel("Add Seasonal Item");
                controlPanel.add(seasonalLabel);
                controlPanel.add(seasonalMenu);
                controlPanel.add(seasonalPrice);
                controlPanel.add(addSeasonal);
                controlPanel.add(xReportButton);
                controlPanel.add(zReportButton);
                controlPanel.add(refreshButton);
                controlPanel.add(restockID);
                controlPanel.add(restockButton);
                controlPanel.add(restockReportButton);
                
                
            

                // puts into viewable list pair
                JScrollPane itemScroll = new JScrollPane();
                itemScroll.setViewportView(itemPanel);


                // Trends side
                JPanel trendsPanel = new JPanel(new GridLayout(0,1));

                // drop down for weeks
                JPanel dateRange = new JPanel(new GridLayout(3,1));
                
                JLabel dateText = new JLabel("Enter date range mm-dd-yyyy/mm-dd-yyyy");
                dateRange.add(dateText);

                JTextField inputDate = new JTextField();
                inputDate.setLayout(new GridLayout(1,2));

                dateRange.add(inputDate, BorderLayout.CENTER);


                JButton dateButton = new JButton("Enter");
            
                dateRange.add(dateButton);

                trendsPanel.add(dateRange);

                JTable dateTable = new JTable();
                JScrollPane dateScroll = new JScrollPane();
                dateScroll.setViewportView(dateTable);

                dateButton.addActionListener(new ActionListener(){
                    public void actionPerformed (ActionEvent e) {
                        
                        try{
                            String twoDates = inputDate.getText();
                            String[] splitDates = twoDates.split("/");
                            String firstID = splitDates[0];
                            String secondID = splitDates[1];
                            String sql = "SELECT name, COUNT(*) FROM sales WHERE sales.date >= '"+firstID+"' and sales.date <= '"+secondID+ "' GROUP BY name";
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            ResultSetMetaData rsmd = rs.getMetaData();

                            DefaultTableModel model = (DefaultTableModel) dateTable.getModel();
                            model.setRowCount(0);
                            dateTable.setModel(model);
                            
                            int cols = rsmd.getColumnCount();
                            String[] colName=new String[cols];
                            for(int i =0;i < cols;i++){
                                colName[i] = rsmd.getColumnName(i+1);
                            }
                            model.setColumnIdentifiers(colName);

                            String itemName, quantity; 
                            while(rs.next()){
                                itemName = rs.getString(1);
                                quantity = rs.getString(2);
                                String[] row = {itemName, quantity};
                                model.addRow(row);
                            }
                            
                        }
                        catch(SQLException s){
                                    System.out.println("HI");
                                    s.printStackTrace();
                        }
                    }
                });
                
                trendsPanel.add(dateScroll);
                
               
                //put all panels together
                JPanel inventoryPanel = new JPanel(new GridLayout(1,1));
                inventoryPanel.add(itemScroll);
                inventoryPanel.add(controlPanel);
                inventoryPanel.add(trendsPanel);

                
                manager.add(inventoryPanel);

                manager.setSize(1000, 600);
                manager.setVisible(true);
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