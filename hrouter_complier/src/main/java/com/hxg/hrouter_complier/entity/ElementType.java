package com.hxg.hrouter_complier.entity;

public class ElementType {
    private String clazz;
    private String type;
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
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
                ", clazz='" + clazz + '\'' +
                ", type=" + type +
                '}';
    }
}
