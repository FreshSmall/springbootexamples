package org.apache.myfaces.blank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * @author: yinchao
 * @ClassName: Message
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/12 09:41
 */
@Data
@AllArgsConstructor
public class Message {

    private String id;

    private String content;
}
