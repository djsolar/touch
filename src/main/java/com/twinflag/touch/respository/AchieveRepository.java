package com.twinflag.touch.respository;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.List;

public interface AchieveRepository extends DataTablesRepository<Achieve, Integer>{
    List<Achieve> findAchievesByCreateUser(User user);
}
