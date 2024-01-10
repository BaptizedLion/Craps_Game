package controller;

import model.ModelCraps;
import view.DiePanel;
import view.MenuPanel;
import view.ToolBarPanel;
import javax.swing.*;
import java.awt.*;

public class DiceController extends JFrame{
    public static void createAndShowGUI() {


        JFrame frame = new JFrame("Virtual Craps");

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final DiePanel panel = new DiePanel();
        ModelCraps.getModelCrapsInstance().addPropertyChangeListener(panel);
        final ToolBarPanel toolBar = new ToolBarPanel();
        ModelCraps.getModelCrapsInstance().addPropertyChangeListener(toolBar);
        final MenuPanel menu = new MenuPanel();
        ModelCraps.getModelCrapsInstance().addPropertyChangeListener(menu);

        frame.setJMenuBar(menu.getMenuBar());
        panel.setPreferredSize(new Dimension(600,200));
        frame.add(panel,BorderLayout.CENTER);
        frame.add(toolBar,BorderLayout.SOUTH);
    }

    public static void main(final String[] theArgs) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DiceController.createAndShowGUI();
            }
        });
    }
}



