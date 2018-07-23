package com.tambapps.website.model.payload;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthenticationResponse {
    public final static String TOKEN_TYPE  = "Bearer";
    @NonNull
    private String accessToken;
    private String tokenType = TOKEN_TYPE;

}