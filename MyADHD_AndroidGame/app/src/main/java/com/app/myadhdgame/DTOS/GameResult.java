package com.app.myadhdgame.DTOS;

public class GameResult {

    private Integer gameId;

    private String timestamp;

    private String score;

    public GameResult(String timestamp, String score) {
        this.timestamp = timestamp;
        this.score = score;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
