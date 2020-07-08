package com.test.officejava;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class DateExportService {


    @Test
    public void getDateExport(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表单
        HSSFSheet sheet = workbook.createSheet("人脸统计报表");
        //遍历查询结果、创建行与单元格，并赋值
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int i=0;i<10;i++){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue("标题");
            cell = row.createCell(1);
            cell.setCellValue(i);
        }
        //输出Excel文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("d:\\logs\\workbook.xls");
            workbook.write(output);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        return null;
    }

}
