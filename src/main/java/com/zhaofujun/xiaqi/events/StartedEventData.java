package com.zhaofujun.xiaqi.events;

import com.zhaofujun.xiaqi.core.EventData;
import com.zhaofujun.xiaqi.core.Qiju;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class StartedEventData extends EventData {

    private Qiju qiju;


    @Override
    public String toMessageString() {
        return String.format("游戏开始，本局游戏内容%s，分值%d，%s持%s方，%s持%s方", qiju.getQipan().getName(), qiju.getSourceValue(),
                qiju.getWanjiaList().get(0).getQishou().getName(), qiju.getWanjiaList().get(0).getCamp().name(),
                qiju.getWanjiaList().get(1).getQishou().getName(), qiju.getWanjiaList().get(1).getCamp().name()
        );
    }
}
