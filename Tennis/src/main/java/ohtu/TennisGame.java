package ohtu;

public class TennisGame {
    
    private int player1matchPoints = 0;
    private int player2matchPoints = 0;
    private String player1Name;//never really used for anything
    private String player2Name;//never really used for anything

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name; //never really used for anything
        this.player2Name = player2Name; //never really used for anything
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1matchPoints += 1;
        else
            player2matchPoints += 1;
    }

    public String getScore() {
        String score = "";
        if (player1matchPoints==player2matchPoints)
        {
            score = giveScoreWhenEven();
        }
        else if (player1matchPoints>=4 || player2matchPoints>=4)
        {
            score = giveScoreWhenAdvantageOrWin();
        }
        else
        {
            score = giveScoreWhenNotEvenNoAdvantageNoWin();
        }
        return score;
    }

    
    private String giveScoreWhenNotEvenNoAdvantageNoWin() {
        String score = giveScoreForPlayerPoints(player1matchPoints);
        score += "-";
        score += giveScoreForPlayerPoints(player2matchPoints);
        return score;
    }
    
    public String giveScoreForPlayerPoints(int points) {
        String score = "";
        switch(points)
            {
                case 0:
                    score = "Love";
                    break;
                case 1:
                    score = "Fifteen";
                    break;
                case 2:
                    score = "Thirty";
                    break;
                case 3:
                    score = "Forty";
                    break;
            }
        return score;
    }

    private String giveScoreWhenAdvantageOrWin() {
        String score;
        int scoreDifference = player1matchPoints-player2matchPoints;
        if (scoreDifference==1) score ="Advantage player1";
        else if (scoreDifference ==-1) score ="Advantage player2";
        else if (scoreDifference>=2) score = "Win for player1";
        else score ="Win for player2";
        return score;
    }

    private String giveScoreWhenEven() {
        String score;
        switch (player1matchPoints)
        {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;
                
        }
        return score;
    }
}