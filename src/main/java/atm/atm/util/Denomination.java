package atm.atm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Denomination {

    BILL_THOUSAND(2, 1000.0),

    BILL_FIVE_HUNDRED(5, 500.0),

    BILL_TWO_HUNDRED(10, 200.0),

    BILL_ONE_HUNDRED(20, 100.0),

    BILL_FIFTY(30, 50.0),

    BILL_TWENTY(40, 20.0),

    COIN_TEEN(50, 10.0),

    COIN_FIVE(100, 5.0),

    COIN_TWO(200, 2.0),

    COIN_ONE(300, 1.0),

    COIN_FIFTY_CENT(100, 0.5);


    private final Integer quantity;
    private final Double denomination;
}






