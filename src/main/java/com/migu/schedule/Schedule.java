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
    //正在运行的节点编号表
    private List<Integer> workingList = new LinkedList<Integer>();
    //任务消耗对照表
    private Map<Integer,Integer> taskMap = new HashMap<Integer, Integer>();
    private int rule = 0;
    public int init() {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


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

    public int unregisterNode(int nodeId) {

        if (nodeId <= rule)
        {
            return  ReturnCodeKeys.E004;
        }
        if (nodeIdList.isEmpty())
        {
            return ReturnCodeKeys.E007;
        }
        if (nodeIdList.contains(nodeId))
        {
            if ( workingList.contains(nodeId)){
                hangTaskList.add(nodeId);
            }else{
                nodeIdList.remove(nodeId);
            }
            return ReturnCodeKeys.E006;
        }else{
            return ReturnCodeKeys.E007;
        }
    }


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


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
