package com.suyihan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QueryClasssitCondition {
    private String syhClasssitName;    //教室名
    private Long syhClassroomId;  //教室id
    private Integer pageSize;   //当前页数据最大条数
    private Integer pageNum;     //当前页码
}
