package cn.svecri.autotopo.repository;

import cn.svecri.autotopo.model.TopoCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoCommandRepository extends JpaRepository<TopoCommand, Integer> {

    TopoCommand getFirstByCmdId(int cmdId);

    List<TopoCommand> getAllByCmdAndTargetTelnet(String cmd, String targetTelnet);

    List<TopoCommand> getAllByTargetTelnet(String targetTelnet);

}
