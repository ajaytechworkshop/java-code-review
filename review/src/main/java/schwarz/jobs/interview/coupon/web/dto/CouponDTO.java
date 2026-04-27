package schwarz.jobs.interview.coupon.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponDTO {

    @NotNull(message = "{coupon.discount.required}")
    @DecimalMin(value = "0.0", message = "{coupon.discount.invalid}")
    @DecimalMax(value = "100.0", message = "{coupon.discount.required}")
    private BigDecimal discount;

    @NotBlank(message = "{coupon.code.required}")
    private String code;

    @NotNull(message = "{coupon.minimumbasket.required}")
    @DecimalMin(value = "0.0", message = "{coupon.minimumbasket.invalid}")
    private BigDecimal minBasketValue;
}
