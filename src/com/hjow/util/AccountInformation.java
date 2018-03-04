package com.hjow.util;

import java.io.Serializable;

public class AccountInformation implements Serializable
{
    private static final long serialVersionUID = -4343783890993320420L;
    protected String id, password;
    public AccountInformation()
    {
        
    }
    public AccountInformation(String id, String password)
    {
        super();
        this.id = id;
        this.password = password;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}
