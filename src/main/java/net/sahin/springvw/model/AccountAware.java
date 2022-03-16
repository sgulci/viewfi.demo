/*
 *
 *  * Copyright (c) Vieworks
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *
 *
 */

package net.sahin.springvw.model;

import java.util.Objects;

public interface AccountAware {
    String accountId();

    default boolean belongsToAccount( String accountId ) {
        return Objects.equals( this.accountId(), accountId );
    }
}
