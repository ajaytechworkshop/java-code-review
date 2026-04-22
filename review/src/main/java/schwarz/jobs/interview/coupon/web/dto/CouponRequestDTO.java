package schwarz.jobs.interview.coupon.web.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponRequestDTO {

    @NotNull
    private List<String> codes;

}
