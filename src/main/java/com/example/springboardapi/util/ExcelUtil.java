package com.example.springboardapi.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


public class ExcelUtil {
    public static List<Map<Object, Object>> readExcel(MultipartFile uploadFile, String ext) {
        List<Map<Object, Object>> list = new ArrayList<>();

        try {
            Workbook workbook = null;
            if (ext.equals("xls")) {
                workbook = new HSSFWorkbook(uploadFile.getInputStream());
            } else if (ext.equals("xlsx")) {
                workbook = new XSSFWorkbook(uploadFile.getInputStream());
            }

            if (workbook != null) {
                int sheets = workbook.getNumberOfSheets();
                getSheet(workbook, sheets, list);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void getSheet(Workbook workbook, int sheets, List<Map<Object, Object>> list) {
        for (int z = 0; z < sheets; z++) {
            Sheet sheet = workbook.getSheetAt(z);
            int rows = sheet.getLastRowNum();
            getRow(sheet, rows, list);
        }
    }

    public static void getRow(Sheet sheet, int rows, List<Map<Object, Object>> list) {
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int cells = row.getPhysicalNumberOfCells(); //getLastCellNum
                list.add(getCell(row, cells));
            }
        }
    }

    public static Map<Object, Object> getCell(Row row, int cells) {
        String[] columns = { "title", "contents", "registId", "tags" };
        Map<Object, Object> map = new HashMap<>();
        for (int j = 0; j < cells; j++) {
            if (j >= columns.length) {
                break;
            }

            Cell cell = row.getCell(j);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BLANK :
                        map.put(columns[j], "");
                        break;
                    case Cell.CELL_TYPE_STRING :
                        map.put(columns[j], cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            map.put(columns[j], cell.getDateCellValue());
                        } else {
                            map.put(columns[j], cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        map.put(columns[j], cell.getErrorCellValue());
                        break;
                    default:
                        map.put(columns[j], "");
                        break;
                }
            } else {
                map.put(columns[j], "");
            }
        }

        return map;
    }
}
