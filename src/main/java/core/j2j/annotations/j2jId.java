package main.java.core.j2j.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface j2jId
{
    public String setMethod();

    public String getMethod();
}
