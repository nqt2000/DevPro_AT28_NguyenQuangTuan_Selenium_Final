package interfaces.element;

public class TextBoxPageInterface {
    public static final String FULL_NAME_INPUT = "//input[@id='userName']";
    public static final String EMAIL_INPUT = "//input[@id='userEmail']";
    public static final String CURRENT_ADDRESS_TEXTAREA = "//textarea[@id='currentAddress']";
    public static final String PERMANENT_ADDRESS_TEXTAREA = "//textarea[@id='permanentAddress']";
    public static final String SUBMIT_BUTTON = "//button[@id='submit']";

    // Output area locators
    public static final String OUTPUT_SECTION = "//div[@id='output']";
    public static final String OUTPUT_NAME = "//p[@id='name']";
    public static final String OUTPUT_EMAIL = "//p[@id='email']";
    public static final String OUTPUT_CURRENT_ADDRESS = "//p[@id='currentAddress']";
    public static final String OUTPUT_PERMANENT_ADDRESS = "//p[@id='permanentAddress']";
}
