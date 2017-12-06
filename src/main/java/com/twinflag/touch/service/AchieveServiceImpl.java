package com.twinflag.touch.service;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.User;
import com.twinflag.touch.respository.AchieveRepository;
import com.twinflag.touch.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AchieveServiceImpl implements AchieveService {

    @Autowired
    private AchieveRepository achieveRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveAchieve(Achieve achieve) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        achieve.setCreateUser(user);
        return achieveRepository.save(achieve) != null;
    }

    @Override
    public List<Achieve> findAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        return achieveRepository.findAchievesByCreateUser(user);
    }

    @Override
    public boolean deleteAchieve(Integer id) {
        achieveRepository.delete(id);
        return true;
    }
}
