package plan.ahorro.futuro.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;
import plan.ahorro.futuro.infraestructure.entity.SavingEntity;
import java.util.List;

public interface SavingCrudRepository extends CrudRepository<SavingEntity, Integer> {
    List<SavingEntity> findByPersonId(Integer personId);
}
