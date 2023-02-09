


package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.GetReviewRequest;
import com.nashss.se.realdeal.activity.results.GetReviewResult;

public class GetReviewLambda extends LambdaActivityRunner<GetReviewRequest, GetReviewResult> implements
    RequestHandler<LambdaRequest<GetReviewRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetReviewRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                GetReviewRequest.builder()
                    .withId(path.get("id"))
                    .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetReviewActivity().handleRequest(request)
        );
    }
}