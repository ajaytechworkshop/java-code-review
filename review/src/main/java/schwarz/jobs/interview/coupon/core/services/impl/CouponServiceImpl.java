package schwarz.jobs.interview.coupon.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schwarz.jobs.interview.coupon.common.mapper.CouponMapper;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.core.services.model.Coupon;
import schwarz.jobs.interview.coupon.web.dto.CouponRequestDTO;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {

    // Repositories
    private final CouponRepository couponRepository;

    // Mappers
    private final CouponMapper couponMapper;

    @Override
    public Optional<CouponEntity> getCoupon(final String code) {
        return couponRepository.findByCode(code);
    }

    @Override
    public Coupon createCoupon(final Coupon coupon) {
        final CouponEntity couponEntity = couponRepository.save(couponMapper.toCouponEntity(coupon));
        return couponMapper.toCoupon(couponEntity);
    }

    @Override
    public List<CouponEntity> getCoupons(final CouponRequestDTO couponRequestDTO) {

        final ArrayList<CouponEntity> foundCoupons = new ArrayList<>();

        couponRequestDTO.getCodes().forEach(code -> foundCoupons.add(couponRepository.findByCode(code).get()));

        return foundCoupons;
    }
}
