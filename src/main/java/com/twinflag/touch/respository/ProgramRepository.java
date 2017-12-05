package com.twinflag.touch.respository;

import com.twinflag.touch.model.Program;
import com.twinflag.touch.model.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends DataTablesRepository<Program, Integer> {

    List<Program> findProgramsByTypeAndCreateUser(int type, User createUser);

    @Query(value = "select id, programName from Program where type =?1 and createUser=?2")
    List<Object> findTemplateInfo(int type, User createUser);
}
