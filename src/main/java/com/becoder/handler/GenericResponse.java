package com.becoder.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {

    private HttpStatus respStatus;
    private String status;
    private String message;
    private Object data;

    public ResponseEntity<Map<String, Object>> create() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status);
        body.put("message", message);

        if (!ObjectUtils.isEmpty(data)) {
            body.put("data", data);
        }

        return new ResponseEntity<>(body, respStatus);
    }
}
