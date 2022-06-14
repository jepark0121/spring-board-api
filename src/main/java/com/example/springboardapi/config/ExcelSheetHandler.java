package com.example.springboardapi.config;


import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;

import java.util.List;

public class ExcelSheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    private List<String[]> rows;

    private String[] row;

    private int columnCnt;

    private int currColNum = 0;

    public ExcelSheetHandler(List<String[]> rows, int columnCnt) {
        this.rows = rows;
        this.columnCnt = columnCnt;
    }

    public List<String[]> getRows() {
        return rows;
    }

    @Override
    public void startRow(int rowNum) {
        this.row = new String[columnCnt];
        currColNum = 0;

    }

    @Override
    public void endRow() {
        boolean addFlag = false;
        for(String data: row) {
            if(!"".equals(data)) addFlag = true;
        }

        if(addFlag) rows.add(row);
    }

    @Override
    public void cell(String cellReference, String formattedValue) {
        row[currColNum++] = formattedValue == null ? "" : formattedValue;
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {

    }
}
