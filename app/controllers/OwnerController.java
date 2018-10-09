package controllers;

import models.DefaultList;
import models.Owner;
import models.Team;
import models.TeamDetail;
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

public class OwnerController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public OwnerController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    public Result getStart()
    {
        session().clear();
        return ok(views.html.start.render());
    }

    public Result getLoginChoice()
    {
        return ok(views.html.loginchoice.render());
    }

    public Result getNewOwner()
    {
        return ok(views.html.newowner.render());
    }

    @Transactional
    public Result postNewOwner()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String ownerName = form.get("ownername");
        String ownerEmail = form.get("ownerEmail");

        String password = form.get("password");
        String passwordretype = form.get("passwordretype");
        String result = "";

        if (ownerName.length() <= 50 && ownerEmail.length() <= 50)
        {
            Owner newOwner = new Owner();
            newOwner.setOwnerName(ownerName);
            newOwner.setOwnerEmail(ownerEmail);
            newOwner.setOwnerPassword(password);
            if (password.equals(passwordretype))
            {
                jpaApi.em().persist(newOwner);
                result = "draftorganization";
                String ownerIdString = newOwner.getOwnerId().toString();
                session().put("ownerId", ownerIdString);
                session().put("ownerEmail", ownerEmail);
            }

            //Logger.debug("Id is" + ownerIdString);

            else
            {
                result = "newowner";
            }
        }
        else
        {
            result = "newowner";
        }
        return redirect("/" + result + "");
    }

    @Transactional
    public Result getReturnOwner()
    {
        String sql = "SELECT o FROM Owner o ";

        List<Owner> ownerList = jpaApi.em().createQuery(sql, Owner.class).getResultList();

        List<DefaultList> defaultLists = new ArrayList<>();
        for (Owner owner : ownerList)
        {
            DefaultList defaultList = new DefaultList();
            defaultList.setEmail(owner.getOwnerEmail());
            defaultList.setPassword(owner.getOwnerPassword());
            defaultLists.add(defaultList);
        }

        return ok(views.html.returningownerlogin.render(defaultLists.get(defaultLists.size()-1)));
    }

    @Transactional
    public Result postReturnOwner()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String emailText = form.get("ownerEmail");

        String passwordText = form.get("password");

        String result = "";

        String sql = "SELECT o FROM Owner o WHERE o.ownerEmail = :ownerEmail ";

        Owner owner = jpaApi.em().createQuery(sql, Owner.class).setParameter("ownerEmail", emailText).getSingleResult();

        if (owner.getOwnerPassword().equals(passwordText))
        {
            result = "/ownerteams/";
            result += owner.getOwnerId();
        }
        else
        {
            result = "/returnowner";
        }


        return redirect(result);
    }

    @Transactional
    public Result getOwnerTeams(Integer ownerId)
    {
        String sql = "SELECT t FROM Team t WHERE ownerId = :ownerId ";

        List<Team> teamList = jpaApi.em().createQuery(sql, Team.class).setParameter("ownerId", ownerId).getResultList();

        List<TeamDetail> teamDetailList = new ArrayList<>();
        for(Team team : teamList)
        {
            TeamDetail teamDetail = new TeamDetail();
            teamDetail.setTeamId(team.getTeamId());
            teamDetail.setTeamName(team.getTeamCity() + " " + team.getTeamName());
            BigDecimal salary = new BigDecimal(""+team.getTeamSalary());
            teamDetail.setOrganizationSalaryCap(salary);
            teamDetailList.add(teamDetail);
        }

        return ok(views.html.ownerteams.render(teamDetailList));
    }

    public Result getTestLogin()
    {
        return ok(views.html.testlogin.render());
    }

    public Result getTestPage()
    {
        return ok(views.html.testpage.render());
    }
}
