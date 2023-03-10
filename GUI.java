import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;

/*
  TODO:
  1) Change credentials for your own team's database
  2) Change SQL command to a relevant query that retrieves a small amount of data
  3) Create a JTextArea object using the queried data
  4) Add the new object to the JPanel p
*/

public class GUI extends JFrame implements ActionListener {
    static JFrame f;


    //array is used to switch their visibility on and off(initialized to show welcome)
      static JPanel[] panels = new JPanel[4];
      static boolean[] panelVisible = new boolean[]{true,false,false,false};



//most of main is connecting to the SQL file
    public static void main(String[] args)
    {
      //Building the connection
      Connection conn = null;
      //TODO STEP 1
      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_rho",
           "csce315331_rho_master", "RHO");
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
      }
      JOptionPane.showMessageDialog(null,"Opened database successfully");

      String name = "";
      try{
        //create a statement object
        Statement stmt = conn.createStatement();
        //create an SQL statement
        //TODO Step 2
        String sqlStatement = "select * from sales";
        //send statement to DBMS
        ResultSet result = stmt.executeQuery(sqlStatement);
        while (result.next()) {
          name += result.getString("Week")+"\n";
        }
      } catch (Exception e){
        // JOptionPane.showMessageDialog(null,"Error accessing Database.");
        System.out.println(e.getMessage());
      }

      
      // create a new frame
      f = new JFrame("DB GUI");

      // create a object
      GUI s = new GUI();

      //creating the close, manager, and server buttons
      JButton closeButton = new JButton("Close");
      closeButton.addActionListener(s);
      closeButton.setActionCommand("Close");
      closeButton.setPreferredSize(new Dimension(100, 50));
      
      JButton managerButton = new JButton("Manager");
      managerButton.addActionListener(s);
      managerButton.setActionCommand("Manager");
      managerButton.setPreferredSize(new Dimension(100, 50));
      
      JButton serverButton = new JButton("Server");
      serverButton.addActionListener(s);
      serverButton.setActionCommand("Server");
      serverButton.setPreferredSize(new Dimension(100, 50));
      

//Creating the array of panels

    //welcome panel for manager/server selection
          JPanel welcomePanel = new JPanel();
          welcomePanel.setOpaque(true);
          JTextArea welcomeMessage = new JTextArea("Welcome to team RHOs GUI");
          
        //adding manager, server close and welcome message
          welcomePanel.add(closeButton);
          welcomePanel.add(managerButton);
          welcomePanel.add(serverButton);
          welcomePanel.add(welcomeMessage);

    //manager panel for inventory management
          JPanel managerPanel = new JPanel();
          managerPanel.setOpaque(true);
          

          managerPanel.add(closeButton);
          managerPanel.add(serverButton);


    //server panel for ordering drinks/food
          JPanel serverPanel = new JPanel();
          serverPanel.setOpaque(true);
          
          serverPanel.add(closeButton);
          serverPanel.add(managerButton);
          

    //customizations panel for altering drinks
          JPanel customizationsPanel = new JPanel();
          customizationsPanel.setOpaque(true);

          customizationsPanel.add(closeButton);
          customizationsPanel.add(serverButton);


  //adding panels to array and 
      panels[0] = welcomePanel;
      panels[1] = managerPanel;
      panels[2] = serverPanel;
      panels[3] = customizationsPanel;


      // add panels to frame
      f.add(welcomePanel);  
      f.add(managerPanel);  
      f.add(serverPanel); 
      f.add(customizationsPanel); 

      //setting the panels visibility
      welcomePanel.setVisible(panelVisible[0]);
      managerPanel.setVisible(panelVisible[1]);
      serverPanel.setVisible(panelVisible[2]);
      customizationsPanel.setVisible(panelVisible[3]);

      // set the size of frame
      f.setSize(600, 400);

      f.setVisible(true);

      //closing the connection
      try {
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed.");
      } catch(Exception e) {
        JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
      }
    }
    

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        } else if(s.equals("Manager")){
          panelVisible = new boolean[]{false,true,false,false};
        }else if(s.equals("Server")){
          panelVisible = new boolean[]{false,false,true,false};
        }else if(s.equals("Customizations")){
          panelVisible = new boolean[]{false,true,false,true};
        }
    }
}
