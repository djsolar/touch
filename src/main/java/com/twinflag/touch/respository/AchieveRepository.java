package com.twinflag.touch.respository;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AchieveRepository extends PagingAndSortingRepository<Achieve, Integer>, JpaSpecificationExecutor<Achieve> {
    List<Achieve> findAchievesByCreateUser(User user);
}
