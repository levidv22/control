package plan.ahorro.futuro.infraestructure.adapter;

import java.util.List;
import org.springframework.stereotype.Repository;
import plan.ahorro.futuro.app.repository.MoneyOutRepository;
import plan.ahorro.futuro.infraestructure.entity.MoneyOutEntity;

@Repository
public class MoneyOutRepositoryImpl implements MoneyOutRepository{
    
    private final MoneyOutCrudRepository moneyOutCrudRepository;

    public MoneyOutRepositoryImpl(MoneyOutCrudRepository moneyOutCrudRepository) {
        this.moneyOutCrudRepository = moneyOutCrudRepository;
    }    

    @Override
    public List<MoneyOutEntity> getMoneyOut() {
        return (List<MoneyOutEntity>)moneyOutCrudRepository.findAll();
    }

    @Override
    public MoneyOutEntity saveMoneyOut(MoneyOutEntity moneyOut) {
        return moneyOutCrudRepository.save(moneyOut);
    }

    @Override
    public MoneyOutEntity getMoneyOutById(Integer id) {
        return moneyOutCrudRepository.findById(id).get();
    }

    @Override
    public boolean deleteMoneyOutById(Integer id) {
        moneyOutCrudRepository.deleteById(id);
        return true;
    }
    
}
