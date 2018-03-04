package com.hjow.util;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.hjow.common.CommonCore;
import com.hjow.common.exception.DefaultException;
import com.hjow.common.ui.HList;

public class UIUtilities
{
    public static final transient Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    public static void defaultTryCatch(final Runnable event)
    {
        try
        {
            event.run();
        }
        catch(Throwable t1)
        {
            CommonCore.error(t1);
        }
    }
    
    public static void centerWindow(Window window)
    {
        Dimension scrSize = TOOLKIT.getScreenSize();
        window.setLocation((int)( scrSize.getWidth()/2 - window.getWidth()/2 ), (int)( scrSize.getHeight()/2 - window.getHeight()/2 ));
    }
    
    public static void addActionListener(Button comp, final Runnable event)
    {
        comp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addActionListener(AbstractButton comp, final Runnable event)
    {
        comp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addActionListener(JMenuItem comp, final Runnable event)
    {
        comp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addActionListener(JTextField comp, final Runnable event)
    {
        comp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addItemListener(JRadioButton comp, final Runnable event)
    {
        comp.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addItemListener(JRadioButtonMenuItem comp, final Runnable event)
    {
        comp.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addItemListener(HList comp, final Runnable event)
    {
        comp.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void addWindowCloseListener(Window comp, final Runnable event)
    {
        comp.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        defaultTryCatch(event);
                    }
                });
            }
        });
    }
    
    public static void setShortcut(JMenuItem item, int keyCode, int modifiers)
    {
        item.setAccelerator(KeyStroke.getKeyStroke(keyCode, modifiers));
    }
    
    public static void initializeArray(JPanel[] arrays)
    {
        for(int idx=0; idx<arrays.length; idx++)
        {
            arrays[idx] = new JPanel();
        }
    }
    
    public static void initializeArray(JLabel[] arrays)
    {
        for(int idx=0; idx<arrays.length; idx++)
        {
            arrays[idx] = new JLabel();
        }
    }
    
    public static void initializeArray(JTextField[] arrays)
    {
        for(int idx=0; idx<arrays.length; idx++)
        {
            arrays[idx] = new JTextField();
        }
    }
    
    public static void addAll(JPanel gridPanel, JPanel[] subPanels)
    {
        for(JPanel c : subPanels)
        {
            gridPanel.add(c);
        }
    }
    
    public static void setLayout(JPanel[] panels, Class<? extends LayoutManager> layout)
    {
        for(JPanel panel : panels)
        {
            try
            {
                panel.setLayout(layout.newInstance());
            }
            catch (Throwable e)
            {
                throw new DefaultException(e);
            }
        }
    }
    
    public static String askPassword(Window window, Object msg)
    {
        String message = "";
        if(msg != null) message = msg.toString();
        
        JPanel masterPanel = new JPanel();
        JPanel[] panels = new JPanel[2];
        initializeArray(panels);
        
        masterPanel.setLayout(new GridLayout(panels.length, 1));
        addAll(masterPanel, panels);
        
        JLabel msgLabel = new JLabel(msg.toString());
        panels[0].add(msgLabel);
        
        JPasswordField passwordField = new JPasswordField(12);
        panels[1].add(passwordField);
        passwordField.requestFocus();
        int sel = JOptionPane.showConfirmDialog(window, masterPanel, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (sel == JOptionPane.OK_OPTION)
        {
            return new String(passwordField.getPassword());
        }
        
        return null;
    }
    
    public static AccountInformation askAccount(Window window, Object msg)
    {
        String message = "";
        if(msg != null) message = msg.toString();
        
        JPanel masterPanel = new JPanel();
        JPanel[] panels = new JPanel[3];
        initializeArray(panels);
        
        masterPanel.setLayout(new GridLayout(panels.length, 1));
        setLayout(panels, FlowLayout.class);
        addAll(masterPanel, panels);
        
        JLabel msgLabel = new JLabel(msg.toString());
        panels[0].add(msgLabel);
        
        JLabel idLabel = new JLabel(CommonCore.trans("ID"));
        panels[1].add(idLabel);
        
        JTextField idField = new JTextField(12);
        panels[1].add(idField);
        
        JLabel pwLabel = new JLabel(CommonCore.trans("Password"));
        panels[2].add(pwLabel);
        
        JPasswordField passwordField = new JPasswordField(12);
        panels[2].add(passwordField);
        
        idField.requestFocus();
        
        int sel = JOptionPane.showConfirmDialog(window, masterPanel, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (sel == JOptionPane.OK_OPTION)
        {
            String id       = idField.getText();
            String password = new String(passwordField.getPassword());
            return new AccountInformation(id, password);
        }
        
        return null;
    }
}
