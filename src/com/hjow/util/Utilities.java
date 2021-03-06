package com.hjow.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utilities
{
    public static boolean isEmpty(Object target)
    {
        if(target == null) return true;
        if(target instanceof List<?>)
        {
            return ((List<?>) target).isEmpty();
        }
        if(target instanceof Map<?, ?>)
        {
            return ((Map<?, ?>) target).isEmpty();
        }
        if(target instanceof Set<?>)
        {
            return ((Set<?>) target).isEmpty();
        }
        
        return target.toString().equals("");
    }
    
    public static boolean isNotEmpty(Object target)
    {
        return (! isEmpty(target));
    }
    
    public static boolean parseBoolean(Object obj)
    {
        if(isEmpty(obj)) return false;
        if(obj instanceof Boolean) return ((Boolean) obj).booleanValue();
        else if(obj instanceof Number)
        {
            if(((Number) obj).doubleValue() == 0.0) return false;
            else return true;
        }
        String str = obj.toString();
        if(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("t") || str.equalsIgnoreCase("true")) return true;
        if(str.equalsIgnoreCase("n") || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("f") || str.equalsIgnoreCase("false")) return false;
        throw new RuntimeException("Cannot parse the object" + str + " to boolean.");
    }
    
    public static String parseString(Object obj)
    {
        return String.valueOf(obj);
    }
    
    public static String parseStringWithoutNull(Object obj)
    {
        if(obj == null) return "";
        return String.valueOf(obj);
    }
    
    public static String toStackTrace(Throwable t)
    {
        StringBuilder results = new StringBuilder("Exception (");
        results = results.append(t.getClass().getName()).append(") ").append(t.getMessage()).append("\n");
        
        StackTraceElement[] elements = t.getStackTrace();
        for(StackTraceElement e : elements)
        {
            results = results.append("\tat").append(e.toString()).append("\n");
        }
        
        Throwable causes = t.getCause();
        if(causes == null) return results.toString();
        
        results = results.append("\tfrom...\n").append(toStackTrace(causes.getCause()));
        return results.toString();
    }
}
