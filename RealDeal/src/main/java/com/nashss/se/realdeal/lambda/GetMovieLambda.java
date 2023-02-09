package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.GetMovieRequest;
import com.nashss.se.realdeal.activity.results.GetMovieResult;




public class GetMovieLambda extends LambdaActivityRunner<GetMovieRequest, GetMovieResult> implements
    RequestHandler<LambdaRequest<GetMovieRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetMovieRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                GetMovieRequest.builder()
                    .withId(path.get("id"))
                    .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetMovieActivity().handleRequest(request)
        );
    }
}