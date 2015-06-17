package windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import states.States;

public class State4Win extends JFrame
   {
   public static JComboBox<String> sourceSelectCB;
   private SourceCBListener sourceCBSelectListener = new SourceCBListener();
   
   public static JTextField nameFileTF = new JTextField("Enter filename (no extension)", 30);
   
   public static JButton homeB = new JButton("Home");
   private commonButtonListener commonButtonMouseListener = new commonButtonListener();
   
   public static JCheckBox downloadCheckBox = new JCheckBox();
   public static boolean downloadCheck = false;
   private checkBoxListener checkBoxSelListener = new checkBoxListener();
   
   public State4Win(int numOfEpisodeLinks, String[] hostNames)
      {
      super("Select Source");
      //this.setVisible(true);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setMinimumSize(new Dimension(800, 300));
      
      sourceSelectCB = new JComboBox<String>(hostNames);
      sourceSelectCB.addActionListener(sourceCBSelectListener);
      homeB.addActionListener(commonButtonMouseListener);
      downloadCheckBox.addItemListener(checkBoxSelListener);
      JLabel checkLab = new JLabel("Check to download instead of watch");
      JPanel mainPan = new JPanel();
      mainPan.add(sourceSelectCB);
      mainPan.add(homeB);
      mainPan.add(checkLab);
      mainPan.add(downloadCheckBox);
      mainPan.add(nameFileTF);
      this.add(mainPan);
      }
   
   
   public class SourceCBListener implements ActionListener
      {
      public void actionPerformed(ActionEvent actEv)
         {
         JComboBox<String> comboRef = (JComboBox<String>)actEv.getSource();
         try {States.State4( comboRef.getSelectedIndex(), downloadCheck, nameFileTF.getText() );}
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
   
   public class checkBoxListener implements ItemListener
      {
      public void itemStateChanged(ItemEvent e) 
         {         
         if (e.getStateChange()==1)
            {
            downloadCheck = !downloadCheck;//downloadCheck = true;
            }   
         }
      }
   
   }
