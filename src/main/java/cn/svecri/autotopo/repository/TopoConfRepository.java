package cn.svecri.autotopo.repository;

import cn.svecri.autotopo.model.TopoConf;
import cn.svecri.autotopo.model.TopoConfigSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoConfRepository extends JpaRepository<TopoConf, Integer> {

    TopoConf getFirstByTopoInfo_TopoIdAndIsDefaultTrueAndIsDeletedFalse(int topoId);

    TopoConf getFirstByConfId(int confId);

    List<TopoConfigSummary> getAllByIsDeletedFalseOrderByLastUpdateDesc();

}
