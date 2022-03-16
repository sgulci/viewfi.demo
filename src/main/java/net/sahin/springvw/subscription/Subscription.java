package net.sahin.springvw.subscription;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.function.Function;

@ToString
@EqualsAndHashCode
@Document(collection = "subscriptions")
public class Subscription implements Serializable {
    public static final int UNLIMITED = -1;
    public String id;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    public boolean deleted = false;
    public String name;
    public int maximumUsers;
    public int maximumAccounts;
    public Duration duration;
    public AccessLevel accessLevel;
    public PaymentMethod paymentMethod;
    public int maximumCampaigns;
    public int maximumPerks;
    public int maximumImpressions;
    public String managerEmail;
    public boolean autorenew;

    public Subscription() {
    }

    public Subscription(String id, String name ) {
        this.id = id;
        this.name = name;
    }

    public Subscription withMaximumUsers( int maximumUsers ) {
        this.maximumUsers = maximumUsers;
        return this;
    }

    public Subscription withMaximumAccounts( int maximumAccounts ) {
        this.maximumAccounts = maximumAccounts;
        return this;
    }


    public static boolean isUnlimited( int value ) {
        return value == UNLIMITED;
    }

    public enum PaymentMethod {
        OFF_PLATFORM, ON_PLATFORM
    }

    public enum AccessLevel {
        STANDARD, ADVANCED
    }

    public enum Duration {
        TWO_WEEKS( dt -> dt.plusWeeks( 2 ) ),
        MONTH( dt -> dt.plusMonths( 1 ) ),
        TWO_MONTHS( dt -> dt.plusMonths( 2 ) ),
        THREE_MONTHS( dt -> dt.plusMonths( 3 ) ),
        SIX_MONTHS( dt -> dt.plusMonths( 6 ) ),
        YEAR( dt -> dt.plusYears( 1 ) ),
        UNLIMITED( dt -> dt.plusYears( 20 ) );

        private final Function<DateTime, DateTime> duration;

        Duration( Function<DateTime, DateTime> duration ) {
            this.duration = duration;
        }

        public DateTime willExpireAt( DateTime startingAt ) {
            return duration.apply( startingAt );
        }
    }

}
