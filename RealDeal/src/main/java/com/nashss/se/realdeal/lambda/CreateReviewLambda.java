package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.realdeal.activity.requests.CreateReviewRequest;
import com.nashss.se.realdeal.activity.results.CreateReviewResult;

public class CreateReviewLambda extends LambdaActivityRunner<CreateReviewRequest, CreateReviewResult>
    implements RequestHandler<LambdaRequest<CreateReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateReviewRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateReviewRequest.class),
            (request, serviceComponent) ->
                serviceComponent.provideCreateReviewActivity().handleRequest(request)
        );

    }
}