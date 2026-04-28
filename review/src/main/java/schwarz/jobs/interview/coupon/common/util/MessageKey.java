package schwarz.jobs.interview.coupon.common.util;

public enum MessageKey {

    // Info
    COUPON_CREATED("coupon.create.success"),
    COUPON_CREATE_FAILED("coupon.create.failed"),

    // Application errors
    APP_ERR_001("app.error.001"),
    APP_ERR_002("app.err.002"),

    // Validation errors for Coupon
    COU_CU_ERR_001("coupon.code.required"),
    COU_CU_ERR_002("coupon.discount.required"),
    COU_CU_ERR_003("coupon.discount.invalid"),
    COU_CU_ERR_004("coupon.minimumbasket.required"),
    COU_CU_ERR_005("coupon.minimumbasket.invalid"),

    // Validation errors filter coupon
    COU_FIL_ERR_001("coupon.filter.codes.required"),

    // Validation errors, Basket
    BAS_VAL_ERR_001("basket.required"),
    BAS_VAL_ERR_002("basket.value.invalid");

    private final String key;

    MessageKey(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}
