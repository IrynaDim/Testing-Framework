package prestashop.exception;

public class FailTest extends RuntimeException {
    public FailTest(Throwable throwable) {
        super(throwable);
    }
}
