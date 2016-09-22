package com.kailismart.com.controller;

import com.kailismart.com.model.ExportMap;
import com.kailismart.com.util.ExcelParse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by 宋正根 on 2016/9/20.
 * 导出excel控制器
 */
@Controller
@RequestMapping("/managent")
public class ExportController {

    @RequestMapping("/exportExcel")
    public void exportExcel(ExportMap map ,HttpServletResponse response){
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            HSSFWorkbook hssfWorkbook = ExcelParse.parseObject(map.getCells());
            response.reset();       //清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + new String((map.getKey()+".xls").getBytes("GB2312"),"ISO8859-1"));
            // 定义输出类型
            response.setContentType("application/vnd.ms-excel");
            hssfWorkbook.write(out);
            out.flush();
            out.close();
            ExcelParse.closeParse();
        } catch (IOException e) {
            if(out != null){
                try {
                    out.print("下载异常");
                    out.flush();
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }

    }

}
