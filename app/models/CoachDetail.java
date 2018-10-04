package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.NumberFormat;

@Entity
public class CoachDetail
{
    @Id
    private Integer coachId;
    private String coachName;
    private Integer coachValue;
    private Integer coachSpecialtyId;
    private Integer coachTier;
    private byte[] coachPicture;
    private String formattedCoachSalary;

    public Integer getCoachId()
    {
        return coachId;
    }

    public void setCoachId(Integer coachId)
    {
        this.coachId = coachId;
    }

    public String getCoachName()
    {
        return coachName;
    }

    public void setCoachName(String coachName)
    {
        this.coachName = coachName;
    }

    public Integer getCoachValue()
    {
        return coachValue;
    }

    public void setCoachValue(Integer coachValue)
    {
        this.coachValue = coachValue;
    }

    public Integer getCoachSpecialtyId()
    {
        return coachSpecialtyId;
    }

    public void setCoachSpecialtyId(Integer coachSpecialtyId)
    {
        this.coachSpecialtyId = coachSpecialtyId;
    }

    public Integer getCoachTier()
    {
        return coachTier;
    }

    public void setCoachTier(Integer coachTier)
    {
        this.coachTier = coachTier;
    }

    public byte[] getCoachPicture()
    {
        return coachPicture;
    }

    public void setCoachPicture(byte[] coachPicture)
    {
        this.coachPicture = coachPicture;
    }

    public String getFormattedCoachSalary()
    {
        String currencyString = coachValue.toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedCoachSalary = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedCoachSalary;
    }
}
