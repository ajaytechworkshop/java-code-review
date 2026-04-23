package schwarz.jobs.interview.coupon.core.services.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schwarz.jobs.interview.coupon.core.services.BasketService;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.core.services.model.Basket;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final CouponService couponService;

    @Override
    public Optional<Basket> apply(final Basket basket, final String code) {

        return couponService.getCoupon(code).map(coupon -> {

            if (basket.getValue().doubleValue() >= 0) {

                if (basket.getValue().doubleValue() > 0) {

                    basket.applyDiscount(coupon.getDiscount());

                } else if (basket.getValue().doubleValue() == 0) {
                    return basket;
                }

            } else {
                System.out.println("DEBUG: TRIED TO APPLY NEGATIVE DISCOUNT!");
                throw new RuntimeException("Can't apply negative discounts");
            }

            return basket;
        });
    }
}
