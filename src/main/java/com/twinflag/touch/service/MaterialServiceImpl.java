package com.twinflag.touch.service;

import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;
import com.twinflag.touch.model.User;
import com.twinflag.touch.respository.MaterialRepository;
import com.twinflag.touch.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveMaterials(List<Material> materials) {
        materialRepository.save(materials);
    }

    @Override
    public void deleteMaterials(Integer[] ids) {
        if (ids == null || ids.length == 0)
            return;
        for(Integer id : ids) {
            materialRepository.delete(id);
        }
    }

    @Override
    public List<Material> getMaterialByAchieveAndType(Achieve achieve, Integer type) {
        List list = materialRepository.findFilterMaterials(achieve, type);
        List<Material> materials = new ArrayList<>();
        for(Object object : list) {
            Material material = new Material();
            Object[] objects = (Object[]) object;
            material.setId((Integer) objects[0]);
            material.setMacName((String) objects[1]);
            material.setOriginName((String) objects[2]);
            materials.add(material);
        }
        return materials;
    }

    @Override
    public DataTableViewPage<Material> findAllMaterial(int page, int pageSize) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Specification<Material> materialSpecification = new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<Material, Achieve> join = root.join("achieve", JoinType.INNER);
                return cb.equal(join.get("createUser").as(User.class), user);
            }
        };

        PageRequest pageRequest = buildPageRequest(page, pageSize);
        Page<Material> materialPage = materialRepository.findAll(materialSpecification, pageRequest);
        DataTableViewPage<Material> dataTableViewPage = new DataTableViewPage<>();
        dataTableViewPage.setAaData(materialPage.getContent());
        dataTableViewPage.setRecordsTotal(materialPage.getTotalElements());
        dataTableViewPage.setRecordsFiltered(materialPage.getTotalElements());
        return dataTableViewPage;
    }

    private PageRequest buildPageRequest(int page, int pageSize) {
        return new PageRequest(page, pageSize);
    }
}
