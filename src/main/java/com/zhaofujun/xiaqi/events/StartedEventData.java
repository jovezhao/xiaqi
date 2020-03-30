package com.zhaofujun.xiaqi.events;

import com.zhaofujun.xiaqi.core.EventData;
import com.zhaofujun.xiaqi.core.Qiju;
import com.zhaofujun.xiaqi.core.Qipan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class StartedEventData extends EventData {

    private Qiju qiju;


    @Override
    public String toMessageString() {
        return String.format("游戏开始，本局游戏内容%s，%s持%s方，%s持%s方", qiju.getQipan().getName(),
                qiju.getWanjiaList().get(0).getQishou().getName(), qiju.getWanjiaList().get(0).getCamp().name(),
                qiju.getWanjiaList().get(1).getQishou().getName(), qiju.getWanjiaList().get(1).getCamp().name()
        );
    }
}
