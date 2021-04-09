package id.dupat.pixel.config

import id.dupat.pixel.auth.ApiKeyInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class ApiKeyConfig(val apiKeyInterceptor: ApiKeyInterceptor): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        registry.addWebRequestInterceptor(apiKeyInterceptor).addPathPatterns(listOf("/api/users/**","/api/post/**"))
    }
}