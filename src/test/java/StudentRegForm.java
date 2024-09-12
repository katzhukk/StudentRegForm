import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegForm {

    @BeforeAll
    static void beforeAll() {/* Всегда пишется со static. Вызывается один перед всеми тестами в этом тестовом классе*/
        Configuration.browserSize = "1920x1080";        /* Задаем разрешение браузера */
        Configuration.baseUrl = "https://demoqa.com";   /* Открывает основную страницу сайта */
        Configuration.pageLoadStrategy = "eager";       /* Не ждем, когда загрузится полностью страница, чтобы долго не ждать*/
        //Configuration.holdBrowserOpen = true;         /* Не дает закрыть тесту браузер. Нужно только для отладки */
    }

    @Test
    void fillFormTest() {
        String firstName = "Kate";
        String lastName = "Zhukova";
        String userEmail = "kate@zhukova.ru";
        String userNumber = "8912345678";
        String dateOfBirthInput = "23 June,2000";
        String subjectsInput = "Math";
        String currentAddress = "Moscow, Pushkin street, 1";
        String State = "NCR";
        String city = "Delhi";

        open("/automation-practice-form"); /*Открывает форму automation-practice-form*/
        /*Заполнение формы студента:*/
        $("#firstName").setValue(firstName);      /*Вводит в форму firstName. Можно сократить: для id= - #, для класса "точка"*/
        $("#lastName").setValue(lastName);        /*Вводит в форму lastName. Можно сократить: для id= - #, для класса "точка"*/
        $("#userEmail").setValue(userEmail);      /*Вводит в форму Email*/
        $("#gender-radio-2").parent().click();    /*Выбор пола по клику*/
        $("#userNumber").setValue(userNumber);    /*Вводит в форму номер телефона */

        $("#dateOfBirthInput").click();    /*Клик по Дате Рождения*/
        $(".react-datepicker__month-select").find("option[value='5']").click();     /*Выбирает июнь*/
        $(".react-datepicker__year-select").find("option[value='2000']").click();   /*Выбирает год*/
        $(".react-datepicker__day--023").click();                                      /*Выбирает день*/

        $("#subjectsInput").setValue(subjectsInput).pressEnter();    /*Вводит в форму предметы*/
        $("#hobbies-checkbox-3").parent().click();                   /*Выбор хобби Music по клику*/
        $("#uploadPicture").uploadFromClasspath("images/StudentRegForm.jpg");     /*Выбор картинку по клику*/
        $("#currentAddress").setValue(currentAddress);                /*Вводит в форму предметы*/
        $("#react-select-3-input").setValue(State).pressEnter();      /*Вводит в форму штат*/
        $("#react-select-4-input").setValue(city).pressEnter();       /*Вводит в форму город*/

        $("#submit").click();    /*Клик по кнопке submit*/

        /*Проверка параметров:*/
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));/*Проверяет имя и фамилию*/
        $(".table-responsive").shouldHave(text(userEmail));                 /*Проверяет Email*/
        $(".table-responsive").shouldHave(text("Female"));                  /*Проверяет пол*/
        $(".table-responsive").shouldHave(text(userNumber));                /*Проверяет номер*/
        $(".table-responsive").shouldHave(text(dateOfBirthInput));          /*Проверяет День Рождения*/
        $(".table-responsive").shouldHave(text(subjectsInput));             /*Проверяет предметы*/
        $(".table-responsive").shouldHave(text("Music"));                   /*Проверяет предметы*/
        $(".table-responsive").shouldHave(text("StudentRegForm.jpg"));      /*Проверяет картинку*/
        $(".table-responsive").shouldHave(text(currentAddress));            /*Проверяет адрес*/
        $(".table-responsive").shouldHave(text(State + " " + city));        /*Проверяет штат и город*/
    }
    @AfterAll
    static void AfterAll() {/* Всегда пишется со static. Вызывается один после всеми тестов в этом тестовом классе*/
        System.out.println("Test, StudentRegForm - StudentRegFormTest");
    }
}