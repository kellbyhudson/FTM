package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TakeOverOrganization
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer takeOverOrganizationId;
    private String takeOverOrganizationName;
    private BigDecimal organizationSalaryCap;
    private byte[] picture;

    public String getTakeOverOrganizationName()
    {
        return takeOverOrganizationName;
    }

    public void setTakeOverOrganizationName(String takeOverOrganizationName)
    {
        this.takeOverOrganizationName = takeOverOrganizationName;
    }

    public BigDecimal getOrganizationSalaryCap()
    {
        return organizationSalaryCap;
    }

    public void setOrganizationSalaryCap(BigDecimal organizationSalaryCap)
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

    public Integer getTakeOverOrganizationId()
    {
        return takeOverOrganizationId;
    }
}
