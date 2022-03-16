package net.sahin.springvw.promotion;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Document(collection = "promotions")
public class PromotionCode implements Serializable {
    public String id;

    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    public boolean deleted = false;

    public String code = "";
    public String subscriptionId;
    public String usedBy;
    public String organizationId;
    public String generatedBy;
    public String prefix;
    public long expireDate;
    public long usedDate;

    public PromotionCode( String id, String subscriptionId, String generatedBy ) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.generatedBy = generatedBy;
    }

    public PromotionCode() {
    }

    public PromotionCode update(String organizationId, String usedBy, long usedDate) {
        this.usedBy = usedBy;
        this.usedDate=usedDate;
        this.organizationId = organizationId;
        return this;
    }
}
