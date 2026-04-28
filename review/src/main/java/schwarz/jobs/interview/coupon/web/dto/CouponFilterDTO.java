package schwarz.jobs.interview.coupon.web.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CouponFilterDTO {

    @NotNull(message = "{coupon.filter.codes.required}")
    private Set<String> codes;
}
