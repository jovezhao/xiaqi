package com.zhaofujun.xiaqi.core;

import lombok.Getter;

@Getter
public class Qishou extends Ren {
    private int source;


    public Qishou(String name,int source) {
        super(name);
        this.source = source;
    }

    public void addSource(int value) {
        //加分
        this.source = this.source + value;
    }

    public void subtractSource(int value) {
        //减分
        this.source = this.source - value;
    }
}
