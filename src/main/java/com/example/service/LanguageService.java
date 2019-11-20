package com.example.service;

import com.example.dao.LanguageRepository;
import com.example.dao.LanguageRepositoryImpl;
import com.example.entity.LanguageEntity;

import java.util.HashMap;
import java.util.Map;

public class LanguageService {

    private LanguageRepository languageRepository = new LanguageRepositoryImpl();

    private Map<String, String> russianMap = new HashMap<String, String>() {{
        put("Назад", "Назад");
        put("Главное меню", "Главное меню");
        put("Язык", "Язык");
        put("Русский", "Русский");
        put("Английский", "Английский");
        put("Регистрация", "Регистрация");
        put("Приветствую! Я телеграм хайп-бот! \nЗарегистрируйтесь!\nHi! I'm bot! Go to registration!", "Приветствую! Я телеграм хайп-бот! \nЗарегистрируйтесь!\nHi! I'm bot! Go to registration!");
        put("Добро пожаловать!", "Добро пожаловать!");
        put("Зарегистрируйтесь/Registration", "Зарегистрируйтесь/Registration");
        put("Введите ID пригласившего Вас пользователя!", "Введите ID пригласившего Вас пользователя!");
        put("Вы уже зарегистрированы в системе", "Вы уже зарегистрированы в системе");
        put("Пользователь с таким ID не найден! \nЗарегистрируйтесь!", "Пользователь с таким ID не найден! \nЗарегистрируйтесь!");
        put("Введите номер Вашей кредитной карты, 16 цифр!", "Введите номер Вашей кредитной карты, 16 цифр!");
        put("Вы успешно зарегистрированы! Ваш ID: ", "Вы успешно зарегистрированы! Ваш ID: ");
        put("У вас уже есть кредитная карта!", "У вас уже есть кредитная карта!");
        put("Сделать заказ", "Сделать заказ");
        put("В данный момент нет места в матрице, ожидаются оплаты пользователей \nПопробуйте позже", "В данный момент нет места в матрице, ожидаются оплаты пользователей \nПопробуйте позже");
        put("Матрица закрылась!", "Матрица закрылась!");
        put("Ваша матрица еще не закрылась!", "Ваша матрица еще не закрылась!");
        put("Подтвердить оплату", "Подтвердить оплату");
        put("Вы подтвердили платеж пользователя ID ", "Вы подтвердили платеж пользователя ID ");
        put("Платеж этого пользователя уже подтвержден!", "Платеж этого пользователя уже подтвержден!");
        put("Покупка уровня подтверждена", "Покупка уровня подтверждена");
        put("Теперь у вас уровень ", "Теперь у вас уровень ");
        put("Моя команда", "Моя команда");
        put("Тут ваша команда", "Тут ваша команда");
        put("Попросить подтвердить оплату", "Попросить подтвердить оплату");
        put("У Вас новый заказ!", "У Вас новый заказ!");
        put(" Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ", " Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ");
        put("Подтвердите", "Подтвердите");
        put("Состав моей команды", "Состав моей команды");
        put("Сменить карту", "Сменить карту");
        put("Введите новый номер карты \nФормат ввода: new 0000000000000000", "Введите новый номер карты \nФормат ввода: new 0000000000000000");
        put("Аккаунт", "Аккаунт");
        put("Результаты опросов", "Результаты опросов");
        put("Поддержка", "Поддержка");
        put("Подтвердить оплату ", "Подтвердить оплату ");
        put("К следуещему шагу", "К следуещему шагу");
        put("Внести", "Внести");
        put("Оплата обязательного взноса подтверждена \nНажмите сделать подарок", "Оплата обязательного взноса подтверждена \nНажмите сделать подарок");
        put("Покупуа уровня подтверждена", "Покупуа уровня подтверждена");
        put("Спонсор спонсора изменил номер карты \nПроизведите оплату на эту карту:", "Спонсор спонсора изменил номер карты \nПроизведите оплату на эту карту:");
        put("Номер карты успешно изменен!", "Номер карты успешно изменен!");
        put("Номер карты не был изменен, попробуйте еще раз!", "Номер карты не был изменен, попробуйте еще раз!");
        put("Статистика хайпа \n", "Статистика хайпа \n");
        put("Всего пользователей:", "Всего пользователей:");
        put("Всего пользователей 1-го уровня:", "Всего пользователей 1-го уровня:");
        put("Всего пользователей 2-го уровня:", "Всего пользователей 2-го уровня:");
        put("Всего пользователей 3-го уровня:", "Всего пользователей 3-го уровня:");
        put("Всего пользователей 4-го уровня:", "Всего пользователей 4-го уровня:");
        put("Всего пользователей 5-го уровня:", "Всего пользователей 5-го уровня:");
        put("Всего пользователей вне матрицы:", "Всего пользователей вне матрицы:");
        put("Всего не купивших уровень у пользователей в матрице:", "Всего не купивших уровень у пользователей в матрице:");
        put("Результаты опросов \n", "Результаты опросов \n");
        put("Ваша команда: \n", "Ваша команда: \n");
        put("Спонсор ID", "Спонсор ID");
        put("Уровень", "Уровень");
        put("Реферал ID", "Реферрал ID");
        put("Статус оплаты:", "Статус оплаты:");
        put("Партнер ID", "Партнер ID");
        put("Аккаунт:", "Аккаунт:");
        put("Уровень:", "Уровень:");
        put("Новых заказов:", "Новых заказов:");
        put("Ваш ID:", "Ваш ID:");
        put("Баланс:", "Баланс:");
        put("Номер карты:", "Номер карты:");
        put("Оплатил", "Оплатил");
        put("Не оплачено", "Не оплачено");
        put("Подтвердить перевод", "Подтвердить перевод");
        put("Оплата добровольного взноса подтверждена! Теперь Вы можете приобрести уровень.", "Оплата добровольного взноса подтверждена! Теперь Вы можете приобрести уровень.");

        put("Ваш заказ отправлен Вашему спонсору. После подтверждения Вашим спонсором получения денег Вам будет присвоен Уровень.", "Ваш заказ отправлен Вашему спонсору. После подтверждения Вашим спонсором получения денег Вам будет присвоен Уровень.");
        put("Сделайте добровольный взнос. Переведите 13 UAH участнику проекта на его карту 4149 4393 9313 0137. После перевода средств нажмите кнопку \"подтвердить перевод\". Если Вы нажали кнопку \"подтвердить перевод\", но не перевели средства, то в течении суток Ваш аккаунт будет удален!", "Сделайте добровольный взнос. Переведите 13 UAH участнику проекта на его карту 4149 4393 9313 0137. После перевода средств нажмите кнопку \"подтвердить перевод\". Если Вы нажали кнопку \"подтвердить перевод\", но не перевели средства, то в течении суток Ваш аккаунт будет удален!");
        put("Чтобы приобрести уровень, переведите средства Вашему спонсору (цену за каждый уровень можно узнать в разделе FAQ).\nВ примечании к платежу напишите свой ID. После перевода средств нажмите кнопку \"Попросить подтвердить оплату\" \nНомер карты спонсора для оплаты: ", "Чтобы приобрести уровень, переведите средства Вашему спонсору (цену за каждый уровень можно узнать в разделе FAQ).\nВ примечании к платежу напишите свой ID. После перевода средств нажмите кнопку \"Попросить подтвердить оплату\" \nНомер карты спонсора для оплаты: ");
        put("Arigato – это не инвестиционный клуб, не MLM, не компания и не корпорация, а также не коммерческое предприятие. Здесь нет ни инвестиций, ни счетов, ни продуктов, ни услуг. Никто не «делает» и не зарабатывает деньги.", "Arigato – это не инвестиционный клуб, не MLM, не компания и не корпорация, а также не коммерческое предприятие. Здесь нет ни инвестиций, ни счетов, ни продуктов, ни услуг. Никто не «делает» и не зарабатывает деньги.");
        put("Именно поэтому мы приглашаем Вас принять участие в программе Arigato – автоматизированной глобальной системе по обмену подарками.", "Именно поэтому мы приглашаем Вас принять участие в программе Arigato – автоматизированной глобальной системе по обмену подарками.");
        put("Arigato – это независимое объединение людей, которые добровольно и сознательно собрались вместе с благотворительными целями.", "Arigato – это независимое объединение людей, которые добровольно и сознательно собрались вместе с благотворительными целями.");
        put("Arigato - это Бот, который создан для того, чтобы дать возможность друзьям и друзьям друзей обмениваться подарками в неофициальной безопасной обстановке", "Arigato - это Бот, который создан для того, чтобы дать возможность друзьям и друзьям друзей обмениваться подарками в неофициальной безопасной обстановке");
        put("Система Arigato является первой совершенно безупречной, полностью автоматизированной системой обмена подарками из рук в руки через кредитные карты.", "Система Arigato является первой совершенно безупречной, полностью автоматизированной системой обмена подарками из рук в руки через кредитные карты.");
        put("Все операции в системе являются актами дарения.", "Все операции в системе являются актами дарения.");
    }};

    private Map<String, String> englishMap = new HashMap<String, String>() {{
        put("Назад", "Back");
        put("Главное меню", "Main menu");
        put("Язык", "Language");
        put("Русский", "Russian");
        put("Английский", "English");
        put("Регистрация", "Registration");
        put("Приветствую! Я телеграм хайп-бот! \nЗарегистрируйтесь!\nHi! I'm bot! Go to registration!", "Приветствую! Я телеграм хайп-бот! \nЗарегистрируйтесь!\nHi! I'm bot! Go to registration!");
        put("Добро пожаловать!", "Welcome!");
        put("Зарегистрируйтесь/Registration", "Зарегистрируйтесь/Registration");
        put("Введите ID пригласившего Вас пользователя!", "Enter the ID of the user who invited you!");
        put("Вы уже зарегистрированы в системе", "You are already registered");
        put("Пользователь с таким ID не найден! \nЗарегистрируйтесь!", "User with this ID not found! \nRegistration!");
        put("Введите номер Вашей кредитной карты, 16 цифр!", "Enter your credit card number, 16 digits!");
        put("Вы успешно зарегистрированы! Ваш ID: ", "You have successfully registered! Your ID: ");
        put("У вас уже есть кредитная карта!", "You already have a credit card!");
        put("Сделать заказ", "Make Order");
        put("В данный момент нет места в матрице, ожидаются оплаты пользователей \nПопробуйте позже", "There is currently no space in the matrix, user payments are expected \nTry later");
        put("Матрица закрылась!", "The matrix is closed!");
        put("Ваша матрица еще не закрылась!", "Your matrix has not closed yet!");
        put("Подтвердить оплату", "Confirm payment");
        put("Вы подтвердили платеж пользователя ID: ", "You have confirmed the payment of the user ID: ");
        put("Платеж этого пользователя уже подтвержден!", "This user's payment has already been confirmed!");
        put("Теперь у вас уровень ", "Now you have a level ");
        put("Моя команда", "My team");
        put("Тут ваша команда", "Here is your team");
        put("Попросить подтвердить оплату", "Ask to confirm payment");
        put("У Вас новый заказ!", "You have a new order!");
        put(" Пожалуйста, проверьте баланс Вашей карты и подтвердите мою оплату \nМой ID: ", " Please check your card balance and confirm my payment \nMy ID: ");
        put("Подтвердите", "Confirm");
        put("Состав моей команды", "The composition of my team");
        put("Сменить карту", "Change card");
        put("Введите новый номер карты \nФормат ввода: new 0000000000000000", "Enter new card number\nInput format: new 0000000000000000");
        put("Аккаунт", "Account");
        put("Результаты опросов", "Survey results");
        put("Поддержка", "Support");
        put("Подтвердить оплату ", "Confirm payment ");
        put("Пропустить этот шаг", "Skip this step");
        put("Внести", "Donate");
        put("Оплата добровольного взноса подтверждена! Теперь Вы можете приобрести уровень \nНажмите Сделать заказ", "Payment of a voluntary contribution is confirmed! Now you can purchase a level \nClick Make Order");
        put("Покупка уровня подтверждена", "Level purchase confirmed");
        put("Спонсор спонсора изменил номер карты \nПроизведите оплату на эту карту:", "Sponsor Sponsor Changes Credit Card Number \nMake a payment on this credit card:");
        put("Номер карты успешно изменен!", "Card number changed successfully!");
        put("Номер карты не был изменен, попробуйте еще раз!", "Card number has not been changed, try again!");
        put("Статистика проекта \n", "Project statistics \n");
        put("Всего пользователей:", "Total Users:");
        put("Всего пользователей 1-го уровня:", "Total 1st Level Users:");
        put("Всего пользователей 2-го уровня:", "Total 2nd Level Users:");
        put("Всего пользователей 3-го уровня:", "Total Level 3 Users:");
        put("Всего пользователей 4-го уровня:", "Total Level 4 Users:");
        put("Всего пользователей 5-го уровня:", "Total Level 5 Users:");
        put("Всего пользователей вне матрицы:", "Total Users Out of Matrix:");
        put("Всего не купивших уровень у пользователей в матрице:", "Total users who did not buy the level in the matrix:");
        put("Результаты опросов \n", "Survey results \n");
        put("Ваша команда: \n", "Your team: \n");
        put("Спонсор ID", "Sponsor ID:");
        put("Уровень", "Level");
        put("Реферал ID", "Referral ID:");
        put("Статус оплаты:", "Payment State:");
        put("Партнер ID", "Partner ID:");
        put("Аккаунт:", "Account:");
        put("Уровень:", "Level:");
        put("Новых заказов:", "New orders:");
        put("Ваш ID:", "Your ID:");
        put("Баланс:", "Balance:");
        put("Номер карты:", "Card number:");
        put("Оплатил", "Paid");
        put("Не оплачено", "Not paid");
        put("Подтвердить перевод", "Сonfirm transfer");
        put("Оплата добровольного взноса подтверждена! Теперь Вы можете приобрести уровень.", "Payment of a voluntary contribution is confirmed! Now you can get a level.");

        put("Ваш заказ отправлен Вашему спонсору. После подтверждения Вашим спонсором получения денег Вам будет присвоен Уровень.", "Your order has been sent to your sponsor. After your sponsor confirms the receipt of money, you will be assigned a Level.");
        put("Сделайте добровольный взнос. Переведите 13 UAH участнику проекта на его карту 4149 4393 9313 0137. После перевода средств нажмите кнопку \"подтвердить перевод\". Если Вы нажали кнопку \"подтвердить перевод\", но не перевели средства, то в течении суток Ваш аккаунт будет удален!", "Make a voluntary contribution. Transfer 13 UAH to the project participant on his card 4149 4393 9313 0137. After transferring funds, click the \"confirm transfer\" button. If you clicked on the \"confirm transfer\" button, but did not transfer funds, then your account will be deleted within 24 hours!");
        put("Чтобы приобрести уровень, переведите средства Вашему спонсору (цену за каждый уровень можно узнать в разделе FAQ).\nВ примечании к платежу напишите свой ID. После перевода средств нажмите кнопку \"Попросить подтвердить оплату\" \nНомер карты спонсора для оплаты: ", "To purchase a level, transfer funds to your sponsor (the price for each level can be found in the FAQ section). \nIn the payment note write your ID. After transferring funds, click the \"Ask for payment confirmation\" button. \nSponsor card number for payment: ");
        put("Arigato – это не инвестиционный клуб, не MLM, не компания и не корпорация, а также не коммерческое предприятие. Здесь нет ни инвестиций, ни счетов, ни продуктов, ни услуг. Никто не «делает» и не зарабатывает деньги.", "Arigato – this is not an investment club, not MLM, not a company or corporation, and not a commercial venture. Here there is no investment, no bills, no food, no service. No one is doing  and not making money.");
        put("Именно поэтому мы приглашаем Вас принять участие в программе Arigato – автоматизированной глобальной системе по обмену подарками.", "That's why we invite you to take part in the program Arigato - automated global system for the exchange of gifts.");
        put("Arigato – это независимое объединение людей, которые добровольно и сознательно собрались вместе с благотворительными целями.", "Arigato - is an independent association of persons who voluntarily and knowingly have come together with charitable purposes.");
        put("Arigato - это Бот, который создан для того, чтобы дать возможность друзьям и друзьям друзей обмениваться подарками в неофициальной безопасной обстановке", "Arigato - it a Bot, which was created  to allow friends and friends of friends to exchange gifts in the unofficial safe environment.");
        put("Система Arigato является первой совершенно безупречной, полностью автоматизированной системой обмена подарками из рук в руки через кредитные карты.", "Arigato system is the first completely perfect,fully automated exchange of gifts from hand to hand through credit cards.");
        put("Все операции в системе являются актами дарения.", "All operations in the system are acts of donation.");
    }};

    public void updateLang(LanguageEntity lang) {
        languageRepository.updateLanguage(lang);
    }

    public void saveLang(LanguageEntity lang) {
        languageRepository.saveLanguage(lang);
    }

    public LanguageEntity getLang(Long userTelegramId) {
        return languageRepository.getLanguageEntity(userTelegramId);
    }

    public Map<String, String> getLng(Long userTelegramId) {
        LanguageEntity lng = languageRepository.getLanguageEntity(userTelegramId);
        if (lng.getLanguage().equals("r")) return russianMap;
        if (lng.getLanguage().equals("e")) return englishMap;
        return russianMap;
    }

}
