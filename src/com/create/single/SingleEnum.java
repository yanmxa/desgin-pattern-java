package com.create.single;

public enum SingleEnum {
    INSTANCE;
    public SingleEnum getInstance(){
        return INSTANCE;
    }
}
