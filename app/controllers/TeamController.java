package controllers;

import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public TeamController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    public Result getSetupTeam()
    {
        return ok(views.html.setupteam.render());
    }

    @Transactional
    public Result postSetupTeam()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String teamName = form.get("teamname");

        session().put("teamname", teamName);

        String teamCity = form.get("teamcity");

        session().put("teamcity", teamCity);

        String ownerIdString = session().get("ownerId");

        String takeOverOrganizationIdString = session().get("takeOverOrganizationId");

        String coachIdString = session().get("coachId");

        Team team = new Team();

        team.setTeamCity(teamCity);
        team.setTeamName(teamName);
        team.setOwnerId(Integer.parseInt(ownerIdString));
        team.setCoachId(Integer.parseInt(coachIdString));
        team.setTakeOverOrganizationId(Integer.parseInt(takeOverOrganizationIdString));

        jpaApi.em().persist(team);

        String teamIdString = team.getTeamId().toString();

        session().put("MYteamId", teamIdString);

        session().put("THISteamId", teamIdString);

        return redirect("/draftteam");
    }

    @Transactional
    public Result getDraftTeam()
    {
        String ownerIdString = session().get("ownerId");
        Integer ownerId = Integer.parseInt(ownerIdString);

        String coachIdString = session().get("coachId");
        Integer coachId = Integer.parseInt(coachIdString);

        String takeOverOrganizationIdString = session().get("takeOverOrganizationId");
        Integer takeOverOrganizationId = Integer.parseInt(takeOverOrganizationIdString);

        String ownerSQL = "SELECT o FROM Owner o WHERE ownerId = :ownerId ";

        Owner owner = jpaApi.em().createQuery(ownerSQL, Owner.class).setParameter("ownerId", ownerId).getSingleResult();

        String coachSQL = "SELECT c FROM Coach c WHERE coachId = :coachId ";

        Coach coach = jpaApi.em().createQuery(coachSQL, Coach.class).setParameter("coachId", coachId).getSingleResult();

        String takeOverOrganizationSQL = "SELECT to FROM TakeOverOrganization to WHERE takeOverOrganizationId = :takeOverOrganizationId ";

        TakeOverOrganization takeOverOrganization = jpaApi.em().createQuery(takeOverOrganizationSQL, TakeOverOrganization.class).setParameter("takeOverOrganizationId", takeOverOrganizationId).getSingleResult();

        String coachSpecialtySQL = "SELECT cs FROM CoachSpecialty cs WHERE coachSpecialtyId = :coachSpecialtyId ";

        CoachSpecialty coachSpecialty = jpaApi.em().createQuery(coachSpecialtySQL, CoachSpecialty.class).setParameter("coachSpecialtyId", coach.getCoachSpecialtyId()).getSingleResult();

        Integer teamId = Integer.parseInt(session().get("MYteamId"));

        String teamPlayersSQL = "SELECT tp FROM TeamPlayer tp WHERE teamId = :teamId";

        List<TeamPlayer> teamPlayers = jpaApi.em().createQuery(teamPlayersSQL, TeamPlayer.class).setParameter("teamId", teamId).getResultList();

        TeamDetail teamDetail = new TeamDetail();
        BigDecimal ZERO = new BigDecimal(0);
        teamDetail.setTeamSalaryAccrued(ZERO);

        Quarterback quarterback = new Quarterback();
        Quarterback2 quarterback2 = new Quarterback2();
        Quarterback3 quarterback3 = new Quarterback3();

        RunningBack runningBack = new RunningBack();
        RunningBack2 runningBack2 = new RunningBack2();
        RunningBack3 runningBack3 = new RunningBack3();

        WideReceiver1 wideReceiver1 = new WideReceiver1();
        WideReceiver2 wideReceiver2 = new WideReceiver2();
        WideReceiver3 wideReceiver3 = new WideReceiver3();
        WideReceiver4 wideReceiver4 = new WideReceiver4();
        WideReceiver5 wideReceiver5 = new WideReceiver5();
        WideReceiver6 wideReceiver6 = new WideReceiver6();

        TightEnd tightEnd = new TightEnd();
        TightEnd2 tightEnd2 = new TightEnd2();

        Tackle1 tackle1 = new Tackle1();
        Tackle2 tackle2 = new Tackle2();
        Tackle3 tackle3 = new Tackle3();
        Tackle4 tackle4 = new Tackle4();

        Guard1 guard1 = new Guard1();
        Guard2 guard2 = new Guard2();
        Guard3 guard3 = new Guard3();
        Guard4 guard4 = new Guard4();

        Center center = new Center();
        Center2 center2 = new Center2();

        DefensiveTackle1 defensiveTackle1 = new DefensiveTackle1();
        DefensiveTackle2 defensiveTackle2 = new DefensiveTackle2();
        DefensiveTackle3 defensiveTackle3 = new DefensiveTackle3();
        DefensiveTackle4 defensiveTackle4 = new DefensiveTackle4();
        DefensiveTackle5 defensiveTackle5 = new DefensiveTackle5();

        DefensiveEnd1 defensiveEnd1 = new DefensiveEnd1();
        DefensiveEnd2 defensiveEnd2 = new DefensiveEnd2();
        DefensiveEnd3 defensiveEnd3 = new DefensiveEnd3();
        DefensiveEnd4 defensiveEnd4 = new DefensiveEnd4();
        DefensiveEnd5 defensiveEnd5 = new DefensiveEnd5();

        OutsideLinebacker1 outsideLinebacker1 = new OutsideLinebacker1();
        OutsideLinebacker2 outsideLinebacker2 = new OutsideLinebacker2();
        OutsideLinebacker3 outsideLinebacker3 = new OutsideLinebacker3();
        OutsideLinebacker4 outsideLinebacker4 = new OutsideLinebacker4();

        InsideLinebacker insideLinebacker = new InsideLinebacker();
        InsideLinebacker2 insideLinebacker2 = new InsideLinebacker2();
        InsideLinebacker3 insideLinebacker3 = new InsideLinebacker3();

        Safety1 safety1 = new Safety1();
        Safety2 safety2 = new Safety2();
        Safety3 safety3 = new Safety3();
        Safety4 safety4 = new Safety4();

        Cornerback1 cornerback1 = new Cornerback1();
        Cornerback2 cornerback2 = new Cornerback2();
        Cornerback3 cornerback3 = new Cornerback3();
        Cornerback4 cornerback4 = new Cornerback4();

        Kicker kicker = new Kicker();
        Punter punter = new Punter();

        Holder holder = new Holder();
        LongSnapper longSnapper = new LongSnapper();

        TeamDisplay teamDisplay = new TeamDisplay(quarterback, quarterback2, quarterback3, runningBack, runningBack2, runningBack3, wideReceiver1, wideReceiver2, wideReceiver3, wideReceiver4, wideReceiver5, wideReceiver6, tightEnd, tightEnd2, tackle1, tackle2, tackle3, tackle4, guard1, guard2, guard3, guard4, center, center2, defensiveTackle1, defensiveTackle2, defensiveTackle3, defensiveTackle4, defensiveTackle5, defensiveEnd1, defensiveEnd2, defensiveEnd3, defensiveEnd4, defensiveEnd5, outsideLinebacker1, outsideLinebacker2, outsideLinebacker3, outsideLinebacker4, insideLinebacker, insideLinebacker2, insideLinebacker3, safety1, safety2, safety3, safety4, cornerback1, cornerback2, cornerback3, cornerback4, punter, kicker, holder, longSnapper);


        for (TeamPlayer teamPlayer : teamPlayers)
        {
            switch (teamPlayer.getSortOrderId())
            {
                case 1:
                    quarterback.setName(teamPlayer.getTeamPlayerName());
                    quarterback.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback.getValue());
                    teamDisplay.setQuarterback(quarterback);
                    break;
                case 2:
                    runningBack.setName(teamPlayer.getTeamPlayerName());
                    runningBack.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack.getValue());
                    teamDisplay.setRunningBack(runningBack);
                    break;
                case 3:
                    wideReceiver1.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver1.getValue());
                    teamDisplay.setWideReceiver1(wideReceiver1);
                    break;
                case 4:
                    wideReceiver2.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver2.getValue());
                    teamDisplay.setWideReceiver2(wideReceiver2);
                    break;
                case 5:
                    wideReceiver3.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver3.getValue());
                    teamDisplay.setWideReceiver3(wideReceiver3);
                    break;
                case 6:
                    tightEnd.setName(teamPlayer.getTeamPlayerName());
                    tightEnd.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tightEnd.getValue());
                    teamDisplay.setTightEnd(tightEnd);
                    break;
                case 7:
                    tackle1.setName(teamPlayer.getTeamPlayerName());
                    tackle1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle1.getValue());
                    teamDisplay.setTackle1(tackle1);
                    break;
                case 8:
                    tackle2.setName(teamPlayer.getTeamPlayerName());
                    tackle2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle2.getValue());
                    teamDisplay.setTackle2(tackle2);
                    break;
                case 9:
                    guard1.setName(teamPlayer.getTeamPlayerName());
                    guard1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard1.getValue());
                    teamDisplay.setGuard1(guard1);
                    break;

                case 10:
                    guard2.setName(teamPlayer.getTeamPlayerName());
                    guard2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard2.getValue());
                    teamDisplay.setGuard2(guard2);
                    break;
                case 11:
                    center.setName(teamPlayer.getTeamPlayerName());
                    center.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(center.getValue());
                    teamDisplay.setCenter(center);
                    break;
                case 13:
                    defensiveTackle1.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle1.getValue());
                    teamDisplay.setDefensiveTackle1(defensiveTackle1);
                    break;
                case 14:
                    defensiveTackle2.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle2.getValue());
                    teamDisplay.setDefensiveTackle2(defensiveTackle2);
                    break;
                case 15:
                    defensiveEnd1.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd1.getValue());
                    teamDisplay.setDefensiveEnd1(defensiveEnd1);
                    break;
                case 16:
                    defensiveEnd2.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd2.getValue());
                    teamDisplay.setDefensiveEnd2(defensiveEnd2);
                    break;
                case 17:
                    outsideLinebacker1.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker1.getValue());
                    teamDisplay.setOutsideLinebacker1(outsideLinebacker1);
                    break;
                case 19:
                    outsideLinebacker2.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker2.getValue());
                    teamDisplay.setOutsideLinebacker2(outsideLinebacker2);
                    break;
                case 18:
                    insideLinebacker.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker.getValue());
                    teamDisplay.setInsideLinebacker(insideLinebacker);
                    break;
                case 20:
                    safety1.setName(teamPlayer.getTeamPlayerName());
                    safety1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety1.getValue());
                    teamDisplay.setSafety1(safety1);
                    break;
                case 21:
                    safety2.setName(teamPlayer.getTeamPlayerName());
                    safety2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety2.getValue());
                    teamDisplay.setSafety2(safety2);
                    break;
                case 22:
                    cornerback1.setName(teamPlayer.getTeamPlayerName());
                    cornerback1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback1.getValue());
                    teamDisplay.setCornerback1(cornerback1);
                    break;
                case 23:
                    cornerback2.setName(teamPlayer.getTeamPlayerName());
                    cornerback2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback2.getValue());
                    teamDisplay.setCornerback2(cornerback2);
                    break;
                case 24:
                    punter.setName(teamPlayer.getTeamPlayerName());
                    punter.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(punter.getValue());
                    teamDisplay.setPunter(punter);
                    break;
                case 12:
                    kicker.setName(teamPlayer.getTeamPlayerName());
                    kicker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(kicker.getValue());
                    teamDisplay.setKicker(kicker);
                    break;
                case 25:
                    quarterback2.setName(teamPlayer.getTeamPlayerName());
                    quarterback2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback2.getValue());
                    teamDisplay.setQuarterback2(quarterback2);
                    break;
                case 26:
                    quarterback3.setName(teamPlayer.getTeamPlayerName());
                    quarterback3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback3.getValue());
                    teamDisplay.setQuarterback3(quarterback3);
                    break;
                case 27:
                    runningBack2.setName(teamPlayer.getTeamPlayerName());
                    runningBack2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack2.getValue());
                    teamDisplay.setRunningBack2(runningBack2);
                    break;
                case 28:
                    runningBack3.setName(teamPlayer.getTeamPlayerName());
                    runningBack3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack3.getValue());
                    teamDisplay.setRunningBack3(runningBack3);
                    break;
                case 29:
                    wideReceiver4.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver4.getValue());
                    teamDisplay.setWideReceiver4(wideReceiver4);
                    break;
                case 30:
                    wideReceiver5.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver5.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver5.getValue());
                    teamDisplay.setWideReceiver5(wideReceiver5);
                    break;
                case 31:
                    wideReceiver6.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver6.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver6.getValue());
                    teamDisplay.setWideReceiver6(wideReceiver6);
                    break;
                case 32:
                    tightEnd2.setName(teamPlayer.getTeamPlayerName());
                    tightEnd2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tightEnd2.getValue());
                    teamDisplay.setTightEnd2(tightEnd2);
                    break;
                case 33:
                    tackle3.setName(teamPlayer.getTeamPlayerName());
                    tackle3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle3.getValue());
                    teamDisplay.setTackle3(tackle3);
                    break;
                case 34:
                    tackle4.setName(teamPlayer.getTeamPlayerName());
                    tackle4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle4.getValue());
                    teamDisplay.setTackle4(tackle4);
                    break;
                case 35:
                    guard3.setName(teamPlayer.getTeamPlayerName());
                    guard3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard3.getValue());
                    teamDisplay.setGuard3(guard3);
                    break;
                case 36:
                    guard4.setName(teamPlayer.getTeamPlayerName());
                    guard4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard4.getValue());
                    teamDisplay.setGuard4(guard4);
                    break;
                case 37:
                    center2.setName(teamPlayer.getTeamPlayerName());
                    center2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(center2.getValue());
                    teamDisplay.setCenter2(center2);
                    break;
                case 38:
                    defensiveTackle3.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle3.getValue());
                    teamDisplay.setDefensiveTackle3(defensiveTackle3);
                    break;
                case 39:
                    defensiveTackle4.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle4.getValue());
                    teamDisplay.setDefensiveTackle4(defensiveTackle4);
                    break;
                case 40:
                    defensiveTackle5.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle5.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle5.getValue());
                    teamDisplay.setDefensiveTackle5(defensiveTackle5);
                    break;
                case 41:
                    defensiveEnd3.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd3.getValue());
                    teamDisplay.setDefensiveEnd3(defensiveEnd3);
                    break;
                case 42:
                    defensiveEnd4.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd4.getValue());
                    teamDisplay.setDefensiveEnd4(defensiveEnd4);
                    break;
                case 43:
                    defensiveEnd5.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd5.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd5.getValue());
                    teamDisplay.setDefensiveEnd5(defensiveEnd5);
                    break;
                case 44:
                    outsideLinebacker3.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker3.getValue());
                    teamDisplay.setOutsideLinebacker3(outsideLinebacker3);
                    break;
                case 45:
                    outsideLinebacker4.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker4.getValue());
                    teamDisplay.setOutsideLinebacker4(outsideLinebacker4);
                    break;
                case 46:
                    insideLinebacker2.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker2.getValue());
                    teamDisplay.setInsideLinebacker2(insideLinebacker2);
                    break;
                case 47:
                    insideLinebacker3.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker3.getValue());
                    teamDisplay.setInsideLinebacker3(insideLinebacker3);
                    break;
                case 48:
                    safety3.setName(teamPlayer.getTeamPlayerName());
                    safety3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety3.getValue());
                    teamDisplay.setSafety3(safety3);
                    break;
                case 49:
                    safety4.setName(teamPlayer.getTeamPlayerName());
                    safety4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety4.getValue());
                    teamDisplay.setSafety4(safety4);
                    break;
                case 50:
                    cornerback3.setName(teamPlayer.getTeamPlayerName());
                    cornerback3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback3.getValue());
                    teamDisplay.setCornerback3(cornerback3);
                    break;
                case 51:
                    cornerback4.setName(teamPlayer.getTeamPlayerName());
                    cornerback4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback4.getValue());
                    teamDisplay.setCornerback4(cornerback4);
                    break;
                case 52:
                    holder.setName(teamPlayer.getTeamPlayerName());
                    holder.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(holder.getValue());
                    teamDisplay.setHolder(holder);
                    break;
                case 53:
                    longSnapper.setName(teamPlayer.getTeamPlayerName());
                    longSnapper.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(longSnapper.getValue());
                    teamDisplay.setLongSnapper(longSnapper);
                    break;

            }
        }

        teamDetail.setCoachId(coachId);
        teamDetail.setOwnerId(ownerId);
        teamDetail.setTakeOverOrganizationId(takeOverOrganizationId);
        teamDetail.setCoachName(coach.getCoachName());
        teamDetail.setTakeOverOrganizationId(takeOverOrganization.getTakeOverOrganizationId());
        teamDetail.setOwnerName(owner.getOwnerName());
        teamDetail.setOrganizationName(owner.getOwnerEmail());
        teamDetail.setOrganizationSalaryCap(takeOverOrganization.getOrganizationSalaryCap());
        teamDetail.setCoachSpecialtyName(coachSpecialty.getCoachSpecialtyName());
        teamDetail.setCoachTier(coach.getCoachTier());

        try{
            String sql = "SELECT t FROM Team t WHERE t.teamId = :teamId ";

            Team team = jpaApi.em().createQuery(sql, Team.class).setParameter("teamId", teamId).getSingleResult();

            teamDetail.setTeamName(team.getTeamCity() + " " + team.getTeamName());

        } catch (Exception e)
        {
            teamDetail.setTeamName(session().get("teamName"));
        }


        session().put("balance", teamDetail.getTeamSalaryBalance().toString());
        session().put("salary", teamDetail.getTeamSalaryAccrued().toString());

        return ok(views.html.draftteam.render(teamDetail, teamDisplay));
    }

    @Transactional
    public Result postDraftTeam()
    {
        String result = "";

        Integer balance = Integer.parseInt(session().get("balance"));

        if (balance >= 0)
        {
            try
            {
                Integer teamId = Integer.parseInt(session().get("THISteamId"));

                String playerSQL = "SELECT tp FROM TeamPlayer tp WHERE teamId = :teamId AND teamPlayerPositionId = :teamPlayerPositionId ORDER BY sortOrderId";

                List<TeamPlayer> quarterbacksList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 1).getResultList();

                List<Quarterback> quarterbacks = new ArrayList<>();
                for (TeamPlayer quarterback : quarterbacksList)
                {
                    Quarterback quarterback1 = new Quarterback();
                    quarterback1.setName(quarterback.getTeamPlayerName());
                    quarterback1.setValue(quarterback.getTeamPlayerValue());
                    quarterbacks.add(quarterback1);
                }

                List<TeamPlayer> runningBacksList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 2).getResultList();

                List<RunningBack> runningBacks = new ArrayList<>();
                for (TeamPlayer runningback : runningBacksList)
                {
                    RunningBack runningBack = new RunningBack();
                    runningBack.setName(runningback.getTeamPlayerName());
                    runningBack.setValue(runningback.getTeamPlayerValue());
                    runningBacks.add(runningBack);
                }

                List<TeamPlayer> wideReceiver1List = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 3).getResultList();

                List<WideReceiver1> wideReceiver1s = new ArrayList<>();
                for (TeamPlayer wideReceiver : wideReceiver1List)
                {
                    WideReceiver1 wideReceiver1 = new WideReceiver1();
                    wideReceiver1.setName(wideReceiver.getTeamPlayerName());
                    wideReceiver1.setValue(wideReceiver.getTeamPlayerValue());
                    wideReceiver1s.add(wideReceiver1);
                }

                List<TeamPlayer> tightEndsList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 4).getResultList();

                List<TightEnd> tightEnds = new ArrayList<>();
                for (TeamPlayer tightEnd : tightEndsList)
                {
                    TightEnd tightEnd1 = new TightEnd();
                    tightEnd1.setName(tightEnd.getTeamPlayerName());
                    tightEnd1.setValue(tightEnd.getTeamPlayerValue());
                    tightEnds.add(tightEnd1);
                }

                List<TeamPlayer> tackleList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 5).getResultList();

                List<Tackle1> tackle1s = new ArrayList<>();
                for (TeamPlayer tackle : tackleList)
                {
                    Tackle1 tackle1 = new Tackle1();
                    tackle1.setName(tackle.getTeamPlayerName());
                    tackle1.setValue(tackle.getTeamPlayerValue());
                    tackle1s.add(tackle1);
                }

                List<TeamPlayer> guardList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 6).getResultList();

                List<Guard1> guard1s = new ArrayList<>();
                for (TeamPlayer guard : guardList)
                {
                    Guard1 guard1 = new Guard1();
                    guard1.setName(guard.getTeamPlayerName());
                    guard1.setValue(guard.getTeamPlayerValue());
                    guard1s.add(guard1);
                }

                List<TeamPlayer> centersList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 7).getResultList();

                List<Center> centers = new ArrayList<>();
                for (TeamPlayer center : centersList)
                {
                    Center center1 = new Center();
                    center1.setName(center.getTeamPlayerName());
                    center1.setValue(center.getTeamPlayerValue());
                    centers.add(center1);
                }

                List<TeamPlayer> defensiveTackleList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 8).getResultList();

                List<DefensiveTackle1> defensiveTackle1s = new ArrayList<>();
                for (TeamPlayer defensivetackle : defensiveTackleList)
                {
                    DefensiveTackle1 defensiveTackle1 = new DefensiveTackle1();
                    defensiveTackle1.setName(defensivetackle.getTeamPlayerName());
                    defensiveTackle1.setValue(defensivetackle.getTeamPlayerValue());
                    defensiveTackle1s.add(defensiveTackle1);
                }

                List<TeamPlayer> defensiveEndList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 9).getResultList();

                List<DefensiveEnd1> defensiveEnd1s = new ArrayList<>();
                for (TeamPlayer defensiveend : defensiveEndList)
                {
                    DefensiveEnd1 defensiveEnd1 = new DefensiveEnd1();
                    defensiveEnd1.setName(defensiveend.getTeamPlayerName());
                    defensiveEnd1.setValue(defensiveend.getTeamPlayerValue());
                    defensiveEnd1s.add(defensiveEnd1);
                }

                List<TeamPlayer> outsideLinebackerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 10).getResultList();

                List<OutsideLinebacker1> outsideLinebacker1s = new ArrayList<>();
                for (TeamPlayer outsidelinebacker : outsideLinebackerList)
                {
                    OutsideLinebacker1 outsideLinebacker1 = new OutsideLinebacker1();
                    outsideLinebacker1.setName(outsidelinebacker.getTeamPlayerName());
                    outsideLinebacker1.setValue(outsidelinebacker.getTeamPlayerValue());
                    outsideLinebacker1s.add(outsideLinebacker1);
                }

                List<TeamPlayer> insideLinebackerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 11).getResultList();

                List<InsideLinebacker> insideLinebackers1 = new ArrayList<>();
                for (TeamPlayer insidelinebacker : insideLinebackerList)
                {
                    InsideLinebacker insideLinebacker = new InsideLinebacker();
                    insideLinebacker.setName(insidelinebacker.getTeamPlayerName());
                    insideLinebacker.setValue(insidelinebacker.getTeamPlayerValue());
                    insideLinebackers1.add(insideLinebacker);
                }

                List<TeamPlayer> safetyList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 12).getResultList();

                List<Safety1> safety1s = new ArrayList<>();
                for (TeamPlayer safety : safetyList)
                {
                    Safety1 safety1 = new Safety1();
                    safety1.setName(safety.getTeamPlayerName());
                    safety1.setValue(safety.getTeamPlayerValue());
                    safety1s.add(safety1);
                }

                List<TeamPlayer> cornerbackList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 13).getResultList();

                List<Cornerback1> cornerback1s = new ArrayList<>();
                for (TeamPlayer cornerback : cornerbackList)
                {
                    Cornerback1 cornerback1 = new Cornerback1();
                    cornerback1.setName(cornerback.getTeamPlayerName());
                    cornerback1.setValue(cornerback.getTeamPlayerValue());
                    cornerback1s.add(cornerback1);
                }

                List<TeamPlayer> kickerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 15).getResultList();

                List<Kicker> kickers = new ArrayList<>();
                for (TeamPlayer kicker : kickerList)
                {
                    Kicker kicker1 = new Kicker();
                    kicker1.setName(kicker.getTeamPlayerName());
                    kicker1.setValue(kicker.getTeamPlayerValue());
                    kickers.add(kicker1);
                }

                List<TeamPlayer> puntersList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 14).getResultList();

                List<Punter> punters = new ArrayList<>();
                for (TeamPlayer punter : puntersList)
                {
                    Punter punter1 = new Punter();
                    punter1.setName(punter.getTeamPlayerName());
                    punter1.setValue(punter.getTeamPlayerValue());
                    punters.add(punter1);
                }

                List<TeamPlayer> holderlist = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 16).getResultList();

                List<Holder> holders = new ArrayList<>();
                for (TeamPlayer holder : holderlist)
                {
                    Holder holder1 = new Holder();
                    holder1.setName(holder.getTeamPlayerName());
                    holder1.setValue(holder.getTeamPlayerValue());
                    holders.add(holder1);
                }

                List<TeamPlayer> longsnapperlist = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 17).getResultList();

                List<LongSnapper> longsnappers = new ArrayList<>();
                for (TeamPlayer longsnapper : longsnapperlist)
                {
                    LongSnapper longSnapper1 = new LongSnapper();
                    longSnapper1.setName(longsnapper.getTeamPlayerName());
                    longSnapper1.setValue(longsnapper.getTeamPlayerValue());
                    longsnappers.add(longSnapper1);
                }

                Quarterback quarterback = quarterbacks.get(0);
                Quarterback quarterback2 = quarterbacks.get(1);
                Quarterback quarterback3 = quarterbacks.get(2);

                RunningBack runningBack = runningBacks.get(0);
                RunningBack runningBack2 = runningBacks.get(1);
                RunningBack runningBack3 = runningBacks.get(2);

                WideReceiver1 wideReceiver1 = wideReceiver1s.get(0);
                WideReceiver1 wideReceiver2 = wideReceiver1s.get(1);
                WideReceiver1 wideReceiver3 = wideReceiver1s.get(2);
                WideReceiver1 wideReceiver4 = wideReceiver1s.get(3);
                WideReceiver1 wideReceiver5 = wideReceiver1s.get(4);
                WideReceiver1 wideReceiver6 = wideReceiver1s.get(5);

                TightEnd tightEnd = tightEnds.get(0);
                TightEnd tightEnd2 = tightEnds.get(1);

                Tackle1 tackle1 = tackle1s.get(0);
                Tackle1 tackle2 = tackle1s.get(1);
                Tackle1 tackle3 = tackle1s.get(2);
                Tackle1 tackle4 = tackle1s.get(3);

                Guard1 guard1 = guard1s.get(0);
                Guard1 guard2 = guard1s.get(1);
                Guard1 guard3 = guard1s.get(2);
                Guard1 guard4 = guard1s.get(3);

                Center center = centers.get(0);
                Center center2 = centers.get(1);

                DefensiveTackle1 defensiveTackle1 = defensiveTackle1s.get(0);
                DefensiveTackle1 defensiveTackle2 = defensiveTackle1s.get(1);
                DefensiveTackle1 defensiveTackle3 = defensiveTackle1s.get(2);
                DefensiveTackle1 defensiveTackle4 = defensiveTackle1s.get(3);
                DefensiveTackle1 defensiveTackle5 = defensiveTackle1s.get(4);

                DefensiveEnd1 defensiveEnd1 = defensiveEnd1s.get(0);
                DefensiveEnd1 defensiveEnd2 = defensiveEnd1s.get(1);
                DefensiveEnd1 defensiveEnd3 = defensiveEnd1s.get(2);
                DefensiveEnd1 defensiveEnd4 = defensiveEnd1s.get(3);
                DefensiveEnd1 defensiveEnd5 = defensiveEnd1s.get(4);

                OutsideLinebacker1 outsideLinebacker1 = outsideLinebacker1s.get(0);
                OutsideLinebacker1 outsideLinebacker2 = outsideLinebacker1s.get(1);
                OutsideLinebacker1 outsideLinebacker3 = outsideLinebacker1s.get(2);
                OutsideLinebacker1 outsideLinebacker4 = outsideLinebacker1s.get(3);

                InsideLinebacker insideLinebacker = insideLinebackers1.get(0);
                InsideLinebacker insideLinebacker2 = insideLinebackers1.get(1);
                InsideLinebacker insideLinebacker3 = insideLinebackers1.get(2);

                Safety1 safety1 = safety1s.get(0);
                Safety1 safety2 = safety1s.get(1);
                Safety1 safety3 = safety1s.get(2);
                Safety1 safety4 = safety1s.get(3);

                Cornerback1 cornerback1 = cornerback1s.get(0);
                Cornerback1 cornerback2 = cornerback1s.get(1);
                Cornerback1 cornerback3 = cornerback1s.get(2);
                Cornerback1 cornerback4 = cornerback1s.get(3);

                Kicker kicker = kickers.get(0);
                Punter punter = punters.get(0);

                Holder holder = holders.get(0);
                LongSnapper longSnapper = longsnappers.get(0);


                String sql = "SELECT t FROM Team t WHERE teamId = :teamId ";

                Team team = jpaApi.em().createQuery(sql, Team.class).setParameter("teamId", teamId).getSingleResult();

                try
                {
                    session().put("salary", team.getTeamSalary().toString());
                    session().put("teamname", team.getTeamName());
                    session().put("teamcity", team.getTeamCity());
                    session().put("ownerId", team.getOwnerId().toString());
                    session().put("coachId", team.getCoachId().toString());
                    session().put("takeOverOrganizationId", team.getTakeOverOrganizationId().toString());

                    Integer teamSalary = Integer.parseInt(session().get("salary"));
                    String teamName = session().get("teamname");
                    String teamCity = session().get("teamcity");
                    Integer ownerId = Integer.parseInt(session().get("ownerId"));
                    Integer coachId = Integer.parseInt(session().get("coachId"));
                    Integer takeOverOrganizationId = Integer.parseInt(session().get("takeOverOrganizationId"));


                    team.setTeamId(teamId);
                    team.setTeamCity(teamCity);
                    team.setTeamName(teamName);
                    team.setOwnerId(ownerId);
                    team.setCoachId(coachId);
                    team.setTakeOverOrganizationId(takeOverOrganizationId);
                    team.setTeamSalary(teamSalary);
                    jpaApi.em().persist(team);
                    result = "teamlayout/";
                    result += teamId;

                } catch (Exception e)
                {
                    Integer teamSalary = Integer.parseInt(session().get("salary"));
                    String teamName = session().get("teamname");
                    String teamCity = session().get("teamcity");
                    Integer ownerId = Integer.parseInt(session().get("ownerId"));
                    Integer coachId = Integer.parseInt(session().get("coachId"));
                    Integer takeOverOrganizationId = Integer.parseInt(session().get("takeOverOrganizationId"));

                    team.setTeamId(teamId);
                    team.setTeamCity(teamCity);
                    team.setTeamName(teamName);
                    team.setOwnerId(ownerId);
                    team.setCoachId(coachId);
                    team.setTakeOverOrganizationId(takeOverOrganizationId);
                    team.setTeamSalary(teamSalary);
                    jpaApi.em().persist(team);
                    result = "teamlayout/";
                    result += teamId;
                }


            } catch (Exception e)
            {
                result = "draftteam";
            }
        }
        else
        {
            result = "draftteam";
        }


        return redirect("/" + result);
    }

    @Transactional
    public Result getDraftPlayer(int sortOrderId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        int playerPositionId = sortOrderId;
        //QB
        if (sortOrderId == 25 || sortOrderId == 26 || sortOrderId == 52)
        {
            playerPositionId = 1;
        }
        //RB
        if (sortOrderId == 27 || sortOrderId == 28)
        {
            playerPositionId = 2;
        }
        //WR
        if (sortOrderId == 3 || sortOrderId == 4 || sortOrderId == 5 || sortOrderId == 29 || sortOrderId == 30 || sortOrderId == 31)
        {
            playerPositionId = 3;
        }
        //TE
        if (sortOrderId == 6 || sortOrderId == 32)
        {
            playerPositionId = 4;
        }
        //T
        if (sortOrderId == 7 || sortOrderId == 8 || sortOrderId == 33 || sortOrderId == 34)
        {
            playerPositionId = 5;
        }
        //G
        if (sortOrderId == 9 || sortOrderId == 10 || sortOrderId == 35 || sortOrderId == 36)
        {
            playerPositionId = 6;
        }
        //C
        if (sortOrderId == 11 || sortOrderId == 37 || sortOrderId == 53)
        {
            playerPositionId = 7;
        }
        //K
        if (sortOrderId == 12)
        {
            playerPositionId = 15;
        }
        //DT
        if (sortOrderId == 13 || sortOrderId == 14 || sortOrderId == 38 || sortOrderId == 39 || sortOrderId == 40)
        {
            playerPositionId = 8;
        }
        //DE
        if (sortOrderId == 15 || sortOrderId == 16 || sortOrderId == 41 || sortOrderId == 42 || sortOrderId == 43)
        {
            playerPositionId = 9;
        }
        //OLB
        if (sortOrderId == 17 || sortOrderId == 19 || sortOrderId == 44 || sortOrderId == 45)
        {
            playerPositionId = 10;
        }
        //ILB
        if (sortOrderId == 18 || sortOrderId == 46 || sortOrderId == 47)
        {
            playerPositionId = 11;
        }
        //S
        if (sortOrderId == 20 || sortOrderId == 21 || sortOrderId == 48 || sortOrderId == 49)
        {
            playerPositionId = 12;
        }
        //CB
        if (sortOrderId == 22 || sortOrderId == 23 || sortOrderId == 50 || sortOrderId == 51)
        {
            playerPositionId = 13;
        }
        //P
        if (sortOrderId == 24)
        {
            playerPositionId = 14;
        }

        String playerPositionSQL = "SELECT pp FROM PlayerPosition pp WHERE playerPositionId = :playerPositionId ";

        PlayerPosition playerPosition = jpaApi.em().createQuery(playerPositionSQL, PlayerPosition.class).setParameter("playerPositionId", playerPositionId).getSingleResult();

        String searchNameText = form.get("searchname");

        String searchName = searchNameText;

        if (searchName == null)
        {
            searchName = "";
        }

        searchName += "%";

        String searchCollegeText = form.get("college");

        String searchCollege = searchCollegeText;

        if (searchCollege == null)
        {
            searchCollege = "";
        }

        searchCollege += "%";

        String minSalary = form.get("searchMinValue");

        String playerSQL = "SELECT p FROM Player p WHERE playerCollege LIKE :searchCollege AND playerName LIKE :searchName AND  playerPositionId = :playerPositionId " +
                "AND playerId NOT IN (SELECT playerId FROM TeamPlayer tp WHERE teamId = :teamId) " +
                "ORDER BY ";

        if (minSalary != null)
        {
            playerSQL += "playerValue, ";
        }


        playerSQL += "playerValue desc ";

        Integer teamId = Integer.parseInt(session().get("MYteamId"));

        List<Player> players = jpaApi.em().createQuery(playerSQL, Player.class).setParameter("playerPositionId", playerPositionId).setParameter("teamId", teamId).setParameter("searchName", searchName).setParameter("searchCollege", searchCollege).getResultList();

        String collegeSQL = "SELECT DISTINCT p.playerCollege FROM Player p WHERE playerPositionId = :playerPositionId " +
                "AND playerId NOT IN (SELECT playerId FROM TeamPlayer tp WHERE teamId = :teamId) " +
                "ORDER BY playerCollege ";

        List<String> colleges = jpaApi.em().createQuery(collegeSQL, String.class).setParameter("playerPositionId", playerPositionId).setParameter("teamId", teamId).getResultList();

        List<PlayerDetail> playerDetails = new ArrayList<>();

        for (Player player : players)
        {
            PlayerDetail playerDetail = new PlayerDetail();
            playerDetail.setPlayerId(player.getPlayerId());
            playerDetail.setPlayerName(player.getPlayerName());
            playerDetail.setPlayerValue(player.getPlayerValue());
            playerDetail.setPlayerHeightFeet(player.getPlayerHeightFeet());
            playerDetail.setPlayerHeightInches(player.getPlayerHeightInches());
            playerDetail.setPlayerWeight(player.getPlayerWeight());
            playerDetail.setYearsExperience(player.getYearsExperience());
            playerDetail.setPlayerCollege(player.getPlayerCollege());

            playerDetails.add(playerDetail);
        }
        return ok(views.html.draftplayer.render(playerPosition, playerDetails, colleges, searchNameText, searchCollegeText));
    }

    @Transactional
    public Result postDraftPlayer(int sortOrderId)
    {
        int playerPositionId = sortOrderId;
        //QB
        if (sortOrderId == 25 || sortOrderId == 26)
        {
            playerPositionId = 1;
        }
        //RB
        if (sortOrderId == 27 || sortOrderId == 28)
        {
            playerPositionId = 2;
        }
        //WR
        if (sortOrderId == 3 || sortOrderId == 4 || sortOrderId == 5 || sortOrderId == 29 || sortOrderId == 30 || sortOrderId == 31)
        {
            playerPositionId = 3;
        }
        //TE
        if (sortOrderId == 6 || sortOrderId == 32)
        {
            playerPositionId = 4;
        }
        //T
        if (sortOrderId == 7 || sortOrderId == 8 || sortOrderId == 33 || sortOrderId == 34)
        {
            playerPositionId = 5;
        }
        //G
        if (sortOrderId == 9 || sortOrderId == 10 || sortOrderId == 35 || sortOrderId == 36)
        {
            playerPositionId = 6;
        }
        //C
        if (sortOrderId == 11 || sortOrderId == 37)
        {
            playerPositionId = 7;
        }
        //K
        if (sortOrderId == 12)
        {
            playerPositionId = 15;
        }
        //DT
        if (sortOrderId == 13 || sortOrderId == 14 || sortOrderId == 38 || sortOrderId == 39 || sortOrderId == 40)
        {
            playerPositionId = 8;
        }
        //DE
        if (sortOrderId == 15 || sortOrderId == 16 || sortOrderId == 41 || sortOrderId == 42 || sortOrderId == 43)
        {
            playerPositionId = 9;
        }
        //OLB
        if (sortOrderId == 17 || sortOrderId == 19 || sortOrderId == 44 || sortOrderId == 45)
        {
            playerPositionId = 10;
        }
        //ILB
        if (sortOrderId == 18 || sortOrderId == 46 || sortOrderId == 47)
        {
            playerPositionId = 11;
        }
        //S
        if (sortOrderId == 20 || sortOrderId == 21 || sortOrderId == 48 || sortOrderId == 49)
        {
            playerPositionId = 12;
        }
        //CB
        if (sortOrderId == 22 || sortOrderId == 23 || sortOrderId == 50 || sortOrderId == 51)
        {
            playerPositionId = 13;
        }
        //P
        if (sortOrderId == 24)
        {
            playerPositionId = 14;
        }

        //H
        if (sortOrderId == 52)
        {
            playerPositionId = 16;
        }
        //LS
        if (sortOrderId == 53)
        {
            playerPositionId = 17;
        }

        DynamicForm form = formFactory.form().bindFromRequest();

        Integer teamId = Integer.parseInt(session().get("MYteamId"));

        String ReplaceSQL = "SELECT tp FROM TeamPlayer tp WHERE sortOrderId = :sortOrderId AND teamId = :teamId";

        try
        {
            TeamPlayer replacePlayer = jpaApi.em().createQuery(ReplaceSQL, TeamPlayer.class).setParameter("sortOrderId", sortOrderId).setParameter("teamId", teamId).getSingleResult();

            jpaApi.em().remove(replacePlayer);

        } catch (Exception e)
        {
            ReplaceSQL = "";
        }
        String playerIdString = form.get("Id");

        Integer playerId = Integer.parseInt(playerIdString);

        String sql = "SELECT p FROM Player p WHERE playerId = :playerId";

        Player player = jpaApi.em().createQuery(sql, Player.class).setParameter("playerId", playerId).getSingleResult();

        TeamPlayer teamPlayer = new TeamPlayer();

        teamPlayer.setTeamId(Integer.parseInt(session().get("MYteamId")));
        teamPlayer.setPlayerId(playerId);
        teamPlayer.setTeamPlayerName(player.getPlayerName());
        teamPlayer.setTeamPlayerValue(player.getPlayerValue());
        teamPlayer.setTeamPlayerPositionId(playerPositionId);
        teamPlayer.setSortOrderId(sortOrderId);
        jpaApi.em().persist(teamPlayer);


        return redirect("/draftteam#" + sortOrderId);
    }

    @Transactional
    public Result getTeamLayout(int intTeamId)
    {

        Integer teamId = intTeamId;

        session().put("THISteamId", teamId.toString());

        String sql = "SELECT NEW models.TeamUnitCount(tp.teamPlayerPositionId, pp.playerPositionName, SUM(tp.teamPlayerValue) AS SalaryOfPosition) " +
                "FROM TeamPlayer tp JOIN PlayerPosition pp ON pp.playerPositionId = tp.teamPlayerPositionId " +
                "WHERE tp.teamId = :teamId " +
                "GROUP BY tp.teamPlayerPositionId, pp.playerPositionName ";

        List<TeamUnitCount> teamUnitCounts = jpaApi.em().createQuery(sql, TeamUnitCount.class).setParameter("teamId", teamId).getResultList();

        ChartValues chartValues = new ChartValues();

        String data = teamUnitCounts.stream().map(teamUnitCount -> Long.toString(teamUnitCount.getSalaryOfTeamUnit())).collect(Collectors.joining("|"));
        String labels = teamUnitCounts.stream().map(teamUnitCount -> (teamUnitCount.getTeamUnitName())).collect(Collectors.joining("|"));
        String colors = teamUnitCounts.stream().map(teamUnitCount -> (teamUnitCount.getRgb())).collect(Collectors.joining("|"));

        chartValues.setData(data);
        chartValues.setLabels(labels);
        chartValues.setColors(colors);

        sql = "SELECT NEW models.TeamUnitCount2(pp.unitId, cs.coachSpecialtyName, SUM(tp.teamPlayerValue) AS SalaryOfUnit) " +
                "FROM TeamPlayer tp JOIN PlayerPosition pp ON pp.playerPositionId = tp.teamPlayerPositionId " +
                "JOIN CoachSpecialty cs ON cs.coachSpecialtyId = pp.unitId " +
                "WHERE tp.teamId = :teamId " +
                "GROUP BY pp.unitId ";

        List<TeamUnitCount2> teamUnitCountList = jpaApi.em().createQuery(sql, TeamUnitCount2.class).setParameter("teamId", teamId).getResultList();

        ChartValues pieValues = new ChartValues();

        String piedata = teamUnitCountList.stream().map(teamUnitCount2 -> Long.toString(teamUnitCount2.getSalaryOfTeamUnit())).collect(Collectors.joining("|"));
        String pielabels = teamUnitCountList.stream().map(teamUnitCount2 -> (teamUnitCount2.getTeamUnitName())).collect(Collectors.joining("|"));
        String piecolors = teamUnitCountList.stream().map(teamUnitCount2 -> (teamUnitCount2.getRgb())).collect(Collectors.joining("|"));

        pieValues.setData(piedata);
        pieValues.setLabels(pielabels);
        pieValues.setColors(piecolors);


        String coachIdSQL = "SELECT t.coachId FROM Team t WHERE teamId = :teamId ";

        Integer coachId = jpaApi.em().createQuery(coachIdSQL, Integer.class).setParameter("teamId", teamId).getSingleResult();

        String coachSQL = "SELECT c FROM Coach c WHERE coachId = :coachId ";

        Coach coach = jpaApi.em().createQuery(coachSQL, Coach.class).setParameter("coachId", coachId).getSingleResult();

        String playerSQL = "SELECT tp FROM TeamPlayer tp WHERE teamId = :teamId AND teamPlayerPositionId = :teamPlayerPositionId ORDER BY sortOrderId";

        List<TeamPlayer> quarterbacksList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 1).getResultList();

        List<Quarterback> quarterbacks = new ArrayList<>();
        for (TeamPlayer quarterback : quarterbacksList)
        {
            Quarterback quarterback1 = new Quarterback();
            quarterback1.setName(quarterback.getTeamPlayerName());
            quarterback1.setValue(quarterback.getTeamPlayerValue());
            quarterbacks.add(quarterback1);
        }

        List<TeamPlayer> runningBacksList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 2).getResultList();

        List<RunningBack> runningBacks = new ArrayList<>();
        for (TeamPlayer runningback : runningBacksList)
        {
            RunningBack runningBack = new RunningBack();
            runningBack.setName(runningback.getTeamPlayerName());
            runningBack.setValue(runningback.getTeamPlayerValue());
            runningBacks.add(runningBack);
        }

        List<TeamPlayer> wideReceiver1List = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 3).getResultList();

        List<WideReceiver1> wideReceiver1s = new ArrayList<>();
        for (TeamPlayer wideReceiver : wideReceiver1List)
        {
            WideReceiver1 wideReceiver1 = new WideReceiver1();
            wideReceiver1.setName(wideReceiver.getTeamPlayerName());
            wideReceiver1.setValue(wideReceiver.getTeamPlayerValue());
            wideReceiver1s.add(wideReceiver1);
        }

        List<TeamPlayer> tightEndsList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 4).getResultList();

        List<TightEnd> tightEnds = new ArrayList<>();
        for (TeamPlayer tightEnd : tightEndsList)
        {
            TightEnd tightEnd1 = new TightEnd();
            tightEnd1.setName(tightEnd.getTeamPlayerName());
            tightEnd1.setValue(tightEnd.getTeamPlayerValue());
            tightEnds.add(tightEnd1);
        }

        List<TeamPlayer> tackleList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 5).getResultList();

        List<Tackle1> tackle1s = new ArrayList<>();
        for (TeamPlayer tackle : tackleList)
        {
            Tackle1 tackle1 = new Tackle1();
            tackle1.setName(tackle.getTeamPlayerName());
            tackle1.setValue(tackle.getTeamPlayerValue());
            tackle1s.add(tackle1);
        }

        List<TeamPlayer> guardList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 6).getResultList();

        List<Guard1> guard1s = new ArrayList<>();
        for (TeamPlayer guard : guardList)
        {
            Guard1 guard1 = new Guard1();
            guard1.setName(guard.getTeamPlayerName());
            guard1.setValue(guard.getTeamPlayerValue());
            guard1s.add(guard1);
        }

        List<TeamPlayer> centersList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 7).getResultList();

        List<Center> centers = new ArrayList<>();
        for (TeamPlayer center : centersList)
        {
            Center center1 = new Center();
            center1.setName(center.getTeamPlayerName());
            center1.setValue(center.getTeamPlayerValue());
            centers.add(center1);
        }

        List<TeamPlayer> defensiveTackleList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 8).getResultList();

        List<DefensiveTackle1> defensiveTackle1s = new ArrayList<>();
        for (TeamPlayer defensivetackle : defensiveTackleList)
        {
            DefensiveTackle1 defensiveTackle1 = new DefensiveTackle1();
            defensiveTackle1.setName(defensivetackle.getTeamPlayerName());
            defensiveTackle1.setValue(defensivetackle.getTeamPlayerValue());
            defensiveTackle1s.add(defensiveTackle1);
        }

        List<TeamPlayer> defensiveEndList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 9).getResultList();

        List<DefensiveEnd1> defensiveEnd1s = new ArrayList<>();
        for (TeamPlayer defensiveend : defensiveEndList)
        {
            DefensiveEnd1 defensiveEnd1 = new DefensiveEnd1();
            defensiveEnd1.setName(defensiveend.getTeamPlayerName());
            defensiveEnd1.setValue(defensiveend.getTeamPlayerValue());
            defensiveEnd1s.add(defensiveEnd1);
        }

        List<TeamPlayer> outsideLinebackerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 10).getResultList();

        List<OutsideLinebacker1> outsideLinebacker1s = new ArrayList<>();
        for (TeamPlayer outsidelinebacker : outsideLinebackerList)
        {
            OutsideLinebacker1 outsideLinebacker1 = new OutsideLinebacker1();
            outsideLinebacker1.setName(outsidelinebacker.getTeamPlayerName());
            outsideLinebacker1.setValue(outsidelinebacker.getTeamPlayerValue());
            outsideLinebacker1s.add(outsideLinebacker1);
        }

        List<TeamPlayer> insideLinebackerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 11).getResultList();

        List<InsideLinebacker> insideLinebackers1 = new ArrayList<>();
        for (TeamPlayer insidelinebacker : insideLinebackerList)
        {
            InsideLinebacker insideLinebacker = new InsideLinebacker();
            insideLinebacker.setName(insidelinebacker.getTeamPlayerName());
            insideLinebacker.setValue(insidelinebacker.getTeamPlayerValue());
            insideLinebackers1.add(insideLinebacker);
        }

        List<TeamPlayer> safetyList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 12).getResultList();

        List<Safety1> safety1s = new ArrayList<>();
        for (TeamPlayer safety : safetyList)
        {
            Safety1 safety1 = new Safety1();
            safety1.setName(safety.getTeamPlayerName());
            safety1.setValue(safety.getTeamPlayerValue());
            safety1s.add(safety1);
        }

        List<TeamPlayer> cornerbackList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 13).getResultList();

        List<Cornerback1> cornerback1s = new ArrayList<>();
        for (TeamPlayer cornerback : cornerbackList)
        {
            Cornerback1 cornerback1 = new Cornerback1();
            cornerback1.setName(cornerback.getTeamPlayerName());
            cornerback1.setValue(cornerback.getTeamPlayerValue());
            cornerback1s.add(cornerback1);
        }

        List<TeamPlayer> kickerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 15).getResultList();

        List<Kicker> kickers = new ArrayList<>();
        for (TeamPlayer kicker : kickerList)
        {
            Kicker kicker1 = new Kicker();
            kicker1.setName(kicker.getTeamPlayerName());
            kicker1.setValue(kicker.getTeamPlayerValue());
            kickers.add(kicker1);
        }

        List<TeamPlayer> puntersList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 14).getResultList();

        List<Punter> punters = new ArrayList<>();
        for (TeamPlayer punter : puntersList)
        {
            Punter punter1 = new Punter();
            punter1.setName(punter.getTeamPlayerName());
            punter1.setValue(punter.getTeamPlayerValue());
            punters.add(punter1);
        }

        List<TeamPlayer> holderlist = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 16).getResultList();


        List<Holder> holders = new ArrayList<>();
        for (TeamPlayer holder : holderlist)
        {
            Holder holder1 = new Holder();
            holder1.setName(holder.getTeamPlayerName());
            holder1.setValue(holder.getTeamPlayerValue());
            holders.add(holder1);
        }

        List<TeamPlayer> longsnapperlist = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 17).getResultList();

        List<LongSnapper> longsnappers = new ArrayList<>();
        for (TeamPlayer longsnapper : longsnapperlist)
        {
            LongSnapper longSnapper1 = new LongSnapper();
            longSnapper1.setName(longsnapper.getTeamPlayerName());
            longSnapper1.setValue(longsnapper.getTeamPlayerValue());
            longsnappers.add(longSnapper1);
        }

        Quarterback quarterback = quarterbacks.get(0);
        Quarterback quarterback2 = quarterbacks.get(1);
        Quarterback quarterback3 = quarterbacks.get(2);

        RunningBack runningBack = runningBacks.get(0);
        RunningBack runningBack2 = runningBacks.get(1);
        RunningBack runningBack3 = runningBacks.get(2);

        WideReceiver1 wideReceiver1 = wideReceiver1s.get(0);
        WideReceiver1 wideReceiver2 = wideReceiver1s.get(1);
        WideReceiver1 wideReceiver3 = wideReceiver1s.get(2);
        WideReceiver1 wideReceiver4 = wideReceiver1s.get(3);
        WideReceiver1 wideReceiver5 = wideReceiver1s.get(4);
        WideReceiver1 wideReceiver6 = wideReceiver1s.get(5);

        TightEnd tightEnd = tightEnds.get(0);
        TightEnd tightEnd2 = tightEnds.get(1);

        Tackle1 tackle1 = tackle1s.get(0);
        Tackle1 tackle2 = tackle1s.get(1);
        Tackle1 tackle3 = tackle1s.get(2);
        Tackle1 tackle4 = tackle1s.get(3);

        Guard1 guard1 = guard1s.get(0);
        Guard1 guard2 = guard1s.get(1);
        Guard1 guard3 = guard1s.get(2);
        Guard1 guard4 = guard1s.get(3);

        Center center = centers.get(0);
        Center center2 = centers.get(1);

        DefensiveTackle1 defensiveTackle1 = defensiveTackle1s.get(0);
        DefensiveTackle1 defensiveTackle2 = defensiveTackle1s.get(1);
        DefensiveTackle1 defensiveTackle3 = defensiveTackle1s.get(2);
        DefensiveTackle1 defensiveTackle4 = defensiveTackle1s.get(3);
        DefensiveTackle1 defensiveTackle5 = defensiveTackle1s.get(4);

        DefensiveEnd1 defensiveEnd1 = defensiveEnd1s.get(0);
        DefensiveEnd1 defensiveEnd2 = defensiveEnd1s.get(1);
        DefensiveEnd1 defensiveEnd3 = defensiveEnd1s.get(2);
        DefensiveEnd1 defensiveEnd4 = defensiveEnd1s.get(3);
        DefensiveEnd1 defensiveEnd5 = defensiveEnd1s.get(4);

        OutsideLinebacker1 outsideLinebacker1 = outsideLinebacker1s.get(0);
        OutsideLinebacker1 outsideLinebacker2 = outsideLinebacker1s.get(1);
        OutsideLinebacker1 outsideLinebacker3 = outsideLinebacker1s.get(2);
        OutsideLinebacker1 outsideLinebacker4 = outsideLinebacker1s.get(3);

        InsideLinebacker insideLinebacker = insideLinebackers1.get(0);
        InsideLinebacker insideLinebacker2 = insideLinebackers1.get(1);
        InsideLinebacker insideLinebacker3 = insideLinebackers1.get(2);

        Safety1 safety1 = safety1s.get(0);
        Safety1 safety2 = safety1s.get(1);
        Safety1 safety3 = safety1s.get(2);
        Safety1 safety4 = safety1s.get(3);

        Cornerback1 cornerback1 = cornerback1s.get(0);
        Cornerback1 cornerback2 = cornerback1s.get(1);
        Cornerback1 cornerback3 = cornerback1s.get(2);
        Cornerback1 cornerback4 = cornerback1s.get(3);

        Kicker kicker = kickers.get(0);
        Punter punter = punters.get(0);

        Holder holder = holders.get(0);
        LongSnapper longSnapper = longsnappers.get(0);

        TeamLayout teamLayout = new TeamLayout(quarterback, quarterback2, quarterback3, runningBack, runningBack2, runningBack3, wideReceiver1, wideReceiver2, wideReceiver3, wideReceiver4, wideReceiver5, wideReceiver6, tightEnd, tightEnd2, tackle1, tackle2, tackle3, tackle4, guard1, guard2, guard3, guard4, center, center2, defensiveTackle1, defensiveTackle2, defensiveTackle3, defensiveTackle4, defensiveTackle5, defensiveEnd1, defensiveEnd2, defensiveEnd3, defensiveEnd4, defensiveEnd5, outsideLinebacker1, outsideLinebacker2, outsideLinebacker3, outsideLinebacker4, insideLinebacker, insideLinebacker2, insideLinebacker3, safety1, safety2, safety3, safety4, cornerback1, cornerback2, cornerback3, cornerback4, punter, kicker, holder, longSnapper);

        TeamId teamId1 = new TeamId();
        teamId1.setTeamId(teamId);

        return ok(views.html.teamlayout.render(chartValues, pieValues, coach, teamLayout, teamId1));
    }

    @Transactional
    public Result postTeamLayout(int teamId)
    {


        return redirect("/");
    }


    @Transactional
    public Result getSearchTeams()
    {
        String testSQL = "SELECT t FROM Team t ";

        List<Team> teamList = jpaApi.em().createQuery(testSQL, Team.class).getResultList();

        String sql = "SELECT NEW models.TeamTable(t.teamId, t.teamCity, t.teamName, o.ownerName, too.organizationSalaryCap, t.teamSalary, c.coachValue) " +
                "FROM Team t JOIN Owner o ON t.ownerId = o.ownerId " +
                "JOIN Coach c ON c.coachId = t.coachId " +
                "JOIN TakeOverOrganization too ON too.takeOverOrganizationId = t.takeOverOrganizationId " +
                "GROUP BY t.teamId " +
                "ORDER BY too.organizationSalaryCap desc ";

        List<TeamTable> teamTable = jpaApi.em().createQuery(sql, TeamTable.class).getResultList();

        session().put("THISteamId", session().get("MYteamId"));

        Logger.debug("My ID is " + session().get("MYteamId"));

        return ok(views.html.searchteams.render(teamTable));
    }

    @Transactional
    public Result postSearchTeams()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String result = "";

        String searchTeamText = form.get("searchTeam");

        if (searchTeamText == null)
        {
            searchTeamText = "";
        }

        searchTeamText += "%";

        String searchTeamSQL = "SELECT t FROM Team t WHERE t.teamName LIKE :searchTeamText ";

        Team team = jpaApi.em().createQuery(searchTeamSQL, Team.class).setParameter("searchTeamText", searchTeamText).getSingleResult();

        result = team.getTeamId().toString();

        return redirect("/teamlayout/" + result);
    }

    @Transactional
    public Result getManageThisTeam(Integer teamId)
    {
        session().put("MYteamId", teamId.toString());

        session().put("THISteamId", teamId.toString());

        String sql = "SELECT t FROM Team t WHERE t.teamId = :teamId ";

        Team existingTeam = jpaApi.em().createQuery(sql, Team.class).setParameter("teamId", teamId).getSingleResult();

        sql = "SELECT c FROM Coach c WHERE c.coachId = :coachId ";

        Coach coach = jpaApi.em().createQuery(sql, Coach.class).setParameter("coachId", existingTeam.getCoachId()).getSingleResult();

        sql = "SELECT too FROM TakeOverOrganization too WHERE too.takeOverOrganizationId = :takeOverOrganizationId ";

        TakeOverOrganization takeOverOrganization = jpaApi.em().createQuery(sql, TakeOverOrganization.class).setParameter("takeOverOrganizationId", existingTeam.getTakeOverOrganizationId()).getSingleResult();

        sql = "SELECT o FROM Owner o WHERE o.ownerId = :ownerId ";

        Owner owner = jpaApi.em().createQuery(sql, Owner.class).setParameter("ownerId", existingTeam.getOwnerId()).getSingleResult();

        session().put("ownerId", existingTeam.getOwnerId().toString());
        session().put("coachId", existingTeam.getCoachId().toString());
        session().put("takeOverOrganizationId", existingTeam.getTakeOverOrganizationId().toString());

        sql = "SELECT cs FROM CoachSpecialty cs WHERE coachSpecialtyId = :coachSpecialtyId ";

        CoachSpecialty coachSpecialty = jpaApi.em().createQuery(sql, CoachSpecialty.class).setParameter("coachSpecialtyId", coach.getCoachSpecialtyId()).getSingleResult();

        String teamPlayersSQL = "SELECT tp FROM TeamPlayer tp WHERE teamId = :teamId";

        List<TeamPlayer> teamPlayers = jpaApi.em().createQuery(teamPlayersSQL, TeamPlayer.class).setParameter("teamId", teamId).getResultList();

        TeamDetail teamDetail = new TeamDetail();
        BigDecimal ZERO = new BigDecimal(0);
        teamDetail.setTeamSalaryAccrued(ZERO);

        Quarterback quarterback = new Quarterback();
        Quarterback2 quarterback2 = new Quarterback2();
        Quarterback3 quarterback3 = new Quarterback3();

        RunningBack runningBack = new RunningBack();
        RunningBack2 runningBack2 = new RunningBack2();
        RunningBack3 runningBack3 = new RunningBack3();

        WideReceiver1 wideReceiver1 = new WideReceiver1();
        WideReceiver2 wideReceiver2 = new WideReceiver2();
        WideReceiver3 wideReceiver3 = new WideReceiver3();
        WideReceiver4 wideReceiver4 = new WideReceiver4();
        WideReceiver5 wideReceiver5 = new WideReceiver5();
        WideReceiver6 wideReceiver6 = new WideReceiver6();

        TightEnd tightEnd = new TightEnd();
        TightEnd2 tightEnd2 = new TightEnd2();

        Tackle1 tackle1 = new Tackle1();
        Tackle2 tackle2 = new Tackle2();
        Tackle3 tackle3 = new Tackle3();
        Tackle4 tackle4 = new Tackle4();

        Guard1 guard1 = new Guard1();
        Guard2 guard2 = new Guard2();
        Guard3 guard3 = new Guard3();
        Guard4 guard4 = new Guard4();

        Center center = new Center();
        Center2 center2 = new Center2();

        DefensiveTackle1 defensiveTackle1 = new DefensiveTackle1();
        DefensiveTackle2 defensiveTackle2 = new DefensiveTackle2();
        DefensiveTackle3 defensiveTackle3 = new DefensiveTackle3();
        DefensiveTackle4 defensiveTackle4 = new DefensiveTackle4();
        DefensiveTackle5 defensiveTackle5 = new DefensiveTackle5();

        DefensiveEnd1 defensiveEnd1 = new DefensiveEnd1();
        DefensiveEnd2 defensiveEnd2 = new DefensiveEnd2();
        DefensiveEnd3 defensiveEnd3 = new DefensiveEnd3();
        DefensiveEnd4 defensiveEnd4 = new DefensiveEnd4();
        DefensiveEnd5 defensiveEnd5 = new DefensiveEnd5();

        OutsideLinebacker1 outsideLinebacker1 = new OutsideLinebacker1();
        OutsideLinebacker2 outsideLinebacker2 = new OutsideLinebacker2();
        OutsideLinebacker3 outsideLinebacker3 = new OutsideLinebacker3();
        OutsideLinebacker4 outsideLinebacker4 = new OutsideLinebacker4();

        InsideLinebacker insideLinebacker = new InsideLinebacker();
        InsideLinebacker2 insideLinebacker2 = new InsideLinebacker2();
        InsideLinebacker3 insideLinebacker3 = new InsideLinebacker3();

        Safety1 safety1 = new Safety1();
        Safety2 safety2 = new Safety2();
        Safety3 safety3 = new Safety3();
        Safety4 safety4 = new Safety4();

        Cornerback1 cornerback1 = new Cornerback1();
        Cornerback2 cornerback2 = new Cornerback2();
        Cornerback3 cornerback3 = new Cornerback3();
        Cornerback4 cornerback4 = new Cornerback4();

        Kicker kicker = new Kicker();
        Punter punter = new Punter();

        Holder holder = new Holder();
        LongSnapper longSnapper = new LongSnapper();

        TeamDisplay teamDisplay = new TeamDisplay(quarterback, quarterback2, quarterback3, runningBack, runningBack2, runningBack3, wideReceiver1, wideReceiver2, wideReceiver3, wideReceiver4, wideReceiver5, wideReceiver6, tightEnd, tightEnd2, tackle1, tackle2, tackle3, tackle4, guard1, guard2, guard3, guard4, center, center2, defensiveTackle1, defensiveTackle2, defensiveTackle3, defensiveTackle4, defensiveTackle5, defensiveEnd1, defensiveEnd2, defensiveEnd3, defensiveEnd4, defensiveEnd5, outsideLinebacker1, outsideLinebacker2, outsideLinebacker3, outsideLinebacker4, insideLinebacker, insideLinebacker2, insideLinebacker3, safety1, safety2, safety3, safety4, cornerback1, cornerback2, cornerback3, cornerback4, punter, kicker, holder, longSnapper);


        for (TeamPlayer teamPlayer : teamPlayers)
        {
            switch (teamPlayer.getSortOrderId())
            {
                case 1:
                    quarterback.setName(teamPlayer.getTeamPlayerName());
                    quarterback.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback.getValue());
                    teamDisplay.setQuarterback(quarterback);
                    break;
                case 2:
                    runningBack.setName(teamPlayer.getTeamPlayerName());
                    runningBack.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack.getValue());
                    teamDisplay.setRunningBack(runningBack);
                    break;
                case 3:
                    wideReceiver1.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver1.getValue());
                    teamDisplay.setWideReceiver1(wideReceiver1);
                    break;
                case 4:
                    wideReceiver2.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver2.getValue());
                    teamDisplay.setWideReceiver2(wideReceiver2);
                    break;
                case 5:
                    wideReceiver3.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver3.getValue());
                    teamDisplay.setWideReceiver3(wideReceiver3);
                    break;
                case 6:
                    tightEnd.setName(teamPlayer.getTeamPlayerName());
                    tightEnd.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tightEnd.getValue());
                    teamDisplay.setTightEnd(tightEnd);
                    break;
                case 7:
                    tackle1.setName(teamPlayer.getTeamPlayerName());
                    tackle1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle1.getValue());
                    teamDisplay.setTackle1(tackle1);
                    break;
                case 8:
                    tackle2.setName(teamPlayer.getTeamPlayerName());
                    tackle2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle2.getValue());
                    teamDisplay.setTackle2(tackle2);
                    break;
                case 9:
                    guard1.setName(teamPlayer.getTeamPlayerName());
                    guard1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard1.getValue());
                    teamDisplay.setGuard1(guard1);
                    break;

                case 10:
                    guard2.setName(teamPlayer.getTeamPlayerName());
                    guard2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard2.getValue());
                    teamDisplay.setGuard2(guard2);
                    break;
                case 11:
                    center.setName(teamPlayer.getTeamPlayerName());
                    center.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(center.getValue());
                    teamDisplay.setCenter(center);
                    break;
                case 13:
                    defensiveTackle1.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle1.getValue());
                    teamDisplay.setDefensiveTackle1(defensiveTackle1);
                    break;
                case 14:
                    defensiveTackle2.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle2.getValue());
                    teamDisplay.setDefensiveTackle2(defensiveTackle2);
                    break;
                case 15:
                    defensiveEnd1.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd1.getValue());
                    teamDisplay.setDefensiveEnd1(defensiveEnd1);
                    break;
                case 16:
                    defensiveEnd2.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd2.getValue());
                    teamDisplay.setDefensiveEnd2(defensiveEnd2);
                    break;
                case 17:
                    outsideLinebacker1.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker1.getValue());
                    teamDisplay.setOutsideLinebacker1(outsideLinebacker1);
                    break;
                case 19:
                    outsideLinebacker2.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker2.getValue());
                    teamDisplay.setOutsideLinebacker2(outsideLinebacker2);
                    break;
                case 18:
                    insideLinebacker.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker.getValue());
                    teamDisplay.setInsideLinebacker(insideLinebacker);
                    break;
                case 20:
                    safety1.setName(teamPlayer.getTeamPlayerName());
                    safety1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety1.getValue());
                    teamDisplay.setSafety1(safety1);
                    break;
                case 21:
                    safety2.setName(teamPlayer.getTeamPlayerName());
                    safety2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety2.getValue());
                    teamDisplay.setSafety2(safety2);
                    break;
                case 22:
                    cornerback1.setName(teamPlayer.getTeamPlayerName());
                    cornerback1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback1.getValue());
                    teamDisplay.setCornerback1(cornerback1);
                    break;
                case 23:
                    cornerback2.setName(teamPlayer.getTeamPlayerName());
                    cornerback2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback2.getValue());
                    teamDisplay.setCornerback2(cornerback2);
                    break;
                case 24:
                    punter.setName(teamPlayer.getTeamPlayerName());
                    punter.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(punter.getValue());
                    teamDisplay.setPunter(punter);
                    break;
                case 12:
                    kicker.setName(teamPlayer.getTeamPlayerName());
                    kicker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(kicker.getValue());
                    teamDisplay.setKicker(kicker);
                    break;
                case 25:
                    quarterback2.setName(teamPlayer.getTeamPlayerName());
                    quarterback2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback2.getValue());
                    teamDisplay.setQuarterback2(quarterback2);
                    break;
                case 26:
                    quarterback3.setName(teamPlayer.getTeamPlayerName());
                    quarterback3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback3.getValue());
                    teamDisplay.setQuarterback3(quarterback3);
                    break;
                case 27:
                    runningBack2.setName(teamPlayer.getTeamPlayerName());
                    runningBack2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack2.getValue());
                    teamDisplay.setRunningBack2(runningBack2);
                    break;
                case 28:
                    runningBack3.setName(teamPlayer.getTeamPlayerName());
                    runningBack3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack3.getValue());
                    teamDisplay.setRunningBack3(runningBack3);
                    break;
                case 29:
                    wideReceiver4.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver4.getValue());
                    teamDisplay.setWideReceiver4(wideReceiver4);
                    break;
                case 30:
                    wideReceiver5.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver5.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver5.getValue());
                    teamDisplay.setWideReceiver5(wideReceiver5);
                    break;
                case 31:
                    wideReceiver6.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver6.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver6.getValue());
                    teamDisplay.setWideReceiver6(wideReceiver6);
                    break;
                case 32:
                    tightEnd2.setName(teamPlayer.getTeamPlayerName());
                    tightEnd2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tightEnd2.getValue());
                    teamDisplay.setTightEnd2(tightEnd2);
                    break;
                case 33:
                    tackle3.setName(teamPlayer.getTeamPlayerName());
                    tackle3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle3.getValue());
                    teamDisplay.setTackle3(tackle3);
                    break;
                case 34:
                    tackle4.setName(teamPlayer.getTeamPlayerName());
                    tackle4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle4.getValue());
                    teamDisplay.setTackle4(tackle4);
                    break;
                case 35:
                    guard3.setName(teamPlayer.getTeamPlayerName());
                    guard3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard3.getValue());
                    teamDisplay.setGuard3(guard3);
                    break;
                case 36:
                    guard4.setName(teamPlayer.getTeamPlayerName());
                    guard4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard4.getValue());
                    teamDisplay.setGuard4(guard4);
                    break;
                case 37:
                    center2.setName(teamPlayer.getTeamPlayerName());
                    center2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(center2.getValue());
                    teamDisplay.setCenter2(center2);
                    break;
                case 38:
                    defensiveTackle3.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle3.getValue());
                    teamDisplay.setDefensiveTackle3(defensiveTackle3);
                    break;
                case 39:
                    defensiveTackle4.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle4.getValue());
                    teamDisplay.setDefensiveTackle4(defensiveTackle4);
                    break;
                case 40:
                    defensiveTackle5.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle5.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle5.getValue());
                    teamDisplay.setDefensiveTackle5(defensiveTackle5);
                    break;
                case 41:
                    defensiveEnd3.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd3.getValue());
                    teamDisplay.setDefensiveEnd3(defensiveEnd3);
                    break;
                case 42:
                    defensiveEnd4.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd4.getValue());
                    teamDisplay.setDefensiveEnd4(defensiveEnd4);
                    break;
                case 43:
                    defensiveEnd5.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd5.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd5.getValue());
                    teamDisplay.setDefensiveEnd5(defensiveEnd5);
                    break;
                case 44:
                    outsideLinebacker3.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker3.getValue());
                    teamDisplay.setOutsideLinebacker3(outsideLinebacker3);
                    break;
                case 45:
                    outsideLinebacker4.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker4.getValue());
                    teamDisplay.setOutsideLinebacker4(outsideLinebacker4);
                    break;
                case 46:
                    insideLinebacker2.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker2.getValue());
                    teamDisplay.setInsideLinebacker2(insideLinebacker2);
                    break;
                case 47:
                    insideLinebacker3.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker3.getValue());
                    teamDisplay.setInsideLinebacker3(insideLinebacker3);
                    break;
                case 48:
                    safety3.setName(teamPlayer.getTeamPlayerName());
                    safety3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety3.getValue());
                    teamDisplay.setSafety3(safety3);
                    break;
                case 49:
                    safety4.setName(teamPlayer.getTeamPlayerName());
                    safety4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety4.getValue());
                    teamDisplay.setSafety4(safety4);
                    break;
                case 50:
                    cornerback3.setName(teamPlayer.getTeamPlayerName());
                    cornerback3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback3.getValue());
                    teamDisplay.setCornerback3(cornerback3);
                    break;
                case 51:
                    cornerback4.setName(teamPlayer.getTeamPlayerName());
                    cornerback4.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback4.getValue());
                    teamDisplay.setCornerback4(cornerback4);
                    break;
                case 52:
                    holder.setName(teamPlayer.getTeamPlayerName());
                    holder.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(holder.getValue());
                    teamDisplay.setHolder(holder);
                    break;
                case 53:
                    longSnapper.setName(teamPlayer.getTeamPlayerName());
                    longSnapper.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(longSnapper.getValue());
                    teamDisplay.setLongSnapper(longSnapper);
                    break;

            }
        }

        teamDetail.setCoachId(existingTeam.getCoachId());
        teamDetail.setOwnerId(existingTeam.getOwnerId());
        teamDetail.setTakeOverOrganizationId(existingTeam.getTakeOverOrganizationId());
        teamDetail.setCoachName(coach.getCoachName());
        teamDetail.setOwnerName(owner.getOwnerName());
        teamDetail.setOrganizationName(owner.getOwnerEmail());
        teamDetail.setOrganizationSalaryCap(takeOverOrganization.getOrganizationSalaryCap());
        teamDetail.setCoachSpecialtyName(coachSpecialty.getCoachSpecialtyName());
        teamDetail.setCoachTier(coach.getCoachTier());
        try{
            sql = "SELECT t FROM Team t WHERE t.teamId = :teamId ";

            Team team = jpaApi.em().createQuery(sql, Team.class).setParameter("teamId", teamId).getSingleResult();

            teamDetail.setTeamName(team.getTeamCity() + " " + team.getTeamName());

        } catch (Exception e)
        {
            teamDetail.setTeamName(session().get("teamName"));
        }


        session().put("balance", teamDetail.getTeamSalaryBalance().toString());
        session().put("salary", teamDetail.getTeamSalaryAccrued().toString());

        return ok(views.html.managethisteam.render(teamDetail, teamDisplay));

    }

    public Result postManageThisTeam(Integer teamId)
    {
        String result = "";

        Integer balance = Integer.parseInt(session().get("balance"));

        if (balance >= 0)
        {
            try
            {
                teamId = Integer.parseInt(session().get("THISteamId"));

                String playerSQL = "SELECT tp FROM TeamPlayer tp WHERE teamId = :teamId AND teamPlayerPositionId = :teamPlayerPositionId ORDER BY sortOrderId";

                List<TeamPlayer> quarterbacksList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 1).getResultList();

                List<Quarterback> quarterbacks = new ArrayList<>();
                for (TeamPlayer quarterback : quarterbacksList)
                {
                    Quarterback quarterback1 = new Quarterback();
                    quarterback1.setName(quarterback.getTeamPlayerName());
                    quarterback1.setValue(quarterback.getTeamPlayerValue());
                    quarterbacks.add(quarterback1);
                }

                List<TeamPlayer> runningBacksList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 2).getResultList();

                List<RunningBack> runningBacks = new ArrayList<>();
                for (TeamPlayer runningback : runningBacksList)
                {
                    RunningBack runningBack = new RunningBack();
                    runningBack.setName(runningback.getTeamPlayerName());
                    runningBack.setValue(runningback.getTeamPlayerValue());
                    runningBacks.add(runningBack);
                }

                List<TeamPlayer> wideReceiver1List = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 3).getResultList();

                List<WideReceiver1> wideReceiver1s = new ArrayList<>();
                for (TeamPlayer wideReceiver : wideReceiver1List)
                {
                    WideReceiver1 wideReceiver1 = new WideReceiver1();
                    wideReceiver1.setName(wideReceiver.getTeamPlayerName());
                    wideReceiver1.setValue(wideReceiver.getTeamPlayerValue());
                    wideReceiver1s.add(wideReceiver1);
                }

                List<TeamPlayer> tightEndsList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 4).getResultList();

                List<TightEnd> tightEnds = new ArrayList<>();
                for (TeamPlayer tightEnd : tightEndsList)
                {
                    TightEnd tightEnd1 = new TightEnd();
                    tightEnd1.setName(tightEnd.getTeamPlayerName());
                    tightEnd1.setValue(tightEnd.getTeamPlayerValue());
                    tightEnds.add(tightEnd1);
                }

                List<TeamPlayer> tackleList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 5).getResultList();

                List<Tackle1> tackle1s = new ArrayList<>();
                for (TeamPlayer tackle : tackleList)
                {
                    Tackle1 tackle1 = new Tackle1();
                    tackle1.setName(tackle.getTeamPlayerName());
                    tackle1.setValue(tackle.getTeamPlayerValue());
                    tackle1s.add(tackle1);
                }

                List<TeamPlayer> guardList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 6).getResultList();

                List<Guard1> guard1s = new ArrayList<>();
                for (TeamPlayer guard : guardList)
                {
                    Guard1 guard1 = new Guard1();
                    guard1.setName(guard.getTeamPlayerName());
                    guard1.setValue(guard.getTeamPlayerValue());
                    guard1s.add(guard1);
                }

                List<TeamPlayer> centersList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 7).getResultList();

                List<Center> centers = new ArrayList<>();
                for (TeamPlayer center : centersList)
                {
                    Center center1 = new Center();
                    center1.setName(center.getTeamPlayerName());
                    center1.setValue(center.getTeamPlayerValue());
                    centers.add(center1);
                }

                List<TeamPlayer> defensiveTackleList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 8).getResultList();

                List<DefensiveTackle1> defensiveTackle1s = new ArrayList<>();
                for (TeamPlayer defensivetackle : defensiveTackleList)
                {
                    DefensiveTackle1 defensiveTackle1 = new DefensiveTackle1();
                    defensiveTackle1.setName(defensivetackle.getTeamPlayerName());
                    defensiveTackle1.setValue(defensivetackle.getTeamPlayerValue());
                    defensiveTackle1s.add(defensiveTackle1);
                }

                List<TeamPlayer> defensiveEndList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 9).getResultList();

                List<DefensiveEnd1> defensiveEnd1s = new ArrayList<>();
                for (TeamPlayer defensiveend : defensiveEndList)
                {
                    DefensiveEnd1 defensiveEnd1 = new DefensiveEnd1();
                    defensiveEnd1.setName(defensiveend.getTeamPlayerName());
                    defensiveEnd1.setValue(defensiveend.getTeamPlayerValue());
                    defensiveEnd1s.add(defensiveEnd1);
                }

                List<TeamPlayer> outsideLinebackerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 10).getResultList();

                List<OutsideLinebacker1> outsideLinebacker1s = new ArrayList<>();
                for (TeamPlayer outsidelinebacker : outsideLinebackerList)
                {
                    OutsideLinebacker1 outsideLinebacker1 = new OutsideLinebacker1();
                    outsideLinebacker1.setName(outsidelinebacker.getTeamPlayerName());
                    outsideLinebacker1.setValue(outsidelinebacker.getTeamPlayerValue());
                    outsideLinebacker1s.add(outsideLinebacker1);
                }

                List<TeamPlayer> insideLinebackerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 11).getResultList();

                List<InsideLinebacker> insideLinebackers1 = new ArrayList<>();
                for (TeamPlayer insidelinebacker : insideLinebackerList)
                {
                    InsideLinebacker insideLinebacker = new InsideLinebacker();
                    insideLinebacker.setName(insidelinebacker.getTeamPlayerName());
                    insideLinebacker.setValue(insidelinebacker.getTeamPlayerValue());
                    insideLinebackers1.add(insideLinebacker);
                }

                List<TeamPlayer> safetyList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 12).getResultList();

                List<Safety1> safety1s = new ArrayList<>();
                for (TeamPlayer safety : safetyList)
                {
                    Safety1 safety1 = new Safety1();
                    safety1.setName(safety.getTeamPlayerName());
                    safety1.setValue(safety.getTeamPlayerValue());
                    safety1s.add(safety1);
                }

                List<TeamPlayer> cornerbackList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 13).getResultList();

                List<Cornerback1> cornerback1s = new ArrayList<>();
                for (TeamPlayer cornerback : cornerbackList)
                {
                    Cornerback1 cornerback1 = new Cornerback1();
                    cornerback1.setName(cornerback.getTeamPlayerName());
                    cornerback1.setValue(cornerback.getTeamPlayerValue());
                    cornerback1s.add(cornerback1);
                }

                List<TeamPlayer> kickerList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 15).getResultList();

                List<Kicker> kickers = new ArrayList<>();
                for (TeamPlayer kicker : kickerList)
                {
                    Kicker kicker1 = new Kicker();
                    kicker1.setName(kicker.getTeamPlayerName());
                    kicker1.setValue(kicker.getTeamPlayerValue());
                    kickers.add(kicker1);
                }

                List<TeamPlayer> puntersList = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 14).getResultList();

                List<Punter> punters = new ArrayList<>();
                for (TeamPlayer punter : puntersList)
                {
                    Punter punter1 = new Punter();
                    punter1.setName(punter.getTeamPlayerName());
                    punter1.setValue(punter.getTeamPlayerValue());
                    punters.add(punter1);
                }

                List<TeamPlayer> holderlist = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 16).getResultList();

                List<Holder> holders = new ArrayList<>();
                for (TeamPlayer holder : holderlist)
                {
                    Holder holder1 = new Holder();
                    holder1.setName(holder.getTeamPlayerName());
                    holder1.setValue(holder.getTeamPlayerValue());
                    holders.add(holder1);
                }

                List<TeamPlayer> longsnapperlist = jpaApi.em().createQuery(playerSQL, TeamPlayer.class).setParameter("teamId", teamId).setParameter("teamPlayerPositionId", 17).getResultList();

                List<LongSnapper> longsnappers = new ArrayList<>();
                for (TeamPlayer longsnapper : longsnapperlist)
                {
                    LongSnapper longSnapper1 = new LongSnapper();
                    longSnapper1.setName(longsnapper.getTeamPlayerName());
                    longSnapper1.setValue(longsnapper.getTeamPlayerValue());
                    longsnappers.add(longSnapper1);
                }

                Quarterback quarterback = quarterbacks.get(0);
                Quarterback quarterback2 = quarterbacks.get(1);
                Quarterback quarterback3 = quarterbacks.get(2);

                RunningBack runningBack = runningBacks.get(0);
                RunningBack runningBack2 = runningBacks.get(1);
                RunningBack runningBack3 = runningBacks.get(2);

                WideReceiver1 wideReceiver1 = wideReceiver1s.get(0);
                WideReceiver1 wideReceiver2 = wideReceiver1s.get(1);
                WideReceiver1 wideReceiver3 = wideReceiver1s.get(2);
                WideReceiver1 wideReceiver4 = wideReceiver1s.get(3);
                WideReceiver1 wideReceiver5 = wideReceiver1s.get(4);
                WideReceiver1 wideReceiver6 = wideReceiver1s.get(5);

                TightEnd tightEnd = tightEnds.get(0);
                TightEnd tightEnd2 = tightEnds.get(1);

                Tackle1 tackle1 = tackle1s.get(0);
                Tackle1 tackle2 = tackle1s.get(1);
                Tackle1 tackle3 = tackle1s.get(2);
                Tackle1 tackle4 = tackle1s.get(3);

                Guard1 guard1 = guard1s.get(0);
                Guard1 guard2 = guard1s.get(1);
                Guard1 guard3 = guard1s.get(2);
                Guard1 guard4 = guard1s.get(3);

                Center center = centers.get(0);
                Center center2 = centers.get(1);

                DefensiveTackle1 defensiveTackle1 = defensiveTackle1s.get(0);
                DefensiveTackle1 defensiveTackle2 = defensiveTackle1s.get(1);
                DefensiveTackle1 defensiveTackle3 = defensiveTackle1s.get(2);
                DefensiveTackle1 defensiveTackle4 = defensiveTackle1s.get(3);
                DefensiveTackle1 defensiveTackle5 = defensiveTackle1s.get(4);

                DefensiveEnd1 defensiveEnd1 = defensiveEnd1s.get(0);
                DefensiveEnd1 defensiveEnd2 = defensiveEnd1s.get(1);
                DefensiveEnd1 defensiveEnd3 = defensiveEnd1s.get(2);
                DefensiveEnd1 defensiveEnd4 = defensiveEnd1s.get(3);
                DefensiveEnd1 defensiveEnd5 = defensiveEnd1s.get(4);

                OutsideLinebacker1 outsideLinebacker1 = outsideLinebacker1s.get(0);
                OutsideLinebacker1 outsideLinebacker2 = outsideLinebacker1s.get(1);
                OutsideLinebacker1 outsideLinebacker3 = outsideLinebacker1s.get(2);
                OutsideLinebacker1 outsideLinebacker4 = outsideLinebacker1s.get(3);

                InsideLinebacker insideLinebacker = insideLinebackers1.get(0);
                InsideLinebacker insideLinebacker2 = insideLinebackers1.get(1);
                InsideLinebacker insideLinebacker3 = insideLinebackers1.get(2);

                Safety1 safety1 = safety1s.get(0);
                Safety1 safety2 = safety1s.get(1);
                Safety1 safety3 = safety1s.get(2);
                Safety1 safety4 = safety1s.get(3);

                Cornerback1 cornerback1 = cornerback1s.get(0);
                Cornerback1 cornerback2 = cornerback1s.get(1);
                Cornerback1 cornerback3 = cornerback1s.get(2);
                Cornerback1 cornerback4 = cornerback1s.get(3);

                Kicker kicker = kickers.get(0);
                Punter punter = punters.get(0);

                Holder holder = holders.get(0);
                LongSnapper longSnapper = longsnappers.get(0);


                String sql = "SELECT t FROM Team t WHERE teamId = :teamId ";

                Team team = jpaApi.em().createQuery(sql, Team.class).setParameter("teamId", teamId).getSingleResult();

                try
                {
                    session().put("salary", team.getTeamSalary().toString());
                    session().put("teamname", team.getTeamName());
                    session().put("teamcity", team.getTeamCity());
                    session().put("ownerId", team.getOwnerId().toString());
                    session().put("coachId", team.getCoachId().toString());
                    session().put("takeOverOrganizationId", team.getTakeOverOrganizationId().toString());

                    Integer teamSalary = Integer.parseInt(session().get("salary"));
                    String teamName = session().get("teamname");
                    String teamCity = session().get("teamcity");
                    Integer ownerId = Integer.parseInt(session().get("ownerId"));
                    Integer coachId = Integer.parseInt(session().get("coachId"));
                    Integer takeOverOrganizationId = Integer.parseInt(session().get("takeOverOrganizationId"));


                    team.setTeamId(teamId);
                    team.setTeamCity(teamCity);
                    team.setTeamName(teamName);
                    team.setOwnerId(ownerId);
                    team.setCoachId(coachId);
                    team.setTakeOverOrganizationId(takeOverOrganizationId);
                    team.setTeamSalary(teamSalary);
                    jpaApi.em().persist(team);
                    result = "teamlayout/";
                    result += teamId;

                } catch (Exception e)
                {
                    Integer teamSalary = Integer.parseInt(session().get("salary"));
                    String teamName = session().get("teamname");
                    String teamCity = session().get("teamcity");
                    Integer ownerId = Integer.parseInt(session().get("ownerId"));
                    Integer coachId = Integer.parseInt(session().get("coachId"));
                    Integer takeOverOrganizationId = Integer.parseInt(session().get("takeOverOrganizationId"));

                    team.setTeamId(teamId);
                    team.setTeamCity(teamCity);
                    team.setTeamName(teamName);
                    team.setOwnerId(ownerId);
                    team.setCoachId(coachId);
                    team.setTakeOverOrganizationId(takeOverOrganizationId);
                    team.setTeamSalary(teamSalary);
                    jpaApi.em().persist(team);
                    result = "teamlayout/";
                    result += teamId;
                }


            } catch (Exception e)
            {
                result = "draftteam";
            }
        }
        else
        {
            result = "draftteam";
        }


        return redirect("/" + result);
    }

}
