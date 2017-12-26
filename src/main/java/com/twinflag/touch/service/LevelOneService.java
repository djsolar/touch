package com.twinflag.touch.service;

import com.twinflag.touch.model.LevelOne;

public interface LevelOneService {

    void deleteLevelOne(LevelOne levelOne);

    void saveLevelOne(LevelOne levelOne);

    LevelOne findLevelOne(Integer id);
}
