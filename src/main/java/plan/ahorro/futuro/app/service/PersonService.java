package plan.ahorro.futuro.app.service;

import java.util.List;
import plan.ahorro.futuro.app.repository.PersonRepository;
import plan.ahorro.futuro.infraestructure.entity.PersonEntity;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonEntity> getersons() {
        return personRepository.getPersons();
    }

    public PersonEntity getPersonById(Integer id) {
        return personRepository.getPersonById(id);
    }
}
