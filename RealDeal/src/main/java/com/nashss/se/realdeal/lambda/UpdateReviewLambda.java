
package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.UpdateReviewRequest;
import com.nashss.se.realdeal.activity.results.UpdateReviewResult;

public class UpdateReviewLambda
    extends LambdaActivityRunner<UpdateReviewRequest, UpdateReviewResult>
    implements RequestHandler<LambdaRequest<UpdateReviewRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateReviewRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(UpdateReviewRequest.class),
            (request, serviceComponent) ->
                serviceComponent.provideUpdateReviewActivity().handleRequest(request)
        );
    }
}