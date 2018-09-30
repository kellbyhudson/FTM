package controllers;

import models.Owner;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;

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
        return ok(views.html.returningownerlogin.render());
    }
}
