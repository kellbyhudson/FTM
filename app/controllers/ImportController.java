package controllers;

import models.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportController extends Controller
{
    private JPAApi jpaApi;

    @Inject
    public ImportController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result importPlayer()
    {
        String returnMessage = "";

        String SAMPLE_CSV_FILE_PATH = "c:\\Users\\kellb\\players.csv";
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for(CSVRecord csvRecord : csvParser)
            {
                String playerIdString = csvRecord.get(0);
                System.out.println(playerIdString);
                Integer playerId = Integer.parseInt(playerIdString);
                System.out.println(playerId);
                String playerName = csvRecord.get(1);
                //System.out.println(playerName);
                String playerAgeString = csvRecord.get(2);
                //System.out.println(playerAgeString);
                Integer playerAge = Integer.parseInt(playerAgeString);
                String playerPositionString = csvRecord.get(3);
                //System.out.println(playerPositionString);
                Integer playerPositionId = 0;
                switch(playerPositionString)
                {
                    case "WR" : playerPositionId = 3;
                        break;
                    case "TE" : playerPositionId = 4;
                        break;
                    case "T" : case "OT" : case "OL" : playerPositionId = 5;
                        break;
                    case "SS" : case "S" : case "FS" : case "DB" : playerPositionId = 12;
                        break;
                    case "RB" : case "HB" : case "FB" : playerPositionId = 2;
                        break;
                    case "QB" : playerPositionId = 1;
                        break;
                    case "P" : playerPositionId = 14;
                        break;
                    case "OLB" : case "LB" : playerPositionId = 10;
                        break;
                    case "OG" : case "G" : playerPositionId = 6;
                        break;
                    case "NT" : case "DT" : playerPositionId = 8;
                        break;
                    case "K" : playerPositionId = 15;
                        break;
                    case "ILB" : playerPositionId = 11;
                        break;
                    case "DE" : case "DL" : playerPositionId = 9;
                        break;
                    case "CB" : playerPositionId = 13;
                        break;
                    case "C" : case "LS" : playerPositionId = 7;
                        break;
                }
                String playerWeightString = csvRecord.get(4);
                //System.out.println(playerWeightString);
                Integer playerWeight = Integer.parseInt(playerWeightString);
                String playerHeightFeetString = csvRecord.get(5);
                //System.out.println(playerHeightFeetString);
                Integer playerHeightFeet = Integer.parseInt(playerHeightFeetString);
                String playerHeightInchesString = csvRecord.get(6);
                //System.out.println(playerHeightInchesString);
                Integer playerHeightInches = Integer.parseInt(playerHeightInchesString);
                String playerCollegeString = csvRecord.get(7);
                //System.out.println(playerCollegeString);
                String yearsExperienceString = csvRecord.get(8);
                //System.out.println(yearsExperienceString);
                Integer yearsExperience = Integer.parseInt(yearsExperienceString);
                String playerValueString = csvRecord.get(9);
                playerValueString = playerValueString.replace(",", "");
                Integer playerValue = Integer.parseInt(playerValueString);

                Player player = new Player();

                player.setPlayerId(playerId);
                player.setPlayerName(playerName);
                player.setPlayerAge(playerAge);
                player.setPlayerPositionId(playerPositionId);
                player.setPlayerWeight(playerWeight);
                player.setPlayerHeightFeet(playerHeightFeet);
                player.setPlayerHeightInches(playerHeightInches);
                player.setPlayerCollege(playerCollegeString);
                player.setYearsExperience(yearsExperience);
                player.setPlayerValue(playerValue);

                System.out.println(player.getPlayerId());

                jpaApi.em().persist(player);

            }
            returnMessage = "success";
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            returnMessage = "failure";
        }
        return ok(returnMessage);
    }
}
