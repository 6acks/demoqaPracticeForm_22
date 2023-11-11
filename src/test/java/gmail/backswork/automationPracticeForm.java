package gmail.backswork;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
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
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Egorov");
        $("#userEmail").setValue("alex@egorov.com");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("8005553535");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(2);
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__day--012").click();
        $("#subjectsInput").setValue("B").pressEnter();
        $("#subjectsInput").setValue("M").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/Bird.CR2"));
        $("#currentAddress").setValue("Tomsk").pressEnter();
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();
        //Проверка, что модальная форма открылась
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //Проверка, что в модальной форме есть введенные данные
        $$(byXpath("//td")).shouldHave(itemWithText("Alex" + " " + "Egorov"),
                itemWithText("alex@egorov.com"),
                itemWithText("Other"),itemWithText("8005553535"),
                itemWithText("12 July,1902"),
                itemWithText("Biology"+", "+"Maths"),itemWithText("Sports"),
                itemWithText("Bird.CR2"),
                itemWithText("Tomsk"),itemWithText("NCR" + " " + "Delhi"));
    }
}
