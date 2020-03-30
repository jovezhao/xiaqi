package com.zhaofujun.xiaqi.core;


import com.zhaofujun.xiaqi.events.WinEventData;

import java.util.Observable;
import java.util.Observer;

public class Caipan extends Ren implements Observer {
    public Caipan(String name) {
        super(name);
    }

    //解说
    public void narrate(Qiju qiju) {
        qiju.addObserver(this);
        System.out.println(String.format("大家好，我是%s，本轮%s将由我来为大家解说", getName(), qiju.getQipan().getName()));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (EventData.class.isInstance(arg)) {
            EventData eventData = (EventData) arg;
            System.out.println(getName() + "现场为您解说:" + eventData.toMessageString());


            if(WinEventData.class.isInstance(eventData)){
                //为棋手分配积分

            }
        } else {
            System.out.println(getName() + "：发生了什么？我一脸萌逼");
        }

    }
}

