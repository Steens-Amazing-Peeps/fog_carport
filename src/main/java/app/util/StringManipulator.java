package app.util;

public class StringManipulator
{
    
    public static StringBuilder addErrMsg( StringBuilder stringBuilderErrMsg, String errMsg )
    {
        stringBuilderErrMsg.append( errMsg ).append( System.lineSeparator() );
        return stringBuilderErrMsg;
    }
    
}
