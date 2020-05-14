package com.phenix.littlechess.sdo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BaseDO implements Serializable {

    private static final long serialVersionUID = 2582987838585486446L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
