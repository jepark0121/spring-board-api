package com.example.springboardapi;

import com.example.springboardapi.board.service.BoardService;
import com.example.springboardapi.board.vo.BoardReqVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@AutoConfigureMockMvc
@NoArgsConstructor
@SpringBootTest
public class SpringBoardApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BoardService boardService;


    @Test
    public void selectNoticeTest() throws Exception{
        String url = "/board/list";

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
                }).andExpect(status().isOk());
    }

    @Test
    public void selectNoticeOneTest() throws Exception{
        String url = "/board/one/25";

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
                }).andExpect(status().isOk());
    }

    @Test
    public void saveNoticeTest() throws Exception{
        String url = "/board/add";

        List<String> tagList = new ArrayList<>();
        tagList.add("tag1");
        tagList.add("tag2");

        BoardReqVo reqVo = new BoardReqVo();
        reqVo.setContents("test contents");
        reqVo.setRegistId("user30");
        reqVo.setTitle("title test");
        reqVo.setTagList(tagList);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqVo)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
                }).andExpect(status().isOk());
    }

}
