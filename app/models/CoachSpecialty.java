package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CoachSpecialty
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer coachSpecialtyId;
    private String coachSpecialtyName;

    public String getCoachSpecialtyName()
    {
        return coachSpecialtyName;
    }

    public void setCoachSpecialtyName(String coachSpecialtyName)
    {
        this.coachSpecialtyName = coachSpecialtyName;
    }
}
