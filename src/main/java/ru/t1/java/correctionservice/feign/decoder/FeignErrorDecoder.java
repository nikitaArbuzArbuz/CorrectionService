package ru.t1.java.correctionservice.feign.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import ru.t1.java.correctionservice.dto.TransactionErrorDto;
import ru.t1.java.correctionservice.utils.exceptions.FeignBadRequestException;

import java.io.IOException;
import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            TransactionErrorDto errorDto;

            try (InputStream bodyIs = response.body().asInputStream()) {
                errorDto = objectMapper.readValue(bodyIs, TransactionErrorDto.class);
            } catch (IOException e) {
                return new RuntimeException("Failed to process response body", e);
            }

            return new FeignBadRequestException(response.status(), response.reason(), errorDto);
        }

        return new Default().decode(methodKey, response);
    }
}
