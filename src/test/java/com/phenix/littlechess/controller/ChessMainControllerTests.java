package com.phenix.littlechess.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.phenix.littlechess.sdo.OperatePlaning;
import com.phenix.littlechess.sdo.Result;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ChessMainControllerTests extends AbstractTest {
    private static Logger logger = LoggerFactory.getLogger(ChessMainControllerTests.class);

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * 测试选择棋盘某个棋子的响应结果
     * @throws Exception
     */
    @Test
    public void selectChess() throws Exception{
        String uri;
        uri = "/chess/1/select/32";
        // Step0: 棋子Index（0-31）选择错误
        Result<OperatePlaning> result = this.getSelectResult(uri);
        assertTrue(result.getErrCode()!= 0);

        // Step1: 首次翻牌
        uri = "/chess/1/select/3";
        //uri = "/chess/select?player=1&offset=3";
        result = this.getSelectResult(uri);
        assertTrue(result.getErrCode()== 0);
        OperatePlaning plan = result.getData();
        // 确认是翻牌 且 offset=3的牌为已翻
        assertTrue(plan.getChessState() == 0);
        assertTrue(plan.getNowAllPieces()[3].getChessFace());
        // 玩家已经转换（ID改为0）
        assertTrue(plan.getNextPlayer() == 0);
        //TODO 按游戏逻辑实现测试用例
    }

    private Result<OperatePlaning> getSelectResult(String uri) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        // NOTE 期望通过Class<Result<T>>对象传参，暂时使用TypeReference
        Result<OperatePlaning> tmp = new Result<>();
        TypeReference<Result<OperatePlaning>> typeReference = new TypeReference<Result<OperatePlaning>>(){};
        Result<OperatePlaning> result = this.mapFromJsonX(content, typeReference);

        OperatePlaning data = result.getData();
        System.out.println(data);

        return result;
    }
}