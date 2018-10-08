package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamId
{
    @Id
    private Integer teamId;

    public Integer getTeamId()
    {
        return teamId;
    }

    public void setTeamId(Integer teamId)
    {
        this.teamId = teamId;
    }
}
