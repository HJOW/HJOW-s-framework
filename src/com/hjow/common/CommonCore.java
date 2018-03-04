package com.hjow.common;

import java.awt.Window;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.script.ScriptEngineManager;

import com.hjow.common.ui.UI;
import com.hjow.script.DefaultScriptEngine;
import com.hjow.script.privilege.Privilege;
import com.hjow.script.scope.EngineScopeCore;
import com.hjow.util.AccountInformation;
import com.hjow.util.Utilities;

/**
 * 프로그램의 실행, 종료 및 반드시 관리되어야 하는 리소스들을 다룹니다.
 */
public class CommonCore 
{
    protected static transient List<Releasable> releasables = new Vector<Releasable>();
    protected static transient UI masterUI;
    protected static transient List<UI> subUIs = new Vector<UI>();
    protected static transient ScriptEngineManager engineManager = new ScriptEngineManager();
    protected static transient Properties stringTable = new Properties();
    
    public static void main(String[] args)
    {
        masterUI.init();
        Window masterWindow = masterUI.getWindow();
        masterWindow.setVisible(true);
    }
    
    public static void addReleasable(Releasable rel)
    {
        releasables.add(rel);
    }
    
    public static void addSubUI(UI ui)
    {
        subUIs.add(ui);
    }
    
    public static void exit()
    {
        for(Releasable r : releasables)
        {
            r.release();
        }
        releasables.clear();
        System.exit(0);
    }
    
    public static void log(Object msg)
    {
        if(masterUI != null) masterUI.log(msg);
        else System.out.println(msg);
    }
    
    public static void error(Throwable t)
    {
        if(masterUI != null) masterUI.error(t);
        else t.printStackTrace();
    }
    
    public static void alert(Object msg)
    {
        if(masterUI != null) masterUI.alert(msg);
        else System.out.println(msg);
    }
    
    public static boolean confirm(Object msg)
    {
        return masterUI.confirm(msg);
    }
    
    public static String askString(Object msg)
    {
        return masterUI.askString(msg);
    }
    
    public static String askPassword(Object msg)
    {
        return masterUI.askPassword(msg);
    }
    
    public static AccountInformation askAccount(Object msg)
    {
        return masterUI.askAccount(msg);
    }
    
    public static DefaultScriptEngine createScriptEngine(List<Privilege> privileges)
    {
        DefaultScriptEngine engine = new DefaultScriptEngine(masterUI, engineManager);
        engine.setPrivileges(privileges);
        EngineScopeCore.init(engine, masterUI);
        return engine;
    }
    
    public static String trans(String original)
    {
        String transed = stringTable.getProperty(original);
        if(Utilities.isEmpty(transed)) return original;
        return transed;
    }
}
