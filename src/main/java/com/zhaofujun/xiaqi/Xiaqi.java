package com.zhaofujun.xiaqi;

import com.zhaofujun.xiaqi.core.*;
import com.zhaofujun.xiaqi.qipans.Wuziqi;

public class Xiaqi {
    public static void main(String[] args) {
        Qishou qishouA = new Qishou("棋手小赵");
        Qishou qishouB = new Qishou("棋手小罗");
        Caipan caipanA = new Caipan("裁判小祖宗");

        Qipan qipan = new Wuziqi();
        Qiju qiju = new Qiju(qipan);

        caipanA.narrate(qiju);

        qiju.join(qishouA, Camp.Black);
        qiju.join(qishouB, Camp.White);
        qiju.start();

    }
}
