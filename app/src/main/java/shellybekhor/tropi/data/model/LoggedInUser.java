package shellybekhor.tropi.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    // Class members //
    private String userId;
    private String displayName;

    /**
     * The logged in user
     * @param userId THe user ID
     * @param displayName The user name
     */
    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    /**
     * @return The user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return The user name
     */
    public String getDisplayName() {
        return displayName;
    }
}
