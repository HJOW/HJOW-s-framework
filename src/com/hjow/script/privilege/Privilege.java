package com.hjow.script.privilege;

import java.io.Serializable;
import java.util.List;

import com.hjow.script.scope.ScriptObject;

public abstract class Privilege implements Serializable
{
    private static final long serialVersionUID = 1139505046201552075L;
    protected String name;
    protected List<Privilege> childPrivileges  = null;
    protected Class<ScriptObject> targetClass  = null;
    protected List<String>        targetMethod = null;
    protected boolean masterPrivilege  = false;
    protected boolean getClassAccepted = false;
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public List<Privilege> getChildPrivileges()
    {
        return childPrivileges;
    }
    public void setChildPrivileges(List<Privilege> childPrivileges)
    {
        this.childPrivileges = childPrivileges;
    }
    public boolean isAccepted(ScriptObject caller, String method)
    {
        if(masterPrivilege) return true;
        if(targetClass != null && targetMethod != null)
        {
            if(targetClass.isInstance(caller) && targetMethod.contains(method)) return true;
        }
        else if(targetClass != null)
        {
            if(targetClass.isInstance(caller)) return true;
        }
        else if(targetMethod != null)
        {
            if(targetMethod.contains(method)) return true;
        }
        if(childPrivileges != null)
        {
            for(Privilege child : childPrivileges)
            {
                if(child.isAccepted(caller, method)) return true;
            }
        }
        return false;
    }
    public boolean isMasterPrivilege()
    {
        return masterPrivilege;
    }
    public void setMasterPrivilege(boolean masterPrivilege)
    {
        this.masterPrivilege = masterPrivilege;
    }
    public boolean isGetClassAccepted()
    {
        return getClassAccepted;
    }
    public void setGetClassAccepted(boolean getClassAccepted)
    {
        this.getClassAccepted = getClassAccepted;
    }
    public Class<ScriptObject> getTargetClass()
    {
        return targetClass;
    }
    public void setTargetClass(Class<ScriptObject> targetClass)
    {
        this.targetClass = targetClass;
    }
    public List<String> getTargetMethod()
    {
        return targetMethod;
    }
    public void setTargetMethod(List<String> targetMethod)
    {
        this.targetMethod = targetMethod;
    }
}
