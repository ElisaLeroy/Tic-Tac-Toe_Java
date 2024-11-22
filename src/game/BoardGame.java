package game;

public abstract class BoardGame {
    private GameType gameType;

    public BoardGame() {
    }

    public GameType getGameType() {
        return gameType;
    }
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

}
