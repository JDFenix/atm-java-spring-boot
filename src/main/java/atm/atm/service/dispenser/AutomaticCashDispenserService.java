package atm.atm.service.dispenser;

import atm.atm.model.Cash;
import atm.atm.util.Denomination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutomaticCashDispenserService {
    private final List<Cash> wallet = new ArrayList<>();

    public AutomaticCashDispenserService() {
        fillAtm();
    }

    public void fillAtm() {
        for (Denomination denomination : Denomination.values()) {
            wallet.add(new Cash(denomination.name(), denomination.getQuantity(), denomination.getDenomination()));
        }
    }

    public List<Cash> withdraw(Double amountSolicited) {
        Double amountTotal = amountSolicited;
        List<Cash> tempWallet = new ArrayList<>();
        for (Cash cash : wallet) {
            tempWallet.add(new Cash(cash.getTypeCash(), cash.getQuantity(), cash.getDenomination()));
        }

        List<Cash> coinsOrBillsReturned = new ArrayList<>();

        for (Cash cash : tempWallet) {
            Double denomination = cash.getDenomination();
            Integer quantityAvailable = cash.getQuantity();
            int numberCoinsOrBills = (int) (amountTotal / denomination);

            if (numberCoinsOrBills > 0 && quantityAvailable > 0) {
                int numberCoinsOrBillsDispensed = Math.min(numberCoinsOrBills, quantityAvailable);
                amountTotal -= denomination * numberCoinsOrBillsDispensed;
            }
        }

        if (amountTotal > 0) {
            return new ArrayList<>();
        }

        amountTotal = amountSolicited;
        for (Cash cash : wallet) {
            Double denomination = cash.getDenomination();
            Integer quantityAvailable = cash.getQuantity();
            int numberCoinsOrBills = (int) (amountTotal / denomination);

            if (numberCoinsOrBills > 0 && quantityAvailable > 0) {
                int numberCoinsOrBillsDispensed = Math.min(numberCoinsOrBills, quantityAvailable);
                amountTotal -= denomination * numberCoinsOrBillsDispensed;
                decreaseQuantity(cash, numberCoinsOrBillsDispensed);
                coinsOrBillsReturned.add(new Cash(cash.getTypeCash(), numberCoinsOrBillsDispensed, cash.getDenomination()));
            }
        }

        return coinsOrBillsReturned;
    }

    public void decreaseQuantity(Cash cash, int quantity) {
        int currentQuantity = cash.getQuantity();
            cash.setQuantity(currentQuantity - quantity);
    }


}
