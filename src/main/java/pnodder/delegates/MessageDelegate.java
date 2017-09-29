package pnodder.delegates;

import pnodder.model.Email;

/**
 * Similar to MDP but has no dependencies on the JMS API
 */
public class MessageDelegate {

    public void handleMessage(Email message) {
        System.out.println("delegate got it");
    }

}
