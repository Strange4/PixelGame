package common.message.server;

import game.entity.types.Player;

public final class LoginAnswer extends ServerMessage<Player[]> {

    /**
     *
     * @param players
     * @param id
     */
    public LoginAnswer(Player[] players, int id) {
        super(players, id);
    }
}
