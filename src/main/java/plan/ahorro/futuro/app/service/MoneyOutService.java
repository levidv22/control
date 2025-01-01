package plan.ahorro.futuro.app.service;

import java.util.List;
import plan.ahorro.futuro.app.repository.MoneyOutRepository;
import plan.ahorro.futuro.infraestructure.entity.MoneyOutEntity;

public class MoneyOutService {
    
    private final MoneyOutRepository moneyOutRepository;

    public MoneyOutService(MoneyOutRepository moneyOutRepository) {
        this.moneyOutRepository = moneyOutRepository;
    }

    public List<MoneyOutEntity> getMoneyOut(){
        return moneyOutRepository.getMoneyOut();
    }
    public MoneyOutEntity saveMoneyOut(MoneyOutEntity moneyOut){
        return moneyOutRepository.saveMoneyOut(moneyOut);
    }
    public MoneyOutEntity getMoneyOutById(Integer id){
        return moneyOutRepository.getMoneyOutById(id);
    }
    public boolean deleteMoneyOutById(Integer id){
        return moneyOutRepository.deleteMoneyOutById(id);
    }
}
