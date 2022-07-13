package com.server.endogphin.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${jwt.secret}")
    public String JWT_SECRET;

    @Value("${jwt.expireMs}")
    public String JWT_EXPIRE_MS;
}
