package school.demo.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdValidatorTest {
    @Test
    public void IsValidId_ShouldReturnTrue_WhenIdIsPositiveInteger() {
        //Given the id is a positive integer
        Integer givenId = 10;
        //When the givenId is validated
        boolean result = IdValidator.isValidId(givenId);
        //Then the result must be true
        assertTrue(result, "The ID should be valid.");
    }

    @Test
    public void IsValidId_ShouldReturnFalse_WhenIdIsNull() {
        //Given the id is null
        Integer givenId = null;
        //When the givenId is validated
        boolean result = IdValidator.isValidId(givenId);
        //Then the result must be false
        assertFalse(result, "The ID should be invalid because it is null.");
    }

    @Test
    public void IsValidId_ShouldReturnFalse_WhenIdIsNegative() {
        //Given the id is negative
        Integer givenId = -1;
        //When the givenId is validated
        boolean result = IdValidator.isValidId(givenId);
        //Then the id must be false
        assertFalse(result, "The ID should be invalid because it is -1.");
    }

    @Test
    public void IsValidId_ShouldReturnFalse_WhenIdIs0() {
        //Given the id is 0
        Integer givenId = 0;
        //When the givenId is validated
        boolean result = IdValidator.isValidId(givenId);
        //Then the id must be false
        assertFalse(result, "The ID should be invalid because it is 0.");
    }

    @Test
    public void IsValidId_ShouldReturnFalse_WhenIdExceedsIntMaxValue() {
        //Given the id exceeds int max value
        Integer givenId = Integer.MAX_VALUE + 1;
        //When the givenId is validated
        boolean result = IdValidator.isValidId(givenId);
        //Then the result must be false
        assertFalse(result, "The ID should be invalid because it exceeds Integer.MAX_VALUE.");
    }

    @Test
    public void IsValidId_ShouldReturnFalse_WhenIdIsIntMinValue() {
        //Given the id is below int min value
        Integer givenId = Integer.MIN_VALUE ;
        //When the givenId is validated
        boolean result = IdValidator.isValidId(givenId);
        //Then the result must be false
        assertFalse(result, "The ID should be invalid because it is Integer.MIN_VALUE.");
    }
}
