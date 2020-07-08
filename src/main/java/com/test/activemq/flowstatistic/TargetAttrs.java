package com.test.activemq.flowstatistic;



public class TargetAttrs {
    public String cameraAddress;
    public String cameraIndexCode;
    public String cameraName;
    public String deviceIndexCode;
    public String deviceName;
    public String deviceType;
    public String recognitionSign;
    public String regionIndexCode;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCameraAddress() {
        return cameraAddress;
    }

    public void setCameraAddress(String cameraAddress) {
        this.cameraAddress = cameraAddress;
    }

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getDeviceIndexCode() {
        return deviceIndexCode;
    }

    public void setDeviceIndexCode(String deviceIndexCode) {
        this.deviceIndexCode = deviceIndexCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getRecognitionSign() {
        return recognitionSign;
    }

    public void setRecognitionSign(String recognitionSign) {
        this.recognitionSign = recognitionSign;
    }

    public String getRegionIndexCode() {
        return regionIndexCode;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public TargetAttrs() {
    }

    public TargetAttrs(String cameraAddress, String cameraIndexCode, String cameraName, String deviceIndexCode, String deviceName,String deviceType, String recognitionSign, String regionIndexCode) {
        this.cameraAddress = cameraAddress;
        this.cameraIndexCode = cameraIndexCode;
        this.cameraName = cameraName;
        this.deviceIndexCode = deviceIndexCode;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.recognitionSign = recognitionSign;
        this.regionIndexCode = regionIndexCode;
    }
}
