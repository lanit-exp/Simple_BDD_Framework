package authorization;

import java.util.List;

public class Authorization {

    private List<AuthValues> authValues;

    public Authorization() {
    }

    public Authorization(List<AuthValues> authValues) {
        this.authValues = authValues;
    }

    public List<AuthValues> getAuthValues() {
        return authValues;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "authValues=" + authValues +
                '}';
    }
}
