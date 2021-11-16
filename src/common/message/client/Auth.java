package common.message.client;

import common.Credentials;
import java.io.Serializable;

/**
 * A message that contains the credentials used to authenticate an User.
 */
public class Auth extends ClientMessage<Credentials> implements Serializable {
    public Auth(Credentials content) {
        super(content);
    }
}
