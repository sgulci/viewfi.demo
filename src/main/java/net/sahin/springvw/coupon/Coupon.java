package net.sahin.springvw.coupon;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Document(collection = "coupons")
public class Coupon implements Serializable {
    public String id;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    private boolean deleted = false;
    public String couponGroupId;
    public String couponCode;
    public String organizationId;
    public String accountId;
    public String viewerId;
    public String generatedBy;
    public String fileName;
    public long usedDate;

    public Coupon( String id, String couponGroupId, String generatedBy,String fileName, String organizationId,String accountId) {
        this.id = id;
        this.couponGroupId = couponGroupId;
        this.generatedBy = generatedBy;
        this.fileName = fileName;
        this.organizationId = organizationId;
        this.accountId = accountId;
    }

    public Coupon() {
    }

    public Coupon update(String viewerId,long usedDate) {
        this.viewerId = viewerId;
        this.usedDate=usedDate;
        return this;
    }
}
