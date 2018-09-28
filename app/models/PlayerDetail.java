package models;

import java.text.NumberFormat;

public class PlayerDetail
{
    private Integer playerId;
    private String playerName;
    private Integer playerValue;
    private Integer playerHeightFeet;
    private Integer playerHeightInches;
    private Integer playerWeight;
    private Integer yearsExperience;
    private String playerCollege;
    private String formattedPlayerValue;

    public Integer getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(Integer playerId)
    {
        this.playerId = playerId;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public Integer getPlayerValue()
    {
        return playerValue;
    }

    public void setPlayerValue(Integer playerValue)
    {
        this.playerValue = playerValue;
    }

    public Integer getPlayerHeightFeet()
    {
        return playerHeightFeet;
    }

    public void setPlayerHeightFeet(Integer playerHeightFeet)
    {
        this.playerHeightFeet = playerHeightFeet;
    }

    public Integer getPlayerHeightInches()
    {
        return playerHeightInches;
    }

    public void setPlayerHeightInches(Integer playerHeightInches)
    {
        this.playerHeightInches = playerHeightInches;
    }

    public Integer getPlayerWeight()
    {
        return playerWeight;
    }

    public void setPlayerWeight(Integer playerWeight)
    {
        this.playerWeight = playerWeight;
    }

    public Integer getYearsExperience()
    {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience)
    {
        this.yearsExperience = yearsExperience;
    }

    public String getPlayerCollege()
    {
        return playerCollege;
    }

    public void setPlayerCollege(String playerCollege)
    {
        this.playerCollege = playerCollege;
    }

    public String getFormattedPlayerValue()
    {
        String currencyString = getPlayerValue().toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedPlayerValue = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedPlayerValue;
    }

}
