package com.migu.schedule;

import com.migu.schedule.info.TaskInfo;

import java.util.Comparator;

public class CompareTask implements Comparator<TaskInfo> {

    public int compare(TaskInfo o1, TaskInfo o2) {
        return o1.getNodeId()-o2.getNodeId();
    }
}
