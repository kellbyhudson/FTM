package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamUnitCount2
{
    @Id
    private Integer teamUnitId;
    private String teamUnitName;
    private String rgb;
    private long salaryOfTeamUnit;

    public TeamUnitCount2(Integer teamUnitId, String teamUnitName, long salaryOfTeamUnit)
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
        if(teamUnitId == 1)
        {
            this.rgb = "rgb(200,100,0)";
        }
        else if (teamUnitId == 2)
        {
            this.rgb = "rgb(0,200,100)";
        }
        else
        {
            this.rgb = "rgb(255,255,0)";
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
