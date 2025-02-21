package com.demo.state.machine.dto;

import lombok.Getter;

/**
 * @author: yinchao
 * @ClassName: States
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/4/13 18:22
 */
@Getter
public enum AgentStates {

    /**
     * 失效
     */
    INVALID(-1, "失效"),
    /**
     * 离线
     */
    OFFLINE(0, "离线"),
    /**
     * 空闲
     */
    FREE(1, "空闲"),
    /**
     * 置忙
     */
    BUSY(2, "置忙"),
    /**
     * 整理
     */
    READY(3, "整理"),
    /**
     * 呼叫中
     */
    CALLING(4, "呼叫中"),
    /**
     * 响铃
     */
    RINGING(5, "响铃"),
    /**
     * 保持
     */
    HOLDING(6, "保持"),
    /**
     * 通话
     */
    CHAT(7, "通话"),

    /**
     * 双向通话
     */
    TWOWAYCHAT(8, "双向通话"),

    /**
     * 外呼异常
     */
    FailCALL(-2, "外呼异常");

    /**
     * code
     */
    private final int code;
    /**
     * desc
     */
    private final String desc;

    AgentStates(final Integer code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

}
