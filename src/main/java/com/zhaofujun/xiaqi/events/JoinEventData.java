package com.zhaofujun.xiaqi.events;

import com.zhaofujun.xiaqi.core.Camp;
import com.zhaofujun.xiaqi.core.EventData;
import com.zhaofujun.xiaqi.core.Qiju;
import com.zhaofujun.xiaqi.core.Qishou;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinEventData extends EventData {

    private Qishou qishou;
    private Camp camp;
    private Qiju qiju;



    @Override
    public String toMessageString() {
        return String.format("%s已经就位，他的阵营是%s方",qishou.getName(),camp);
    }
}
