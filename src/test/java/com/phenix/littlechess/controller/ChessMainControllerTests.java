package com.phenix.littlechess.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phenix.littlechess.sdo.OperatePlaning;
import com.phenix.littlechess.sdo.Result;
import net.bytebuddy.description.method.MethodDescription;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.lang.reflect.Type;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ChessMainControllerTests extends AbstractTest {
    private static Logger logger = LoggerFactory.getLogger(ChessMainControllerTests.class);

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void selectChess() throws Exception{
        String uri = "/chess/1/select/3";
        //uri = "/chess/select?player=1&offset=3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        //Result result = super.mapFromJson(content, Result.class);
        Result<OperatePlaning> result = mapFromJsonX(content);
        // TODO 按游戏逻辑实现测试用例
        OperatePlaning data = result.getData();
        System.out.println(data);
        assertTrue(result.getErrCode()== 0);

    }

    protected Result<OperatePlaning> mapFromJsonX(String json)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Type jsonType = new TypeToken<BaseResponseBean<LoginUser>>() {}.getType();
        return objectMapper.readValue(json, new TypeReference<Result<OperatePlaning>>(){});

    }
}