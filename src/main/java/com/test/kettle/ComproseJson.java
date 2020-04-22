package com.test.kettle;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *@ClassName ComproseJson
 *@Description kettle类编辑
 *@Author wangyongtao
 *@Date 2020/4/15 20:18
 *@Version 1.0
 */
public class ComproseJson {
    public static void main(String[] args) {
        try{

        }catch (Exception e){
            e.getMessage();
        }
        String sendTime = null;
        String channelId = null;
        String dataType = null;
        String dataTime = null;
        String eventDescription = null;
        String eventType = null;
        String ipAddress = null;
        String portNo = null;
        String recvTime = null;

        String areaCode = null;
        String cameraAddress = null;
        String cameraIndexCode = null;
        String cameraName = null;
        String cascade = null;
        Integer crossingId = null;
        String crossingIndexCode = null;
        String deviceIndexCode = null;
        String deviceName = null;
        String deviceType = null;
        String directionIndex = null;
        String imageServerCode = null;
        Integer laneNo = null;
        Integer multiVehicle = null;
        String passID = null;
        String passTime = null;
        String platePicUrl = null;
        Integer recognitionSign = null;
        String regionIndexCode = null;
        String vehicleColorDepth = null;
        Integer vehicleLen = null;
        Integer vehicleSpeed = null;
        String plateNo = null;
        String plateColor = null;
        String plateType = null;
        String vehicleType = null;

        String targetPicUrl = null;
        String taskID = null;

        JSONObject jplateNo = new JSONObject();
        jplateNo.put("confidence",90);
        jplateNo.put("value",plateNo);
        JSONObject jplateColor = new JSONObject();
        jplateColor.put("value",plateColor);
        JSONObject jplateType = new JSONObject();
        jplateType.put("value",plateType);
        JSONObject jvehicleType = new JSONObject();
        jvehicleType.put("value",vehicleType);

        JSONArray target = new JSONArray();
        JSONObject subTarget = new JSONObject();
        subTarget.put("plateNo",jplateNo);
        subTarget.put("plateColor",jplateColor);
        subTarget.put("plateType",jplateType);
        subTarget.put("vehicleType",jvehicleType);
        target.add(subTarget);

        JSONObject targetAttrs = new JSONObject();
        targetAttrs.put("areaCode",areaCode);
        targetAttrs.put("cameraAddress",cameraAddress);
        targetAttrs.put("cameraIndexCode",cameraIndexCode);
        targetAttrs.put("cameraName",cameraName);
        targetAttrs.put("cascade",cascade);
        targetAttrs.put("crossingId",crossingId);
        targetAttrs.put("crossingIndexCode",crossingIndexCode);
        targetAttrs.put("deviceIndexCode",deviceIndexCode);
        targetAttrs.put("deviceName",deviceName);
        targetAttrs.put("deviceType",deviceType);
        targetAttrs.put("directionIndex",directionIndex);
        targetAttrs.put("imageServerCode",imageServerCode);
        targetAttrs.put("laneNo",laneNo);
        targetAttrs.put("multiVehicle",multiVehicle);
        targetAttrs.put("passID",passID);
        targetAttrs.put("passTime",passTime);
        targetAttrs.put("platePicUrl",platePicUrl);
        targetAttrs.put("recognitionSign",recognitionSign);
        targetAttrs.put("regionIndexCode",regionIndexCode);
        targetAttrs.put("vehicleColorDepth",vehicleColorDepth);
        targetAttrs.put("vehicleLen",vehicleLen);
        targetAttrs.put("vehicleSpeed",vehicleSpeed);

        JSONArray vehicleRcogResult = new JSONArray();
        JSONObject subVehicleRcogResult = new JSONObject();
        subVehicleRcogResult.put("target",target);
        subVehicleRcogResult.put("targetAttrs",targetAttrs);
        subVehicleRcogResult.put("targetPicUrl",targetPicUrl);
        subVehicleRcogResult.put("taskID",taskID);
        vehicleRcogResult.add(subVehicleRcogResult);

        JSONObject root = new JSONObject();
        root.put("sendTime",sendTime);
        root.put("channelID",channelId);
        root.put("dataType",dataType);
        root.put("dateTime",dataTime);
        root.put("eventDescription",eventDescription);
        root.put("eventType",eventType);
        root.put("ipAddress",ipAddress);
        root.put("portNo",portNo);
        root.put("recvTime",recvTime);
        root.put("vehicleRcogResult",vehicleRcogResult);
        root.put("sendTime",sendTime);

        String vehicleInfo = root.toJSONString();


        System.out.println(vehicleInfo);
    }

    public String getDate(){
        DateTimeFormatter isoformat =  DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString(isoformat)+"+08:00";
    }
    
                        
}
