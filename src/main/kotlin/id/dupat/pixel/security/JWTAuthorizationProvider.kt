package id.dupat.pixel.security

import id.dupat.pixel.exception.CustomException
import id.dupat.pixel.util.getJWTToken
import io.jsonwebtoken.*
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import java.util.stream.Collectors
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest
import java.util.*
import javax.servlet.ServletException

@Component
class JWTAuthorizationProvider {

    private val HEADER: String = "Authorization"
    private val PREFIX: String = "Bearer "
    private val SECRET: String = "SecretKey"

//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        chain: FilterChain
//    ) {
//        try {
//            if (checkJWTToken(request, response)) {
//                val claims: Claims = validateToken(request)
//                if (claims["authorities"] != null) {
//                    setUpSpringAuthentication(claims)
//                } else {
//                    SecurityContextHolder.clearContext()
//                }
//            } else {
//                SecurityContextHolder.clearContext()
//            }
//            chain.doFilter(request, response)
//        } catch (e: ExpiredJwtException) {
//            response.status = HttpServletResponse.SC_FORBIDDEN
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
//            throw CustomException("Invalid token signature")
//        } catch (e: SignatureException ) {
//            response.status = HttpServletResponse.SC_BAD_REQUEST
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.message)
//            throw CustomException("Invalid token signature")
//        }
//        catch (e: MalformedJwtException){
//            response.status = HttpServletResponse.SC_FORBIDDEN
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
//            throw CustomException("Invalid token signature")
//        }
//        catch (e: Exception) {
//            response.status = HttpServletResponse.SC_FORBIDDEN
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
//            throw CustomException("Invalid token signature")
//        }

//        throw CustomException("Invalid token signature")
//
//    }

    public fun createToken(email: String): String{
        val grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER")

        val token = Jwts
            .builder()
            .setId("Pixel")
            .setSubject(email)
            .claim("authorities",
                grantedAuthorities.stream()
                    .map { obj: GrantedAuthority -> obj.authority }
                    .collect(Collectors.toList()))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 600000))
            .signWith(
                SignatureAlgorithm.HS512,
                SECRET.toByteArray()
            ).compact()

        return "Bearer $token"
    }

    public fun validateToken(request: WebRequest): Claims {
        val jwtToken: String = request.getHeader(HEADER)!!.replace(PREFIX, "")
        return Jwts.parser().setSigningKey(SECRET.toByteArray()).parseClaimsJws(jwtToken).body
    }

    public fun setUpSpringAuthentication(claims: Claims) {
        val authorities = claims["authorities"] as List<String>
        val auth = UsernamePasswordAuthenticationToken(claims.subject, null,
            authorities.stream().map { SimpleGrantedAuthority(it) }.collect(Collectors.toList()))
        SecurityContextHolder.getContext().authentication = auth
    }

    public fun checkJWTToken(request: HttpServletRequest, res: HttpServletResponse): Boolean {
        val authenticationHeader = request.getHeader(HEADER)
        return !(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
    }
}