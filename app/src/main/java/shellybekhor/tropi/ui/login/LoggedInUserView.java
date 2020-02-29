package shellybekhor.tropi.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;

    /**
     * Displaying the login view
     */
    public LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
