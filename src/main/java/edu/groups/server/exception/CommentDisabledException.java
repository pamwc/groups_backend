package edu.groups.server.exception;

/**
 * Created by Dawid on 25.11.2017 at 14:20.
 */
public class CommentDisabledException extends RuntimeException {
    public CommentDisabledException() {
        super();
    }

    public CommentDisabledException(String message) {
        super(message);
    }

    public CommentDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentDisabledException(Throwable cause) {
        super(cause);
    }

    protected CommentDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
