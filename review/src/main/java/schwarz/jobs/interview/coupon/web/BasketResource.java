package schwarz.jobs.interview.coupon.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import schwarz.jobs.interview.coupon.common.mapper.BasketMapper;
import schwarz.jobs.interview.coupon.common.util.Paths;
import schwarz.jobs.interview.coupon.core.services.BasketService;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.web.dto.ApplyDiscountRequestDTO;
import schwarz.jobs.interview.coupon.web.dto.BasketDTO;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BasketResource {


    private final BasketService basketService;

    private final BasketMapper basketMapper;


    //@ApiOperation(value = "Applies currently active promotions and coupons from the request to the requested Basket - Version 1")
    @PostMapping(value = Paths.BASKET_APPLY_COUPON)
    public ResponseEntity<BasketDTO> applyCoupon(
        //@ApiParam(value = "Provides the necessary basket and customer information required for the coupon application", required = true)
        @RequestBody @Valid final ApplyDiscountRequestDTO applicationRequestDTO) {

        log.info("Applying coupon with coupon code : {}", applicationRequestDTO.getCode());

        final Basket requestedBasket = basketMapper.toBasket(applicationRequestDTO.getBasket());

        final BasketDTO basketDTO = basketService.apply(requestedBasket, applicationRequestDTO.getCode())
            .map(basketMapper::toBasketDTO)
            .orElse(null);

        if (basketDTO == null) {
            return ResponseEntity.notFound().build();
        }

        if (!basketDTO.isApplicationSuccessful()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        log.info("Applied coupon");

        return ResponseEntity.ok().body(basketDTO);
    }
}
