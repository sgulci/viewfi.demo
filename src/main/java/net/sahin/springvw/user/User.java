package net.sahin.springvw.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.sahin.springvw.model.OrganizationAware;
import net.sahin.springvw.model.Roles;
import org.bson.codecs.pojo.annotations.BsonId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString( exclude = "view" )
@EqualsAndHashCode( exclude = "view" )
@Document(collection = "users")
public class User implements OrganizationAware, Serializable {
    @JsonIgnore
    public final View view = new View();
    @BsonId
    public String email;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    public boolean deleted = false;
    public String firstName;
    public String lastName;
    public String role = Roles.USER;
    public String organizationId;
    public Set<String> accounts = new LinkedHashSet<>();
    public boolean canAccessAnyAccount = false;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    public String lastLogin;
    public boolean banned = false;
    public boolean confirmed = false;
    public String apiKey = "";
    private String password;

    public User() {
    }

    @JsonCreator
    public User( String email, String organizationId ) {
        this.email = email;
        this.organizationId = organizationId;
    }

    public User( String email, String firstName, String lastName, String role, String password, String organizationId, boolean confirmed ) {
        this( email, organizationId );
        this.update(firstName, lastName, role, new HashSet<>(), false);
        this.confirm( confirmed );
        this.updatePassword( password );
    }

    public User( String email, String firstName, String lastName, String organizationId ) {
        this( email, firstName, lastName, Roles.USER, null, organizationId, false );
    }

    public static String encrypt( String password ) {
        return password;
//        return Hash.md5( password );
    }

    public boolean canAccessAccount(String accountId) {
        return canAccessAnyAccount || accounts.contains( accountId );
    }

    public void addAccount( String accountId ) {
        accounts.add( accountId );
    }

    @Override
    public String organizationId() {
        return organizationId;
    }

    public User update( String firstName, String lastName, String role, Set<String> accounts, boolean canAccessAnyAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.accounts = accounts;
        this.canAccessAnyAccount = canAccessAnyAccount;
        return this;
    }

    public User confirm( boolean status ) {
        this.confirmed = status;
        return this;
    }

    public User ban( Boolean banStatus ) {
        this.banned = banStatus;
        return this;
    }

    public User updatePassword( String password ) {
        this.password = password != null ? encrypt( password ) : null;
        return this;
    }

    public boolean passwordMatches( String password ) {
        return this.password != null && this.password.equals( encrypt( password ) );
    }

    public String getAccessKey() {
        return email ;
    }

    public boolean hasPassword() {
        return password != null;
    }

    public boolean authentic( String password ) {
        return passwordMatches( password ) && !banned && confirmed;
    }

    public boolean authentic( String accessKey, String apiKey ) {
        return getAccessKey().equals( accessKey ) && this.apiKey.equals( apiKey ) && !banned;
    }


    public class View  {
        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getRole() {
            return role;
        }

        public String getOrganization() {
            return organizationId;
        }

        public Set<String> getAccounts() {
            return accounts;
        }

        public boolean isCanAccessAnyAccount() {
            return canAccessAnyAccount;
        }

        public String getApiKey() {
            return apiKey;
        }

        public String getAccessKey() {
            return User.this.getAccessKey();
        }

        public boolean isBanned() {
            return banned;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
        public String getLastLogin() {
            return lastLogin;
        }

    }
}