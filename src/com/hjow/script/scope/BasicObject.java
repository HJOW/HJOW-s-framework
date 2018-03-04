package com.hjow.script.scope;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptException;

import com.hjow.common.CommonCore;
import com.hjow.common.exception.DefaultException;
import com.hjow.common.ui.UI;
import com.hjow.script.DefaultScriptEngine;
import com.hjow.util.AccountInformation;
import com.hjow.util.Utilities;

public class BasicObject extends ScriptObject
{
    protected static transient Runtime runtime = Runtime.getRuntime();
    public BasicObject(UI ui, DefaultScriptEngine engine)
    {
        super(ui, engine, "lang", ACTION_PUT_GLOBAL);
    }
    
    public String string(Object obj)
    {
        return defaultStringTrans(obj);
    }
    
    @Override
    public void addBindingAction(int addInScopeAction)
    {
        super.addBindingAction(addInScopeAction);
        
        StringBuilder defaultAction = new StringBuilder("");
        defaultAction = defaultAction.append("function string(obj)\n"                             );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.string(obj);\n"                     );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function bigint(obj)\n"                             );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.bigint(obj);\n"                     );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append("function biginteger(obj)\n"                         );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.biginteger(obj);\n"                 );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function bigdecimal(obj)\n"                         );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.bigdecimal(obj);\n"                 );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function list()\n"                                  );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.list();\n"                          );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function map()\n"                                   );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.map();\n"                           );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function log(obj)\n"                                );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    lang.log(obj);\n"                               );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function alert(obj)\n"                              );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    lang.alert(obj);\n"                             );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function confirm(obj)\n"                            );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.confirm(obj);\n"                    );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function askString(obj)\n"                          );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.askString(obj);\n"                  );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function askPassword(obj)\n"                        );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.askPassword(obj);\n"                );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function askAccount(obj)\n"                         );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.askAccount(obj);\n"                 );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function tryOn(scripts, exceptionClauses)\n"        );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.tryOn(scripts, exceptionClauses);\n");
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function thread(scripts)\n"                         );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.thread(scripts);\n"                 );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function today()\n"                                 );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.today();\n"                         );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function currentTimeMills()\n"                      );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.currentTimeMills();\n"              );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function totalMemory()\n"                           );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.totalMemory();\n"                   );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function maxMemory()\n"                             );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.maxMemory();\n"                     );
        defaultAction = defaultAction.append("};\n"                                               );
        defaultAction = defaultAction.append(" \n"                                                );
        defaultAction = defaultAction.append("function freeMemory()\n"                            );
        defaultAction = defaultAction.append("{\n"                                                );
        defaultAction = defaultAction.append("    return lang.freeMemory();\n"                    );
        defaultAction = defaultAction.append("};\n"                                               );
        
        try
        {
            engine.eval(defaultAction.toString());
        }
        catch (ScriptException e)
        {
            throw new DefaultException(e);
        }
    }
    
    public BigInteger bigint(Object obj)
    {
        return new BigInteger(string(obj));
    }
    
    public BigInteger biginteger(Object obj)
    {
        return bigint(obj);
    }
    
    public BigDecimal bigdecimal(Object obj)
    {
        return new BigDecimal(string(obj));
    }
    
    public List<Object> list()
    {
        return new ArrayList<Object>();
    }
    
    public Map<Object, Object> map()
    {
        return new HashMap<Object, Object>();
    }
    
    public void log(Object msg)
    {
        CommonCore.log(msg);
    }
    
    public void alert(Object msg)
    {
        CommonCore.alert(msg);
    }
    
    public Boolean confirm(Object msg)
    {
        return new Boolean(CommonCore.confirm(msg));
    }
    
    public String askString(Object msg)
    {
        return CommonCore.askString(msg);
    }
    
    public String askPassword(Object msg)
    {
        return CommonCore.askPassword(msg);
    }
    
    public AccountInformation askAccount(Object msg)
    {
        return CommonCore.askAccount(msg);
    }
    
    public Object tryOn(String scripts, Map<?, ?> exceptionClases)
    {
        try
        {
            return engine.eval(scripts);
        }
        catch(Throwable ex)
        {
            if(exceptionClases == null) return null;
            Set<?> excKeys = exceptionClases.keySet();
            for(Object excKey : excKeys)
            {
                boolean isTarget = ex.getClass().getSimpleName().equals(excKey.toString());
                if((! isTarget) && (ex instanceof DefaultException))
                {
                    DefaultException defEx = (DefaultException) ex;
                    if(! Utilities.isEmpty(defEx.getOriginalMessage()))
                    {
                        isTarget = defEx.getOriginalMessage().equals(excKey.toString());
                    }
                }
                if(isTarget)
                {
                    try { engine.eval(exceptionClases.get(excKey).toString()); } catch(Throwable ex2) { throw new DefaultException(ex2); }
                    break;
                }
            }
            return null;
        }
    }
    
    public ScriptThread thread(Object script)
    {
        return new ScriptThread(script.toString(), engine);
    }
    
    public Date today()
    {
        return new Date(System.currentTimeMillis());
    }
    
    public Long currentTimeMills()
    {
        return System.currentTimeMillis();
    }
    
    public Long totalMemory()
    {
        return runtime.totalMemory();
    }
    
    public Long maxMemory()
    {
        return runtime.maxMemory();
    }
    
    public Long freeMemory()
    {
        return runtime.freeMemory();
    }
}
