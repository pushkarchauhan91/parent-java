private static final ScopedValue<String> requestId = ScopedValue.newInstance();

void main() {
    handleRequest(UUID.randomUUID().toString());
}

static void handleRequest(String reqId) {
    ScopedValue.where(requestId, reqId).run(() -> {
        log("Start processing request");
        // Simulate some processing
        authenticate();
        try {
            fetchData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log("Finished processing request");
    });
}

static void authenticate() {
    log("Authenticating request...");
}

static void fetchData() throws InterruptedException {
    log("Fetching data from DB...");

    // Capture the current binding value
    String currentReqId = requestId.get();

    // Re-bind explicitly inside child thread
    Thread t = new Thread(() ->
            ScopedValue.where(requestId, currentReqId).run(() -> {
                log("Child thread fetching related data...");
            })
    );
    t.start();
    t.join(); // wait for child
}

static void log(String message) {
    IO.println("[" + requestId.get() + "] " + message);
}