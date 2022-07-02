/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.example.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author huangmengxue
 * @description
 * @team wuhan operational dev.
 * @date 2021/4/26 9:56 上午
 **/
@Data
@TableName("tmk_crm_predict_task_log")
public class PredictTaskLogEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long taskId;
    private String taskName;
    private Integer taskStatus;
    private String taskDescription;
    private Integer calledCount;
    private Integer answerCount;
    private Integer bridgeCount;
    private Integer numberCount1;
    private Integer numberCount2;
    private Integer numberCount3;
    private Integer numberCountGt3;
    private Integer harassCount;
    private Integer telRetryRound;
    private Date startTime;
    private Date endTime;
    private Integer duration;
    private Integer runDuration;
    private Integer harassRate;
    private Integer answerRate;
    private Integer agentAnswerRate;
    private Integer answerSeconds;
    private Date createTime;
    private Date updateTime;
    private String sceneId;
    @TableField(exist = false)
    private String durationStr;
    @TableField(exist = false)
    private String runDurationStr;
    @TableField(exist = false)
    private Integer answerMinutes;
    @TableField(exist = false)
    private String harassRateStr;
    @TableField(exist = false)
    private String answerRateStr;
    @TableField(exist = false)
    private String agentAnswerRateStr;

}
