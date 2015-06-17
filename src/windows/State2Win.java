package windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import states.States;
import windows.State1Win.ShowCBListener;
import windows.State1Win.commonButtonListener;

public class State2Win extends JFrame
   {
   public static JComboBox<String> showSelectCB;
   private SeasonCBListener seasonCBSelectListener = new SeasonCBListener();
   
   public static JButton homeB = new JButton("Home");
   private commonButtonListener commonButtonMouseListener = new commonButtonListener();
   
   public State2Win(int numOfSeasons, String[] seasonNames, String[] seasonInfo)
      {
      super("Select Season");
      //this.setVisible(true);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setMinimumSize(new Dimension(800, 300));
      
      String[] seasonNamesAndInfo = seasonNames;
      for(int i = 0; i < numOfSeasons; i++)
         {
         seasonNamesAndInfo[i] = seasonNames[i] + " " + seasonInfo[i];
         }
      
      showSelectCB = new JComboBox<String>(seasonNamesAndInfo);
      showSelectCB.addActionListener(seasonCBSelectListener);
      homeB.addActionListener(commonButtonMouseListener);
      JPanel mainPan = new JPanel();
      mainPan.add(showSelectCB);
      mainPan.add(homeB);
      this.add(mainPan);
      }
   
   
   public class SeasonCBListener implements ActionListener
      {
      public void actionPerformed(ActionEvent actEv)
         {
         JComboBox<String> comboRef = (JComboBox<String>)actEv.getSource();
         try {States.State2( comboRef.getSelectedIndex() );} 
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
