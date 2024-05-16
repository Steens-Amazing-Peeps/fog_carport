package app.web.services.bom.planks;

import app.web.entities.Plank;
import app.web.exceptions.DatabaseException;
import app.web.persistence.mappers.PlankMapper;

import java.util.Map;

public class ValidPlanksImpl implements ValidPlanks  //TODO: Finish this
{
    
    Map< Integer, Plank > boards; //Brædt
    Map< Integer, Plank > laths; //Lægter
    Map< Integer, Plank > beams; //Reglar
    Map< Integer, Plank > rafters; //Spærtræ
    Map< Integer, Plank > posts; //Stolpe
    
    @Override
    public void startUp( PlankMapper plankMapper )
    {
        //Boards
        try {
            this.setBoards( plankMapper.readAllByType( Plank.BOARD ) );
            
        } catch ( DatabaseException ignored ) {
        }
        
        //Laths
        try {
            this.setLaths( plankMapper.readAllByType( Plank.LATH ) );
            
        } catch ( DatabaseException ignored ) {
        }

//        //Beams
        try {
            this.setBeams( plankMapper.readAllByType( Plank.BEAM ) );
            
        } catch ( DatabaseException ignored ) {
        }
        
        
        
        //Rafters
        try {
            this.setRafters( plankMapper.readAllByType( Plank.RAFTER ) );
            
        } catch ( DatabaseException ignored ) {
        }
        
        //Posts
        try {
            this.setPosts( plankMapper.readAllByType( Plank.POST ) );
            
        } catch ( DatabaseException ignored ) {
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "------ValidPlanksImpl------" ).append( System.lineSeparator() );
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "-Boards-" ).append( System.lineSeparator() );
        for ( Plank plank : this.boards.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "-Laths-" ).append( System.lineSeparator() );
        for ( Plank plank : this.laths.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "-Beams-" ).append( System.lineSeparator() );
        for ( Plank plank : this.beams.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "-Rafters-" ).append( System.lineSeparator() );
        for ( Plank plank : this.rafters.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "-Posts-" ).append( System.lineSeparator() );
        for ( Plank plank : this.posts.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        return stringBuilder.toString();
    }
    
    //Getters and Setters
    @Override
    public Map< Integer, Plank > getBoards()
    {
        return this.boards;
    }
    
    @Override
    public void setBoards( Map< Integer, Plank > boards )
    {
        this.boards = boards;
    }
    
    @Override
    public Map< Integer, Plank > getLaths()
    {
        return this.laths;
    }
    
    @Override
    public void setLaths( Map< Integer, Plank > laths )
    {
        this.laths = laths;
    }
    
    @Override
    public Map< Integer, Plank > getBeams()
    {
        return this.beams;
    }
    
    @Override
    public void setBeams( Map< Integer, Plank > beams )
    {
        this.beams = beams;
    }
    
    @Override
    public Map< Integer, Plank > getRafters()
    {
        return this.rafters;
    }
    
    @Override
    public void setRafters( Map< Integer, Plank > rafters )
    {
        this.rafters = rafters;
    }
    
    @Override
    public Map< Integer, Plank > getPosts()
    {
        return this.posts;
    }
    
    @Override
    public void setPosts( Map< Integer, Plank > posts )
    {
        this.posts = posts;
    }
    
    
    
    
    
}
