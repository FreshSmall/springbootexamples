package com.demo.state.machine.dto;

/**
 * @author: yinchao
 * @ClassName: Events
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/4/13 18:23
 */
public enum AgentEvents {


    /**
     * 置闲事件
     */
    FREE(1, "置闲事件"),

    /**
     * 置忙事件
     */
    BUSY(2, "置忙事件"),


    /**
     * 外呼事件
     */
    OUTCALL(4, "外呼事件"),

    /**
     * 响铃事件
     */
    RINGING(5, "响铃事件"),

    /**
     * 双向通话
     */
    TWOWAYCHAT(8, "双向通话"),

    /**
     * 挂断事件
     */
    HANGUP(3, "挂断事件"),

    ;


    AgentEvents(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final int code;
    private final String desc;
}
