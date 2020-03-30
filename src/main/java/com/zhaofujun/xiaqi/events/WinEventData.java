package com.zhaofujun.xiaqi.events;

import com.zhaofujun.xiaqi.core.Camp;
import com.zhaofujun.xiaqi.core.EventData;
import com.zhaofujun.xiaqi.core.Qiju;
import com.zhaofujun.xiaqi.core.Qishou;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WinEventData extends EventData {
    private Qishou qishou;
    private Camp camp;
    private Qiju qiju;

    @Override
    public String toMessageString() {
        return String.format("游戏结束，%s持方%s取得胜利", camp.name(), qishou.getName());
    }
}
