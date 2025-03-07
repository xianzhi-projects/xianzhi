package io.xianzhi.common.oauth2;

import io.xianzhi.core.content.Context;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

/**
 * 用户详情
 *
 * @author Max
 * @since 1.0.0
 */
@Setter
@AllArgsConstructor
public abstract class OAuth2UserDetails implements OAuth2AuthenticatedPrincipal, Context, UserDetails {

}