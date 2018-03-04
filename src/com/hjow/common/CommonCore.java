package com.hjow.common;

import java.awt.Window;
import java.util.List;
import java.util.Vector;

import com.hjow.common.ui.UI;

/**
 * 프로그램의 실행, 종료 및 반드시 관리되어야 하는 리소스들을 다룹니다.
 */
public class CommonCore 
{
    protected static List<Releasable> releasables = new Vector<Releasable>();
    protected static UI masterUI;
    protected static List<UI> subUIs = new Vector<UI>();
    
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
}
