package com.xiaosong.immichdemo.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    public CustomPhysicalNamingStrategy() { System.out.println("CustomPhysicalNamingStrategy Loaded!"); }
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        System.out.println("CustomPhysicalNamingStrategy.toPhysicalColumnName: " + name);
        return name; // This keeps the original column name as is
    }
}
