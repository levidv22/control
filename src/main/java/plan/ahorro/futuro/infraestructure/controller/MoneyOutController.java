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
import plan.ahorro.futuro.app.service.MoneyOutService;
import plan.ahorro.futuro.app.service.SavingService;
import plan.ahorro.futuro.infraestructure.entity.MoneyOutEntity;
import plan.ahorro.futuro.infraestructure.entity.SavingEntity;

@Controller
@RequestMapping("/money-out")
public class MoneyOutController {

    private final MoneyOutService moneyOutService;
    private final SavingService savingService;

    public MoneyOutController(MoneyOutService moneyOutService, SavingService savingService) {
        this.moneyOutService = moneyOutService;
        this.savingService = savingService;
    }

    @GetMapping
    public String listMoneyOut(Model model) {
        List<MoneyOutEntity> moneyOuts = moneyOutService.getMoneyOut();
        // Obtener total de ahorros
        BigDecimal totalSavings = savingService.getSavings()
                .stream()
                .map(SavingEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Obtener total de gastos
        BigDecimal totalExpenses = moneyOutService.getMoneyOut()
                .stream()
                .map(MoneyOutEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calcular el monto restante
        BigDecimal remainingSavings = totalSavings.subtract(totalExpenses);

        // Formatear la fecha de cada ahorro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy -- hh:mm a");
        for (MoneyOutEntity moneyOut : moneyOuts) {
            String formattedFecha = moneyOut.getDate().format(formatter);
            moneyOut.setFormattedFecha(formattedFecha);
        }

        // Agregar datos al modelo
        model.addAttribute("totalSavings", totalSavings);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("remainingSavings", remainingSavings);
        model.addAttribute("moneyOuts", moneyOuts);
        model.addAttribute("4", "Registro de Gastos");
        Collections.reverse(moneyOuts);
        return "savings/money_out"; // Vista
    }
    
    @GetMapping("/new2")
    public String newMoneyForm(Model model) {
        return "savings/new2";
    }

    @PostMapping
    public String saveMoneyOut(@RequestParam BigDecimal amount, String description, String tipo, Model model) {
        // Obtener total de ahorros
        BigDecimal totalSavings = savingService.getSavings()
                .stream()
                .map(SavingEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Obtener total de gastos
        BigDecimal totalExpenses = moneyOutService.getMoneyOut()
                .stream()
                .map(MoneyOutEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calcular el monto restante
        BigDecimal remainingSavings = totalSavings.subtract(totalExpenses);

        // Validar que el monto del gasto no sobrepase los ahorros
        if (amount.compareTo(remainingSavings) > 0) {
            // Agregar un mensaje de error al modelo
            model.addAttribute("errorMessage", "El monto del gasto no puede ser mayor al total de ahorros disponibles.");
            // Redirigir a la misma página con el mensaje de error
            return listMoneyOut(model);
        }

        // Registrar el gasto
        MoneyOutEntity moneyOut = new MoneyOutEntity();
        moneyOut.setAmount(amount);
        moneyOut.setDate(LocalDateTime.now());
        moneyOut.setDescription(description);
        moneyOut.setTipo(tipo);
        moneyOutService.saveMoneyOut(moneyOut);

        return "redirect:/money-out";
    }

    @GetMapping("/edit/{id}")
    public String editMoneyOutForm(@PathVariable Integer id, Model model) {
        MoneyOutEntity expense = moneyOutService.getMoneyOutById(id);
        model.addAttribute("expense", expense);
        model.addAttribute("5", "Editar Gastos");
        return "savings/editGasto";
    }

    @PostMapping("/update")
    public String updateMoneyOut(@ModelAttribute MoneyOutEntity expense) {
        MoneyOutEntity existingExpense = moneyOutService.getMoneyOutById(expense.getId());
        if (existingExpense != null) {
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setDescription(expense.getDescription());
            existingExpense.setTipo(expense.getTipo());
            moneyOutService.saveMoneyOut(existingExpense);
        }
        return "redirect:/money-out";
    }

    @GetMapping("/delete/{id}")
    public String deleteSaving(@PathVariable Integer id) {
        moneyOutService.deleteMoneyOutById(id);
        return "redirect:/money-out";
    }
}
