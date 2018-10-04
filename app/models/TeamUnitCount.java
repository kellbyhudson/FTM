package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamUnitCount
{
    @Id
    private Integer teamUnitId;
    private String teamUnitName;
    private String rgb;
    private long salaryOfTeamUnit;

    public TeamUnitCount(Integer teamUnitId, String teamUnitName, long salaryOfTeamUnit)
    {
        this.teamUnitId = teamUnitId;
        this.teamUnitName = teamUnitName;
        setRgb();
        this.rgb = getRgb();
        this.salaryOfTeamUnit = salaryOfTeamUnit;
    }

    public Integer getTeamUnitId()
    {
        return teamUnitId;
    }

    public void setTeamUnitId(Integer teamUnitId)
    {
        this.teamUnitId = teamUnitId;
    }

    public String getTeamUnitName()
    {
        return teamUnitName;
    }

    public void setTeamUnitName(String teamUnitName)
    {
        this.teamUnitName = teamUnitName;
    }

    public String getRgb()
    {
        return rgb;
    }

    public void setRgb()
    {
        if(teamUnitId == 0)
        {
            this.rgb = "rgb(255,0,0)";
        }
        else
        {
            this.rgb = "rgb(0,100,200)";
        }

    }

    public long getSalaryOfTeamUnit()
    {
        return salaryOfTeamUnit;
    }

    public void setSalaryOfTeamUnit(long salaryOfTeamUnit)
    {
        this.salaryOfTeamUnit = salaryOfTeamUnit;
    }
}
