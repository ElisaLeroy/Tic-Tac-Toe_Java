package game;
/**
 * Abstract class representing the core logic of a game.
 */

import board.Cell;
import board.Coordinates;
import board.State;
import player.ArtificialPlayer;
import player.Player;
import player.RealPlayer;

import java.security.SecureRandom;
import java.util.Objects;

public abstract class GameModel {
    private GameType gameType;

    private int alignCellsCondition;
    private boolean win = false;
    Coordinates coordinates;
    private Player player1;
    private Player player2;
    private Player currentPlayer;


    public GameModel() {

    }


    protected void instanceFirstPlayer(int type) {
        switch (type) {
            case 1:
                this.player1 = new RealPlayer(State.X, "Player 1"); ////////////
                break;
            case 2:
                this.player1 = new ArtificialPlayer(State.X, "Player 1"); ///////////////
                break;
        }
    }
    protected void instanceSecondPlayer(int type) {
        switch (type) {
            case 1:
                this.player2 = new RealPlayer(State.O, "Player 2"); ////////////
                break;
            case 2:
                this.player2 = new ArtificialPlayer(State.O, "Player 2"); ///////////////
                break;
        }
    }
    protected void setCoordinates(int line, int column) { ///////////////
        this.coordinates = new Coordinates(line, column);

    }
    protected int getRandomColumnIndex(int verticalSize) { /////????????///////
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(verticalSize);
    }
    protected int getRandomLineIndex(int horizontalSize) { ////////////?????????//////
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(horizontalSize);
    }
    protected void changeCurrentPlayer(){
        switch (currentPlayer.getName()) {
            case "Player 1":
                this.currentPlayer = player2;
                break;
            case "Player 2":
                this.currentPlayer = player1;
                break;
        }
    }
    protected PlayerType getCurrentPlayerType() {
        return currentPlayer.getType();
    }





    // Getter and Setter methods for various properties
    public int getCoordinateLine() {
        return coordinates.getLine();
    }
    public int getCoordinateColumn() {
        return coordinates.getColumn();
    }
    public int getAlignCellsCondition() {
        return alignCellsCondition;
    }
    public GameType getGameType() {
        return gameType;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Player getPlayer1(){
        return this.player1;
    }
    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setAlignCellsCondition(int alignCellsCondition) {
        this.alignCellsCondition = alignCellsCondition;
    }
}
