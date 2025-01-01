package plan.ahorro.futuro.infraestructure.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import plan.ahorro.futuro.app.service.PersonService;
import plan.ahorro.futuro.app.service.SavingService;
import plan.ahorro.futuro.infraestructure.entity.SavingEntity;

@Controller
@RequestMapping("/savings")
public class SavingController {

    private final SavingService savingService;
    private final PersonService personService;

    public SavingController(SavingService savingService, PersonService personService) {
        this.savingService = savingService;
        this.personService = personService;
    }

    @GetMapping
    public String listSavings(@RequestParam(required = false) Integer personId, Model model) {
        model.addAttribute("persons", personService.getersons());

        List<SavingEntity> savings;
        if (personId != null) {
            savings = savingService.getSavingsByPersonId(personId);
            model.addAttribute("selectedPersonId", personId);
        } else {
            savings = savingService.getSavings();
        }

        // Formatear la fecha de cada ahorro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy -- hh:mm a");
        for (SavingEntity saving : savings) {
            String formattedFecha = saving.getDate().format(formatter);
            saving.setFormattedFecha(formattedFecha);
        }

        model.addAttribute("savings", savings);

        // Calcular el total
        BigDecimal totalAmount = savings.stream()
                .map(SavingEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("1", "Listado de Ahorros");
        Collections.reverse(savings);
        return "savings/list";
    }

    @GetMapping("/new")
    public String newSavingForm(Model model) {
        model.addAttribute("persons", personService.getersons());
        model.addAttribute("2", "Agregar Ahorros");
        return "savings/new";
    }

    @PostMapping
    public String saveSaving(@RequestParam Integer personId, @RequestParam BigDecimal amount, String motivo, String tipo) {
        SavingEntity saving = new SavingEntity();
        saving.setPerson(personService.getPersonById(personId));
        saving.setAmount(amount);
        saving.setDate(LocalDateTime.now());
        saving.setMotivo(motivo);
        saving.setTipo(tipo);
        savingService.saveSaving(saving);
        return "redirect:/savings";
    }

    @GetMapping("/edit/{id}")
    public String editSavingForm(@PathVariable Integer id, Model model) {
        SavingEntity saving = savingService.getSavingById(id);
        model.addAttribute("saving", saving);
        model.addAttribute("3", "Editar Ahorros");
        return "savings/edit";
    }

    @PostMapping("/update")
    public String updateSaving(@ModelAttribute SavingEntity saving) {
        SavingEntity existingSaving = savingService.getSavingById(saving.getId());
        if (existingSaving != null) {
            existingSaving.setAmount(saving.getAmount());
            existingSaving.setMotivo(saving.getMotivo());
            existingSaving.setTipo(saving.getTipo());
            savingService.saveSaving(existingSaving);
        }
        return "redirect:/savings";
    }

    @GetMapping("/delete/{id}")
    public String deleteSaving(@PathVariable Integer id) {
        savingService.deleteSavingById(id);
        return "redirect:/savings";
    }
}
