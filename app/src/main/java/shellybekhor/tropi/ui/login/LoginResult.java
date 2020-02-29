package shellybekhor.tropi.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    // CLASS MEMBERS //
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    // CONSTRUCTORS //
    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    // GETTERS //
    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
