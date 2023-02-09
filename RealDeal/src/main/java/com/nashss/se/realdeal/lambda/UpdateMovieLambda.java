package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.UpdateMovieRequest;
import com.nashss.se.realdeal.activity.results.UpdateMovieResult;

public class UpdateMovieLambda
    extends LambdaActivityRunner<UpdateMovieRequest, UpdateMovieResult>
    implements RequestHandler<LambdaRequest<UpdateMovieRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateMovieRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(UpdateMovieRequest.class),
            (request, serviceComponent) ->
                serviceComponent.provideUpdateMovieActivity().handleRequest(request)
        );
    }
}
