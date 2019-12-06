package com.hxg.lib_hrouter.entity;

public class ElementType {
    private Class clazz;
    private String type;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ElementType{" +
                "clazz=" + clazz +
                ", type='" + type + '\'' +
                '}';
    }
}
