package interfaces.element;

public class WebTablesInterface {

    // Table row containing specific First Name
    public static final String ROW_BY_FIRST_NAME = "//div[@class='rt-tr-group']//div[text()='%s']/ancestor::div[@class='rt-tr-group']";

    // Email cell in the same row as given First Name
    public static final String EMAIL_BY_FIRST_NAME = ROW_BY_FIRST_NAME + "//div[@class='rt-td'][4]";

    // Edit button in the same row as given Last Name
    public static final String EDIT_BUTTON_BY_LAST_NAME = "//div[text()='%s']/ancestor::div[@class='rt-tr-group']//span[@title='Edit']";

    // Row having Age = %s
    public static final String ROW_BY_AGE = "//div[@class='rt-td' and text()='%s']/ancestor::div[@class='rt-tr-group']";

    // All rows following after a given Age row
    public static final String FOLLOWING_ROWS_AFTER_AGE = ROW_BY_AGE + "/following-sibling::div";
}
