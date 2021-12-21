package cn.svecri.autotopo.repository;

import cn.svecri.autotopo.model.TopoInfo;
import cn.svecri.autotopo.model.TopoTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoTestCaseRepository extends JpaRepository<TopoTestCase, Integer> {

    List<TopoTestCase> getAllByTopoInfo(TopoInfo topoInfo);

}
