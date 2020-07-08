package com.test.httpmethod;

import lombok.Data;

/**
 * @ClassName TwoInfo
 * @Description @TODO
 * @Author wangyongtao
 * @Date 2019/12/30 20:09
 * @Version 1.0
 */
@Data
public class TwoInfo {
    public String info1;
    public String info2;

    public TwoInfo(String info1, String info2) {
        this.info1 = info1;
        this.info2 = info2;
    }
}
