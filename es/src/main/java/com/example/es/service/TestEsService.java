package com.example.es.service;

import com.example.es.exception.ApiException;
import com.example.es.vo.ElkTestVO;
import com.example.es.vo.TestEsVO;

import java.util.List;

/**
 * @author jianhui.Yang
 * @version $Id TestEsService.java, v 0.1 2018-09-19 14:44 jianhui.Yang Exp $$
 */
public interface TestEsService {

    List<TestEsVO> findData (String distance, String orgName) throws ApiException;


    List<ElkTestVO> findMessage(String message) throws ApiException;

    ElkTestVO queryOneMessgae (String message) throws ApiException;
}
