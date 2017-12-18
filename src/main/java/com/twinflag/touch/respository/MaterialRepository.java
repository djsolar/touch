package com.twinflag.touch.respository;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends PagingAndSortingRepository<Material, Integer>, JpaSpecificationExecutor<Material> {

    @Query("select m.id, m.macName, m.originName from Material m where m.achieve=:achieve and m.type=:type")
    List findFilterMaterials(@Param("achieve") Achieve achieve, @Param("type") Integer type);

}
