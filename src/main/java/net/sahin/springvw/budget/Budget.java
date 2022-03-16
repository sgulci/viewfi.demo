package net.sahin.springvw.budget;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTimeUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@EqualsAndHashCode
@ToString
@Slf4j
@Document(collection = "budgets")
public class Budget implements Serializable {
    @Id
    public  String organizationId;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    private boolean deleted = false;
    public  AccountCounter accountCounter = new AccountCounter();
    public  CampaignCounter campaignCounter = new CampaignCounter();
    public Map<String, Integer> limits = new LinkedHashMap<>();
    public static final int UNLIMITED = -1;
    public  long expireAt;

    public Budget() {
        this.organizationId = new Random(100000).toString();
    }

    public Budget(String organizationId) {
        this.organizationId = organizationId;
    }

    @JsonCreator
    public Budget( String organizationId, long expireAt, Map<String, Integer> limits ) {
        this.organizationId = organizationId;
        this.expireAt = expireAt;
        this.limits.putAll( limits );
    }

    public boolean isSubscriptionExpired() {
        return DateTimeUtils.currentTimeMillis() > expireAt;
    }

    public boolean allowed( Type type ) {
        int limit = limits.getOrDefault( type, UNLIMITED );
        return limit == UNLIMITED || getTotalCount(type ) < limit;
    }

    public synchronized void inc( String accountId,String campaignId, Type type ) {
        accountCounter.inc( accountId, type );
        campaignCounter.inc( campaignId, type );
    }

    public synchronized int getTotalCount(Type type ) {
        return accountCounter.getTotalValue( type.toString() );
    }

    public synchronized int getAccountCount( String accountId, Type type ) {

        Counter counter = accountCounter.counter( accountId );
        return counter.value( type.toString() );
    }

    public synchronized int getCampaignCount( String campaignId, Type type ) {

        Counter counter = campaignCounter.counter( campaignId );
        return counter.value( type.toString() );
    }

    @ToString
    @EqualsAndHashCode
    public static class Counter implements Serializable {

        private  Map<String, Integer> typeCounter = new HashMap<>();

        @JsonCreator
        public Counter() {
        }

        public void inc( Type type ) {
            Integer count = ofType( type.toString() );
            typeCounter.put( type.toString(), count + 1 );
        }

        private Integer ofType( String type ) {
            return typeCounter.computeIfAbsent( type, t -> 0 );
        }

        public Integer value( String type ) {
            return ofType( type );
        }

    }


    @ToString
    @EqualsAndHashCode
    public static class AccountCounter implements Serializable {
        protected  Map<String, Counter> counters = new HashMap<>();

        public AccountCounter() {
        }

        public Counter counter(String accountId ) {
            return counters.computeIfAbsent( accountId, cid -> new Counter() );
        }

        public void inc( String accountId, Type type ) {
            Counter counter = counter( accountId );
            counter.inc( type );
        }

        public int getTotalValue( String type ) {
            return counters.values().stream()
                    .map( c -> c.value( type ) )
                    .reduce( 0, Integer::sum );
        }
    }

    @ToString
    @EqualsAndHashCode
    public static class CampaignCounter implements Serializable {
        protected  Map<String, Counter> counters = new HashMap<>();

        public CampaignCounter() {
        }

        public Counter counter(String campaignId ) {
            return counters.computeIfAbsent( campaignId, cid -> new Counter() );
        }

        public void inc( String campaignId, Type type ) {
            Counter counter = counter( campaignId );
            counter.inc( type );
        }

        public int getTotalValue( Type type ) {
            return counters.values().stream()
                    .map( c -> c.value( type.toString() ) )
                    .reduce( 0, Integer::sum );
        }
    }

    public enum Type {
        IMPRESSIONS,
        PERKS
    }
}
