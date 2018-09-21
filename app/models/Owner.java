package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private int ownerId;
    private String ownerName;
    private String organizationCity;
    private String organizationName;
    private int takeOverOrganizationId;
    private int coachId;
    private int teamId;

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOrganizationCity()
    {
        return organizationCity;
    }

    public void setOrganizationCity(String organizationCity)
    {
        this.organizationCity = organizationCity;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public int getTakeOverOrganizationId()
    {
        return takeOverOrganizationId;
    }

    public void setTakeOverOrganizationId(int takeOverOrganizationId)
    {
        this.takeOverOrganizationId = takeOverOrganizationId;
    }

    public int getCoachId()
    {
        return coachId;
    }

    public void setCoachId(int coachId)
    {
        this.coachId = coachId;
    }

    public int getTeamId()
    {
        return teamId;
    }

    public void setTeamId(int teamId)
    {
        this.teamId = teamId;
    }
}
