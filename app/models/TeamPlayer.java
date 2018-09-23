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
}
