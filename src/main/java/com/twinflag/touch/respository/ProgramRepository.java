package com.twinflag.touch.respository;

import com.twinflag.touch.model.Program;
import com.twinflag.touch.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProgramRepository extends PagingAndSortingRepository<Program, Integer>, JpaSpecificationExecutor<Program> {

    List<Program> findProgramsByTypeAndCreateUser(int type, User createUser);

    @Query(value = "select id, programName from Program where type =?1 and createUser=?2")
    List<Object> findTemplateInfo(int type, User createUser);
}
