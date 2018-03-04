package com.hjow.script.scope;

import javax.script.ScriptException;

import com.hjow.common.DefaultThread;
import com.hjow.common.exception.DefaultException;
import com.hjow.script.DefaultScriptEngine;

public class ScriptThread extends DefaultThread
{
    protected transient String script;
    protected transient DefaultScriptEngine engine;
    
    public ScriptThread(String script, DefaultScriptEngine engine)
    {
        super();
        this.script = script;
        this.engine = engine;
        init();
    }
    public ScriptThread(String script, DefaultScriptEngine engine, long gap)
    {
        super(gap);
        this.script = script;
        this.engine = engine;
        init();
    }
    
    protected void init()
    {
        setEndWork(new Runnable()
        {
            @Override
            public void run()
            {
                engine = null;
                script = null;
            }
        });
    }
    
    @Override
    protected void work()
    {
        try
        {
            engine.eval(script);
        }
        catch (ScriptException e)
        {
            throw DefaultException.makeException("Script exception on thread.", false);
        }
    }
    
    @Override
    public void release()
    {
        super.release();
        engine = null;
        script = null;
    }
}
