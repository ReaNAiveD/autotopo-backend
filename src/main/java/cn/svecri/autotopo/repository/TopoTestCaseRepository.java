package cn.svecri.autotopo.repository;

import cn.svecri.autotopo.model.TopoTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoTestCaseRepository extends JpaRepository<TopoTestCase, Integer> {
}
