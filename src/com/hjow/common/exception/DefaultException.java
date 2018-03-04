package com.hjow.common.exception;

public class DefaultException extends RuntimeException
{
    private static final long serialVersionUID = -3153283939270094429L;
    protected boolean seriousity = false;
    public DefaultException()
    {
        super();
    }
    public DefaultException(Throwable causes)
    {
        super(causes);
    }
    public DefaultException(String msg, Throwable causes)
    {
        super(msg, causes);
    }
    public DefaultException(boolean seriousity)
    {
        super();
        this.seriousity = seriousity;
    }
    public DefaultException(boolean seriousity, Throwable causes)
    {
        super(causes);
        this.seriousity = seriousity;
    }
    public DefaultException(String msg, boolean seriousity, Throwable causes)
    {
        super(msg, causes);
        this.seriousity = seriousity;
    }
    public boolean isSerious()
    {
        return seriousity;
    }
}
