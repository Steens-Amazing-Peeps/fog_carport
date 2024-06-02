package app.util.demo;

import java.io.FileWriter;
import java.io.IOException;

public class FileIO
{
    public static boolean txtOverWrite( String fileName, String fileContent ) {
        
        try ( FileWriter fileWriter = new FileWriter( fileName + ".txt" ) ) {
            fileWriter.write( fileContent );
            fileWriter.flush();
            
            return true;
            
        } catch ( IOException e ) {
            return false;
        }
        
    }
    
}
