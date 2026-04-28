package schwarz.jobs.interview.coupon.common.util;

public enum MessageKey {

    // Info
    COUPON_CREATED("coupon.create.success"),
    COUPON_CREATE_FAILED("coupon.create.failed"),

    // Application errors
    APP_ERR_001("app.error.001"),

    // Validation errors create/update coupon
    COU_CU_ERR_001("coupon.code.required"),
    COU_CU_ERR_002("coupon.discount.required"),
    COU_CU_ERR_003("coupon.discount.invalid"),
    COU_CU_ERR_004("coupon.minimumbasket.required"),
    COU_CU_ERR_005("coupon.minimumbasket.invalid"),

    // Validation errors filter coupon
    COU_FIL_ERR_001("coupon.filter.codes.required");

    private final String key;

    MessageKey(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}
