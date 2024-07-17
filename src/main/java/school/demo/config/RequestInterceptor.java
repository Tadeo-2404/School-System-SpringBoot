package school.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    private String secretKey;

    public RequestInterceptor(String secret_key) {
        this.secretKey = secret_key;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("authorization");

        boolean validToken = Encryption.isValidCipherText(token);

        //valido -> Jrsh+ly/WuZPjHdRP6WMKA==:h2dehG1fz98VgOKg7+oVtQ==
        if (!validToken) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false; 
        }

        String decrypted = Encryption.decrypt(token, secretKey);
        if(decrypted == null) {
            return false;
        }

        return true;
    }
}