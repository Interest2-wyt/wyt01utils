package com.test.officejava;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Service
public class DateExportService {

    /**
     * 原始excel导出方法
     */
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

    /**
     * 获取要导出的报表对象
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values,HSSFWorkbook wb){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
//        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
//            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 导出报表
     * @param workbook 要导出的报表对象
     * @param excelName 导出的excel名称
     * @param response
     */
    public void exportReportForm(HSSFWorkbook workbook,String excelName,HttpServletResponse response) {
        //4、将excel表通过浏览器输出
        if (workbook!=null){
            OutputStream os = null;
            try {
                response.setContentType("application/binary;charset=ISO8859-1");
                response.setHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode
                        (excelName, "UTF-8") + "\"");
                os = response.getOutputStream();
                workbook.write(os);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (null != os) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            System.out.println("表格为空");
        }
    }


}
