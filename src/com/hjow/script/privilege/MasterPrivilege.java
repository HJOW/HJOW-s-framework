package com.hjow.script.privilege;

public class MasterPrivilege extends Privilege
{
    private static final long serialVersionUID = 4927408620796490775L;
    public MasterPrivilege()
    {
        super();
        masterPrivilege = true;
    }
    
    @Override
    public boolean isMasterPrivilege()
    {
        return true;
    }
}
