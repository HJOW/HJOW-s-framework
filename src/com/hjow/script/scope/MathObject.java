package com.hjow.script.scope;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import com.hjow.common.ui.UI;
import com.hjow.script.DefaultScriptEngine;

public class MathObject extends ScriptObject
{
    public MathObject(UI ui, DefaultScriptEngine engine)
    {
        super(ui, engine, "math", ACTION_PUT_GLOBAL);
    }
    
    private double t(Object obj)
    {
        return Double.parseDouble(String.valueOf(obj));
    }

    public Double abs(Object obj)
    {
        return new Double(Math.abs(t(obj)));
    }
    
    public Double pow(Object one, Object two)
    {
        return new Double(Math.pow(t(one), t(two)));
    }
    
    public Double sqrt(Object obj)
    {
        return new Double(Math.sqrt(t(obj)));
    }
    
    public Double round(Object obj)
    {
        return new Double(Math.round(t(obj)));
    }
    
    public Double floor(Object obj)
    {
        return new Double(Math.floor(t(obj)));
    }
    
    public Double ceil(Object obj)
    {
        return new Double(Math.ceil(t(obj)));
    }
    
    public Double exp(Object obj)
    {
        return new Double(Math.exp(t(obj)));
    }
    
    public Double expm1(Object obj)
    {
        return new Double(Math.expm1(t(obj)));
    }
    
    public Double sin(Object obj)
    {
        return new Double(Math.sin(t(obj)));
    }
    
    public Double cos(Object obj)
    {
        return new Double(Math.cos(t(obj)));
    }
    
    public Double tan(Object obj)
    {
        return new Double(Math.tan(t(obj)));
    }
    
    public Double asin(Object obj)
    {
        return new Double(Math.asin(t(obj)));
    }
    
    public Double acos(Object obj)
    {
        return new Double(Math.acos(t(obj)));
    }
    
    public Double atan(Object obj)
    {
        return new Double(Math.atan(t(obj)));
    }
    
    public Double sinh(Object obj)
    {
        return new Double(Math.sinh(t(obj)));
    }
    
    public Double cosh(Object obj)
    {
        return new Double(Math.cosh(t(obj)));
    }
    
    public Double log(Object obj)
    {
        return new Double(Math.log(t(obj)));
    }
    
    public Double log10(Object obj)
    {
        return new Double(Math.log10(t(obj)));
    }
    
    public Double log1p(Object obj)
    {
        return new Double(Math.log1p(t(obj)));
    }
    
    public Double getExponent(Object obj)
    {
        return new Double(Math.getExponent(t(obj)));
    }
    
    public Double cbrt(Object obj)
    {
        return new Double(Math.cbrt(t(obj)));
    }
    
    public Double signum(Object obj)
    {
        return new Double(Math.signum(t(obj)));
    }
    
    public Double hypot(Object one, Object two)
    {
        return new Double(Math.hypot(t(one), t(two)));
    }
    
    public Double IEEERemainder(Object one, Object two)
    {
        return new Double(Math.IEEEremainder(t(one), t(two)));
    }
    
    public Double atan2(Object one, Object two)
    {
        return new Double(Math.atan2(t(one), t(two)));
    }
    
    public Double copySign(Object one, Object two)
    {
        return new Double(Math.copySign(t(one), t(two)));
    }
    
    public Double nextAfter(Object one, Object two)
    {
        return new Double(Math.nextAfter(t(one), t(two)));
    }
    
    public Double random()
    {
        return new Double(Math.random());
    }
    
    public Long randomInt()
    {
        return new Random().nextLong();
    }
    
    public Double pi()
    {
        return new Double(Math.PI);
    }
    
    public Double e()
    {
        return new Double(Math.E);
    }
    
    public BigInteger bigint(Object obj)
    {
        return new BigInteger(defaultStringTrans(obj));
    }
    
    public BigInteger biginteger(Object obj)
    {
        return bigint(obj);
    }
    
    public BigDecimal bigdecimal(Object obj)
    {
        return new BigDecimal(defaultStringTrans(obj));
    }
}
