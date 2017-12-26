package com.twinflag.touch.service;

import com.twinflag.touch.model.LevelOne;
import com.twinflag.touch.respository.LevelOneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LevelOneServiceImpl implements LevelOneService {

    @Autowired
    private LevelOneRepository levelOneRepository;

    @Override
    public void deleteLevelOne(LevelOne levelOne) {
        levelOneRepository.delete(levelOne);
    }

    public void saveLevelOne(LevelOne levelOne) {
        levelOneRepository.save(levelOne);
    }

    @Override
    public LevelOne findLevelOne(Integer id) {
        return levelOneRepository.findOne(id);
    }
}
