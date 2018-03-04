package com.hjow.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.hjow.common.exception.DefaultException;

public class IOUtilities
{
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static String readString(Reader reader, boolean closeAfter) 
    {
        BufferedReader buffered = null;
        StringBuilder results = new StringBuilder("");
        try
        {
            buffered = new BufferedReader(reader);
            String lines = null;
            while(true)
            {
                lines = buffered.readLine();
                if(lines == null) break;
                results = results.append("\n").append(lines);
            }
            if(closeAfter) 
            {
                try { buffered.close(); } catch(Throwable ignores) {}
                try { reader.close();   } catch(Throwable ignores) {}
            }
            return results.toString().trim();
        }
        catch(Throwable e1)
        {
            throw new DefaultException(e1);
        }
        finally
        {
            if(closeAfter) 
            {
                if(buffered != null)
                {
                    try { buffered.close(); } catch(Throwable ignores) {}
                    try { reader.close();   } catch(Throwable ignores) {}
                }
            }
        }
    }
    
    public static String readString(InputStream stream, String charset, boolean closeAfter)
    {
        InputStreamReader reader = null;
        if(Utilities.isEmpty(charset)) charset = DEFAULT_CHARSET;
        try
        {
            reader = new InputStreamReader(stream, charset);
            String results = readString(reader, closeAfter);
            if(closeAfter)
            {
                try { reader.close(); } catch(Throwable ignores) {}
                try { stream.close(); } catch(Throwable ignores) {}
            }
            return results;
        }
        catch(Throwable e1)
        {
            throw new DefaultException(e1);
        }
        finally
        {
            if(closeAfter)
            {
                if(reader != null)
                {
                    try { reader.close(); } catch(Throwable ignores) {}
                    try { stream.close(); } catch(Throwable ignores) {}
                }
            }
        }
    }
    
    public static String readString(InputStream stream, boolean closeAfter)
    {
        return readString(stream, null, closeAfter);
    }
    
    public static String readString(InputStream stream)
    {
        return readString(stream, null, true);
    }
    
    public static String readString(InputStream stream, String charset)
    {
        return readString(stream, charset, true);
    }
    
    public static String readString(File file, String charset)
    {
        FileInputStream stream = null;
        try
        {
            stream = new FileInputStream(file);
            String results = readString(stream, charset, true);
            try { stream.close(); } catch(Throwable ignores) {}
            return results;
        }
        catch(Throwable e1)
        {
            throw new DefaultException(e1);
        }
        finally 
        {
            if(stream != null)
            {
                try { stream.close(); } catch(Throwable ignores) {}
            }
        }
    }
    
    public static String readString(File file)
    {
        return readString(file, DEFAULT_CHARSET);
    }
    
    public static String getDirectoryPath(File file)
    {
        String abs = file.getAbsolutePath();
        abs = abs.replace("\\", "/");
        List<String> blocks = new ArrayList<String>();
        StringTokenizer blockTokenizer = new StringTokenizer(abs, "/");
        
        return null;
    }
}
