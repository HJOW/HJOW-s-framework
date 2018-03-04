package com.hjow.common.ui;

import java.awt.Window;

public interface UI
{
    public void init();
    public void onExit();
    public Window getWindow();
    public void log(Object msg);
    public void alert(Object msg);
    public boolean confirm(Object msg);
    public String askString(Object msg);
    public void error(Throwable t);
}
