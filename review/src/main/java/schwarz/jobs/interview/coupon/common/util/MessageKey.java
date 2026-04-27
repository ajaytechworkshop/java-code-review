package schwarz.jobs.interview.coupon.common.util;

public enum MessageKey {

    // Info
    COUPON_CREATED("coupon.create.success"),
    COUPON_CREATE_FAILED("coupon.create.failed"),

    // Application errors
    APP_ERR_001("app.error.001"),

    // Validation errors
    COU_VAL_ERR_001("coupon.code.required"),
    COU_VAL_ERR_002("coupon.discount.required"),
    COU_VAL_ERR_003("coupon.discount.invalid"),
    COU_VAL_ERR_004("coupon.minimumbasket.required"),
    COU_VAL_ERR_005("coupon.minimumbasket.invalid");

    private final String key;

    MessageKey(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}
