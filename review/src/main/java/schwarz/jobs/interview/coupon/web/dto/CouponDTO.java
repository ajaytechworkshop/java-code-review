package schwarz.jobs.interview.coupon.web.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponDTO {

    private BigDecimal discount;

    @NotBlank
    private String code;

    private BigDecimal minBasketValue;

}
