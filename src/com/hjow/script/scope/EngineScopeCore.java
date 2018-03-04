package com.hjow.script.scope;

import com.hjow.common.ui.UI;
import com.hjow.script.DefaultScriptEngine;

public class EngineScopeCore
{
    public static void init(DefaultScriptEngine engine, UI ui)
    {
        new BasicObject(ui, engine);
        new MathObject(ui, engine);
    }
}
