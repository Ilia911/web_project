package com.epam.jwd.web.servlet.command;

public enum DefaultCommand implements Command {
    INSTANCE;

    private ResponseContext responseContext = new ResponseContext() {
        @Override
        public String getPage() {
            return null;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        return null;
    }
}
