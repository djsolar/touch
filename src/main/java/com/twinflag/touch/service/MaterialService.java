package com.twinflag.touch.service;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface MaterialService {

    void saveMaterials(List<Material> materials);

    void deleteMaterials(Integer[] ids);

    List<Material> getMaterialByAchieveAndType(Achieve achieve, Integer type);

    DataTablesOutput<Material> findAllMaterial(DataTablesInput dataTablesInput);

}
