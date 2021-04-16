package com.suyihan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
 * @since 2021-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("syh_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "syh_menu_id", type = IdType.AUTO)
    private Long syhMenuId;

    @ApiModelProperty(value = "菜单名")
    private String syhMenuName;

    @ApiModelProperty(value = "菜单类型：1菜单，2功能")
    private String syhMenuType;

    @ApiModelProperty(value = "父级菜单id")
    private Long syhMenuParentId;

    @ApiModelProperty(value = "菜单状态")
    private Integer syhMenuStatus;

    @ApiModelProperty(value = "菜单路径")
    private String syhMenuUrl;

    @ApiModelProperty(value = "功能标识")
    private String syhMenuOpcode;

    @ApiModelProperty(value = "描述")
    private String syhMenuDetail;

    @ApiModelProperty(value = "排序")
    private Integer syhMenuQueue;

    @TableField(exist = false)
    private List<Menu> children;


}
