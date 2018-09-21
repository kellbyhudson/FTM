package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coach
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private int coachId;
    private String coachName;
    private int coachValue;
    private int coachSpecialtyId;
    private int coachTier;
    private byte[] coachPicture;

    public int getCoachId()
    {
        return coachId;
    }

    public String getCoachName()
    {
        return coachName;
    }

    public void setCoachName(String coachName)
    {
        this.coachName = coachName;
    }

    public int getCoachValue()
    {
        return coachValue;
    }

    public void setCoachValue(int coachValue)
    {
        this.coachValue = coachValue;
    }

    public int getCoachSpecialtyId()
    {
        return coachSpecialtyId;
    }

    public void setCoachSpecialtyId(int coachSpecialtyId)
    {
        this.coachSpecialtyId = coachSpecialtyId;
    }

    public int getCoachTier()
    {
        return coachTier;
    }

    public void setCoachTier(int coachTier)
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
}
