package app.web.pageControllers.controllers.admins;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.admins.EditBuildingMaterialsModel;
import app.web.services.SvgCarport;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class EditBuildingMaterialsController
{
    private static EditBuildingMaterialsModel editBuildingMaterialsModel;
    
    public static void startUp( EditBuildingMaterialsModel editBuildingMaterialsModel )
    {
        if ( EditBuildingMaterialsController.editBuildingMaterialsModel == null ) {
            EditBuildingMaterialsController.editBuildingMaterialsModel = editBuildingMaterialsModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.EDIT_BUILDING_MATERIALS_GET_PAGE, ctx -> getPage( ctx ) );
        app.post( WebPages.EDIT_BUILDING_MATERIALS_POST_PAGE, ctx -> post( ctx ) );

    }
    
   
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.EDIT_BUILDING_MATERIALS_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.EDIT_BUILDING_MATERIALS_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    { //TODO
        render( ctx );
    }

    private static void post( Context ctx )
    { //TODO

    }

}
