package com.hjow.common.ui;

import java.awt.Window;

import com.hjow.util.AccountInformation;

public interface UI
{
    public void init();
    public void onExit();
    public void show();
    public Window getWindow();
    public void log(Object msg);
    public void alert(Object msg);
    public boolean confirm(Object msg);
    public String askString(Object msg);
    public String askPassword(Object msg);
    public AccountInformation askAccount(Object msg);
    public void error(Throwable t);
}
