package de.dele1907.Model.Tournament.Score;

public class OutdoorScore extends BaseScore {
    private final int tenCount;
    private final int xCount;

    public OutdoorScore(String shooterId, String tournamentId, int scoreSetOne, int scoreSetTwo, int tenCount, int xCount) {
        super(shooterId, tournamentId, scoreSetOne, scoreSetTwo);
        this.tenCount = tenCount;
        this.xCount = xCount;
    }

    public int getTenCount() {
        return tenCount;
    }

    public int getXCount() {
        return xCount;
    }
}
