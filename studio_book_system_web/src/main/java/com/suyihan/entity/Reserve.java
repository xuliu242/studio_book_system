package com.suyihan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("syh_reserve")
@ApiModel(value="Reserve对象", description="")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "syh_reserve_id", type = IdType.AUTO)
    private Long syhReserveId;

    @ApiModelProperty(value = "用户id")
    private Long syhUserId;

    @ApiModelProperty(value = "工位id")
    private Long syhSitId;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date syhReserveStartTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date syhReserveEndTime;

    @ApiModelProperty(value = "预定结果（1成功，0失败、2已结束）")
    private Integer syhReserveStatus;
    /**
     * 用户名
     */
    @TableField(exist = false)
    private String syhUserName;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @TableField(exist = false)
    private Integer syhUserNumber;
    /**
     * 教室编号
     */
    @TableField(exist = false)
    private String syhClassroomNumber;
    /**
     * 座位名
     */
    @TableField(exist = false)
    private String syhSitName;

    @TableField(exist = false)
    private List<Long> syhSitIds;

}
