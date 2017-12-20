package com.twinflag.touch.service;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.User;

import java.util.List;

public interface AchieveService {

    Achieve saveAchieve(Achieve achieve);

    List<Achieve> findAll();

    boolean deleteAchieve(Integer id);

    Achieve findAchieve(Integer id);

    Achieve findAchieveByNameAndCreateUserAndAuthority(String name, User createUser, int authority);
}
