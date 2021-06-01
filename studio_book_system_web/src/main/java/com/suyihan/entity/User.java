package com.suyihan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
 * @since 2021-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("syh_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "syh_user_id", type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long syhUserId;

    @ApiModelProperty(value = "账号")
        private Integer syhUserNumber;

    @ApiModelProperty(value = "姓名")
    private String syhUserName;

    @ApiModelProperty(value = "密码")
    private String syhPassword;

    @ApiModelProperty(value = "电话")
    private String syhPhone;

    @ApiModelProperty(value = "邮箱")
    private String syhEmail;

    @ApiModelProperty(value = "1男0女")
    private Integer syhSex;

    @ApiModelProperty(value = "注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date syhRegisterTime;

    @ApiModelProperty(value = "状态（1启用，0禁用）")
    private Integer syhStatus;


}
