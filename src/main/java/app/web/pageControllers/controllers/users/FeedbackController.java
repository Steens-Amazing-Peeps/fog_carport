package app.web.pageControllers.controllers.users;


import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.FeedbackModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class FeedbackController
{

    private static FeedbackModel feedbackModel;

    public static void startUp( FeedbackModel feedbackModel)
    {
        if ( FeedbackController.feedbackModel == null ) {
            FeedbackController.feedbackModel = feedbackModel;
        }
    }

    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.FEEDBACK_GET_PAGE, ctx -> getPage( ctx ) );

        app.post( WebPages.FEEDBACK_POST_PAGE, ctx -> post( ctx ) );
    }


    public static void render( Context ctx )
    {
        ctx.render( WebHtml.FEEDBACK_HTML );
    }


    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.FEEDBACK_GET_PAGE );
    }

    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx )
    { //TODO: Implement this
        String feedback;

        feedback = ctx.formParam( WebFormParam.feedbackSend );

        FeedbackController.redirect( ctx );
    }




}
