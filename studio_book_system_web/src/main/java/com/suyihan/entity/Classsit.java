package com.suyihan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("syh_classsit")
@ApiModel(value="Classsit对象", description="")
public class Classsit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "syh_sit_id", type = IdType.AUTO)
    private Long syhSitId;

    @ApiModelProperty(value = "教室id")
    private Long syhClassroomId;

    @ApiModelProperty(value = "工位名称")
    private String syhSitName;

    @ApiModelProperty(value = "预定状态(1可用，0不可用)")
    private Integer syhSitStatus;

    @ApiModelProperty(value = "添加时间")
    private Date syhSitUpdateTime;


}
