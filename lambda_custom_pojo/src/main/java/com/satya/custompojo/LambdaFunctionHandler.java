package com.satya.custompojo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.satya.custompojo.model.CustomRequest;
import com.satya.custompojo.model.CustomResponse;

public class LambdaFunctionHandler implements RequestHandler<CustomRequest, CustomResponse> 
{
    @Override
    public CustomResponse handleRequest(CustomRequest request, Context context) 
    {
        context.getLogger().log("Input: " + request);
        
        String response = String.format("Hello %s %s.", request.getFirstName(), request.getLastName());
        return new CustomResponse(response);
    }

}
