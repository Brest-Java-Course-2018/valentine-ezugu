package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServletDataAccesException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
     throw new ServletDataAccesException(" ERROR: " + response.getStatusCode()
        +" :" + response.getStatusText()
        + " :" + response.getBody());
    }
}
