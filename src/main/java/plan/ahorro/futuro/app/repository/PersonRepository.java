package plan.ahorro.futuro.app.repository;

import java.util.List;
import plan.ahorro.futuro.infraestructure.entity.PersonEntity;

public interface PersonRepository {
    List<PersonEntity> getPersons();
    PersonEntity getPersonById(Integer id); 
}
