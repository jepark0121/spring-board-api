package com.example.springboardapi.util;

import com.example.springboardapi.config.ExcelSheetHandler;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;


public class ExcelUtil {
    public static List<Map<Object, Object>> readExcel(MultipartFile uploadFile, String ext) throws IOException {
        List<Map<Object, Object>> list = new ArrayList<>();

        try {
            OPCPackage opcPackage = OPCPackage.open(uploadFile.getInputStream());
            XSSFReader xssfReader = new XSSFReader(opcPackage);
            XSSFReader.SheetIterator iterator = (XSSFReader.SheetIterator) xssfReader.getSheetsData();

            StylesTable stylesTable = xssfReader.getStylesTable();
            ReadOnlySharedStringsTable data = new ReadOnlySharedStringsTable(opcPackage);

            List<String[]> dataList = new ArrayList<>();


            while (iterator.hasNext()) {
                //엑셀의 첫번째 sheet정보만 읽어오기 위해 사용 만약 다중 sheet 처리를 위해서는 반복문 필요
                InputStream sheetStream = iterator.next();
                InputSource sheetSource = new InputSource(sheetStream);

                ExcelSheetHandler excelSheetHandler = new ExcelSheetHandler(dataList, 4);
                ContentHandler contentHandler = new XSSFSheetXMLHandler(stylesTable, data, excelSheetHandler, false);

                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();

                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(contentHandler);
                xmlReader.parse(sheetSource);

                sheetStream.close();

                List<String[]> rows = excelSheetHandler.getRows();
                for(int j=1; j<rows.size(); j++) {
                    list.add(getCell(rows.get(j)));
                }
            }
            opcPackage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static Map<Object, Object> getCell(String[] rows) {
        String[] columns = { "title", "contents", "registId", "tags" };
        Map<Object, Object> map = new HashMap<>();
        for (int j = 0; j < rows.length; j++) {
            if (j >= columns.length) {
                break;
            }

            if (rows[j] == null) {
                map.put(columns[j], "");
            } else {
                map.put(columns[j], rows[j]);
            }
        }

        return map;
    }
}
