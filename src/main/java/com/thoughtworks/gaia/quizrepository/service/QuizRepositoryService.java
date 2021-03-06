package com.thoughtworks.gaia.quizrepository.service;

import com.exmertec.yaz.query.EmptyQuery;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.quizrepository.QuizRepositoryMapper;
import com.thoughtworks.gaia.quizrepository.dao.QuizRepositoryDao;
import com.thoughtworks.gaia.quizrepository.entity.QuizRepository;
import com.thoughtworks.gaia.quizrepository.model.QuizRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class QuizRepositoryService implements Loggable {
    @Autowired
    private QuizRepositoryMapper mapper;

    @Autowired
    private QuizRepositoryDao qrDao;

    public List<QuizRepository> getAllQuizRepositories() {
        List<QuizRepositoryModel> qrModel = qrDao.where(new EmptyQuery()).queryList();
        return mapper.mapList(qrModel, QuizRepository.class);
    }

    public QuizRepository getQuizRepositoryById(Long Id) {
        QuizRepositoryModel qrModel = qrDao.idEquals(Id).querySingle();

        if (qrModel == null) {
            error("No quiz repositories in the system was found.");
            throw new NotFoundException();
        }

        return mapper.map(qrModel, QuizRepository.class);
    }
}
