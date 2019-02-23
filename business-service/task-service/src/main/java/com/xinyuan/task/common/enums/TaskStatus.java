
package com.xinyuan.task.common.enums;


/**
 * the task status
 *
 * @author liang
 */

public enum TaskStatus {

    SUBMITTED(1, "已提交"),
    RUNNING(2, "运行中"),
    SUCCESS(3, "已成功"),
    FAILED(4, "已失败");

    private Integer id;

    private String name;

    TaskStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TaskStatus parse(Integer id) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.getId() == id) {
                return taskStatus;
            }
        }
        return null;
    }

}
