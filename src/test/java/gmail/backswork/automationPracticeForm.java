package gmail.backswork;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;



public class automationPracticeForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Egorov");
        $("#userEmail").setValue("alex@egorov.com");
        $("label[for=gender-radio-1]").click();
        $("#userNumber").setValue("8005553535");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(2);
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__day--012").click();
        $("#subjectsInput").setValue("B").pressEnter();
        $("#subjectsInput").setValue("M").pressEnter();
        $("label[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/Bird.CR2"));
        $("#currentAddress").setValue("Tomsk").pressEnter();
        $("#state").click();
        $("#react-select-3-option-3").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();
        //Проверка, что модальная форма открылась
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //Проверка, что в модальной форме есть введенные данные
        $$(byXpath("//td")).shouldHave(itemWithText("Alex" + " " + "Egorov"),
                itemWithText("alex@egorov.com"),
                itemWithText("Male"),itemWithText("8005553535"),
                itemWithText("12 July,1902"),
                itemWithText("Biology"+", "+"Maths"),itemWithText("Reading"),
                itemWithText("Bird.CR2"),
                itemWithText("Tomsk"),itemWithText("Rajasthan" + " " + "Jaipur"));
    }
}
