package com.example.es.controller;

import com.example.es.service.TestEsService;
import com.example.es.vo.TestEsVO;
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

    @RequestMapping(value = "/findTest",method = RequestMethod.GET)
    @ResponseBody
    public List<TestEsVO> findData(String distance, String orgName){
        return testEsService.findData(distance,orgName);
    }

}
