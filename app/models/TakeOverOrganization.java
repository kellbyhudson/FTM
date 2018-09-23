package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TakeOverOrganization
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer takeOverOrganizationId;
    private String takeOverOrganizationName;
    private Integer organizationSalaryCap;
    private byte[] picture;

    public String getTakeOverOrganizationName()
    {
        return takeOverOrganizationName;
    }

    public void setTakeOverOrganizationName(String takeOverOrganizationName)
    {
        this.takeOverOrganizationName = takeOverOrganizationName;
    }

    public Integer getOrganizationSalaryCap()
    {
        return organizationSalaryCap;
    }

    public void setOrganizationSalaryCap(Integer organizationSalaryCap)
    {
        this.organizationSalaryCap = organizationSalaryCap;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }
}
