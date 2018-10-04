package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Entity
public class TeamTable
{
    @Id
    private Integer teamTableId;
    private String teamCity;
    private String teamName;
    private String ownerName;
    private BigDecimal organizationSalaryCap;
    private Integer teamSalary;
    private Integer coachSalary;
    private String formattedSalaryCap;
    private String formattedTeamSalary;
    private String formattedCoachSalary;

    public TeamTable(Integer teamTableId, String teamCity, String teamName, String ownerName, BigDecimal organizationSalaryCap, Integer teamSalary, Integer coachSalary)
    {
        this.teamTableId = teamTableId;
        this.teamCity = teamCity;
        this.teamName = teamName;
        this.ownerName = ownerName;
        this.organizationSalaryCap = organizationSalaryCap;
        this.teamSalary = teamSalary;
        this.coachSalary = coachSalary;
    }

    public Integer getTeamTableId()
    {
        return teamTableId;
    }

    public String getTeamCity()
    {
        return teamCity;
    }

    public void setTeamCity(String teamCity)
    {
        this.teamCity = teamCity;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public BigDecimal getOrganizationSalaryCap()
    {
        return organizationSalaryCap;
    }

    public void setOrganizationSalaryCap(BigDecimal organizationSalaryCap)
    {
        this.organizationSalaryCap = organizationSalaryCap;
    }

    public Integer getTeamSalary()
    {
        return teamSalary;
    }

    public void setTeamSalary(Integer teamSalary)
    {
        this.teamSalary = teamSalary;
    }

    public Integer getCoachSalary()
    {
        return coachSalary;
    }

    public void setCoachSalary(Integer coachSalary)
    {
        this.coachSalary = coachSalary;
    }

    public String getFormattedSalaryCap()
    {
        String currencyString = organizationSalaryCap.toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedSalaryCap = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedSalaryCap;
    }

    public String getFormattedTeamSalary()
    {
        String currencyString = teamSalary.toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedTeamSalary = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedTeamSalary;
    }

    public String getFormattedCoachSalary()
    {
        String currencyString = coachSalary.toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedCoachSalary = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedCoachSalary;
    }

}
