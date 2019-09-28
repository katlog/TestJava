package com.saasovation.identityaccess.application;

/**
 * Created by fw on 2019/9/10
 */
public class Response {

    private Response() {
    }

    public static Builder noContent() {
        return Builder.ONE;
    }

    static class Builder {
        private Builder() {
        }
        public static Builder ONE = new Builder();

        public Response build() {
            return new Response();
        }
    }
}
