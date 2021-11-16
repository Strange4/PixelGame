package common;
import java.io.Serializable;

public record Credentials(String username, String password) implements Serializable {
}
