package atm.atm.controller.dispenser;

import atm.atm.service.dispenser.AutomaticCashDispenserService;
import atm.atm.model.Cash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/atm")
public class AutomaticCashDispenserController {

    private StringBuilder currentAmount = new StringBuilder();

    @Autowired
    private AutomaticCashDispenserService automaticCashDispenserService;


    @GetMapping("/index")
    public String get(Model model) {
        return "indexC";
    }

    @PostMapping("/clickNumber")
    public String click(@RequestParam("number") String number, Model model) {
        currentAmount.append(number);
        model.addAttribute("currentAmount", currentAmount.toString());
        return "indexC";
    }


    @PostMapping("/clear")
    public String clear(Model model) {
        currentAmount.setLength(0);
        model.addAttribute("currentAmount", currentAmount.toString());
        return "indexC";
    }


    @PostMapping("/withdraw")
    public String withdraw(Model model) {
        try {
            Double quantitySolicited = Double.parseDouble(currentAmount.toString());
            System.out.println("Cantidad solicitada: " + quantitySolicited);
            currentAmount.setLength(0);

            List<Cash> coinsOrBillsToWithdraw = automaticCashDispenserService.withdraw(quantitySolicited);

            if (!coinsOrBillsToWithdraw.isEmpty()) {
                model.addAttribute("coinsOrBillsToWithdraw", coinsOrBillsToWithdraw);
                model.addAttribute("message", "Retiro exitoso");
                model.addAttribute("cashSolicited", quantitySolicited);
            } else {
                model.addAttribute("message", "No hay suficiente dinero para retirar la cantidad solicitada.");
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Entrada no válida. Por favor, ingrese una cantidad válida.");
        } catch (Exception e) {
            model.addAttribute("message", "Ocurrió un error inesperado. Por favor, intente nuevamente.");
        } finally {
            currentAmount.setLength(0);
            System.out.println("Longitud de currentAmount restablecida");
        }

        return "indexC";
    }


}
