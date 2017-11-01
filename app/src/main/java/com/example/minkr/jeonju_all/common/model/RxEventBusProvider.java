package com.example.minkr.jeonju_all.common.model;


import com.example.minkr.jeonju_all.util.Logger;

public class RxEventBusProvider {
    private static final RxEventBus rxEventBus = new RxEventBus();

    public static RxEventBus provide(){
        Logger.log("event bus hashcode -> " + rxEventBus.hashCode());
        return rxEventBus;
    }
}
