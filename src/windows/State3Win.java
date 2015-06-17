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

public class State3Win extends JFrame
   {
   public static JComboBox<String> showSelectCB;
   private SeasonCBListener seasonCBSelectListener = new SeasonCBListener();
   
   public static JButton homeB = new JButton("Home");
   private commonButtonListener commonButtonMouseListener = new commonButtonListener();
   
   public String htmlTextOut;
   
   public State3Win(int numOfEpisodes, String[] episodeNames, String htmlTextIn)
      {
      super("Select Episode");
      //this.setVisible(true);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setMinimumSize(new Dimension(800, 300));
      
      htmlTextOut = htmlTextIn;
      showSelectCB = new JComboBox<String>(episodeNames);
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
         try {States.State3( comboRef.getSelectedIndex(), htmlTextOut );} 
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
