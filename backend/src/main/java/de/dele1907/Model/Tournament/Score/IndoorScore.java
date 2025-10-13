package de.dele1907.Model.Tournament.Score;

public class IndoorScore extends BaseScore {
    private final int nineCount;
    private final int tenCount;

    public IndoorScore(String shooterId, String tournamentId, int scoreSetOne, int scoreSetTwo, int nineCount, int tenCount) {
        super(shooterId, tournamentId, scoreSetOne, scoreSetTwo);
        this.nineCount = nineCount;
        this.tenCount = tenCount;
    }

    public int getNineCount() {
        return nineCount;
    }

    public int getTenCount() {
        return tenCount;
    }
}
