package schwarz.jobs.interview.coupon.core.services;

import java.util.Optional;
import schwarz.jobs.interview.coupon.core.services.model.Basket;

public interface BasketService {

    // Apply coupon to basket
    Optional<Basket> apply(final Basket basket, final String code);
}
