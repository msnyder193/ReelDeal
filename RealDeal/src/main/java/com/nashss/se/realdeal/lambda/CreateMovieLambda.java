package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.CreateMovieRequest;
import com.nashss.se.realdeal.activity.results.CreateMovieResult;

public class CreateMovieLambda extends LambdaActivityRunner<CreateMovieRequest, CreateMovieResult>
    implements RequestHandler<LambdaRequest<CreateMovieRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateMovieRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateMovieRequest.class),
            (request, serviceComponent) ->
                serviceComponent.provideCreateMovieActivity().handleRequest(request)
        );

    }
}