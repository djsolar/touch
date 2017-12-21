package com.twinflag.touch.service;

import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;
import com.twinflag.touch.model.User;
import com.twinflag.touch.respository.AchieveRepository;
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
    private AchieveRepository achieveRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveMaterials(List<Material> materials) {
        materialRepository.save(materials);
    }

    @Override
    public void deleteMaterials(Integer achieveId, Integer[] ids) {
        if (ids == null || ids.length == 0)
            return;
        Achieve achieve = achieveRepository.findOne(achieveId);
        List<Material> materials = new ArrayList<>();
        for(Integer id : ids) {
            Material material = materialRepository.findOne(id);
            materials.add(material);
            material.setAchieves(null);
        }
        achieve.getMaterials().removeAll(materials);
        achieveRepository.save(achieve);
        materialRepository.delete(materials);
    }

    @Override
    public List<Material> getMaterialByAchieveAndType(Achieve achieve, Integer type) {
        List<Achieve> achieves = new ArrayList<>();
        achieves.add(achieve);
        List<Material> materials = materialRepository.findMaterialsByAchievesAndType(achieves, type);
        return materials;
    }

    @Override
    public DataTableViewPage<Material> findAllMaterial(int page, int pageSize, int type) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Specification<Material> materialSpecification = new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate typePredicate = cb.equal(root.get("type"), type);
                Join<Material, Achieve> join = root.join("achieves", JoinType.INNER);
                Predicate joinPredicate = cb.equal(join.get("createUser").as(User.class), user);
                return cb.and(typePredicate, joinPredicate);
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

    @Override
    public Material findMaterialByMacName(String md5Name) {
        return materialRepository.findMaterialByMacName(md5Name);
    }

    @Override
    public Material saveMaterial(Material material) {
        if (material == null)
            return null;
        Material material1 = materialRepository.findMaterialByMacName(material.getMacName());
        if (material1 != null) {
            return material1;
        }
        materialRepository.save(material);
        return materialRepository.findMaterialByMacName(material.getMacName());
    }

    private PageRequest buildPageRequest(int page, int pageSize) {
        return new PageRequest(page, pageSize);
    }
}
