package com.zhaofujun.xiaqi.events;

import com.zhaofujun.xiaqi.core.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayEventData extends EventData {
    private Qishou qishou;
    private Camp camp;
    private int x;
    private int y;
    private Qiju qiju;


    @Override
    public String toMessageString() {
        return String.format("棋手%s，所属阵营%s，落子在（%d,%d）", qishou.getName(), camp.name(), x, y);
    }
}
