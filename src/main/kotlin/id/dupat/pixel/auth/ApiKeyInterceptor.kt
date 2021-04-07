package id.dupat.pixel.auth

import id.dupat.pixel.exception.CustomException
import id.dupat.pixel.exception.TokenException
import id.dupat.pixel.security.JWTAuthorizationProvider
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(val jwtAuthorizationProvider: JWTAuthorizationProvider): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val auth = request.getHeader("Authorization")

        try {
            if (!auth.isNullOrEmpty() && auth.startsWith("Bearer ")) {
                val claims: Claims = jwtAuthorizationProvider.validateToken(request)
                if (claims["authorities"] != null) {
                    jwtAuthorizationProvider.setUpSpringAuthentication(claims)

                    //Token valid
                } else {
                    throw TokenException("Authorization header failed")
                }
            } else {
                throw TokenException("Authorization header required")
            }
        } catch (e: ExpiredJwtException) {
            throw TokenException("Token expired")
        } catch (e: SignatureException) {
            throw CustomException("Invalid token signature")
        }
        catch (e: MalformedJwtException){
            throw CustomException("Invalid token")
        }
        catch (e: Exception) {
            throw CustomException("Authorization header failed")
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
    }

}