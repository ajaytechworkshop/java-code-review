package schwarz.jobs.interview.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import schwarz.jobs.interview.coupon.common.service.MessageService;
import schwarz.jobs.interview.coupon.web.BasketResource;
import schwarz.jobs.interview.coupon.web.CouponResource;

@AutoConfigureRestTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponApplicationTests {

	@Autowired
	protected CouponResource couponResource;

	@Autowired
	protected BasketResource basketResource;

	@Autowired
	protected RestTestClient restTestClient;

	@Autowired
	protected MessageService messageService;

	@Test
	void contextLoads() {
		assertThat(couponResource).isNotNull();
		assertThat(basketResource).isNotNull();
	}
}
