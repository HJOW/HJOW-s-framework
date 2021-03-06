package com.hjow.common.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import com.hjow.common.CommonCore;
import com.hjow.common.exception.DefaultException;
import com.hjow.script.DefaultScriptEngine;
import com.hjow.script.privilege.MasterPrivilege;
import com.hjow.script.privilege.Privilege;
import com.hjow.util.AccountInformation;
import com.hjow.util.UIUtilities;
import com.hjow.util.Utilities;

public class DefaultUI implements UI
{
    protected transient TransparentFrame frame;
    protected transient JMenuBar menuBar;
    protected transient TransparentTextArea consoleArea;
    protected transient TransparentSplitPane consoleSplits;
    protected transient JTextField consoleField;
    protected transient boolean inited = false;
    protected transient boolean isFirstShown = true;
    protected transient TransparentPanel contentPanel;
    protected transient JMenu menuFile, menuTool, menuAct;
    
    protected transient DefaultScriptEngine consoleEngine;
    protected transient List<String> consoleHistory;
    
    @Override
    public void init()
    {
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width, height;
        width  = 600;
        height = 400;
        
        if(scrSize.getWidth()  > width  + 100) width  = (int) (scrSize.getWidth()  - 100);
        if(scrSize.getHeight() > height + 100) height = (int) (scrSize.getHeight() - 100);
        
        frame = new TransparentFrame();
        frame.setSize(width, height);
        
        UIUtilities.addWindowCloseListener(frame, new Runnable()
        {
            @Override
            public void run()
            {
                onExit();
                CommonCore.exit();
            }
        });
        
        frame.setLayout(new BorderLayout());
        
        TransparentPanel mainPanel, consolePanel;
        
        mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        
        consolePanel = new TransparentPanel();
        consoleArea  = new TransparentTextArea();
        contentPanel = new TransparentPanel();
        
        consoleArea.setLineWrap(true);
        consoleArea.setEditable(false);
        
        consolePanel.setLayout(new BorderLayout());
        consolePanel.add(new TransparentScrollPane(consoleArea), BorderLayout.CENTER);
        
        consoleSplits = new TransparentSplitPane(JSplitPane.VERTICAL_SPLIT);
        consoleSplits.setTopComponent(contentPanel);
        consoleSplits.setBottomComponent(consolePanel);
        
        TransparentPanel consoleBottomPanel = new TransparentPanel();
        TransparentPanel consoleRightPanel  = new TransparentPanel();
        
        consolePanel.add(consoleBottomPanel, BorderLayout.SOUTH);
        
        consoleBottomPanel.setLayout(new BorderLayout());
        consoleRightPanel.setLayout(new BorderLayout());
        
        consoleField = new JTextField();
        consoleBottomPanel.add(consoleField, BorderLayout.CENTER);
        consoleBottomPanel.add(consoleRightPanel, BorderLayout.EAST);
        
        UIUtilities.addActionListener(consoleField, new Runnable()
        {
            @Override
            public void run()
            {
                runScript(consoleField.getText());
            }
        });
        
        JButton btnConsole = new JButton("▶");
        consoleRightPanel.add(btnConsole, BorderLayout.CENTER);
        
        UIUtilities.addActionListener(btnConsole, new Runnable()
        {
            @Override
            public void run()
            {
                runScript(consoleField.getText());
            }
        });
        
        mainPanel.add(consoleSplits, BorderLayout.CENTER);
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        menuFile = new JMenu(CommonCore.trans("File"));
        menuTool = new JMenu(CommonCore.trans("Tool"));
        menuAct  = new JMenu(CommonCore.trans("Action"));
        
        menuBar.add(menuFile);
        menuBar.add(menuTool);
        menuBar.add(menuAct);
        
        List<Privilege> consolePriv = new ArrayList<Privilege>();
        consolePriv.add(new MasterPrivilege());
        consoleEngine = CommonCore.createScriptEngine(consolePriv);
        consoleHistory = new Vector<String>();
        
        inited = true;
    }
    
    public void show()
    {
        if(isFirstShown)
        {
            UIUtilities.centerWindow(frame);
            consoleSplits.setDividerLocation(0.7);
        }
        frame.setVisible(true);
        isFirstShown = false;
    }

    @Override
    public void onExit()
    {
        
    }
    
    protected synchronized void runScript(String script)
    {
        try
        {
            log(">> " + script);
            consoleField.setText("");
            consoleHistory.add(script);
            if(consoleHistory.size() >= 100) consoleHistory.remove(0);
            Object obj = consoleEngine.eval(script);
            log(obj);
        }
        catch(Throwable t1)
        {
            String errMsg = (t1 == null ? "NullPointer" : t1.getMessage());
            log("ERROR : " + errMsg);
        }
        finally
        {
            consoleField.requestFocus();
        }
    }

    @Override
    public Window getWindow()
    {
        return frame;
    }

    @Override
    public void log(Object msg)
    {
        consoleArea.append("\n" + String.valueOf(msg));
        consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
    }

    @Override
    public void alert(Object msg)
    {
        JOptionPane.showMessageDialog(getWindow(), Utilities.parseStringWithoutNull(msg));
    }

    @Override
    public boolean confirm(Object msg)
    {
        int sel = JOptionPane.showConfirmDialog(getWindow(), Utilities.parseStringWithoutNull(msg), Utilities.parseStringWithoutNull(msg), JOptionPane.YES_NO_OPTION);
        return sel == JOptionPane.YES_OPTION;
    }

    @Override
    public String askString(Object msg)
    {
        return JOptionPane.showInputDialog(getWindow(), Utilities.parseStringWithoutNull(msg));
    }

    @Override
    public String askPassword(Object msg)
    {
        return UIUtilities.askPassword(getWindow(), Utilities.parseStringWithoutNull(msg));
    }

    @Override
    public AccountInformation askAccount(Object msg)
    {
        return UIUtilities.askAccount(getWindow(), Utilities.parseStringWithoutNull(msg));
    }

    @Override
    public void error(Throwable t)
    {
        if(t instanceof DefaultException)
        {
            if(! (((DefaultException) t).isSerious()))
            {
                log("ERROR : " + t.getMessage());
                return;
            }
        }
        log("ERROR : " + Utilities.toStackTrace(t));
        alert(CommonCore.trans("Error") + " : " + t.getMessage());
    }
}
