package com.zhaofujun.xiaqi.core;

import com.zhaofujun.xiaqi.events.JoinEventData;
import com.zhaofujun.xiaqi.events.StartedEventData;
import com.zhaofujun.xiaqi.events.WinEventData;
import com.zhaofujun.xiaqi.tools.IOTools;
import com.zhaofujun.xiaqi.tools.Zuobiao;
import lombok.Getter;

import java.util.*;

@Getter
public class Qiju {

    public class QijuObservable extends Observable {
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


    QijuObservable observable = new QijuObservable();

    public Qiju(Qipan qipan) {
        this.qipan = qipan;
        wanjiaList = new ArrayList<>();
    }

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
        observable.notifyObservers(new JoinEventData(qishou, camp,this));
    }

    public void start() {
        if (this.wanjiaList.size() != 2) throw new RuntimeException("人还没到位，再等等吧");
        this.status = 1;
        observable.notifyObservers(new StartedEventData(this));

        while (status == 1) {
            Wanjia wanjia = wanjiaList.get(stepCount % 2);
            Zuobiao zuobiao = IOTools.getZuobiao(wanjia.getQishou().getName());
            if (zuobiao == null) zuobiao = IOTools.getZuobiao(wanjia.getQishou().getName());
            play(wanjia, zuobiao.getX(), zuobiao.getY());
            stepCount++;

        }

    }


    public void end() {
        this.status = 2;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
        qipan.addObserver(observer);
    }

    public void play(Wanjia wanjia, int x, int y) {
        qipan.addQizi(wanjia.qishou, wanjia.getCamp(), x, y);

        if (qipan.decide(wanjia.getCamp())) {
            status = 2;
            observable.notifyObservers(new WinEventData(wanjia.qishou, wanjia.camp, this));
        }
    }

}
