package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Entity
public class OrganizationDetail
{
    @Id
    private Integer takeOverOrganizationId;
    private String takeOverOrganizationName;
    private BigDecimal organizationSalaryCap;
    private byte[] picture;
    private String formattedSalaryCap;

    public Integer getTakeOverOrganizationId()
    {
        return takeOverOrganizationId;
    }

    public void setTakeOverOrganizationId(Integer takeOverOrganizationId)
    {
        this.takeOverOrganizationId = takeOverOrganizationId;
    }

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

    public String getFormattedSalaryCap()
    {
        String currencyString = organizationSalaryCap.toString();

        long currencyLong = Long.parseLong(currencyString);

        formattedSalaryCap = NumberFormat.getCurrencyInstance().format(currencyLong);

        return formattedSalaryCap;
    }

}
