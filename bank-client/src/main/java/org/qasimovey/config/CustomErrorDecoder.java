package org.qasimovey.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.qasimovey.exception.BadRequestException;
import org.qasimovey.exception.NotFoundException;
import org.qasimovey.model.HrApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String s, Response response) {
        String exceptionCause = getReasonForException(response);
        switch (response.status()) {
            case 400:
                return new BadRequestException(exceptionCause);
            case 404:
                return new NotFoundException("Verilen Kriteriyada melumat tapilmadi");
            case 500:
                return new RuntimeException("Server daxili xeta bash verdi");
            default:
                return new Exception(exceptionCause);
        }

    }

    private String getReasonForException(Response response) {
        HrApiResponse resp = null;
        String reason = null;
        try {
            Decoder.Default decoder = new Decoder.Default();
            String jsonString = (String) decoder.decode(response, String.class);
            logger.info(" decoded json is -> " + jsonString);
            resp = new ObjectMapper().readValue(jsonString, HrApiResponse.class);
            reason = resp.getMessage() + "^^" + resp.getErrors().toString();

        } catch (Exception e) {
            logger.info("cast error verdi");
        }

        return reason;
    }

}
