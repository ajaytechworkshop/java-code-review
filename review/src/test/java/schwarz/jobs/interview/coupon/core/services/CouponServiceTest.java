package schwarz.jobs.interview.coupon.core.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import schwarz.jobs.interview.coupon.common.mapper.CouponMapper;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.impl.BasketServiceImpl;
import schwarz.jobs.interview.coupon.core.services.impl.CouponServiceImpl;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.core.services.model.Coupon;
import schwarz.jobs.interview.coupon.core.services.model.CouponFilter;

@ExtendWith(SpringExtension.class)
public class CouponServiceTest {

    private final CouponService couponService;
    private final BasketService basketService;

    public CouponServiceTest() {
        this.couponService = new CouponServiceImpl(couponRepository, Mappers.getMapper(CouponMapper.class));
        this.basketService = new BasketServiceImpl(couponService);
    }

    @Mock
    private CouponRepository couponRepository = Mockito.mock(CouponRepository.class);

    @Test
    public void createCoupon() {
        //given
        final Coupon coupon = Coupon.builder()
            .code("12345")
            .discount(BigDecimal.TEN)
            .minBasketValue(BigDecimal.valueOf(50))
            .build();

        // when
        couponService.createCoupon(coupon);

        // then
        verify(couponRepository, times(1)).save(any());
    }

    @Test
    public void test_apply_coupon_method() {
        // given
        final Basket firstBasket = Basket.builder()
            .value(BigDecimal.valueOf(100))
            .build();

        final Basket secondBasket = Basket.builder()
            .value(BigDecimal.valueOf(0))
            .build();

        final Basket thirdBasket = Basket.builder()
            .value(BigDecimal.valueOf(-1))
            .build();

        final CouponEntity couponEntity = new CouponEntity(new Random().nextLong(), "1111", BigDecimal.TEN, BigDecimal.valueOf(50));
        when(couponRepository.findByCode("1111")).thenReturn(Optional.of(couponEntity));

        // when
        final Optional<Basket> applyCouponFirstBasket = basketService.apply(firstBasket, "1111");
        final Optional<Basket> applyCouponSecondBasket = basketService.apply(secondBasket, "1111");

        // then
        assertThat(applyCouponFirstBasket).hasValueSatisfying(b -> {
            assertThat(b.getAppliedDiscount()).isEqualTo(BigDecimal.TEN);
            assertThat(b.isApplicationSuccessful()).isTrue();
        });

        assertThat(applyCouponSecondBasket).hasValueSatisfying(b -> {
            assertThat(b).isEqualTo(secondBasket);
            assertThat(b.isApplicationSuccessful()).isFalse();
        });

        assertThatThrownBy(() -> {
            basketService.apply(thirdBasket, "1111");
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("Can't apply negative discounts");
    }

    @Test
    public void should_test_get_Coupons() {

        CouponFilter filter = new CouponFilter().setCodes(Set.of("1111", "1234"));

        final CouponEntity couponEntity01 = new CouponEntity(new Random().nextLong(), "1111", BigDecimal.TEN, BigDecimal.valueOf(50));
        final CouponEntity couponEntity02 = new CouponEntity(new Random().nextLong(), "1234", BigDecimal.TEN, BigDecimal.valueOf(50));

        when(couponRepository.findByCode(any()))
            .thenReturn(Optional.of(couponEntity01))
            .thenReturn(Optional.of(couponEntity02));

        List<Coupon> returnedCoupons = couponService.filterCoupons(filter);

        assertThat(returnedCoupons.get(0).getCode()).isEqualTo("1111");

        assertThat(returnedCoupons.get(1).getCode()).isEqualTo("1234");
    }
}
