package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.GetAllMovieReviewsRequest;
import com.nashss.se.realdeal.activity.results.GetAllMovieReviewsResult;

public class GetAllMovieReviewsLambda extends LambdaActivityRunner<GetAllMovieReviewsRequest, GetAllMovieReviewsResult>
    implements RequestHandler<LambdaRequest<GetAllMovieReviewsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllMovieReviewsRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                GetAllMovieReviewsRequest.builder()
                    .withMovieId(path.get("movieId"))
                    .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetAllMovieReviewsActivity().handleRequest(request)
        );
    }
}