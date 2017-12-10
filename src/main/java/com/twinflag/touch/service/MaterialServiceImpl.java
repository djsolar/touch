package com.twinflag.touch.service;

import com.twinflag.touch.model.Material;
import com.twinflag.touch.respository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public void saveMaterials(List<Material> materials) {
        materialRepository.save(materials);
    }
}
