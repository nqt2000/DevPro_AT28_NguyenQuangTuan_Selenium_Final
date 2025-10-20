package interfaces.element;

public class CheckBoxPageInterface {
    public static final String EXPAND_ALL_BUTTON = "//button[@title='Expand all']";
    public static final String CHECKBOX_INPUT_BY_LABEL = "//span[@class='rct-title' and text()='%s']/preceding-sibling::span[@class='rct-checkbox']";
    public static final String OUTPUT_SECTION = "//div[@id='result']";
    public static final String OUTPUT_TEXT = "//div[@id='result']//span";
}
