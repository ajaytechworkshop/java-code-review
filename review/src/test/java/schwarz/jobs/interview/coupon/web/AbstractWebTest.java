package schwarz.jobs.interview.coupon.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import schwarz.jobs.interview.coupon.CouponApplicationTests;
import schwarz.jobs.interview.coupon.common.util.MessageKey;
import schwarz.jobs.interview.coupon.web.dto.ApplicationResponseDto;
import schwarz.jobs.interview.coupon.web.dto.CouponDTO;

public class AbstractWebTest extends CouponApplicationTests {

    protected RestTestClient.ResponseSpec createCoupon(final CouponDTO couponDTO) {
        return restTestClient.post()
                             .uri("/api/coupon/create")
                             .body(couponDTO)
                             .accept(MediaType.APPLICATION_JSON)
                             .exchange();
    }

    protected void assertResponse(final ApplicationResponseDto actual,
                                  final MessageKey expectedMessage,
                                  final List<ApplicationResponseDto.Error> errors) {

        assertThat(actual.message()).isEqualTo(messageService.message(expectedMessage.key()));
        assertThat(actual.code()).isEqualTo(expectedMessage.name());

        if (!errors.isEmpty()) {
            assertThat(actual.errors()).containsExactlyInAnyOrderElementsOf(actual.errors());
        }
    }

    protected ApplicationResponseDto.Error error(final String field, final MessageKey messageKey) {
        return ApplicationResponseDto.Error.builder()
                                           .field(field)
                                           .message(messageService.message(messageKey.key()))
                                           .build();
    }
}
