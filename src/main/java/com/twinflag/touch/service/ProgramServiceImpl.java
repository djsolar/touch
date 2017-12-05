package com.twinflag.touch.service;

import com.twinflag.touch.model.Program;
import com.twinflag.touch.model.User;
import com.twinflag.touch.respository.ProgramRepository;
import com.twinflag.touch.respository.UserRepository;
import com.twinflag.touch.utils.ProgramType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public void saveProgram(Program program) {
        programRepository.save(program);
    }

    @Override
    public void deleteProgram(Program program) {

    }

    @Override
    public void findAllProgram() {

    }

    @Override
    public void publishProgram(Program program) {

    }

    @Override
    public void stopProgram(Program program) {

    }

    @Override
    public void findProgram(Program program) {

    }

    @Override
    public DataTablesOutput<Program> findAll(DataTablesInput input) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Specification<Program> programSpecification = new Specification<Program>() {
            @Override
            public Predicate toPredicate(Root<Program> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("createUser").as(User.class), user);
                Predicate p2 = cb.equal(root.get("type").as(Integer.class), ProgramType.PROGRAM.getType());
                return cb.and(p1, p2);
            }
        };
        return programRepository.findAll(input, programSpecification);
    }
}
