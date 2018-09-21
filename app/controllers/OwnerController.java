package controllers;

import models.Owner;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

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
        return ok(views.html.start.render());
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
        String organizationCity = form.get("organizationcity");
        String organizationName = form.get("organizationname");
        String result;

        if(ownerName.length() <= 50 && organizationCity.length() <= 50 && organizationName.length() <= 50)
        {
            Owner newOwner = new Owner();
            newOwner.setOwnerName(ownerName);
            newOwner.setOrganizationCity(organizationCity);
            newOwner.setOrganizationName(organizationName);
            jpaApi.em().persist(newOwner);
            result = "Saved";
        }
        else
        {
            result = "Not Saved";
        }
        return ok(result);
    }
}
