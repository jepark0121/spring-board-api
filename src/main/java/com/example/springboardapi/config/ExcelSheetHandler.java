package com.example.springboardapi.config;


import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;

import java.util.ArrayList;
import java.util.List;

public class ExcelSheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    //header를 제외한 데이터부분
    private List<List<String>> rows = new ArrayList<>();
    //cell 호출시마다 쌓아놓을 1 row List
    private List<String> row = new ArrayList<>();
    //Header 정보를 입력
    private List<String> header = new ArrayList<>();
    //빈 값을 체크하기 위해 사용할 셀번호
    private int currentCol = -1;
    //현재 읽고 있는 Cell의 Col
    private int currRowNum = 0;
    private int count = 0;

    public List<List<String>> getRows() {
        return rows;
    }

    @Override
    public void startRow(int rowNum) {
        //empty 값을 체크하기 위한 초기 셋팅값
        this.currentCol = -1;
        this.currRowNum = rowNum;

    }

    @Override
    public void endRow() {
        if(this.count == 0){
            this.header = new ArrayList(row);
        } else {
            if(row.size() < header.size()) {
                for (int i=row.size(); i<header.size(); i++) {
                    row.add("");
                }
            }
            rows.add(new ArrayList(row));
        }
        row.clear();
        this.count++;
    }

    @Override
    public void cell(String cellReference, String formattedValue) {
        System.out.println(cellReference);
        //row[currColNum++] = formattedValue == null ? "" : formattedValue;
        int iCol = (new CellReference(cellReference)).getCol();
        int emptyCol = iCol - currentCol - 1;

        //읽은 Cell의 번호를 이용하여 빈Cell 자리에 빈값을 강제로 저장시켜줌
        for(int i = 0 ; i < emptyCol ; i++) {
            row.add("");
        }
        currentCol = iCol;
        row.add(formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {

    }
}
