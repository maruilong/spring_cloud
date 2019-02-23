package com.xinyuan.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "key_depot")
public class KeyDepot {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "prefix")
    private Long prefix;

    @Column(name = "value")
    private Long value;

    @Column(name = "c_id")
    private Long cId;
}
