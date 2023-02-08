package dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nashss.se.realdeal.exception.UserNotFoundException;
import com.nashss.se.realdeal.metrics.MetricsConstants;
import com.nashss.se.realdeal.metrics.MetricsPublisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.realdeal.dynamodb.models.Users;

public class UsersDAOTest {
    private static final String USERNAME = "test_user";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "password";

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private MetricsPublisher metricsPublisher;
    private UsersDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dao = new UsersDAO(mapper, metricsPublisher);
    }

    @Test
    public void testGetUser_callwithPartitionKey_returnsUser() {
        Users expectedUser = new Users();
        expectedUser.setUsername(USERNAME);
        expectedUser.setEmail(EMAIL);
        expectedUser.setPassword(PASSWORD);

        when(mapper.load(Users.class, USERNAME)).thenReturn(expectedUser);

        Users actualUser = dao.getUser(USERNAME);
        verify(metricsPublisher).addCount(MetricsConstants.GETUSER_USERNOTFOUND_COUNT, 0);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUser_userNotFound_throwsException() {
        when(mapper.load(Users.class, "JaneDoe")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> dao.getUser("JaneDoe"));
        verify(metricsPublisher).addCount(MetricsConstants.GETUSER_USERNOTFOUND_COUNT, 1);
    }
}
