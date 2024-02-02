package com.uno.getinline.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest // 모든 컨트롤러를 읽는다.
@WebMvcTest(BaseController.class) // 해당 컨트롤러를 읽는다.
class BaseControllerTest {

    private final MockMvc mvc;

    public BaseControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 기본 페이지 요청")
    @Test
    void givenNothing_whenRequestingRootPage_thenReturnsIndexPage() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.TEXT_HTML)) // 아래 코드로 사용
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("This is default page.")))
                .andExpect(view().name("index"))
                .andDo(print());
    }

}
