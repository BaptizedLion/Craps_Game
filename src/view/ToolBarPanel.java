package view;

import model.ModelCraps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
public class ToolBarPanel extends JPanel implements PropertyChangeListener {
    private final JLabel myBankLabel;
    private final JTextField myBank;
    private final JButton myRollButton;
    private final JButton myPlayAgainButton;
    private final JTextField myBet;
    private final JLabel myBetLabel;

    public ToolBarPanel(){
        super();
        GridLayout panelLayout = new GridLayout(1, 5);
        this.setLayout(panelLayout);
        myBankLabel = new JLabel("Bank:",SwingConstants.CENTER);
        myBank = new JTextField();
        myBank.setToolTipText("Set bank account prior to first roll of game.");
        myBank.setEditable(false);
        myBetLabel = new JLabel("Bet:", SwingConstants.CENTER);
        myBet = new JTextField("",SwingConstants.CENTER);
        myBet.setToolTipText("Bet minimum must be 1.");
        myBet.setEditable(false);
        myRollButton = new JButton("Roll");
        myRollButton.setMnemonic(KeyEvent.VK_N);
        myRollButton.setToolTipText("Shortcut: ALT + N");
        myRollButton.setEnabled(false);
        myPlayAgainButton = new JButton("Play Again");
        myPlayAgainButton.setMnemonic(KeyEvent.VK_ENTER);
        myPlayAgainButton.setToolTipText("Shortcut: ALT + Enter");
        myPlayAgainButton.setEnabled(false);
        final JToolBar toolBar = createToolBar();
        add(toolBar);
        addListeners();
    }

    private JToolBar createToolBar() {
        final JToolBar bar = new JToolBar();
        GridLayout panelLayout = new GridLayout(1, 5);
        bar.setLayout(panelLayout);
        bar.add(myRollButton);
        bar.add(myBetLabel);
        bar.add(myBet);
        bar.add(myBankLabel);
        bar.add(myBank);
        bar.add(myPlayAgainButton);
        return bar;
    }

    private void addListeners() {
        betListener();
        bankListener();
        myRollListener();
        playAgainListener();
        bankHelper();
        betHelper();
    }

    private void myRollListener(){
        myRollButton.addActionListener(theEvent -> {
            ModelCraps die = ModelCraps.getModelCrapsInstance();

            if (die.getGameActive()){
                System.out.println("your bank account is:" + die.getBank());
                if (die.getBank() <= 0){
                    JOptionPane.showMessageDialog(this,"Please set proper bank" +
                            " amount before rolling.");
                }
                else if (die.getBet() <= 0){
                    JOptionPane.showMessageDialog(this,"Please set proper bet" +
                            " amount before rolling.");
                }
                else if (die.getBet() > die.getBank()){
                    JOptionPane.showMessageDialog(this,"Please set a proper bet" +
                            " that does not exceed your bank amount.");
                }
                else {
                    die.diceRoll();
                    myBet.setEditable(false);
                    myBank.setEditable(false);
                }
            }
        });
    }
    private void playAgainListener(){
        myPlayAgainButton.addActionListener(theEvent -> {
            ModelCraps game = ModelCraps.getModelCrapsInstance();
            game.startGame();
            game.playAgain();
            myBet.setEditable(true);
            myBank.setEditable(false);
        });
    }

    private void bankListener(){
            ModelCraps game = ModelCraps.getModelCrapsInstance();
            final int minValue = 1;
            try {
                final int value = Integer.parseInt(myBank.getText());
                if (game.getBank() > 0) {
                    game.setBank(value);
                    myBank.setText(String.valueOf(game.getBank()));
                }
                else {
                    game.setBank(minValue);
                    myBank.setText(String.valueOf(minValue));
                }
            } catch (final NumberFormatException exception) {
                myBank.setText(String.valueOf(minValue));
                game.setBank(minValue);
            }
    }

    private void betListener() {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        final int minValue = 1;
        try {
            final int value = Integer.parseInt(myBet.getText());
            if(game.getBet() > 0){
                System.out.println("I placed a bet branch 1");
                game.setBet(value);
                myBet.setText(String.valueOf(game.getBet()));
            }
            else {
               game.setBet(minValue);
               myBet.setText(String.valueOf(minValue));
                System.out.println("I hit an else statement branch 2");
            }
        } catch (final NumberFormatException exception) {
            myBet.setText(String.valueOf(minValue));
            game.setBet(minValue);
        }
    }

    private void bankHelper() {
        myBank.addActionListener(theEvent -> myBank.requestFocus());
        myBank.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent theE) {
                myBank.addActionListener(event -> bankListener());
                //System.out.println("branch bank 1");
            }
            @Override
            public void focusLost(final FocusEvent theE) {
                bankListener();
                //System.out.println("branch bank 2");
            }
        });
    }

    private void betHelper() {
        myBet.addActionListener(theEvent -> myBet.requestFocus());
        myBet.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent theE) {
                myBet.addActionListener(event -> betListener());
                //System.out.println("branch bet 1");
            }
            @Override
            public void focusLost(final FocusEvent theE) {
                betListener();
                //System.out.println("branch bet 2");
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        if (Objects.equals(evt.getPropertyName(), "active")) {
            myRollButton.setEnabled(true);
            myPlayAgainButton.setEnabled(false);
            myBet.setEditable(true);
            myBank.setEditable(true);

        }
        if (Objects.equals(evt.getPropertyName(), "win")) {
            JOptionPane.showMessageDialog(this, "Congrats!\n" +
                    "You rolled a " + game.getTotal() + "." +
                    "\nYou Win!");

            game.adjustBankWin();
            myBank.setText(String.valueOf(game.getBank()));
        }
        if (Objects.equals(evt.getPropertyName(),"lose")) {
            JOptionPane.showMessageDialog(this, "Uh Oh!\n" +
                    "You rolled a " + game.getTotal() + "." +
                    "\nYou Lose!");
            game.adjustBankLoss();
            myBank.setText(String.valueOf(game.getBank()));
            if(game.getBank() == 0){
                JOptionPane.showMessageDialog(this, """
                        Sorry!
                        You ran out of money.
                        Game Over!""");
                System.exit(0);
            }
        }

        if (Objects.equals(evt.getPropertyName(), "finishedGame")) {
            myRollButton.setEnabled(false);
            myPlayAgainButton.setEnabled(true);
        }
        if (Objects.equals(evt.getPropertyName(), "newGame")) {
            myRollButton.setEnabled(true);
            myBank.setEditable(true);
            myBet.setEditable(true);
        }
        if (Objects.equals(evt.getPropertyName(), "gameOver")) {
            JOptionPane.showMessageDialog(this,"Sorry!\n" +
                    "You ran out of money.\n" +
                    "Game Over!");
            System.exit(0);
        }

        if (Objects.equals(evt.getPropertyName(),"updateBank")) {

            final int bankValue = Integer.parseInt(myBank.getText());
            game.setBank(bankValue);
            System.out.println("bank updated");
        }
        if (Objects.equals(evt.getPropertyName(),"updateBet")) {
            final int betValue = Integer.parseInt(myBet.getText());
            game.setBet(betValue);
            System.out.println("bet updated");
        }
    }
}

