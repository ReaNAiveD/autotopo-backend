package cn.svecri.autotopo.repository;

import cn.svecri.autotopo.model.TopoConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoConfRepository extends JpaRepository<TopoConf, Integer> {
}
