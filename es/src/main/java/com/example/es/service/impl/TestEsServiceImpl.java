package com.example.es.service.impl;

import com.example.es.dao.TestEsDao;
import com.example.es.service.TestEsService;
import com.example.es.vo.ElkTestVO;
import com.example.es.vo.TestEsVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author jianhui.Yang
 * @version $Id TestEsServiceImpl.java, v 0.1 2018-09-19 14:45 jianhui.Yang Exp $$
 */
@Service
public class TestEsServiceImpl implements TestEsService {

    @Resource
    private TestEsDao testEsDao;

    @Override
    public List<TestEsVO> findData(String distance, String orgName) {
        return testEsDao.findData(distance,orgName);
    }

    @Override
    public List<ElkTestVO> findMessage(String message) {
        return testEsDao.findMessage(message);
    }

    @Override
    public ElkTestVO queryOneMessgae(String message) {
        return testEsDao.queyOneMessge(message);
    }
}
