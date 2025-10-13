package de.dele1907.Model.Tournament.Score;

public abstract class BaseScore {
    private final String shooterId;
    private final String tournamentId;
    private final int scoreSetOne;
    private final int scoreSetTwo;

    public BaseScore(String shooterId, String tournamentId, int scoreSetOne, int scoreSetTwo) {
        this.shooterId = shooterId;
        this.tournamentId = tournamentId;
        this.scoreSetOne = scoreSetOne;
        this.scoreSetTwo = scoreSetTwo;
    }

    public String getShooterId() {
        return shooterId;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public int getScoreSetOne() {
        return scoreSetOne;
    }

    public int getScoreSetTwo() {
        return scoreSetTwo;
    }

}
