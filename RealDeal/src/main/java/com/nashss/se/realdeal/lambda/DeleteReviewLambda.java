package com.nashss.se.realdeal.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.DeleteReviewRequest;
import com.nashss.se.realdeal.activity.results.DeleteReviewResult;

public class DeleteReviewLambda extends LambdaActivityRunner<DeleteReviewRequest, DeleteReviewResult>
    implements RequestHandler<AuthenticatedLambdaRequest<DeleteReviewRequest>, LambdaResponse> {

    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return a LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteReviewRequest> input, Context context) {
        DeleteReviewRequest unauthenticatedRequest = input.fromPath(path -> DeleteReviewRequest.builder()
            .withId(path.get("id"))
            .build());

        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                DeleteReviewRequest.builder()
                    .withId(unauthenticatedRequest.getId())
                    .withUsername(claims.get("email"))
                    .build()),
            (request, serviceComponent) -> serviceComponent.provideDeleteReviewActivity().handleRequest(request)
        );
    }
}