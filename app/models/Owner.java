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
    private String organizationName;

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


}
