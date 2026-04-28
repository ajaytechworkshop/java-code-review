package schwarz.jobs.interview.coupon.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BasketDTO {

    @NotNull
    @Min(value = 0, message = "{basket.value.invalid}")
    private BigDecimal value;

    private BigDecimal appliedDiscount;

    private boolean applicationSuccessful;
}
