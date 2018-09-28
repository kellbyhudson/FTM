package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TeamPlayer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer teamPlayerId;
    private Integer playerId;
    private Integer teamId;
    private String teamPlayerName;
    private Integer teamPlayerValue;
    private Integer teamPlayerPositionId;
    private Integer sortOrderId;


    public Integer getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(Integer playerId)
    {
        this.playerId = playerId;
    }

    public Integer getTeamId()
    {
        return teamId;
    }

    public void setTeamId(Integer teamId)
    {
        this.teamId = teamId;
    }

    public String getTeamPlayerName()
    {
        return teamPlayerName;
    }

    public void setTeamPlayerName(String teamPlayerName)
    {
        this.teamPlayerName = teamPlayerName;
    }

    public Integer getTeamPlayerValue()
    {
        return teamPlayerValue;
    }

    public void setTeamPlayerValue(Integer teamPlayerValue)
    {
        this.teamPlayerValue = teamPlayerValue;
    }

    public Integer getTeamPlayerPositionId()
    {
        return teamPlayerPositionId;
    }

    public void setTeamPlayerPositionId(Integer teamPlayerPositionId)
    {
        this.teamPlayerPositionId = teamPlayerPositionId;
    }

    public Integer getSortOrderId()
    {
        return sortOrderId;
    }

    public void setSortOrderId(Integer sortOrderId)
    {
        this.sortOrderId = sortOrderId;
    }
}
