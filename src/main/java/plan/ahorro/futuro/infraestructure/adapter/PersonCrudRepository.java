package plan.ahorro.futuro.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;
import plan.ahorro.futuro.infraestructure.entity.PersonEntity;

public interface PersonCrudRepository extends CrudRepository<PersonEntity, Integer> {
    
}
