package com.hjow.script.scope;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.script.ScriptContext;

import com.hjow.common.CommonCore;
import com.hjow.common.Releasable;
import com.hjow.common.exception.DefaultException;
import com.hjow.common.ui.UI;
import com.hjow.script.DefaultScriptEngine;
import com.hjow.script.privilege.Privilege;
import com.hjow.util.IOUtilities;

public abstract class ScriptObject implements Releasable
{
    protected transient UI ui;
    protected transient DefaultScriptEngine engine;
    protected transient String variableName;
    
    public static final int ACTION_NOT_PUT    = -1;
    public static final int ACTION_PUT_LOCAL  = ScriptContext.ENGINE_SCOPE;
    public static final int ACTION_PUT_GLOBAL = ScriptContext.GLOBAL_SCOPE;
    
    public ScriptObject(UI ui, DefaultScriptEngine engine, String variableName)
    {
        this(ui, engine, variableName, ACTION_PUT_LOCAL);
    }
    
    public ScriptObject(UI ui, DefaultScriptEngine engine, String variableName, int addInScopeAction)
    {
        this.ui     = ui;
        this.engine = engine;
        this.variableName = variableName;
        CommonCore.addReleasable(this);
        addBindingAction(addInScopeAction);
    }
    
    public void addBindingAction(int addInScopeAction)
    {
        switch(addInScopeAction)
        {
        case ACTION_NOT_PUT:
            break;
        case ACTION_PUT_LOCAL:
            engine.getBindings(addInScopeAction).put(variableName, this);
            break;
        case ACTION_PUT_GLOBAL:
            engine.getBindings(addInScopeAction).put(variableName, this);
            break;
        default:
            throw DefaultException.makeException("Wrong scope action code.");
        }
    }

    @Override
    public void release()
    {
        ui     = null;
        engine = null;
        variableName = null;
    }

    @Override
    public boolean isReleased()
    {
        return (ui != null);
    }
    
    public String getVariableName()
    {
        return variableName;
    }
    
    protected ScriptObject getSelfObject()
    {
        return this;
    }
    
    protected void checkPrivilege(String methodName)
    {
        List<Privilege> privs = engine.getPrivileges();
        boolean checks = false;
        for(Privilege p : privs)
        {
            if(p.isAccepted(getSelfObject(), methodName)) checks = true; 
        }
        if(! checks) throw DefaultException.makeException("Not enough privilege.", false);
    }

    protected String defaultStringTrans(Object obj)
    {
        try
        {
            if(obj == null) return null;
            if(obj instanceof CharSequence) return obj.toString();
            if(obj instanceof byte[])       return new String((byte[]) obj, IOUtilities.DEFAULT_CHARSET);
            if(obj instanceof char[])       return new String((char[]) obj);
            return String.valueOf(obj);
        }
        catch(UnsupportedEncodingException e1)
        {
            throw new DefaultException(e1);
        }
    }
}
