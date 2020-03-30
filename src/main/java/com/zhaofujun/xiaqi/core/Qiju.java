package com.zhaofujun.xiaqi.core;

import com.zhaofujun.xiaqi.events.JoinEventData;
import com.zhaofujun.xiaqi.events.PlayEventData;
import com.zhaofujun.xiaqi.events.StartedEventData;
import com.zhaofujun.xiaqi.events.WinEventData;
import com.zhaofujun.xiaqi.tools.IOTools;
import com.zhaofujun.xiaqi.tools.Zuobiao;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
public class Qiju {

    @AllArgsConstructor
    class QijuObservable extends Observable {
        private Qiju source;
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    }

    @Getter
    public class Wanjia {
        private Qishou qishou;
        private Camp camp;

        public Wanjia(Qishou qishou, Camp camp) {
            this.qishou = qishou;
            this.camp = camp;
        }


    }


    private QijuObservable observable = new QijuObservable(this);

    public Qiju(Qipan qipan, int sourceValue) {
        this.qipan = qipan;
        this.sourceValue = sourceValue;
        wanjiaList = new ArrayList<>();
    }

    private int sourceValue;
    private List<Wanjia> wanjiaList;
    private Qipan qipan;
    // 0 未开始  1 进行中 2 结束
    private int status;
    private int stepCount;

    public void join(Qishou qishou, Camp camp) {
        if (status == 1) throw new RuntimeException("本局已经开始啦");
        if (status == 2) throw new RuntimeException("游戏已经结束");


        if (wanjiaList.stream().filter(p -> p.getCamp().equals(camp)).count() > 0)
            throw new RuntimeException("座位已经有人了");
        wanjiaList.add(new Wanjia(qishou, camp));
        observable.notifyObservers(new JoinEventData(qishou, camp, this));
    }

    public void start() {
        if (this.wanjiaList.size() != 2) throw new RuntimeException("人还没到位，再等等吧");
        this.status = 1;
        observable.notifyObservers(new StartedEventData(this));

        while (status == 1) {
            Wanjia wanjia = wanjiaList.get(stepCount % 2);
            Zuobiao zuobiao = IOTools.getZuobiao(wanjia.getQishou().getName());
            play(wanjia, zuobiao.getX(), zuobiao.getY());
            stepCount++;

        }

    }


    public void end(Qishou qishou, Camp camp) {
        this.status = 2;
        observable.notifyObservers(new WinEventData(qishou, camp, this));
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
        qipan.addObserver(observer);
    }

    private void play(Wanjia wanjia, int x, int y) {
        qipan.addQizi(wanjia.qishou, wanjia.getCamp(), x, y);

        observable.notifyObservers(new PlayEventData(wanjia.qishou, wanjia.camp, x, y, this));

    }

}

