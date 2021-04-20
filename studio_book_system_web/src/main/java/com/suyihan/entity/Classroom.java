package com.suyihan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("syh_classroom")
@ApiModel(value="Classroom对象", description="")
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "syh_classroom_id", type = IdType.AUTO)
    private Long syhClassroomId;

    @ApiModelProperty(value = "教室号	")
    private String syhClassroomNumber;

    @ApiModelProperty(value = "教室名称")
    private String syhClassroomName;

    @ApiModelProperty(value = "简介")
    private String syhClassroomInfo;

    @ApiModelProperty(value = "教室类型：大教室，小教室，中型教室")
    private String syhClassroomType;

    @ApiModelProperty(value = "教室工位数量")
    private Integer syhClassroomSitNumber;
    /**
     * 可预订座位数量
     */
    @TableField(exist = false)
    private  Integer reserveAbleSitNum;


}
