package net.sahin.springvw.organization;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public class Account implements Serializable {
    public String id;
    public String name;
    public String description;
    public String entityName;
    public String entityLegalAddress;
    public String privacyPolicyLink;
    public String sentFromEmailAddress;
    public AccountExt ext;

    public Account() {
    }

    public Account( String name ) {
        this.name = name;
    }

    public Account( String id, String name ) {
        this.id = id;
        this.name = name;
    }

    public Account(String id, String name, String entityName, String entityLegalAddress, String privacyPolicyLink, String sentFromEmailAddress) {
        this.id = id;
        this.name = name;
        this.entityName = entityName;
        this.entityLegalAddress = entityLegalAddress;
        this.privacyPolicyLink = privacyPolicyLink;
        this.sentFromEmailAddress = sentFromEmailAddress;
    }
}
