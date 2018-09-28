package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer teamId;
    private String teamName;
    private String teamCity;
    private Integer ownerId;
    private Integer coachId;
    private Integer takeOverOrganizationId;
    private Integer teamSalary;


    public Integer getTeamId()
    {
        return teamId;
    }

    public void setTeamId(Integer teamId)
    {
        this.teamId = teamId;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public Integer getTeamSalary()
    {
        return teamSalary;
    }

    public void setTeamSalary(Integer teamSalary)
    {
        this.teamSalary = teamSalary;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getTeamCity()
    {
        return teamCity;
    }

    public void setTeamCity(String teamCity)
    {
        this.teamCity = teamCity;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId = ownerId;
    }

    public Integer getCoachId()
    {
        return coachId;
    }

    public void setCoachId(Integer coachId)
    {
        this.coachId = coachId;
    }

    public Integer getTakeOverOrganizationId()
    {
        return takeOverOrganizationId;
    }

    public void setTakeOverOrganizationId(Integer takeOverOrganizationId)
    {
        this.takeOverOrganizationId = takeOverOrganizationId;
    }
}
