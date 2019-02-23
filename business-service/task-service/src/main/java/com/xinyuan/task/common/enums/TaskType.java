package com.xinyuan.task.common.enums;

/**
 * The task type.
 *
 * @author liang
 */
public enum TaskType {

    RS_IMPORT_TASK(1, "位点数据导入任务"),

    GENE_IMPORT_TASK(2, "儿童基因数据导入任务"),

    COMPUTER_TASK(3, "成人计算任务"),

    CHILD_COMPUTER_TASK(4, "儿童计算任务"),

    REPORT_TASK(5, "报告生成任务"),

    HTML_REPORT_TASK(6, "报告生成任务");

    private Integer id;

    private String name;

    TaskType(Integer id, String name) {
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

    public static TaskType parse(Integer id) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getId() == id) {
                return taskType;
            }
        }
        return null;
    }
}
