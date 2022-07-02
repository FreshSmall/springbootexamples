/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.example.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author huangmengxue
 * @description
 * @team wuhan operational dev.
 * @date 2021/7/7 4:49 下午
 **/
@Data
@TableName("tmk_crm_predict_import_record")
public class PredictImportRecordEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String sceneId;
    private Long predictTaskId;
    // 0任务创建中，1任务创建完成
    private Integer isFinish;
    private String uData;
    private Date createTime;
    private Date updateTime;
}
