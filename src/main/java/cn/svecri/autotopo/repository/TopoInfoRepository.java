package cn.svecri.autotopo.repository;

import cn.svecri.autotopo.model.TopoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoInfoRepository extends JpaRepository<TopoInfo, Integer> {

    TopoInfo getFirstByTopoId(int topoId);

}
