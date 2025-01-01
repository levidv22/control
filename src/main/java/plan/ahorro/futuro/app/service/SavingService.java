package plan.ahorro.futuro.app.service;

import java.util.List;
import plan.ahorro.futuro.app.repository.SavingRepository;
import plan.ahorro.futuro.infraestructure.entity.SavingEntity;

public class SavingService {
    private final SavingRepository savingRepository;

    public SavingService(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }

    public List<SavingEntity> getSavings() {
        return savingRepository.getSavings();
    }

    public List<SavingEntity> getSavingsByPersonId(Integer personId) {
        return savingRepository.getSavingsByPersonId(personId);
    }

    public SavingEntity saveSaving(SavingEntity saving) {
        return savingRepository.saveSaving(saving);
    }

    public SavingEntity getSavingById(Integer id) {
        return savingRepository.getSavingById(id);
    }

    public boolean deleteSavingById(Integer id) {
        return savingRepository.deleteSavingById(id);
    }
}
