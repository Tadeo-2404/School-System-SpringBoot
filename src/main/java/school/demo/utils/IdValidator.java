package school.demo.utils;

public class IdValidator {
    /**
     * Validates that the ID is not null and -1 and is within the valid range of integers.
     *
     * @param id the ID to validate
     * @return true if the ID is valid, false otherwise
     */
    public static boolean isValidId(Integer id) {
        return id != null && id > 0 && id >= Integer.MIN_VALUE && id <= Integer.MAX_VALUE;
    }
}
