package com.test.activemq.flowstatistic;

import java.util.concurrent.ConcurrentHashMap;

public class FlowStatisticStore {
    {
        for (int i=0;i<17;i++){
            camera[i] = new ConcurrentHashMap<String, FlowStatisticInfo>();
        }
    }
    /**
     * 加一个定时任务，每天早晨八点清空之前的数据
     */

    public static ConcurrentHashMap<String, FlowStatisticInfo>[] camera = new ConcurrentHashMap[17];


    public static int[] hourlyFlowOnMountain = new int[24];

    public static int[] hourlyFlowOnPSTDL = new int[24];

    public static int[] hourlyFlowOnEntryMountain = new int[24];

    public static int[] realTimeKeeps = new int[7];

}
