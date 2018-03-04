package com.hjow.common.exception;

import com.hjow.common.CommonCore;

public class DefaultException extends RuntimeException
{
    private static final long serialVersionUID = -3153283939270094429L;
    protected transient boolean seriousity  = false;
    protected transient String  originalMsg = null;
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
    public DefaultException(String msg)
    {
        super(msg);
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
    public DefaultException(String msg, boolean seriousity)
    {
        super(msg);
        this.seriousity = seriousity;
    }
    public DefaultException(Throwable causes, String originalMsg)
    {
        super(causes);
    }
    public DefaultException(String msg, Throwable causes, String originalMsg)
    {
        super(msg, causes);
        this.originalMsg = originalMsg;
    }
    public DefaultException(boolean seriousity, String originalMsg)
    {
        super();
        this.seriousity = seriousity;
        this.originalMsg = originalMsg;
    }
    public DefaultException(boolean seriousity, Throwable causes, String originalMsg)
    {
        super(causes);
        this.seriousity = seriousity;
        this.originalMsg = originalMsg;
    }
    public DefaultException(String msg, boolean seriousity, Throwable causes, String originalMsg)
    {
        super(msg, causes);
        this.seriousity = seriousity;
        this.originalMsg = originalMsg;
    }
    public DefaultException(String msg, boolean seriousity, String originalMsg)
    {
        super(msg);
        this.seriousity = seriousity;
        this.originalMsg = originalMsg;
    }
    public static DefaultException makeException(String msg)
    {
        DefaultException obj = new DefaultException(CommonCore.trans(msg));
        obj.originalMsg = msg;
        return obj;
    }
    public static DefaultException makeException(String msg, boolean seriousity)
    {
        DefaultException obj = new DefaultException(CommonCore.trans(msg), seriousity);
        obj.seriousity  = seriousity;
        obj.originalMsg = msg;
        return obj;
    }
    public static DefaultException makeException(Throwable causes, String msg, boolean seriousity)
    {
        DefaultException obj = new DefaultException(causes, CommonCore.trans(msg));
        obj.seriousity  = seriousity;
        obj.originalMsg = msg;
        return obj;
    }
    public static DefaultException makeException(Throwable causes, String msg)
    {
        DefaultException obj = new DefaultException(causes, CommonCore.trans(msg));
        obj.originalMsg = msg;
        return obj;
    }
    public boolean isSerious()
    {
        return seriousity;
    }
    public String getOriginalMessage()
    {
        return originalMsg;
    }
}
