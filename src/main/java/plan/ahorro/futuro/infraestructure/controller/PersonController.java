package plan.ahorro.futuro.infraestructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import plan.ahorro.futuro.app.service.PersonService;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public String getPersons(Model model) {
        model.addAttribute("persons", personService.getersons());
        return "savings/new"; // Vista para seleccionar persona
    }

}
