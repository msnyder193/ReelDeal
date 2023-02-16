package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.realdeal.activity.requests.CreateReviewRequest;
import com.nashss.se.realdeal.activity.results.CreateReviewResult;

public class CreateReviewLambda extends LambdaActivityRunner<CreateReviewRequest, CreateReviewResult>
    implements RequestHandler<AuthenticatedLambdaRequest<CreateReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateReviewRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateReviewRequest unauthenticatedRequest = input.fromBody(CreateReviewRequest.class);
                return input.fromUserClaims(claims ->
                    CreateReviewRequest.builder()
                        .withText(unauthenticatedRequest.getText())
                        .withMovieDate(unauthenticatedRequest.getMovieDate())
                        .withRating(unauthenticatedRequest.getRating())
                        .withMovieId(unauthenticatedRequest.getMovieId())
                        .withId(unauthenticatedRequest.getId())
                        .withUsername(claims.get("email"))
                        .build());
            },

            (request, serviceComponent) ->
                serviceComponent.provideCreateReviewActivity().handleRequest(request)
        );

    }
}