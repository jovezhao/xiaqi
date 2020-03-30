package com.zhaofujun.xiaqi.qipans;

import com.zhaofujun.xiaqi.core.Camp;
import com.zhaofujun.xiaqi.core.Qipan;

import java.util.List;

public class Wuziqi extends Qipan {

    @Override
    public String getName() {
        return "五子棋";
    }

    @Override
    public boolean decide(Camp camp) {
        List<Qizi> qiziList = getPanmian().getQiziList();//.stream().filter(p->p.getCamp().equals(camp)).collect(Collectors.toList());
        //按五子棋的规则计算是否获胜，这里模拟任意一方包括了(1,1)和(1,2)就可以获胜
        if (qiziList.contains(new Qizi(camp, 1, 1)) && qiziList.contains(new Qizi(camp, 1, 2)))
            return true;
        return false;
    }
}

