package prestashop.exception;

public class FailTest extends RuntimeException {
    public FailTest(String e) {
        super(e);
    }
    public FailTest(Throwable throwable) {
        super(throwable);
    }
}
