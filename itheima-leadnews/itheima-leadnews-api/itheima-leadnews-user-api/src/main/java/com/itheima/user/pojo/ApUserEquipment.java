package com.itheima.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * APP用户设备信息表
 * </p>
 *
 * @author ljh
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_equipment")
@ApiModel(value="ApUserEquipment", description="APP用户设备信息表")
public class ApUserEquipment implements Serializable {


    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "设备ID")
    @TableField("equipment_id")
    private Integer equipmentId;

    @ApiModelProperty(value = "上次使用时间")
    @TableField("last_time")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
