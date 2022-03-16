package net.sahin.springvw.organization;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@EqualsAndHashCode
public class AccountExt implements Serializable {
    public String logo;
    public String domain;
    public List<AccountUrl> accountUrls;

    @ToString
    @EqualsAndHashCode
    public static class AccountUrl implements Serializable {
        public String domain;
        public String subDomain;
        public Status status;

        public AccountUrl() {

        }

        public AccountUrl(String domain, String subDomain, Status status) {
            this.domain = domain;
            this.subDomain = subDomain;
            this.status = status;
        }

        public enum Status {
            DEFAULT,
            PENDING,
            COMPLETE
        }
    }

}
