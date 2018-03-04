package com.hjow.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.hjow.common.exception.DefaultException;

public class IOUtilities
{
    public static final String DEFAULT_CHARSET  = "UTF-8";
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static String readString(Reader reader, boolean closeAfter) 
    {
        BufferedReader buffered = null;
        StringBuilder results = new StringBuilder("");
        try
        {
            buffered = new BufferedReader(reader);
            String lines = null;
            boolean isFirst = true;
            while(true)
            {
                lines = buffered.readLine();
                if(lines == null) break;
                if(! isFirst) results = results.append("\n");
                results = results.append(lines);
                isFirst = false;
            }
            if(closeAfter) 
            {
                try { buffered.close(); } catch(Throwable ignores) {}
                try { reader.close();   } catch(Throwable ignores) {}
            }
            return results.toString();
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
                }
                try { reader.close();   } catch(Throwable ignores) {}
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
                }
                try { stream.close(); } catch(Throwable ignores) {}
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
    
    public static byte[] readBytes(InputStream stream, boolean setLengthCorrectly, boolean closeAfter)
    {
        try
        {
            ByteArrayOutputStream collector = new ByteArrayOutputStream();
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            zeroingByteArray(buffer);
            int totalLength = 0;
            while(true)
            {
                int readByteCount = stream.read(buffer);
                if(readByteCount < 0) break;
                collector.write(buffer, 0, readByteCount);
                totalLength += readByteCount;
            }
            if(closeAfter)
            {
                try { stream.close();   } catch(Throwable ignores) {}
            }
            byte[] resultsArray = collector.toByteArray();
            if(! setLengthCorrectly) return resultsArray;
            
            byte[] newArray     = new byte[totalLength];
            for(int idx=0; idx<totalLength; idx++)
            {
                newArray[idx] = resultsArray[idx];
            }
            return newArray;
        }
        catch(Throwable e1)
        {
            throw new DefaultException(e1);
        }
        finally
        {
            if(closeAfter)
            {
                if(stream != null)
                {
                    try { stream.close();   } catch(Throwable ignores) {}
                }
            }
        }
    }
    
    public static byte[] readBytes(InputStream stream, boolean closeAfter)
    {
        return readBytes(stream, true, closeAfter);
    }
    
    public static byte[] readBytes(InputStream stream)
    {
        return readBytes(stream, true, true);
    }
    
    public static void zeroingByteArray(byte[] array)
    {
        for(int idx=0; idx<array.length; idx++)
        {
            array[idx] = 0;
        }
    }
    
    public static List<String> getDirectoryBlocks(File file)
    {
        String abs = file.getAbsolutePath();
        abs = abs.replace("\\", "/");
        List<String> blocks = new ArrayList<String>();
        StringTokenizer blockTokenizer = new StringTokenizer(abs, "/");
        while(blockTokenizer.hasMoreTokens())
        {
            String one = blockTokenizer.nextToken().trim();
            if(Utilities.isEmpty(one)) break;
            blocks.add(one);
        }
        
        return blocks;
    }
    
    public static String getDirectoryPath(File file)
    {
        List<String> blocks = getDirectoryBlocks(file);
        blocks.remove(blocks.size() - 1);
        StringBuilder results = new StringBuilder("");
        boolean isFirst = true;
        for(String b : blocks)
        {
            if(! isFirst) results = results.append(File.separator);
            results = results.append(b);
            isFirst = false;
        }
        return results.toString();
    }
    
    public static String getFileExtension(File file)
    {
        if(! file.exists()) return null;
        if(file.isDirectory()) return null;
        String fileName = file.getName();
        StringTokenizer dotTokenizer = new StringTokenizer(fileName, ".");
        List<String> dotBlocks = new ArrayList<String>();
        if(dotTokenizer.countTokens() <= 1) return "";
        while(dotTokenizer.hasMoreTokens())
        {
            dotBlocks.add(dotTokenizer.nextToken().trim());
        }
        return dotBlocks.get(dotBlocks.size() - 1).trim();
    }
    
    public static void writeString(Writer writer, String contents, boolean closeAfter)
    {
        BufferedWriter buffered = null;
        try
        {
            buffered = new BufferedWriter(writer);
            StringTokenizer lineTokenizer = new StringTokenizer(contents, "\n");
            boolean isFirst = true;
            while(lineTokenizer.hasMoreTokens())
            {
                if(! isFirst) buffered.newLine();
                buffered.write(lineTokenizer.nextToken());
                isFirst = false;
            }
            if(closeAfter)
            {
                try { buffered.close(); } catch(Throwable ignores) {}
            }
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
                }
            }
        }
    }
    
    public static void writeString(OutputStream stream, String contents, String charset, boolean closeAfter)
    {
        if(Utilities.isEmpty(charset)) charset = DEFAULT_CHARSET;
        
        OutputStreamWriter writer = null;
        try
        {
            writer = new OutputStreamWriter(stream, charset);
            writeString(writer, contents, closeAfter);
            
            if(closeAfter)
            {
                try { writer.close(); } catch(Throwable ignores) {}
                try { stream.close(); } catch(Throwable ignores) {}
            }
        }
        catch(Throwable e1)
        {
            throw new DefaultException(e1);
        }
        finally
        {
            if(closeAfter)
            {
                if(writer != null)
                {
                    try { writer.close(); } catch(Throwable ignores) {}
                }
                try { stream.close(); } catch(Throwable ignores) {}
            }
        }
    }
}
