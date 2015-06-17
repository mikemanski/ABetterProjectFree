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
import windows.State0Win.commonButtonListener;

public class State1Win extends JFrame
   {
   
   public static JComboBox<String> showSelectCB;
   private ShowCBListener showCBSelectListener = new ShowCBListener();
   
   public static JButton homeB = new JButton("Home");
   private commonButtonListener commonButtonMouseListener = new commonButtonListener();
   
   
   public State1Win(int numOfShows, String[] showNames)
      {
      super("Select Show");
      //this.setVisible(true);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setMinimumSize(new Dimension(700, 200));
      
      homeB.addActionListener(commonButtonMouseListener);
      showSelectCB = new JComboBox<String>(showNames);
      showSelectCB.addActionListener(showCBSelectListener);
      JPanel mainPan = new JPanel();
      mainPan.add(showSelectCB);
      mainPan.add(homeB);
      this.add(mainPan);
      }
   
   
   public class ShowCBListener implements ActionListener
      {
      public void actionPerformed(ActionEvent actEv)
         {
         JComboBox<String> comboRef = (JComboBox<String>)actEv.getSource();
         try {States.State1( comboRef.getSelectedIndex() );} 
         catch (IOException e) {e.printStackTrace();}
         }
      }
   
   
   public class commonButtonListener implements ActionListener 
   {
   public void actionPerformed(ActionEvent e)
      {
      if (e.getSource() == homeB)
         {
         States.Start();
         }
      }
   
   }
 
   }
