package schwarz.jobs.interview.coupon.core.services;

import java.util.List;
import java.util.Optional;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.model.Coupon;
import schwarz.jobs.interview.coupon.web.dto.CouponRequestDTO;

public interface CouponService {

    Optional<CouponEntity> getCoupon(final String code);

    Coupon createCoupon(final Coupon coupon);

    List<CouponEntity> getCoupons(final CouponRequestDTO couponRequestDTO);
}
