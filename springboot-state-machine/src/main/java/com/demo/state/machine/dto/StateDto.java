package com.demo.state.machine.dto;

import lombok.Data;

/**
 * @author: yinchao
 * @ClassName: StateDto
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/21 14:53
 */
@Data
public class StateDto {

    private String cno;
    private String businessId;
    private int state;
}
