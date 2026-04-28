package schwarz.jobs.interview.coupon.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import schwarz.jobs.interview.coupon.CouponApplicationTests;
import schwarz.jobs.interview.coupon.common.util.MessageKey;
import schwarz.jobs.interview.coupon.web.dto.AppResponseDto;

public class AbstractWebTest extends CouponApplicationTests {

    protected void assertResponse(final AppResponseDto actual,
                                  final MessageKey expectedMessage,
                                  final List<AppResponseDto.Error> errors) {

        assertThat(actual.message()).isEqualTo(messageService.message(expectedMessage.key()));
        assertThat(actual.code()).isEqualTo(expectedMessage.name());

        if (!errors.isEmpty()) {
            assertThat(actual.errors()).containsExactlyInAnyOrderElementsOf(actual.errors());
        }
    }

    protected AppResponseDto.Error error(final String field, final MessageKey messageKey) {
        return AppResponseDto.Error.builder()
            .field(field)
            .message(messageService.message(messageKey.key()))
            .build();
    }
}
