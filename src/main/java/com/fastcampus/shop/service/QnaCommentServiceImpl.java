package com.fastcampus.shop.service;

import com.fastcampus.shop.dao.QnaCommentDao;
import com.fastcampus.shop.dao.QnaDao;
import com.fastcampus.shop.dto.QnaCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QnaCommentServiceImpl implements QnaCommentService {
    @Autowired
    QnaDao qnaDao;
    @Autowired
    QnaCommentDao qnaCommentDao;

//    @Autowired
//    public QnaCommentServiceImpl(QnaCommentDao qnaCommentDao, QnaDao qnaDao) {
//        this.qnaCommentDao = qnaCommentDao;
//        this.qnaDao = qnaDao;
//    }

    @Override
    public int getCount(Integer qnaId) throws Exception {
        return qnaCommentDao.count(qnaId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int remove(Integer answerId, Integer qnaId, Integer memberId) throws Exception {
        System.out.println("answerId: " + answerId);
        System.out.println("qnaId: " + qnaId);
        System.out.println("memberId: " + memberId);
        int rowCnt = qnaDao.updateCommentCnt(qnaId, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
        //throw new Exception("test");
        rowCnt = qnaCommentDao.delete(answerId, memberId);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int write(QnaCommentDto qnaCommentDto) throws Exception {
        qnaDao.updateCommentCnt(qnaCommentDto.getQnaId(), 1);
        //throw new Exception("test");
        return qnaCommentDao.insert(qnaCommentDto);
    }

    @Override
    public List<QnaCommentDto> getListByQnaId(Integer qnaId) throws Exception {
        return qnaCommentDao.findAll(qnaId);
    }

    @Override
    public QnaCommentDto getById(Integer answerId) throws Exception {
        return qnaCommentDao.findById(answerId);
    }

    @Override
    public int modify(QnaCommentDto qnaCommentDto) throws Exception {
        return qnaCommentDao.update(qnaCommentDto);
    }
}