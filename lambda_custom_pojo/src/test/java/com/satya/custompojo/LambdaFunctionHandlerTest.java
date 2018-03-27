package com.satya.custompojo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.satya.custompojo.model.CustomRequest;
import com.satya.custompojo.model.CustomResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

	static CustomRequest request = null;

    @BeforeClass
    public static void createInput() throws IOException {
    	
    	request = new CustomRequest("Satyendra", "Singh");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        ctx.setFunctionName(" Lambda custom pojo input and output ");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();

        CustomResponse output = handler.handleRequest(request, ctx);

        Assert.assertEquals("Hello Satyendra Singh.", output.getMsg());
    }
}
