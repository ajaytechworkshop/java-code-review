package schwarz.jobs.interview.coupon.common.mapper;

import org.mapstruct.Mapper;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.model.Coupon;
import schwarz.jobs.interview.coupon.web.dto.CouponDTO;
import schwarz.jobs.interview.coupon.web.dto.CreateCouponDTO;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    Coupon toCoupon(final CreateCouponDTO createCouponRequestDTO);

    Coupon toCoupon(final CouponEntity couponEntity);

    CouponEntity toCouponEntity(final Coupon coupon);

    CouponDTO toCouponDto(final Coupon coupon);
}
