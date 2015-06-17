package windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import states.States;

public class State0Win extends JFrame
   {
   public static JButton searchB = new JButton("Search");
   public static JTextField searchTF = new JTextField("", 15);
   private commonButtonListener commonButtonMouseListener = new commonButtonListener();
   
   public State0Win()
      {
      super("Search Show");
      //this.setVisible(true);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setMinimumSize(new Dimension(400, 200));
      

      searchB.addActionListener(commonButtonMouseListener);
      JPanel mainPan = new JPanel();
      mainPan.add(searchTF);
      mainPan.add(searchB);
      this.add(mainPan);
      }
   
   

   
   public class commonButtonListener implements ActionListener 
      {
      public void actionPerformed(ActionEvent e)
         {
         if (e.getSource() == searchB)
            {
            try {States.State0(searchTF.getText());} 
            catch (IOException e1) {e1.printStackTrace();}
            }
         }
      
      }

   
   
   }



