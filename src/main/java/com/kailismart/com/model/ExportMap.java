package com.kailismart.com.model;

import com.kailismart.com.util.ExcelParse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 宋正根 on 2016/9/20.
 * 导出excel model
 */
public class ExportMap {
    private List<ExportMap> cells;
    private String key;
    private String[] cell;

    public String[] getCell() {
        return cell;
    }

    public void setCell(String[] cell) {
        this.cell = cell;
    }

    public List<ExportMap> getCells() {
        return cells;
    }

    public void setCells(List<ExportMap> cells) {
        this.cells = cells;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
