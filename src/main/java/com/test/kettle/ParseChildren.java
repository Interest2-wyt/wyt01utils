package com.test.kettle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class ParseChildren {
    public static final ArrayList<ArrayList<String>> parseChildren(String json){
        //resultTables用来存放所有的行,每行是一个节点解析出来的值
        ArrayList<ArrayList<String>> resultTables=new ArrayList<ArrayList<String>>();
        JSONObject root=JSONObject.parseObject(json);
        String id= root.getString("id");
        String name= root.getString("name");
        int age= root.getIntValue("age");
        JSONArray children=root.getJSONArray("children");
        ArrayList<String> resultRow=new ArrayList<>();
        resultRow.add(id);
        resultRow.add(name);
        resultRow.add(String.valueOf(age));
        resultTables.add(resultRow);
        if(children!=null&&children.size()>0) {
            for(int i=0;i<children.size();i++) {
                String childrenString= children.getString(i);
                ArrayList<ArrayList<String>> childrenTables=parseChildren(childrenString);
                resultTables.addAll(childrenTables);
            }
        }
        return resultTables;
    }

    public static void main(String[] args) {

    }

}
