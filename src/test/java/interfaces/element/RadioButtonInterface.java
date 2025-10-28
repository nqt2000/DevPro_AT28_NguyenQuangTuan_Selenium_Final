package interfaces.element;

public class RadioButtonInterface {
    public static final String RADIO_BUTTON_BY_LABEL = "//label[text()='%s']";
    public static final String OUTPUT_TEXT = "//span[@class='text-success']";
    public static final String RADIO_INPUT_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
}
