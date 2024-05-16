package app.web.persistence.mappers;



import app.web.entities.Bom;
import app.web.entities.Plank;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;

import java.sql.*;
import java.util.*;


public final class BomMapperImpl implements BomMapper
{ //TODO: Make this thing work like backend needs it to
    
    
    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();
    
    
    public BomMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }
    
    @Override
    public int create( Bom bom, Integer carportId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        StringBuilder sqlBuilder = new StringBuilder();
        
        int totalUnitsInMaps = bom.getCombinedMapSize();
        int amountOfVariablesPrUnit = 3;
        int totalVariables = totalUnitsInMaps * amountOfVariablesPrUnit;
        
        Object[] parametersForSql = new Object[ totalVariables ];
        
        String sql =
                "INSERT INTO public.bom " +
                "   ( carport_id, variant_id, amount) " +
                "VALUES " + System.lineSeparator();
        
        String insertRow = "   (?, ?, ?)";
        
        sqlBuilder.append( sql );
        for ( int i = 0; i < totalUnitsInMaps; i++ ) {
            sqlBuilder.append( insertRow );
            
            if ( i + 1 < totalUnitsInMaps ) {
                sqlBuilder.append( ", " ).append( System.lineSeparator() );
            } else {
                sqlBuilder.append( ";" );
            }
        }
        int insertionIndex = 0;
        
        for ( Plank post : bom.getPosts().values() ) {
            parametersForSql[ insertionIndex++ ] = carportId;
            parametersForSql[ insertionIndex++ ] = post.getId();
            parametersForSql[ insertionIndex++ ] = post.getAmount();
        }
        
        for ( Plank beam : bom.getBeams().values() ) {
            parametersForSql[ insertionIndex++ ] = carportId;
            parametersForSql[ insertionIndex++ ] = beam.getId();
            parametersForSql[ insertionIndex++ ] = beam.getAmount();
        }
        
        for ( Plank rafter : bom.getRafters().values() ) {
            parametersForSql[ insertionIndex++ ] = carportId;
            parametersForSql[ insertionIndex++ ] = rafter.getId();
            parametersForSql[ insertionIndex++ ] = rafter.getAmount();
        }
        
        return this.dataStore.create( sqlBuilder.toString(), bom, parametersForSql, ENTITY_CREATOR );
    }
    
    
    public Map< Integer, Bom > readAll() throws DatabaseException
    { //TODO
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.bom_combined " +
                "ORDER BY " +
                "   bom_id;";
        
        
        return ( Map< Integer, Bom > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }
    
    
    public Bom readAllByCarportId( Integer carport_id ) throws DatabaseException
    { //TODO
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.bom_combined " +
                "WHERE " +
                "   carport_id = ?;";
        
        return ( Bom ) this.dataStore.readSingle( sql, carport_id, ENTITY_CREATOR );
    }
    
    
    
    
    
    
    private static class EntityCreatorImpl implements EntityCreator
    {
        
        private EntityCreatorImpl()
        {
        
        }
        
        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        { //TODO
            Bom bom = new Bom();
            
            String category;
            
            do {
                category = rs.getString( "category" ).toLowerCase();
                
                switch ( category ) {
                    case "planke":
                        this.createPlank( rs, bom );
                        break;
                    case "skrue":
                        this.createScrew( rs, bom );
                        break;
                    case "beslag":
                        this.createFitting( rs, bom );
                        break;
                    default:
                        new DatabaseException( "Unknown category = " + category, "Unknown category = " + category );
                        break;
                }
                
            } while ( rs.next() );
            
            return bom;
            
            
//                rs.getInt( "carport_id" );
//                rs.getInt( "amount" );
//                rs.getInt( "variant_id" );
//                rs.getInt( "length_in_mm" );
//                rs.getInt( "amount_pr_unit" );
//                rs.getInt( "price_in_oere" );
//                rs.getInt( "height_in_mm" );
//                rs.getInt( "width_in_mm" );
//                rs.getString( "direction" );
//                rs.getInt( "angle_in_degrees" );
//                rs.getString( "category" )
//                rs.getString( "type" );
//                rs.getString( "material" );
//                rs.getString( "treatment" );
//                rs.getString( "unit" );
        
        }
        
        private void createPlank( ResultSet rs, Bom bom ) throws SQLException
        {
            Plank plank = new Plank();

//            rs.getInt( "carport_id" );
            plank.setAmount( rs.getInt( "amount" ) );
            plank.setId( rs.getInt( "variant_id" ) );
            plank.setId( rs.getInt( "length_in_mm" ) );
//            rs.getInt( "amount_pr_unit" );
            plank.setPrice( rs.getInt( "price_in_oere" ) );
            plank.setHeight( rs.getInt( "height_in_mm" ) );
            plank.setWidth( rs.getInt( "width_in_mm" ) );
//            rs.getString( "direction" );
//            rs.getInt( "angle_in_degrees" );
//            rs.getString( "category" )
            plank.setType( Plank.convertTypeToInt( rs.getString( "type" ) ) );
            plank.setMaterial( rs.getString( "material" ) );
            plank.setTreatment( rs.getString( "treatment" ) );
//            rs.getString( "unit" );
            
            try {
                bom.addPlank( plank );
            } catch ( WebInvalidInputException e ) {
            }
        }
        
        private void createScrew( ResultSet rs, Bom bom )
        {
        }
        
        private void createFitting( ResultSet rs, Bom bom )
        {
        }
        
        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        { //TODO
            Map< Integer, Bom > bomMap = new LinkedHashMap<>();
            Bom bom = new Bom();
            
            while ( rs.next() ) {

//                bom.setVariantId( rs.getInt( "variant_id" ) );
//                bom.setAmount( rs.getInt( "amount" ) );
//
//                bomMap.put( bom.getBomId(), bom );
            }
            
            return bomMap;
        }
        
        @Override
        public Object setId( Object entity, Integer id )
        {
            return entity; //Which is the same as returning entity
        }
        
    }
    
}
