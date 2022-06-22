//package com.example.springboardapi.board.controller;
//
//import com.example.springboardapi.board.vo.BoardInsertReqVo;
//import com.example.springboardapi.board.vo.BoardUpdateReqVo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.NoArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Log4j2
//@AutoConfigureMockMvc
//@NoArgsConstructor
//
//@ActiveProfiles( "test" )
//@SpringBootTest
//public class SpringBoardApiControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    public void selectNoticeTest() throws Exception{
//        String url = "/board/list";
//
//        mockMvc.perform(get(url)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(result -> {
//                    MockHttpServletResponse response = result.getResponse();
//                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
//                }).andExpect(status().isOk());
//    }
//
//    @Test
//    public void selectNoticeOneTest() throws Exception{
//        String url = "/board/one/25";
//
//        mockMvc.perform(get(url)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(result -> {
//                    MockHttpServletResponse response = result.getResponse();
//                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
//                }).andExpect(status().isOk());
//    }
//
//    @Test
//    public void saveNoticeTest() throws Exception{
//        String url = "/board/add";
//
//        List<String> tagList = new ArrayList<>();
//        tagList.add("tag1");
//        tagList.add("tag2");
//
//        BoardInsertReqVo reqVo = new BoardInsertReqVo();
//        reqVo.setContents("test contents");
//        reqVo.setRegistId("user30");
//        reqVo.setTitle("title test");
//        reqVo.setTagList(tagList);
//
//        mockMvc.perform(MockMvcRequestBuilders.post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(reqVo)))
//                .andExpect(result -> {
//                    MockHttpServletResponse response = result.getResponse();
//                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
//                }).andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void updateNoticeTest() throws Exception{
//        String url = "/board/update";
//
//        List<String> tagList = new ArrayList<>();
//        tagList.add("tag5");
//        tagList.add("tag6");
//
//        BoardUpdateReqVo reqVo = new BoardUpdateReqVo();
//        reqVo.setBoardId(25);
//        reqVo.setContents("contents");
//        reqVo.setUpdateId("updateUser1");
//        reqVo.setTitle("title test22222");
//        reqVo.setTagList(tagList);
//
//        mockMvc.perform(MockMvcRequestBuilders.put(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(reqVo)))
//                .andExpect(result -> {
//                    MockHttpServletResponse response = result.getResponse();
//                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
//                }).andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void deleteNoticeTest() throws Exception{
//        String url = "/board/delete/27";
//
//        mockMvc.perform(MockMvcRequestBuilders.delete(url)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(result -> {
//                    MockHttpServletResponse response = result.getResponse();
//                    log.info(response.getContentAsString(StandardCharsets.UTF_8));
//                }).andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//
//}
