package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer ownerId;
    private String ownerName;
    private String ownerEmail;
    private String ownerPassword;

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail()
    {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail)
    {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPassword()
    {
        return ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword)
    {
        this.ownerPassword = ownerPassword;
    }
}
