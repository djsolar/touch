package com.twinflag.touch.service;

import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;

import java.util.List;

public interface MaterialService {

    void saveMaterials(List<Material> materials);

    void deleteMaterials(Integer[] ids);

    List<Material> getMaterialByAchieveAndType(Achieve achieve, Integer type);

    DataTableViewPage<Material> findAllMaterial(int page, int pageSize);

    Material findMaterialByMacName(String md5Name);

    Material saveMaterial(Material material);
}
