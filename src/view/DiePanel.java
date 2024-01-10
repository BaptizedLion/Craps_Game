package view;

import model.ModelCraps;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class DiePanel extends JPanel implements PropertyChangeListener {
    private final JLabel myPoints;
    private final JLabel myPointLabel;
    private final JLabel myDie1;
    private final JLabel myDie1Label;
    private final JLabel myDie2;
    private final JLabel myDie2Label;
    private final JLabel myTotal;
    private final JLabel myTotalLabel;
    private final JLabel myPlayerWins;
    private final JLabel myPlayerWinsLabel;
    private final JLabel myHouseWins;
    private final JLabel myHouseWinsLabel;
    private final Border blackline = BorderFactory.createLineBorder(Color.black);
    private final File myFile = new File("src/audio/Wah Wah Wah Spongebob.wav");
    private final File myFileDice = new File("src/audio/OOT_Dialogue_Next(1).wav");
    private final File myFileWin = new File("src/audio/OOT_Secret(1).wav");
    private final JLabel myDieImageOneLabel;
    private final JLabel myDieImageTwoLabel;

    public DiePanel(){
        super();
        myPointLabel = new JLabel("Points: ",SwingConstants.CENTER);
        myPoints = new JLabel("",SwingConstants.CENTER);
        myDie1Label = new JLabel("Die1: ",SwingConstants.CENTER);
        myDie1 = new JLabel("",SwingConstants.CENTER);
        myDie2Label = new JLabel("Die2: ",SwingConstants.CENTER);
        myDie2 = new JLabel("",SwingConstants.CENTER);
        myPlayerWinsLabel = new JLabel("Player Wins: ",SwingConstants.CENTER);
        myPlayerWins = new JLabel("",SwingConstants.CENTER);
        myHouseWinsLabel = new JLabel("House Wins: ",SwingConstants.CENTER);
        myHouseWins = new JLabel("",SwingConstants.CENTER);
        myTotalLabel = new JLabel("Total",SwingConstants.CENTER);
        myTotal = new JLabel("",SwingConstants.CENTER);
        myTotal.setOpaque(true);
        myDieImageOneLabel = new JLabel("", SwingConstants.CENTER);
        myDieImageTwoLabel = new JLabel("", SwingConstants.CENTER);
        layoutComponents();
    }

    private void layoutComponents(){

        GridLayout panelLayout = new GridLayout(7,7);
        setLayout(panelLayout);
        myPointLabel.setBorder(blackline);
        add(myPointLabel);
        add(myPoints);
        myDie1Label.setBorder(blackline);
        add(myDie1Label);
        add(myDie1);
        myDie2Label.setBorder(blackline);
        add(myDie2Label);
        add(myDie2);
        myTotalLabel.setBorder(blackline);
        add(myTotalLabel);
        add(myTotal);
        myPlayerWinsLabel.setBorder(blackline);
        add(myPlayerWinsLabel);
        add(myPlayerWins);
        myHouseWinsLabel.setBorder(blackline);
        add(myHouseWinsLabel);
        add(myHouseWins);
        add(myDieImageOneLabel);
        add(myDieImageTwoLabel);
        myDieImageOneLabel.setBorder(blackline);
        myDieImageTwoLabel.setBorder(blackline);
    }

    private void audioFileLose() throws UnsupportedAudioFileException, IOException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(myFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void audioFileRoll() throws UnsupportedAudioFileException, IOException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(myFileDice);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void audioFileWin() throws UnsupportedAudioFileException, IOException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(myFileWin);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "rolled")) {
                ModelCraps die = model.ModelCraps.getModelCrapsInstance();
                myPoints.setText(String.valueOf(die.getPoint()));
                myDie1.setText(String.valueOf(die.getDie1()));
                myDie2.setText(String.valueOf(die.getDie2()));
                myTotal.setText(String.valueOf(die.getTotal()));
                myPlayerWins.setText(String.valueOf(die.getPlayerWins()));
                myHouseWins.setText(String.valueOf(die.getHouseWins()));
            try {
                audioFileRoll();
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon myDieImageOne;
            if (die.getDie1() == 1) {
                myDieImageOne = new ImageIcon(new ImageIcon("src/images/dieOne.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageOneLabel.setIcon(myDieImageOne);

            }
            if (die.getDie1() == 2) {
                myDieImageOne = new ImageIcon(new ImageIcon("src/images/dieTwo.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageOneLabel.setIcon(myDieImageOne);
            }
            if (die.getDie1() == 3) {
                myDieImageOne = new ImageIcon(new ImageIcon("src/images/dieThree.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageOneLabel.setIcon(myDieImageOne);
            }
            if (die.getDie1() == 4) {
                myDieImageOne = new ImageIcon(new ImageIcon("src/images/dieFour.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageOneLabel.setIcon(myDieImageOne);
            }
            if (die.getDie1() == 5) {
                myDieImageOne = new ImageIcon(new ImageIcon("src/images/dieFive.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageOneLabel.setIcon(myDieImageOne);
            }
            if (die.getDie1() == 6) {
                myDieImageOne = new ImageIcon(new ImageIcon("src/images/dieSix.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageOneLabel.setIcon(myDieImageOne);
            }
            ImageIcon myDieImageTwo;
            if (die.getDie2() == 1) {
                myDieImageTwo =  new ImageIcon(new ImageIcon("src/images/dieOne.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageTwoLabel.setIcon(myDieImageTwo);
            }
            if (die.getDie2() == 2) {
                myDieImageTwo =  new ImageIcon(new ImageIcon("src/images/dieTwo.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageTwoLabel.setIcon(myDieImageTwo);
            }
            if (die.getDie2() == 3) {
                myDieImageTwo =  new ImageIcon(new ImageIcon("src/images/dieThree.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageTwoLabel.setIcon(myDieImageTwo);
            }
            if (die.getDie2() == 4) {
                myDieImageTwo =  new ImageIcon(new ImageIcon("src/images/dieFour.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageTwoLabel.setIcon(myDieImageTwo);
            }
            if (die.getDie2() == 5) {
                myDieImageTwo =  new ImageIcon(new ImageIcon("src/images/dieFive.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageTwoLabel.setIcon(myDieImageTwo);
            }
            if (die.getDie2() == 6) {
                myDieImageTwo =  new ImageIcon(new ImageIcon("src/images/dieSix.png").getImage().
                        getScaledInstance(20,20,Image.SCALE_DEFAULT));
                myDieImageTwoLabel.setIcon(myDieImageTwo);
            }
        }

        if (Objects.equals(evt.getPropertyName(), "newGame")) {
            ModelCraps die = model.ModelCraps.getModelCrapsInstance();
            myPoints.setText(String.valueOf(die.getPoint()));
            myDie1.setText(String.valueOf(die.getDie1()));
            myDie2.setText(String.valueOf(die.getDie2()));
            myTotal.setText(String.valueOf(die.getTotal()));
            myPlayerWins.setText(String.valueOf(die.getPlayerWins()));
            myHouseWins.setText(String.valueOf(die.getHouseWins()));
        }
        if (Objects.equals(evt.getPropertyName(), "playAgain")){
            ModelCraps die = model.ModelCraps.getModelCrapsInstance();
            System.out.println("I'm firing rolled");
            myPoints.setText(String.valueOf(die.getPoint()));
            myDie1.setText(String.valueOf(die.getDie1()));
            myDie2.setText(String.valueOf(die.getDie2()));
            myTotal.setText(String.valueOf(die.getTotal()));
            myPlayerWins.setText(String.valueOf(die.getPlayerWins()));
            myHouseWins.setText(String.valueOf(die.getHouseWins()));
            myTotal.setOpaque(false);
        }
         if (Objects.equals(evt.getPropertyName(), "win")){
            myTotal.setOpaque(true);
            myTotal.setBackground(Color.green);
             try {
                 audioFileWin();
             } catch (UnsupportedAudioFileException | IOException e) {
                 throw new RuntimeException(e);
             }
         }
         if (Objects.equals(evt.getPropertyName(), "lose")) {
            myTotal.setOpaque(true);
            myTotal.setBackground(Color.red);
             try {
                 audioFileLose();
             } catch (UnsupportedAudioFileException | IOException e) {
                 throw new RuntimeException(e);
             }

         }
    }
}
