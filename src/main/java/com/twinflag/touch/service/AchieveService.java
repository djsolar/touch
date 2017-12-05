package com.twinflag.touch.service;

import com.twinflag.touch.model.Achieve;

import java.util.List;

public interface AchieveService {

    boolean saveAchieve(Achieve achieve);

    List<Achieve> findAll();
}
