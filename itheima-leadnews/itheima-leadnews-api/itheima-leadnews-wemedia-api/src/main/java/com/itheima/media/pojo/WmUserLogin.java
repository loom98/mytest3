package com.itheima.media.pojo;

import java.math.BigDecimal;
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
 * 自媒体用户登录行为信息表
 * </p>
 *
 * @author ljh
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_user_login")
@ApiModel(value="WmUserLogin", description="自媒体用户登录行为信息表")
public class WmUserLogin implements Serializable {


    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "登录设备ID")
    @TableField("equipment_id")
    private Integer equipmentId;

    @ApiModelProperty(value = "登录IP")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "登录地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
