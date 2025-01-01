package plan.ahorro.futuro.infraestructure.adapter;

import java.util.List;
import org.springframework.stereotype.Repository;
import plan.ahorro.futuro.app.repository.PersonRepository;
import plan.ahorro.futuro.infraestructure.entity.PersonEntity;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonCrudRepository personCrudRepository;

    public PersonRepositoryImpl(PersonCrudRepository personCrudRepository) {
        this.personCrudRepository = personCrudRepository;
    }

    @Override
    public List<PersonEntity> getPersons() {
        return (List<PersonEntity>) personCrudRepository.findAll();
    }

    @Override
    public PersonEntity getPersonById(Integer id) {
        return personCrudRepository.findById(id).orElse(null);
    }
}
