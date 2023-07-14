package ar.com.Semillerochallengebackend.Semillerochallengebackend.utils;

import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StringUtilsTest {

    @InjectMocks
    private StringUtils stringUtils;

    @Test
    @DisplayName("It should return false when String is null")
    public void testNullOrEmpty() {
        // ARRANGE - Setting up the data that required for the test case
        String string = null;
        boolean expectedResult = false;

        // ACT - Calling a Method/Unit that is being tested
        boolean result = stringUtils.nullOrEmpty(string);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult, result);
        assertNotNull(result);
    }
}
