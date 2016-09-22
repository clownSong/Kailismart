package com.kailismart.com.util;

import com.kailismart.com.model.ExportMap;
import org.apache.poi.hssf.usermodel.*;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 宋正根 on 2016/9/19.
 * 对象导出excel
 */
public class ExcelParse {
    // 声明一个工作薄
     private static HSSFWorkbook workbook;
    // 生成一个表格
    private static HSSFSheet sheet;
    // 生成一个样式
    private static HSSFCellStyle style;
//    声明一个行对象
    private static HSSFRow row;
//  声明一个列对象
    private static HSSFCell cell;


    /**
     * 转换对象中指定的属性集合
     * @return 转换后的输出流对象
     */
    public static HSSFWorkbook parseObject(List<ExportMap> exportMaps){
        initUtil();
        Iterator<ExportMap> iterator = exportMaps.iterator();
        ExportMap key = null;
        String[] tempData = null;
        int i = 0;
        //循环列数据
        while (iterator.hasNext()){
            key = iterator.next();
            row = sheet.createRow(i);       //初始化行
            tempData = key.getCell();
            if(tempData != null){
                for (int x = 0; x < tempData.length;x++){
                    cell = row.createCell(x);
                    try {
                        cell.setCellValue(Double.parseDouble(tempData[x]));
                    } catch (NumberFormatException e) {
                        //转换number失败，设置为String类型
                        cell.setCellValue(tempData[x]);
                    }

                }
                i++;
            }
        }
        return workbook;
    }

    public static void initUtil(){
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();
        style = workbook.createCellStyle();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
    }

    public static void closeParse(){
        style = null;
        sheet = null;
        workbook = null;
        System.gc();
    }
}
