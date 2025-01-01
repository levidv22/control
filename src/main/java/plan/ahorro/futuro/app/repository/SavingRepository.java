package plan.ahorro.futuro.app.repository;

import java.util.List;
import plan.ahorro.futuro.infraestructure.entity.SavingEntity;

public interface SavingRepository {
    List<SavingEntity> getSavings();
    List<SavingEntity> getSavingsByPersonId(Integer personId);
    SavingEntity saveSaving(SavingEntity saving);
    SavingEntity getSavingById(Integer id);
    boolean deleteSavingById(Integer id);
}
