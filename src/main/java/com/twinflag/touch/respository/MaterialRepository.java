package com.twinflag.touch.respository;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface MaterialRepository extends PagingAndSortingRepository<Material, Integer>, JpaSpecificationExecutor<Material> {

    List<Material> findMaterialsByAchievesAndType(List<Achieve> achieves, Integer type);

    Material findMaterialByMacName(String macName);
}
