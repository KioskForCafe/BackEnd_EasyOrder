package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OptionEntity;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {
    public List<OptionEntity> findByMenuId(int menuId);
    public OptionEntity findByOptionId(int optionId);

    @Query(value = "SELECT * FROM `option` WHERE option_id NOT IN :optionIdList", nativeQuery = true)
    public List<OptionEntity> findOptionNotInList(@Param("optionIdList") List<Integer> optionIdList);


}
