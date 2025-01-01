package plan.ahorro.futuro.app.repository;

import java.util.List;
import plan.ahorro.futuro.infraestructure.entity.MoneyOutEntity;

public interface MoneyOutRepository {
    List<MoneyOutEntity> getMoneyOut();
    MoneyOutEntity saveMoneyOut(MoneyOutEntity moneyOut);
    MoneyOutEntity getMoneyOutById(Integer id);
    boolean deleteMoneyOutById(Integer id);
}
