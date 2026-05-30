package Validation;

public class ValidatorFactory {
    private ValidatorFactory() {
    }

    public static IValidator createDefault() {
        return new Validator();
    }
}
