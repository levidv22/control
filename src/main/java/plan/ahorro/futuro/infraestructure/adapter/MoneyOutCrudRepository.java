package plan.ahorro.futuro.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;
import plan.ahorro.futuro.infraestructure.entity.MoneyOutEntity;

public interface MoneyOutCrudRepository extends CrudRepository<MoneyOutEntity, Integer>{
    
}
