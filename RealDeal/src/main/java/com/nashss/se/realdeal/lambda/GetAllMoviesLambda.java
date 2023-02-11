package com.nashss.se.realdeal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.requests.GetAllMoviesRequest;
import com.nashss.se.realdeal.activity.results.GetAllMoviesResult;

public class GetAllMoviesLambda extends LambdaActivityRunner<GetAllMoviesRequest, GetAllMoviesResult>
    implements RequestHandler<LambdaRequest<GetAllMoviesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllMoviesRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                GetAllMoviesRequest.builder()
                    .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetAllMoviesActivity().handleRequest(request)
        );
    }
}
