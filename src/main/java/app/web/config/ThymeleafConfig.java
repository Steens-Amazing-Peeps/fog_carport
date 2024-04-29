package app.web.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafConfig
{
    
    public static TemplateEngine templateEngine()
    {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix( "web/templates/" ); // assuming templates are in resources/web/templates/
        templateResolver.setSuffix( ".html" );
        templateEngine.setTemplateResolver( templateResolver );
        return templateEngine;
    }
    
}