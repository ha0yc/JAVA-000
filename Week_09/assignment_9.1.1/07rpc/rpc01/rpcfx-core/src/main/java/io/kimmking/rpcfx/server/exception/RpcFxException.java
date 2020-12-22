package io.kimmking.rpcfx.server.exception;

public class RpcFxException extends Exception {
    public RpcFxException() {
    }

    public RpcFxException(String message) {
        super(message);
    }

    public RpcFxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcFxException(Throwable cause) {
        super(cause);
    }

    public RpcFxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
