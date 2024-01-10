package Tests;

import model.ModelCraps;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ModelCrapsTest {

    @org.junit.jupiter.api.Test
    void constructorTest() {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        assertTrue(game.getGameActive());
        game.setGameActive(false);
        assertFalse(game.getGameActive());
    }

    @org.junit.jupiter.api.Test
    void doesBankAdd() {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        game.setBank(100);
        game.setBet(5);
        game.setTotal(7);
        game.adjustBankWin();
        assertEquals(105, game.getBank());

    }

    @org.junit.jupiter.api.Test
    void doesBankSubtract() {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        game.setBank(100);
        game.setBet(5);
        game.setTotal(7);
        game.adjustBankLoss();
        assertEquals(95, game.getBank());
    }

    @org.junit.jupiter.api.Test
    void getBetTest() {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        game.setBank(100);
        game.setBet(10);
        game.setTotal(7);
        assertEquals(10,game.getBet());
    }

    @org.junit.jupiter.api.Test
    void settersTest() {
    ModelCraps game = ModelCraps.getModelCrapsInstance();
    game.setPoint(5);
    assertEquals(5, game.getPoint());
    game.setBet(10);
    assertEquals(10,game.getBet());
    game.setBank(100);
    assertEquals(100,game.getBank());
    game.setDie1(3);
    assertEquals(3,game.getDie1());
    game.setDie2(4);
    assertEquals(4,game.getDie2());
    game.setPlayerWins(3);
    assertEquals(3, game.getPlayerWins());
    game.setHouseWins(7);
    assertEquals(7, game.getHouseWins());
    game.setTotal(7);
    assertEquals(7,game.getTotal());

    }

    @org.junit.jupiter.api.Test
    void diceRollRandomnessTest() throws UnsupportedAudioFileException, IOException {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        boolean twoSeen = false;
        boolean threeSeen = false;
        boolean fourSeen = false;
        boolean fiveSeen = false;
        boolean sixSeen = false;
        boolean sevenSeen = false;
        boolean eightSeen = false;
        boolean nineSeen = false;
        boolean tenSeen = false;
        boolean elevenSeen = false;
        boolean twelveSeen = false;
        for(int i = 0; i < 100; i++){
            game.setGameActive(true);
            game.diceRoll();
            //System.out.println(game.diceRoll());
            System.out.println("Your total is:" + game.getTotal());
            if (game.getTotal() == 2){
                twoSeen = true;
            }
            if (game.getTotal() == 3){
                threeSeen = true;
            }
            if (game.getTotal() == 4){
                fourSeen = true;
            }
            if (game.getTotal() == 5){
                fiveSeen = true;
            }
            if (game.getTotal() == 6){
                sixSeen = true;
            }
            if (game.getTotal() == 7){
                sevenSeen = true;
            }
            if (game.getTotal() == 8){
                eightSeen = true;
            }
            if (game.getTotal() == 9){
                nineSeen = true;
            }
            if (game.getTotal() == 10){
                tenSeen = true;
            }
            if (game.getTotal() == 11){
                elevenSeen = true;
            }
            if (game.getTotal() == 12){
                twelveSeen = true;
            }
        }

        assertTrue(twoSeen && threeSeen && fourSeen && fiveSeen && sixSeen
        && sevenSeen && eightSeen && nineSeen && tenSeen && elevenSeen && twelveSeen, "Dice did not" +
                "roll through every available option!");


    }

    @org.junit.jupiter.api.Test
    void betExceedsBankAmount(){
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        game.setBet(50);
        game.setBank(20);
        // dice will stay at zero because it is defaulted to 0.
        // diceRoll will not execute because bet exceeded bank amount.
        assertEquals(0,game.diceRoll());
    }

    @org.junit.jupiter.api.Test
    void setPointTest() {
        ModelCraps game = ModelCraps.getModelCrapsInstance();
        game.diceRoll();

        if (game.diceRoll() == 4 || game.diceRoll() == 5 || game.diceRoll() == 6 ||
                game.diceRoll() == 8 || game.diceRoll() == 9 || game.diceRoll() == 10){
            assertEquals(game.diceRoll(),game.getPoint());
        }
       else if (game.diceRoll() == 7 || game.diceRoll() == 11){
           assertNotEquals(game.diceRoll(),game.getPoint());
        }
       else if (game.diceRoll() == 2 || game.diceRoll() == 3 || game.diceRoll() == 12){
           assertNotEquals(game.diceRoll(),game.getPoint());
        }
    }

}