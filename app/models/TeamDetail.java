package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Entity
public class TeamDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer teamId;
    private Integer ownerId;
    private Integer coachId;
    private Integer takeOverOrganizationId;
    private String ownerName;
    private String organizationName;
    private BigDecimal organizationSalaryCap;
    private String coachName;
    private Integer coachTier;
    private String coachSpecialtyName;
    private String teamName;
    private BigDecimal teamSalaryAccrued;
    private BigDecimal teamSalaryBalance;
    private String formattedOrganizationSalaryCap;
    private String formattedTeamSalaryAccrued;
    private String formattedTeamSalaryBalance;

    public void setTeamId(Integer teamId)
    {
        this.teamId = teamId;
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

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public BigDecimal getOrganizationSalaryCap()
    {
        return organizationSalaryCap;
    }

    public void setOrganizationSalaryCap(BigDecimal organizationSalaryCap)
    {
        this.organizationSalaryCap = organizationSalaryCap;
    }

    public String getCoachName()
    {
        return coachName;
    }

    public void setCoachName(String coachName)
    {
        this.coachName = coachName;
    }

    public Integer getCoachTier()
    {
        return coachTier;
    }

    public void setCoachTier(Integer coachTier)
    {
        this.coachTier = coachTier;
    }

    public String getCoachSpecialtyName()
    {
        return coachSpecialtyName;
    }

    public void setCoachSpecialtyName(String coachSpecialtyName)
    {
        this.coachSpecialtyName = coachSpecialtyName;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public BigDecimal getTeamSalaryAccrued()
    {
        return teamSalaryAccrued;
    }

    public void setTeamSalaryAccrued(BigDecimal teamSalaryAccrued)
    {
        this.teamSalaryAccrued = teamSalaryAccrued;
    }

    public void addTeamSalaryAccrued(int addedSalary)
    {
        String salaryString = Integer.toString(addedSalary);
        BigDecimal salaryAccrual = new BigDecimal(salaryString);
        teamSalaryAccrued = teamSalaryAccrued.add(salaryAccrual);
    }

    public BigDecimal getTeamSalaryBalance()
    {
        return (organizationSalaryCap.subtract(teamSalaryAccrued));
    }

    public void setTeamSalaryBalance(BigDecimal teamSalaryBalance)
    {
        this.teamSalaryBalance = teamSalaryBalance;
    }

    public Integer getTeamId()
    {
        return teamId;
    }


    public String getFormattedOrganizationSalaryCap()
    {
        String currencyString = organizationSalaryCap.toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedOrganizationSalaryCap = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedOrganizationSalaryCap;
    }

    public String getFormattedTeamSalaryAccrued()
    {
        String currencyString = getTeamSalaryAccrued().toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedTeamSalaryAccrued = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedTeamSalaryAccrued;
    }

    public String getFormattedTeamSalaryBalance()
    {
        String currencyString = getTeamSalaryBalance().toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedTeamSalaryBalance = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedTeamSalaryBalance;
    }
}
