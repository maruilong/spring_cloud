package com.xinyuan.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 保存用户的查询栏目习惯
 *
 * @author liang
 */
@Data
@Entity
@Table(name = "column_habit")
public class ColumnHabit {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "function_id")
    private Long functionId;

    @Column(name = "query")
    private String query;

}
