package atm.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cash {
    public String typeCash;
    public Integer  quantity;
    public Double denomination;
}
