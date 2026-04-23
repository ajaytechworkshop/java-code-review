package schwarz.jobs.interview.coupon.core.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.core.services.model.Coupon;
import schwarz.jobs.interview.coupon.web.dto.CouponRequestDTO;

@ExtendWith(SpringExtension.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;
    private BasketService basketService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    public void createCoupon() {
        Coupon coupon = Coupon.builder()
            .code("12345")
            .discount(BigDecimal.TEN)
            .minBasketValue(BigDecimal.valueOf(50))
            .build();

        couponService.createCoupon(coupon);

        verify(couponRepository, times(1)).save(any());
    }

    @Test
    public void test_apply_coupon_method() {

        final Basket firstBasket = Basket.builder()
            .value(BigDecimal.valueOf(100))
            .build();

        final CouponEntity couponEntity = new CouponEntity(new Random().nextLong(), "1111", BigDecimal.TEN, BigDecimal.valueOf(50));

        when(couponRepository.findByCode("1111")).thenReturn(Optional.of(couponEntity));

        Optional<Basket> optionalBasket = basketService.apply(firstBasket, "1111");

        assertThat(optionalBasket).hasValueSatisfying(b -> {
            assertThat(b.getAppliedDiscount()).isEqualTo(BigDecimal.TEN);
            assertThat(b.isApplicationSuccessful()).isTrue();
        });

        final Basket secondBasket = Basket.builder()
            .value(BigDecimal.valueOf(0))
            .build();

        optionalBasket = basketService.apply(secondBasket, "1111");

        assertThat(optionalBasket).hasValueSatisfying(b -> {
            assertThat(b).isEqualTo(secondBasket);
            assertThat(b.isApplicationSuccessful()).isFalse();
        });

        final Basket thirdBasket = Basket.builder()
            .value(BigDecimal.valueOf(-1))
            .build();

        assertThatThrownBy(() -> {
            basketService.apply(thirdBasket, "1111");
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("Can't apply negative discounts");
    }

    @Test
    public void should_test_get_Coupons() {

        CouponRequestDTO dto = CouponRequestDTO.builder()
            .codes(Arrays.asList("1111", "1234"))
            .build();

        final CouponEntity couponEntity01 = new CouponEntity(new Random().nextLong(), "1111", BigDecimal.TEN, BigDecimal.valueOf(50));
        final CouponEntity couponEntity02 = new CouponEntity(new Random().nextLong(), "1234", BigDecimal.TEN, BigDecimal.valueOf(50));
        when(couponRepository.findByCode(any()))
            .thenReturn(Optional.of(couponEntity01))
            .thenReturn(Optional.of(couponEntity02));

        List<CouponEntity> returnedCoupons = couponService.getCoupons(dto);

        assertThat(returnedCoupons.get(0).getCode()).isEqualTo("1111");

        assertThat(returnedCoupons.get(1).getCode()).isEqualTo("1234");
    }
}
