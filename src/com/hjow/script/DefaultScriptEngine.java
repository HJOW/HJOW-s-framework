package com.hjow.script;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.hjow.common.CommonCore;
import com.hjow.common.Releasable;
import com.hjow.common.exception.DefaultException;
import com.hjow.common.ui.UI;
import com.hjow.script.privilege.Privilege;
import com.hjow.util.IOUtilities;

public class DefaultScriptEngine implements ScriptEngine, Releasable
{
    protected transient UI ui;
    protected transient ScriptEngine parents;
    protected transient List<String>    abandoned  = new ArrayList<String>();
    protected transient List<Privilege> privileges = new ArrayList<Privilege>();
    
    public static final String DEFAULT_LANGUAGE = "JavaScript";
    
    public DefaultScriptEngine(UI ui, ScriptEngineManager manager)
    {
        this.ui = ui;
        parents = manager.getEngineByName(DEFAULT_LANGUAGE);
    }
    
    protected void checkAbandoned(String contents)
    {
        for(String ab : abandoned)
        {
            if(contents.contains(ab)) throw new DefaultException(CommonCore.trans("Abandoned-keyword detected."), false, "Abandoned-keyword detected.");
        }
    }

    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException
    {
        return parents.eval(script, context);
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException
    {
        String contents = IOUtilities.readString(reader, true);
        return eval(contents, context);
    }

    @Override
    public Object eval(String script) throws ScriptException
    {
        checkAbandoned(script);
        return parents.eval(script);
    }

    @Override
    public Object eval(Reader reader) throws ScriptException
    {
        String contents = IOUtilities.readString(reader, true);
        return eval(contents);
    }

    @Override
    public Object eval(String script, Bindings n) throws ScriptException
    {
        checkAbandoned(script);
        return parents.eval(script, n);
    }

    @Override
    public Object eval(Reader reader, Bindings n) throws ScriptException
    {
        String contents = IOUtilities.readString(reader, true);
        return eval(contents, n);
    }

    @Override
    public void put(String key, Object value)
    {
        parents.put(key, value);
    }

    @Override
    public Object get(String key)
    {
        return parents.get(key);
    }

    @Override
    public Bindings getBindings(int scope)
    {
        return parents.getBindings(scope);
    }

    @Override
    public void setBindings(Bindings bindings, int scope)
    {
        parents.setBindings(bindings, scope);
    }

    @Override
    public Bindings createBindings()
    {
        return parents.createBindings();
    }

    @Override
    public ScriptContext getContext()
    {
        return parents.getContext();
    }

    @Override
    public void setContext(ScriptContext context)
    {
        parents.setContext(context);
    }

    @Override
    public ScriptEngineFactory getFactory()
    {
        return parents.getFactory();
    }

    @Override
    public void release()
    {
        parents.getBindings(ScriptContext.ENGINE_SCOPE).clear();
        privileges.clear();
        privileges = null;
        parents    = null;
        ui         = null;
    }

    @Override
    public boolean isReleased()
    {
        return (parents != null);
    }
    
    public UI getUI()
    {
        return ui;
    }

    public List<Privilege> getPrivileges()
    {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges)
    {
        this.privileges = privileges;
    }
}