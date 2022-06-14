package com.example.springboardapi.board.service;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.model.Mail;
import com.example.springboardapi.board.vo.BoardInsertReqVo;
import com.example.springboardapi.board.mapper.BoardMapper;
import com.example.springboardapi.board.vo.BoardUpdateReqVo;
import com.example.springboardapi.util.ExcelUtil;
import com.example.springboardapi.util.MaskingUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    private final MailService mailService;

    public BoardService(BoardMapper boardMapper, MailService mailService) {
        this.boardMapper = boardMapper;
        this.mailService = mailService;
    }

    @Transactional(readOnly = true)
    public ArrayList<Board> selectList() {
        ArrayList<Board> list = boardMapper.selectBoardList();

        list.stream().forEach(v -> {
            v.setRegistId(MaskingUtil.nameMasking(v.getRegistId()));
        });

        return list;
    }

    @Transactional(readOnly = true)
    public Board selectOne(int boardId) {
        Board board = boardMapper.selectBoardOne(boardId);
        board.setRegistId(MaskingUtil.nameMasking(board.getRegistId()));
        return board;
    }

    public int save(BoardInsertReqVo reqVo) {
        int count = 0;
        // 게시글 저장
        count = boardMapper.insertBoard(reqVo);
        // 게시글 태그 저장
        BoardUpdateReqVo updateReqVo = new BoardUpdateReqVo();
        updateReqVo.setBoardId(reqVo.getBoardId());
        updateReqVo.setTitle(reqVo.getTitle());
        updateReqVo.setContents(reqVo.getContents());
        updateReqVo.setTagList(reqVo.getTagList());
        boardMapper.insertBoardTags(updateReqVo);

        return count;
    }

    public int update(BoardUpdateReqVo reqVo) {
        int count = 0;
        // 게시글 수정
        count = boardMapper.updateBoard(reqVo);
        // 태그 수정
        List<String> tagList = reqVo.getTagList();
        if(tagList.size() > 0) {
            boardMapper.deleteTagList(reqVo.getBoardId());
            boardMapper.insertBoardTags(reqVo);
        }
        return count;
    }

    public int delete(int boardId) {
        // 게시글 삭제
        return boardMapper.deleteBoard(boardId);
    }

    public int realDelete(int boardId) {
        List<Integer> tagList = boardMapper.selectTagList(boardId);
        // 연관 태그 있는 경우 삭제
        if(tagList.size() > 0) {
            boardMapper.deleteTagList(boardId);
        }
        // 게시글 삭제
        return boardMapper.realDeleteBoard(boardId);
    }

    public AtomicInteger uploadExcel(MultipartFile uploadFile, String ext) throws IOException {
        AtomicInteger count = new AtomicInteger();
        List<Map<Object, Object>> list = ExcelUtil.readExcel(uploadFile, ext);
        List<Map<Object, Object>> errList = new ArrayList<>();

        list.stream().forEach(v -> {
            try {
                BoardInsertReqVo reqVo = new BoardInsertReqVo();

                String tags = v.get("tags").toString();
                List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));

                reqVo.setTitle(v.get("title").toString());
                reqVo.setContents(v.get("contents").toString());
                reqVo.setRegistId(v.get("registId").toString());
                reqVo.setTagList(tagList);

                count.addAndGet(save(reqVo));
            } catch (Exception e) {
                errList.add(v);
                e.printStackTrace();
            }
        });

        if(errList.size() > 0) {
            String subject = "데이터 저장 실패";
            String text = errList.toString();

            Mail mail = new Mail();
            mail.setSubject(subject);
            mail.setText(text);
            mailService.sendMail(mail);
        }

        return count;

    }

    public void downloadExcel(List<Board> list, HttpServletResponse res) {
        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("게시글리스트");
            int rowNo = 0;

            Row headerRow = sheet.createRow(rowNo++);
            headerRow.createCell(0).setCellValue("제목");
            headerRow.createCell(1).setCellValue("내용");
            headerRow.createCell(2).setCellValue("작성자");
            headerRow.createCell(3).setCellValue("태그");

            for (Board board : list) {
                Row row = sheet.createRow(rowNo++);
                row.createCell(0).setCellValue(board.getTitle());
                row.createCell(1).setCellValue(board.getContents());
                row.createCell(2).setCellValue(board.getRegistId());
                row.createCell(3).setCellValue(board.getTagList());
            }

            String fileName = "게시글 일괄 다운로드.xlsx";
            fileName = new String (fileName.getBytes("UTF-8"),"ISO-8859-1");
            res.setContentType("ms-vnd/excel;");
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            workbook.write(res.getOutputStream());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
