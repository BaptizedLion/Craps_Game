package view;

import model.ModelCraps;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class MenuPanel extends JPanel implements PropertyChangeListener {
    private JMenuBar myMenuBar;
    private Action myExitAction;
    private JMenuItem myStart;
    private JMenuItem myReset;
    private JMenuItem myExit;
    private JMenuItem myAbout;
    private JMenuItem myRules;
    private JMenuItem myShortcuts;


    public MenuPanel(){
        super();
        final JMenuBar menuBar = createMenuBar();
        add(menuBar);
        addListeners();
    }

    private JMenuBar createMenuBar(){

        myMenuBar = new JMenuBar();
        final JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.setToolTipText("Start, Reset, Exit");
        menu.add(myExitAction);
        myStart = new JMenuItem("Start");
        menu.add(myStart);
        myStart.setToolTipText("Shortcut: ALT + S");
        myStart.setMnemonic(KeyEvent.VK_S);
        myReset = new JMenuItem("Reset");
        menu.add(myReset);
        myReset.setMnemonic(KeyEvent.VK_R);
        myReset.setEnabled(false);
        myReset.setToolTipText("Shortcut: ALT + R");
        myExit = new JMenuItem("Exit");
        myExit.setMnemonic(KeyEvent.VK_E);
        myExit.setToolTipText("Shortcut: ALT + E");
        menu.add(myExit);
        myMenuBar.add(menu);
        final JMenu helpMenu = new JMenu("Help");
        myAbout = new JMenuItem("About");
        myAbout.setMnemonic(KeyEvent.VK_A);
        helpMenu.setToolTipText("About, Rules, Shortcuts");
        helpMenu.add(myAbout);
        myAbout.setToolTipText("Shortcut: ALT + A");
        myRules = new JMenuItem("Rules");
        myRules.setMnemonic(KeyEvent.VK_R);
        myRules.setToolTipText("Shortcut: ALT + R");
        helpMenu.add(myRules);

        myShortcuts = new JMenuItem("Shortcuts");
        helpMenu.add(myShortcuts);
        myShortcuts.setMnemonic(KeyEvent.VK_S);
        myShortcuts.setToolTipText("Shortcut: ALT + S");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(myExitAction);
        myMenuBar.add(helpMenu);
        return myMenuBar;

    }
    public JMenuBar getMenuBar(){
        return myMenuBar;
    }

    private void addListeners() {
        startMenuListener();
        resetMenuListener();
        exitMenuListener();
        aboutMenuListener();
        rulesMenuListener();
        shortcutsMenuListener();
    }

    private void startMenuListener() {
        myStart.addActionListener(theEvent -> {
             model.ModelCraps.getModelCrapsInstance().startGame();
            //the changes line is what is allowing the myReset to be enabled.
            ModelCraps die = ModelCraps.getModelCrapsInstance();
            die.getChanges().firePropertyChange("active",null,false);
            System.out.println("Game Started!");
        });
    }

    private void resetMenuListener() {
        myReset.addActionListener(theEvent -> {
            ModelCraps game = ModelCraps.getModelCrapsInstance();
            game.getChanges().firePropertyChange("newGame",null,false);
        });
    }

    private void exitMenuListener() {
        myExit.addActionListener(theEvent -> {
           int option = JOptionPane.showConfirmDialog(this, "Are you sure you would like to exit?");
           if (option == JOptionPane.YES_OPTION){
               System.exit(0);
           }
           if (option == JOptionPane.NO_OPTION){
               //do nothing
           }
        });
    }

    private void aboutMenuListener() {
        myAbout.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(this,"Michael Mapanao\n" +
                    "version: 1.0.01\n" +
                    "IDE version: IntelliJ IDEA 2023.2.5 (Community Edition)\n" +
                    "Build #IC-232.10227.8, built on November 8, 2023\n" +
                    "TCSS 305A : Prof Capaul's class!"
                    );
        });
    }

    private void rulesMenuListener() {
        myRules.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(this,
                    "The rules of the Game of craps are as follows:\n" +
                            "\n" +
                            " A player rolls two dice where each die has six faces in " +
                            "the usual way (values 1 through 6).\n" +
                            " After the dice have come to rest the sum of the " +
                            "two upward faces is calculated.\n" + "The first roll/throw\n" +
                            " --If the sum is 7 or 11 on the first throw the roller/player wins.\n" +
                            " --If the sum is 2, 3 or 12 the roller/player loses, that " +
                            "is the house wins.\n" + "--If the sum is 4, 5, 6, 8, 9, or 10, " +
                            "that sum becomes the roller/player's 'point'.\n" +
                            " -Continue rolling given the player's point\n" +
                            " -Now the player must roll the 'point' total before " +
                            "rolling a 7 in order to win.\n" +
                            " -If they roll a 7 before rolling the point value they got on" +
                            " the first roll the roller/player " +
                            "s (the 'house' wins).\n");
        });
    }

        private void shortcutsMenuListener() {
            myShortcuts.addActionListener(theEvent -> {
                JOptionPane.showMessageDialog(this,
                        "Shortcuts Help: \n"
                + "Alt + G ---- Opens Game Menu\n" +
                                "Alt + H ---- Opens Help Menu\n" +
                                "After Menu Opens: \n" +
                                "A ---- About \n" +
                                "R ---- Rules\n" +
                                "S ---- Start\n" +
                                "P ---- Play Again\n" +
                                "SpaceBar ---- Roll\n");
            });

        }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "active")) {
               myReset.setEnabled(true);
               myStart.setEnabled(false);
        }
        if (Objects.equals(evt.getPropertyName(), "newGame")){
            ModelCraps game = ModelCraps.getModelCrapsInstance();
            game.setGameActive(true);
            game.resetGame();
        }
    }
}
