package com.hjow.util;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UIUtilities
{
    public static void addActionListener(Button comp, final Runnable event)
    {
        comp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SwingUtilities.invokeLater(event);
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
                SwingUtilities.invokeLater(event);
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
                SwingUtilities.invokeLater(event);
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
                SwingUtilities.invokeLater(event);
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
                SwingUtilities.invokeLater(event);
            }
        });
    }
}
