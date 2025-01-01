package plan.ahorro.futuro.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plan.ahorro.futuro.app.repository.MoneyOutRepository;
import plan.ahorro.futuro.app.repository.PersonRepository;
import plan.ahorro.futuro.app.repository.SavingRepository;
import plan.ahorro.futuro.app.service.MoneyOutService;
import plan.ahorro.futuro.app.service.PersonService;
import plan.ahorro.futuro.app.service.SavingService;

@Configuration
public class BeanConfig {
    
    @Bean
    public PersonService personService(PersonRepository personRepository) {
        return new PersonService(personRepository);
    }

    @Bean
    public SavingService savingService(SavingRepository savingRepository) {
        return new SavingService(savingRepository);
    }
    
    @Bean
    public MoneyOutService moneyOutService(MoneyOutRepository moneyOutRepository) {
        return new MoneyOutService(moneyOutRepository);
    }
}
