package com.example.es.controller;

import com.example.es.exception.ApiException;
import com.example.es.service.TestEsService;
import com.example.es.vo.ElkTestVO;
import com.example.es.vo.TestEsVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jianhui.Yang
 * @version $Id TestEsController.java, v 0.1 2018-09-19 14:46 jianhui.Yang Exp $$
 */
@RestController
@RequestMapping("/test")
public class TestEsController {

    @Autowired
    private TestEsService testEsService;
    @ApiOperation(value = "三只眼ES查询Demo",notes = "根据distance/orgName查询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "distance",value = "距离",required = false, paramType = "String"),
            @ApiImplicitParam(name = "orgName", value = "广场名称", required = false,paramType = "String")
    })
    @RequestMapping(value = "/findTest",method = RequestMethod.GET)
    @ResponseBody
    public List<TestEsVO> findData(String distance, String orgName) throws ApiException {
        return testEsService.findData(distance,orgName);
    }

    @ApiImplicitParam(name = "message", value = "消息",required = true,paramType = "String")
    @RequestMapping(value = "findMessage",method = RequestMethod.GET)
    @ResponseBody
    public List<ElkTestVO> findMessage(String message) throws ApiException {
        return testEsService.findMessage(message);
    }

    @ApiImplicitParam(name = "message", value = "消息",required = true,paramType = "String")
    @RequestMapping(value = "/queryMessage",method = RequestMethod.GET)
    @ResponseBody
    public ElkTestVO queryOneMessage (String message) throws ApiException {
        return testEsService.queryOneMessgae(message);
    }

}
