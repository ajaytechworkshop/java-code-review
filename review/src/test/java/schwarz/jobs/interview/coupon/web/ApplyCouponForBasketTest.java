package schwarz.jobs.interview.coupon.web;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import schwarz.jobs.interview.coupon.common.util.MessageKey;
import schwarz.jobs.interview.coupon.web.dto.AppResponseDto;
import schwarz.jobs.interview.coupon.web.dto.ApplyDiscountRequestDTO;

public class ApplyCouponForBasketTest extends AbstractWebTest {

    @Test
    @DisplayName("Validate apply discount unsuccessfully when basket is null")
    void validate_apply_discount_unsuccessfully_when_basket_is_null() {
        // given
        final ApplyDiscountRequestDTO applyDiscountRequestDTO = ApplyDiscountRequestDTO.builder()
            .code("code01")
            .basket(null)
            .build();

        // when
        final AppResponseDto responseDto = applyCouponToBasket(applyDiscountRequestDTO)
            .returnResult(AppResponseDto.class)
            .getResponseBody();

        // then
        assertResponse(responseDto, MessageKey.APP_ERR_002, List.of(error("basket", MessageKey.BAS_VAL_ERR_001)));
    }

    @Test
    @DisplayName("Validate apply discount unsuccessfully when basket and coupon code is null")
    void validate_apply_discount_unsuccessfully_when_basket_and_coupon_code_is_null() {
        // given
        final ApplyDiscountRequestDTO applyDiscountRequestDTO = ApplyDiscountRequestDTO.builder().build();

        // when
        final AppResponseDto responseDto = applyCouponToBasket(applyDiscountRequestDTO)
            .returnResult(AppResponseDto.class)
            .getResponseBody();

        // then
        assertResponse(responseDto,
            MessageKey.APP_ERR_002,
            List.of(error("basket", MessageKey.BAS_VAL_ERR_001), error("code", MessageKey.COU_CU_ERR_001)));
    }
}
