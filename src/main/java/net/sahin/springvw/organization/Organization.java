package net.sahin.springvw.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Document(collection = "organizations")
public class Organization implements Serializable {
    public String id;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    private boolean deleted = false;
    public String name;
    public String description;
    public String country;
    public String billingAddressLine1;
    public String billingAddressLine2;
    public String city;
    public String postalCode;
    public String contactName;
    public String contactEmail;
    public String subscriptionId = subscribe( "TRIAL" );
    public String subscriptionDate;
    public List<Account> accounts = new ArrayList<>();

    @JsonIgnore
    public View view = new View();

    public Organization() {
    }

    public Organization addOrUpdateAccount(Account account ) {
//        if( account.id == null ) synchronized( this ) {
//            account.id = toUserFriendlyId( account.name, 5, id -> accounts.containsKey( id ), NO_VOWELS );
//        }
        this.accounts.add( account );
        return this;
    }

    public Organization removeAccount( String accountId ) {
//        this.accounts.removeKey( accountId );
        return this;
    }

//    public Organization update( Organization organization ) {
//        this.organization = organization;
//        return this;
//    }

    public String subscribe( String subscriptionId ) {
        this.subscriptionId = subscriptionId;
        this.subscriptionDate =DateTime.now().toString();
        return subscriptionId;
    }


    public class View implements Serializable {
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getSubscriptionId() {
            return subscriptionId;
        }

        @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
        public String getSubscriptionDate() {
            return subscriptionDate;
        }

    }
}
