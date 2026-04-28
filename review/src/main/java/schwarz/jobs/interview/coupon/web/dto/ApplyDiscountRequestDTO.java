package schwarz.jobs.interview.coupon.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplyDiscountRequestDTO {

    @NotBlank(message = "{coupon.code.required}")
    private String code;

    @NotNull(message = "{basket.required}")
    private BasketDTO basket;
}
