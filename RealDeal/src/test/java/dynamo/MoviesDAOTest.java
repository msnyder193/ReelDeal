package dynamo;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.realdeal.dynamodb.DAO.MoviesDAO;
import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.metrics.MetricsConstants;
import com.nashss.se.realdeal.metrics.MetricsPublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MoviesDAOTest {

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private MoviesDAO moviesDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        moviesDAO = new MoviesDAO(mapper, metricsPublisher);
    }

    @Test
    public void testGetMovie() {
        String id = "123";
        Movies movie = new Movies();
        movie.setId(id);
        movie.setTitle("The Godfather");
        movie.setGenres(List.of("Crime", "Action"));
        movie.setDirector("Francis Ford Coppola");
        movie.setDescription("The Godfather");

        when(mapper.load(Movies.class, id)).thenReturn(movie);
        Movies result = moviesDAO.getMovie("123");

        verify(metricsPublisher).addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 0);
        Assertions.assertEquals("123", result.getId());
        Assertions.assertEquals("The Godfather", result.getTitle());
        Assertions.assertEquals(List.of("Crime", "Action"), result.getGenres());
        Assertions.assertEquals("Francis Ford Coppola", result.getDirector());
    }

    @Test
    public void testGetMovie_MovieNotFound() {
        when(mapper.load(Movies.class, "456")).thenReturn(null);
        moviesDAO.getMovie("456");
        verify(metricsPublisher).addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 1);
    }
}
