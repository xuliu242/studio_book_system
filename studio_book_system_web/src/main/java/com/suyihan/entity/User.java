package com.suyihan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Suyihan
 * @since 2021-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lx_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "用户编号")
    @TableField("lxUserNumber")
    private String lxusernumber;

    @ApiModelProperty(value = "用户名")
    @TableField("lxUsername")
    private String lxusername;

    @ApiModelProperty(value = "用户密码")
    @TableField("lxUserPassword")
    private String lxuserpassword;

    @ApiModelProperty(value = "性别")
    @TableField("lxUserSex")
    private Integer lxusersex;

    @ApiModelProperty(value = "手机号")
    @TableField("lxUserPhone")
    private String lxuserphone;

    @ApiModelProperty(value = "邮箱")
    @TableField("lxUserEmail")
    private String lxuseremail;

    @ApiModelProperty(value = "角色id")
    @TableField("lxRoleId")
    private String lxroleid;


}
