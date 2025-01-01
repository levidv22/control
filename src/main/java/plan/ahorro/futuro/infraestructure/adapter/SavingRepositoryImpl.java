package plan.ahorro.futuro.infraestructure.adapter;

import java.util.List;
import org.springframework.stereotype.Repository;
import plan.ahorro.futuro.app.repository.SavingRepository;
import plan.ahorro.futuro.infraestructure.entity.SavingEntity;

@Repository
public class SavingRepositoryImpl implements SavingRepository {

    private final SavingCrudRepository savingCrudRepository;

    public SavingRepositoryImpl(SavingCrudRepository savingCrudRepository) {
        this.savingCrudRepository = savingCrudRepository;
    }

    @Override
    public List<SavingEntity> getSavings() {
        return (List<SavingEntity>) savingCrudRepository.findAll();
    }

    @Override
    public List<SavingEntity> getSavingsByPersonId(Integer personId) {
        return savingCrudRepository.findByPersonId(personId);
    }

    @Override
    public SavingEntity saveSaving(SavingEntity saving) {
        return savingCrudRepository.save(saving);
    }

    @Override
    public SavingEntity getSavingById(Integer id) {
        return savingCrudRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteSavingById(Integer id) {
        savingCrudRepository.deleteById(id);
        return true;
    }    
}
