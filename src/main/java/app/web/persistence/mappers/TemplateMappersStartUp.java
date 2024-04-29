package app.web.persistence.mappers;



import app.web.persistence.GetConnectionIf;

public interface TemplateMappersStartUp
{
    static void startUpSetConnectionPoolAccess( GetConnectionIf connectionPool ) {
        TemplateSharedCrud.setConnectionPoolAccess( connectionPool );
    }
}
