package com.ethan.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
@NoArgsConstructor
@ToString
public class Session {
    private String userId;

    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
