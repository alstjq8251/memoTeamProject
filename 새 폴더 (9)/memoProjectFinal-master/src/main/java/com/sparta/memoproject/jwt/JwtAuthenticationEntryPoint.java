package com.sparta.memoproject.jwt;

import com.sparta.memoproject.model.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
      String exception = request.getAttribute("exception").toString();

      if(exception.equals("null") ) {
         setResponse(response, Code.UNKNOWN_ERROR);
      }
      else if(exception.equals(Code.UNKNOWN_ERROR.getCode())){
         setResponse(response, Code.UNKNOWN_ERROR);
      }
      //잘못된 타입의 토큰인 경우
      else if(exception.equals(Code.WRONG_TYPE_TOKEN.getCode())) {
         setResponse(response, Code.WRONG_TYPE_TOKEN);
      }
      //토큰 만료된 경우
      else if(exception.equals(Code.EXPIRED_TOKEN.getCode())) {
         setResponse(response, Code.EXPIRED_TOKEN);
      }
      //지원되지 않는 토큰인 경우
      else if(exception.equals(Code.UNSUPPORTED_TOKEN.getCode())) {
         setResponse(response, Code.UNSUPPORTED_TOKEN);
      }
      else {
         setResponse(response, Code.ACCESS_DENIED);
      }
   }
   //한글 출력을 위해 getWriter() 사용
   private void setResponse(HttpServletResponse response, Code code) throws IOException {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


      JSONObject responseJson = new JSONObject();
      responseJson.put("message", code.getMessage());
      responseJson.put("code", code.getCode());
      if(code.getCode().equals("1004"))
         response.setStatus(HttpServletResponse.SC_OK);
      else if(code.getCode().equals("1003"))
         response.setStatus(HttpServletResponse.SC_OK);
      else if(code.getCode().equals("1005"))
         response.setStatus(HttpServletResponse.SC_OK);
      else if(code.getCode().equals("1006"))
         response.setStatus(HttpServletResponse.SC_OK);
      else if(code.getCode().equals("1007"))
         response.setStatus(HttpServletResponse.SC_OK);

      response.getWriter().print(responseJson);
   }
}