package net.sahin.springvw.viewer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.sahin.springvw.model.AccountAware;
import net.sahin.springvw.model.OrganizationAware;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString( exclude = "view" )
@EqualsAndHashCode( exclude = "view" )
@Document(collection = "viewers")
public class Viewer implements Serializable {
    @BsonId
    public String id;
    public String email;
    public String firstName;
    public String lastName;
    public String mobileNumber;
    public List<Perk> perks = new ArrayList<>();
    @JsonIgnore
    public View view = new View();

    public Viewer() {
    }

    public Viewer( String id, String firstName, String lastName, String email, String mobileNumber ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
    }

//    public void addPerk( Perk perk ) {
//        this.perks.add( perk );
//    }
//
//    public boolean containsPerk( String campaignId ) {
//        return perks.containsKey( campaignId );
//    }

//    public static class Perks extends AssocList<String, Perk> {
//        @Override
//        protected String keyOf( Perk perk ) {
//            return perk.campaignId;
//        }
//    }

    public static class Perk implements OrganizationAware, AccountAware, Serializable  {
        public String campaignId;
        public String organizationId;
        public String accountId;
        public String optionalData1;
        public String optionalData2;
        public String optionalData3;
        public String coupon;

        public Perk( String campaignId, String organizationId, String accountId,
                     String optionalData1, String optionalData2, String optionalData3,String coupon ) {
            this.campaignId = campaignId;
            this.organizationId = organizationId;
            this.accountId = accountId;
            this.optionalData1 = optionalData1;
            this.optionalData2 = optionalData2;
            this.optionalData3 = optionalData3;
            this.coupon = coupon;
        }

        @Override
        public String accountId() {
            return accountId;
        }

        @Override
        public String organizationId() {
            return organizationId;
        }
    }

    public class View  implements Serializable {
        public String getId() {
            return Viewer.this.id;
        }

        public String getFirstName() {
            return Viewer.this.firstName;
        }

        public String getLastName() {
            return Viewer.this.lastName;
        }

        public String getEmail() {
            return Viewer.this.email;
        }

        public String getMobileNumber() {
            return Viewer.this.mobileNumber;
        }
    }
}
