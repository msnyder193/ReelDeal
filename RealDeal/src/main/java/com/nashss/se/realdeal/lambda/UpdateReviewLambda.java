
package com.nashss.se.realdeal.lambda;

    import java.util.Map;

    import static com.nashss.se.realdeal.utils.NullUtils.ifNull;

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
            () -> {
                UpdateReviewRequest updateReviewRequest = input.fromBody(UpdateReviewRequest.class);
                Map<String, String> path = ifNull(input.getPathParameters(), Map.of());
                updateReviewRequest.setId(path.get("id"));
                return updateReviewRequest;
            },
            (request, serviceComponent) ->
                serviceComponent.provideUpdateReviewActivity().handleRequest(request)
        );
    }
}