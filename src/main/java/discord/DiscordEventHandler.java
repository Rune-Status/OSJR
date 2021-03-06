package discord;

/**
 * Discord Event Handler class
 */
public interface DiscordEventHandler {
	/**
	 * Called when the connection is being disconnected
	 *
	 * @param errorCode
	 *            Error code
	 * @param message
	 *            Error message
	 */
	void disconnected(ErrorCode errorCode, String message);

	/**
	 * Called when an error occurs on the connection
	 *
	 * @param errorCode
	 *            Error code
	 * @param message
	 *            Error message
	 */
	void errored(ErrorCode errorCode, String message);

	/**
	 * Called when joining a game
	 *
	 * @param joinSecret
	 *            Join secret
	 */
	void joinGame(String joinSecret);

	/**
	 * Called when requesting to join a game
	 *
	 * @param joinRequest
	 *            Join secret
	 */
	void joinRequest(DiscordJoinRequest joinRequest);

	/**
	 * Called when the connection is ready
	 */
	void ready();

	/**
	 * Called when spectating a game
	 *
	 * @param spectateSecret
	 *            Spectate secret
	 */
	void spectateGame(String spectateSecret);
}
