package com.zhaofujun.xiaqi.core;

import com.zhaofujun.xiaqi.events.PlayEventData;
import lombok.Getter;

import java.util.*;

public abstract class Qipan implements Guize {

    @Getter
    public class Qizi {
        private Camp camp;
        private int x;
        private int y;

        public Qizi(Camp camp, int x, int y) {
            this.camp = camp;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Qizi qizi = (Qizi) o;
            return x == qizi.x &&
                    y == qizi.y &&
                    camp == qizi.camp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(camp, x, y);
        }
    }

    public class QipanObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    }

    public class Panmian {
        private List<Qipan.Qizi> qiziList = new ArrayList<>();

        public List<Qipan.Qizi> getQiziList() {
            return qiziList;
        }
    }

    private QipanObservable observable = new QipanObservable();

    private Panmian panmian = new Panmian();

    public abstract String getName();

    public Panmian getPanmian() {
        return panmian;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    public void addQizi(Qishou qishou, Camp camp, int x, int y) {
        panmian.getQiziList().add(new Qizi(camp, x, y));

        observable.notifyObservers(new PlayEventData(qishou, camp, x, y, this));
    }
}

interface Guize {
    boolean decide(Camp camp);
}





