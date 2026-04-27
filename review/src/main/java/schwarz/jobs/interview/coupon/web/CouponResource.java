package schwarz.jobs.interview.coupon.web;


import static schwarz.jobs.interview.coupon.common.util.MessageKey.COUPON_CREATED;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import schwarz.jobs.interview.coupon.common.mapper.ApplicationResponseDTOMapper;
import schwarz.jobs.interview.coupon.common.mapper.CouponMapper;
import schwarz.jobs.interview.coupon.common.util.Paths;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.web.dto.ApplicationResponseDto;
import schwarz.jobs.interview.coupon.web.dto.CouponRequestDTO;
import schwarz.jobs.interview.coupon.web.dto.CreateCouponDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CouponResource {

    // Services
    private final CouponService couponService;

    // Mappers
    private final CouponMapper couponMapper;
    private final ApplicationResponseDTOMapper responseDTOMapper;

    @PostMapping(Paths.COUPON_CREATE)
    public ResponseEntity<ApplicationResponseDto<Object>> create(@RequestBody @Valid final CreateCouponDTO couponDTO) {
        log.info("CREATE COUPON WITH COUPON_CODE : {}", couponDTO.getCode());

        final Long couponId = couponService.createCoupon(couponMapper.toCoupon(couponDTO));

        log.info("COUPON CREATED SUCCESSFULLY: {}", couponId);

        URI uri = ServletUriComponentsBuilder.fromUriString(Paths.COUPON_BASE)
                                             .path("/{id}")
                                             .buildAndExpand(couponId)
                                             .toUri();

        return ResponseEntity.created(uri).body(responseDTOMapper.mapSuccess(COUPON_CREATED, couponId));
    }

    @GetMapping("/coupons")
    public List<CouponEntity> getCoupons(@RequestBody @Valid final CouponRequestDTO couponRequestDTO) {
        return couponService.getCoupons(couponRequestDTO);
    }
}
