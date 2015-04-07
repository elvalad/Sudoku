package org.elvalad.sudoku;

/**
 * Created by Administrator on 2015/4/7.
 */
public class GameResult {
    int difficulty;
    int score;
    String time;

    public GameResult() {
        this.difficulty = 0;
        this.score = 0;
        this.time = null;
    }

    public void setDiff(int diff) {
        this.difficulty = diff;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getScore() {
        return this.score;
    }

    public String getTime() {
        return this.time;
    }
}
