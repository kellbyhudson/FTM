package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlayerPosition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer playerPositionId;
    private String playerPositionName;

    public String getPlayerPositionName()
    {
        return playerPositionName;
    }

    public void setPlayerPositionName(String playerPositionName)
    {
        this.playerPositionName = playerPositionName;
    }
}
