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
}
