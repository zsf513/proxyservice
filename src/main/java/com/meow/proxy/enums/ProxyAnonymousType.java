package com.meow.proxy.enums;

public enum ProxyAnonymousType {
    transparent(1, "transparent"),
    anonymous(2, "anonymous"),
    distorting(3, "distorting"),
    elite(4, "elite");

    private int anonymousKey;
    
    private String anonymousType;
    
    public int getAnonymousKey() {
        return anonymousKey;
    }
    
    public String getAnonymousType() {
        return anonymousType;
    }
    
    ProxyAnonymousType(int anonymousKey, String anonymousType) {
        this.anonymousKey = anonymousKey;
        this.anonymousType = anonymousType;
    }
}
