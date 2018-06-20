package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
*类名和方法不能修改
 */
public class Schedule {
    //服务节点编号表
    private List<Integer> nodeIdList = new LinkedList<Integer>();
    //挂起任务队列
    private List<Integer> hangTaskList = new LinkedList<Integer>();
    //任务消耗对照表
    private Map<Integer,Integer> taskMap = new HashMap<Integer, Integer>();
    //任务状态表
    private Map<Integer,Integer> taskStatusMap = new HashMap<Integer, Integer>();
    //节点运行任务表
    private Map<Integer,List<Integer>> nodeTaskMap = new HashMap<Integer, List<Integer>>();
    private int rule = 0;
    //1
    public int init() {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

    //2
    public int registerNode(int nodeId) {
        if (nodeId <= rule)
        {
            return  ReturnCodeKeys.E004;
        }
        if (nodeIdList.isEmpty())
        {
            nodeIdList.add(nodeId);
            return ReturnCodeKeys.E003;
        }
        if (nodeIdList.contains(nodeId)){
            return ReturnCodeKeys.E005;
        }
        nodeIdList.add(nodeId);
        return ReturnCodeKeys.E003;
    }

    //3
    public int unregisterNode(int nodeId) {

        if (nodeId <= rule)
        {
            return  ReturnCodeKeys.E004;
        }
        if (nodeIdList.isEmpty())
        {
            return ReturnCodeKeys.E007;
        }
        List<Integer> taskList = nodeTaskMap.get("nodeId");
        if (!taskList.isEmpty()){
            for (Integer task:taskList)
            {
                hangTaskList.add(task);
            }
        }
        nodeIdList.remove(nodeId);
        return ReturnCodeKeys.E006;
    }


    //4
    public int addTask(int taskId, int consumption) {
        if (taskId <= rule)
        {
            return  ReturnCodeKeys.E009;
        }
        if ( taskMap.containsKey(taskId))
        {
            return ReturnCodeKeys.E010;
        }
        taskMap.put(taskId,consumption);
        return ReturnCodeKeys.E008;
    }


    //5
    public int deleteTask(int taskId) {
        if (taskId <= rule)
        {
            return  ReturnCodeKeys.E009;
        }
        if (!taskMap.containsKey(taskId)){
            return ReturnCodeKeys.E012;
        }
        taskMap.remove(taskId);
        return ReturnCodeKeys.E011;
    }


    //6
    public int scheduleTask(int threshold) {
        //while (hangTaskList.isEmpty())
        return ReturnCodeKeys.E000;
    }


    //7
    public int queryTaskStatus(List<TaskInfo> tasks) {
        tasks.clear();
        TaskInfo taskInfo;
        for (Integer task:taskStatusMap.keySet())
        {
            Integer node = taskStatusMap.get(task);
            taskInfo = new TaskInfo();
            taskInfo.setNodeId(node);
            taskInfo.setTaskId(task);
            tasks.add(taskInfo);
        }
        if (tasks.isEmpty()){
            return ReturnCodeKeys.E016;
        }
        return ReturnCodeKeys.E015;
    }


}
