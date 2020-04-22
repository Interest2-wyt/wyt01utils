package com.test.systemprint;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 这种输出方式在测试时可以简单用一下。实际应用中尽量用日志来记录。后期也要学习学习日志的使用
 */

public class SystemPrintTest {
    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream("D:/test.txt"));
        SystemPrintTest test = new SystemPrintTest();
        test.PrintTest1();
        "123".toString();

        test.PrintTest2();
        test.PrintTest3();
        test.PrintTest4();
    }

    public void PrintTest1(){
        System.out.println("{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_pdc\",\"sendTime\":\"2019-07-11T17:02:14.582+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2019-07-11T17:02:14.581+08:00\",\"channelID\":1,\"dataType\":\"flowStatistic\",\"dateTime\":\"2019-07-11T17:23:26.000+08:00\",\"eventDescription\":\"peopleCounting\",\"eventType\":\"peopleCounting\",\"ipAddress\":\"172.27.7.72\",\"peopleCounting\":[{\"enter\":3,\"exit\":1088,\"pass\":35986,\"statisticalMethods\":\"realTime\",\"targetAttrs\":{\"cameraAddress\":\"平山堂东路汉墓附近1\",\"cameraIndexCode\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"cameraName\":\"平山堂东路汉墓附近1\",\"deviceIndexCode\":\"8870bb3539e04e6c94b993ae2996e6cb\",\"deviceName\":\"平山堂东路汉墓附近1\",\"deviceType\":\"102014\",\"latitude\":32.425015,\"longitude\":119.42471,\"recognitionSign\":1,\"regionIndexCode\":\"ba431d2348c24c1a9935cd77c1cc5816\"}}],\"portNo\":8000,\"recvTime\":\"2019-07-11T17:02:14.580+08:00\"},\"eventId\":\"F6287739-8D69-C447-9E2A-B506DAFE10A6\",\"eventType\":131616,\"happenTime\":\"2019-07-11T17:23:26.000+08:00\",\"srcIndex\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"srcType\":\"camera\",\"status\":0,\"timeout\":0}]}}");
    }
    public void PrintTest2(){
        System.out.println("{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_pdc\",\"sendTime\":\"2019-07-11T17:02:14.582+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2019-07-11T17:02:14.581+08:00\",\"channelID\":1,\"dataType\":\"flowStatistic\",\"dateTime\":\"2019-07-11T17:23:26.000+08:00\",\"eventDescription\":\"peopleCounting\",\"eventType\":\"peopleCounting\",\"ipAddress\":\"172.27.7.72\",\"peopleCounting\":[{\"enter\":3,\"exit\":1088,\"pass\":35986,\"statisticalMethods\":\"realTime\",\"targetAttrs\":{\"cameraAddress\":\"平山堂东路汉墓附近1\",\"cameraIndexCode\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"cameraName\":\"平山堂东路汉墓附近1\",\"deviceIndexCode\":\"8870bb3539e04e6c94b993ae2996e6cb\",\"deviceName\":\"平山堂东路汉墓附近1\",\"deviceType\":\"102014\",\"latitude\":32.425015,\"longitude\":119.42471,\"recognitionSign\":1,\"regionIndexCode\":\"ba431d2348c24c1a9935cd77c1cc5816\"}}],\"portNo\":8000,\"recvTime\":\"2019-07-11T17:02:14.580+08:00\"},\"eventId\":\"F6287739-8D69-C447-9E2A-B506DAFE10A6\",\"eventType\":131616,\"happenTime\":\"2019-07-11T17:23:26.000+08:00\",\"srcIndex\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"srcType\":\"camera\",\"status\":0,\"timeout\":0}]}}");
    }
    public void PrintTest3(){
        System.out.println("{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_pdc\",\"sendTime\":\"2019-07-11T17:02:14.582+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2019-07-11T17:02:14.581+08:00\",\"channelID\":1,\"dataType\":\"flowStatistic\",\"dateTime\":\"2019-07-11T17:23:26.000+08:00\",\"eventDescription\":\"peopleCounting\",\"eventType\":\"peopleCounting\",\"ipAddress\":\"172.27.7.72\",\"peopleCounting\":[{\"enter\":3,\"exit\":1088,\"pass\":35986,\"statisticalMethods\":\"realTime\",\"targetAttrs\":{\"cameraAddress\":\"平山堂东路汉墓附近1\",\"cameraIndexCode\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"cameraName\":\"平山堂东路汉墓附近1\",\"deviceIndexCode\":\"8870bb3539e04e6c94b993ae2996e6cb\",\"deviceName\":\"平山堂东路汉墓附近1\",\"deviceType\":\"102014\",\"latitude\":32.425015,\"longitude\":119.42471,\"recognitionSign\":1,\"regionIndexCode\":\"ba431d2348c24c1a9935cd77c1cc5816\"}}],\"portNo\":8000,\"recvTime\":\"2019-07-11T17:02:14.580+08:00\"},\"eventId\":\"F6287739-8D69-C447-9E2A-B506DAFE10A6\",\"eventType\":131616,\"happenTime\":\"2019-07-11T17:23:26.000+08:00\",\"srcIndex\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"srcType\":\"camera\",\"status\":0,\"timeout\":0}]}}");
    }
    public void PrintTest4(){
        System.out.println("{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_pdc\",\"sendTime\":\"2019-07-11T17:02:14.582+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2019-07-11T17:02:14.581+08:00\",\"channelID\":1,\"dataType\":\"flowStatistic\",\"dateTime\":\"2019-07-11T17:23:26.000+08:00\",\"eventDescription\":\"peopleCounting\",\"eventType\":\"peopleCounting\",\"ipAddress\":\"172.27.7.72\",\"peopleCounting\":[{\"enter\":3,\"exit\":1088,\"pass\":35986,\"statisticalMethods\":\"realTime\",\"targetAttrs\":{\"cameraAddress\":\"平山堂东路汉墓附近1\",\"cameraIndexCode\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"cameraName\":\"平山堂东路汉墓附近1\",\"deviceIndexCode\":\"8870bb3539e04e6c94b993ae2996e6cb\",\"deviceName\":\"平山堂东路汉墓附近1\",\"deviceType\":\"102014\",\"latitude\":32.425015,\"longitude\":119.42471,\"recognitionSign\":1,\"regionIndexCode\":\"ba431d2348c24c1a9935cd77c1cc5816\"}}],\"portNo\":8000,\"recvTime\":\"2019-07-11T17:02:14.580+08:00\"},\"eventId\":\"F6287739-8D69-C447-9E2A-B506DAFE10A6\",\"eventType\":131616,\"happenTime\":\"2019-07-11T17:23:26.000+08:00\",\"srcIndex\":\"4c777ca96a1c4eb997ab89caf34f3044\",\"srcType\":\"camera\",\"status\":0,\"timeout\":0}]}}");
    }
}
