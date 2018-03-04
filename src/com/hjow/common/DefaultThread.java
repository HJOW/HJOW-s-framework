package com.hjow.common;

public abstract class DefaultThread implements Runnable, Releasable
{
    protected transient boolean threadSwitch = true;
    protected transient long gap = 50;
    protected transient boolean isPaused = false;
    protected static int accums = 0;
    protected transient int id = 0;
    protected transient Runnable endWork = null;
    
    public DefaultThread()
    {
        init();
    }
    public DefaultThread(long gap)
    {
        this.gap = gap;
        init();
    }
    protected synchronized void init()
    {
        accums++;
        id = accums;
        Thread thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void release()
    {
        threadSwitch = false;
    }
    
    public boolean isAlive()
    {
        return threadSwitch;
    }
    
    public void pause()
    {
        isPaused = true;
    }
    
    public void resume()
    {
        isPaused = false;
    }
    
    public boolean isPaused()
    {
        return isPaused;
    }
    
    public void togglePause()
    {
        isPaused = (! isPaused);
    }

    @Override
    public void run()
    {
        while(threadSwitch)
        {
            if(! isPaused) 
            { 
                try { work(); } catch(Throwable e) { CommonCore.error(e); }
            }
            try { Thread.sleep(gap); } catch(Throwable ignores) { }
        }
        if(endWork != null)
        {
            endWork.run();
            endWork = null;
        }
    }
    
    protected abstract void work();
    @Override
    public boolean isReleased()
    {
        return threadSwitch;
    }
    protected void setEndWork(Runnable runnable)
    {
        endWork = runnable;
    }
}
