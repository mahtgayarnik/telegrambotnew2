package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRepositoryImpl;
import com.example.dao.VoteRepository;
import com.example.dao.VoteRepositoryImpl;
import com.example.entity.UserEntity;
import com.example.entity.VoteEntity;

import java.util.List;
import java.util.Optional;

public class StatService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private VoteRepository voteRepository = new VoteRepositoryImpl();
    private PaymentService paymentService = new PaymentService();
    private LanguageService lng = new LanguageService();

    public String getRusFaq() {
        return "Введение" + "\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato – это не инвестиционный клуб, не MLM, не компания и не корпорация, а также не коммерческое предприятие. \n" +
                KeyboardService.emoji(":white_check_mark:") + "Здесь нет ни инвестиций, ни счетов, ни продуктов, ни услуг. Никто не «делает» и не зарабатывает деньги.\n" +
                KeyboardService.emoji(":white_check_mark:") + "Именно поэтому мы приглашаем Вас принять участие в программе Arigato – автоматизированной глобальной системе по обмену подарками.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato – это независимое объединение людей, которые добровольно и сознательно собрались вместе с благотворительными целями.\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato - это Бот, который создан для того, чтобы дать возможность друзьям и друзьям друзей обмениваться подарками в неофициальной безопасной обстановке.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Система Arigato является первой совершенно безупречной, полностью автоматизированной системой обмена подарками из рук в руки через кредитные карты.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Все операции в системе являются актами дарения.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Больше информации в разделе FAQ" + "\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Как это работает" + KeyboardService.emoji(":white_check_mark:") + "\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Система основана на очень легко понимаемой и легко заполняемой принудительной короткой матрице 2 x2.\n " +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Система Arigato состоит в общей сложности всего из шести позиций:\n " +
                "две на первом уровне (1 и 2) и\n" +
                "четыре на втором уровне (3, 4, 5 и 6). \t \n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Система Arigato заключается в следующем:\n " +
                " \n" +
                KeyboardService.emoji(":pencil2:") + "Вы приглашаете два партнера, которые в свою очередь так же приглашают по два участника.Два участника, \n " +
                "которых пригласили Вы, это Ваш первый уровень. Когда каждый из них пригласит по два партнера, завершается \n" +
                "Ваш второй уровень. \n" +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "На этом ваша неделящаяся матрица закрыта и Вы начинаете новый цикл.Ваши партнеры, после завершения своих \n " +
                "циклов следуют за Вами постоянно и помогают вам закрывать цикл за циклом.\n" +
                KeyboardService.emoji(":white_check_mark:") + "Система Arigato действительно ориентирована на КОМАНДНУЮ деятельность, которая оказывает \n " +
                "поддержку всем членам команды снова и снова.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Система Arigato имеет 5 независимых уровней:\n " +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "Вы начинаете с первого уровня и потом уже на прибыль активируются постепенно \n " +
                "следующие четыре уровня. Больше ни одной копейки со своего кармана вкладывать не придется. \n" +
                "\n" +
                KeyboardService.emoji(":moneybag:") + "1 $, 2 $, 4 $, 8 $, 16 $" + KeyboardService.emoji(":moneybag:00") + "\n" +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "Каждый раз, когда закрывается ваш цикл в 1 $.вы получаете 4 $. \n " +
                KeyboardService.emoji(":pencil2:") + "Каждый раз, когда закрывается ваш цикл в 2 $.вы получаете 8 $. \n " +
                KeyboardService.emoji(":pencil2:") + "Каждый раз, когда закрывается ваш цикл в 4 $.вы получаете 16 $. \n " +
                KeyboardService.emoji(":pencil2:") + "Каждый раз, когда закрывается ваш цикл в 8 $.вы получаете 32 $. \n " +
                KeyboardService.emoji(":pencil2:") + "Каждый раз, когда закрывается ваш цикл в 16 $.вы получаете 64 $. \n " +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "ОСНОВНЫЕ ОСОБЕННОСТИ СИСТЕМЫ \n " +
                "\n" +
                KeyboardService.emoji(":one:") + "Благодаря вложению небольших инвестиций 1 $.система доступна многим.\n " +
                KeyboardService.emoji(":two:") + "Оплаты подарков идут непосредственно участникам.\n " +
                KeyboardService.emoji(":three:") + "Реинвестиции делаются из уже заработанных денег.Их не надо отрывать от семьи.\n " +
                KeyboardService.emoji(":four:") + "Все партнеры равны. Каждый получает  умноженное на четыре , ни больше, ни меньше.\n" +
                KeyboardService.emoji(":five:") + "Независимая Компьютерная Программа беспристрастно распределяет людей внутри системы и \n" +
                "защищает от наживы сверху. \n" +
                KeyboardService.emoji(":six:") + "Отсутствует прямая выгода того, кто зарегистрировался раньше, т.к.нет скопления денег у того, кто наверху.\n" +
                KeyboardService.emoji(":seven:") + "Отсутствие электронных кошельков и виртуальных денег.Нет опасений \n" +
                "потерять заработанное. Хакерам в нашей системе делать нечего!\n" +
                KeyboardService.emoji(":eight:") + "Заполнение пустых мест проходит автоматически, без усилий каждого члена сети.\n" +
                KeyboardService.emoji(":nine:") + "В любом случае система выдаст Вам четыре подарка.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "ОТ ПИРАМИДЫ СИСТЕМУ Arigato ОТЛИЧАЕТ СЛЕДУЮЩЕЕ\n" +
                "\n" +
                KeyboardService.emoji(":one:") + "Созданная Вами команда из 6партнеров плавно перемещается из матрицы в матрицу, из Фазы в Фазу.Отсутствует необходимость поиска бесконечного числа людей.\n" +
                KeyboardService.emoji(":two:") + "Отсутствует понятие верха и низа.  \n" +
                "За счет реинвестиций каждый раз  верхний  может оказаться дочерним  нижнему.\n" +
                KeyboardService.emoji(":three:") + "Отсутствует скопление денег наверху и перераспределения их в виде бонусов партнерам. \n" +
                KeyboardService.emoji(":four:") + "Все партнеры равны.Каждый получает умноженное на четыре, ни больше, ни меньше.\n" +
                KeyboardService.emoji(":five:") + "Отсутствует прямая выгода того, кто пришел раньше.\n" +
                KeyboardService.emoji(":six:") + "Отсутствие финансово пострадавших.Программа не терпит пустых мест и заполняет их автоматически сверху вниз, справа налево.";
    }

    public String getEngFaq() {
        return "Intro" + "\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato – this is not an investment club, not MLM, not a company or corporation, and not a commercial venture. \n" +
                KeyboardService.emoji(":white_check_mark:") + "Here there is no investment, no bills, no food, no service. No one is doing  and not making money.\n" +
                KeyboardService.emoji(":white_check_mark:") + "That ‘s why we invite you to take part in the program Arigato - automated global system for the exchange of gifts.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato - is an independent association of persons who voluntarily and knowingly have come together with charitable purposes.\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato - it a Bot, which was created  to allow friends and friends of friends to exchange gifts in the unofficial safe environment.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + " Arigato system is the first completely perfect,fully automated exchange of gifts from hand to hand through credit cards.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "All operations in the system are acts of donation.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "More information in the FAQ" + "\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "How does this work" + KeyboardService.emoji(":white_check_mark:") + "\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "It is based on a very easy to understand and easy to complete a short forced matrix 2 x2.\n " +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato system consists of a total of only six items:\n " +
                "two on the first level (1 and 2) and\n" +
                "four on the second level (3, 4, 5 and 6).\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato system is as follows:\n " +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "You invite the two partners, which in turn are also invited two participants. \n " +
                "Two participants who are invited  you, this is your first level. When each of them \n" +
                "to invite two partners   completed  your second level.\n" +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "This completes your matrix nondividing closed and you start a new cycle.\n " +
                "Your partners after completing their cycles follow you and help you to \n" +
                "constantly close cycle after cycle.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "System Arigato really focused on team activities, which provides support \n " +
                "to all members of the team again and again.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "Arigato system has 5 separate levels:\n " +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "You start with the first level and then gradually activate profits \n" +
                "following four levels. Over a single penny from your pocket will not \n" +
                "have to invest.\n" +
                "\n" +
                KeyboardService.emoji(":moneybag:") + "1 $, 2 $, 4 $, 8 $, 16 $" + KeyboardService.emoji(":moneybag:") + "\n" +
                "\n" +
                KeyboardService.emoji(":pencil2:") + "Every time your cycle closes at 1 $.You get 4 $.\n " +
                KeyboardService.emoji(":pencil2:") + "Every time your cycle closes at 2 $.You get 8 $.\n " +
                KeyboardService.emoji(":pencil2:") + "Every time your cycle closes at 4 $.You get 16 $.\n " +
                KeyboardService.emoji(":pencil2:") + "Every time your cycle closes at 8 $.You get 32 $.\n " +
                KeyboardService.emoji(":pencil2:") + "Every time your cycle closes at 16 $.You get 64 $. \n " +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "The key features\n " +
                "\n" +
                KeyboardService.emoji(":one:") + "Due to the embedding of small investments 1 $.System is available to many.\n " +
                KeyboardService.emoji(":two:") + "Gift payment goes directly to the participants.\n " +
                KeyboardService.emoji(":three:") + "Reinvestment is made \u200B\u200Bof the money earned.They should not be torn away from the family\n " +
                KeyboardService.emoji(":four:") + "All partners are equal.Everyone gets a multiplied by four, no more, no less.\n " +
                KeyboardService.emoji(":five:") + "Independent Computer Program distributes dispassionately people within \n " +
                "the system and protects against profit on top.\n" +
                KeyboardService.emoji(":six:") + "There is no direct benefit to those who have registered before, because \n " +
                "no accumulation of money from those who top.\n" +
                KeyboardService.emoji(":seven:") + "The lack of electronic wallets and virtual money.No fear of losing earnings. \n " +
                "Hackers in our system to do nothing!\n" +
                KeyboardService.emoji(":eight:") + "Filling the empty spaces is automatic, without the efforts of each member of the network.\n" +
                KeyboardService.emoji(":nine:") + "In any case, the system will give you four gift.\n" +
                "\n" +
                KeyboardService.emoji(":white_check_mark:") + "FROM THE PYRAMID SYSTEM Arigato characterized by the following\n" +
                "\n" +
                KeyboardService.emoji(":one:") + "You have created a team of 6 partners moves smoothly \n" +
                "from the matrix in a matrix from phase to phase. There is no \n" +
                "need to search an infinite number of people\n" +
                KeyboardService.emoji(":two:") + "There is no concept of  top and  bottom Through reinvestment each time the  top may be a child of the  lower. \n" +
                KeyboardService.emoji(":three:") + "There is no accumulation of money at the top and the redistribution of their partners in the form of bonuses.\n" +
                KeyboardService.emoji(":four:") + "All partners are equal. Everyone gets a multiplied by four, no more, no less.\n" +
                KeyboardService.emoji(":five:") + "There is no direct benefit to those who came before.\n" +
                KeyboardService.emoji(":six:") + "Lack of financial victims. The program does not tolerate empty space \n" +
                "fills them in automatically from the top down, right to left.";
    }

    public String getStartMessage(Long userTelegramId) {
        if (lng.getLang(userTelegramId).getLanguage().equals("r")) {
            return KeyboardService.emoji(":white_check_mark:") + "Arigato – это не инвестиционный клуб, не MLM, не компания и не корпорация, а также не коммерческое предприятие. \n" +
                    KeyboardService.emoji(":white_check_mark:") + "Здесь нет ни инвестиций, ни счетов, ни продуктов, ни услуг. Никто не «делает» и не зарабатывает деньги.\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Именно поэтому мы приглашаем Вас принять участие в программе Arigato – автоматизированной глобальной системе по обмену подарками.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Arigato – это независимое объединение людей, которые добровольно и сознательно собрались вместе с благотворительными целями.\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Arigato - это Бот, который создан для того, чтобы дать возможность друзьям и друзьям друзей обмениваться подарками в неофициальной безопасной обстановке.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Система Arigato является первой совершенно безупречной, полностью автоматизированной системой обмена подарками из рук в руки через кредитные карты.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Все операции в системе являются актами дарения.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Больше информации в разделе FAQ";
        } else {
            return KeyboardService.emoji(":white_check_mark:") + "Arigato – this is not an investment club, not MLM, not a company or corporation, and not a commercial venture. \n" +
                    KeyboardService.emoji(":white_check_mark:") + "Here there is no investment, no bills, no food, no service. No one is doing  and not making money.\n" +
                    KeyboardService.emoji(":white_check_mark:") + "That ‘s why we invite you to take part in the program Arigato - automated global system for the exchange of gifts.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Arigato - is an independent association of persons who voluntarily and knowingly have come together with charitable purposes.\n" +
                    KeyboardService.emoji(":white_check_mark:") + "Arigato - it a Bot, which was created  to allow friends and friends of friends to exchange gifts in the unofficial safe environment.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + " Arigato system is the first completely perfect,fully automated exchange of gifts from hand to hand through credit cards.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "All operations in the system are acts of donation.\n" +
                    "\n" +
                    KeyboardService.emoji(":white_check_mark:") + "More information in the FAQ";
        }
    }

    public String sendInfoAboutBot(Long userTelegramId) {
        int one = userRepository.getCountUser(1) - 1;
        int two = userRepository.getCountUser(2) - 1;
        int three = userRepository.getCountUser(3) - 1;
        int four = userRepository.getCountUser(4) - 1;
        int five = userRepository.getCountUser(5) - 1;
        int all = one + two + three + four + five;
        return lng.getLng(userTelegramId).get("Статистика хайпа \n") +
                lng.getLng(userTelegramId).get("Всего пользователей:") + " " + all + "\n" +
                lng.getLng(userTelegramId).get("Всего пользователей 1-го уровня:") + " " + one + "\n" +
                lng.getLng(userTelegramId).get("Всего пользователей 2-го уровня:") + " " + two + "\n" +
                lng.getLng(userTelegramId).get("Всего пользователей 3-го уровня:") + " " + three + "\n" +
                lng.getLng(userTelegramId).get("Всего пользователей 4-го уровня:") + " " + four + "\n" +
                lng.getLng(userTelegramId).get("Всего пользователей 5-го уровня:") + " " + five + "\n" +
                " \n" +
                lng.getLng(userTelegramId).get("Всего пользователей вне матрицы:") + " " + userRepository.getCountUserOutOfMatrix() + "\n" +
                lng.getLng(userTelegramId).get("Всего не купивших уровень у пользователей в матрице:") + " " + userRepository.getCountUserWithoutSponsorPay();
    }

    public String getResultVotes(Long userTelegramId) {
        StringBuilder result = new StringBuilder(lng.getLng(userTelegramId).get("Результаты опросов \n")).append("\n");
        List<VoteEntity> votes = voteRepository.getAllVote();
        votes.forEach(vote -> {
            result.append(vote.getQuestion() + "\n");
            if (!("").equals(vote.getAnswerOne()) && null != vote.getAnswerOne()) {
                result.append(vote.getAnswerOne() + " " + vote.getCountOne());
                result.append("\n");
            }
            if (!("").equals(vote.getAnswerTwo()) && null != vote.getAnswerTwo()) {
                result.append(vote.getAnswerTwo() + " " + vote.getCountTwo());
                result.append("\n");
            }
            if (!("").equals(vote.getAnswerThree()) && null != vote.getAnswerThree()) {
                result.append(vote.getAnswerThree() + " " + vote.getCountThree());
                result.append("\n");
            }
            if (!("").equals(vote.getAnswerFour()) && null != vote.getAnswerFour()) {
                result.append(vote.getAnswerFour() + " " + vote.getCountFour());
                result.append("\n");
            }
            if (!("").equals(vote.getAnswerFive()) && null != vote.getAnswerFive()) {
                result.append(vote.getAnswerFive() + " " + vote.getCountFive());
                result.append("\n");
            }
            result.append("\n");
        });
        return result.toString();
    }

    public String sendInfoAboutMyTeam(Long userTelegramId) {
        UserEntity user = userRepository.getUserByTelegramId(userTelegramId);
        UserEntity left = Optional.ofNullable(userRepository.getUserByTelegramId(user.getInvitedLeftId())).orElse(null);
        UserEntity right = Optional.ofNullable(userRepository.getUserByTelegramId(user.getInvitedRightId())).orElse(null);
        Long leftLeft = 0L;
        Long leftRight = 0L;
        Long rightLeft = 0L;
        Long rightRight = 0L;
        if (left != null) {
            leftLeft = left.getInvitedLeftId();
            leftRight = left.getInvitedRightId();
        }
        if (right != null) {
            rightLeft = right.getInvitedLeftId();
            rightRight = right.getInvitedRightId();
        }
        return KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Ваша команда: \n") +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Уровень") + " " + user.getLevel() + "\n" +
                KeyboardService.emoji(":bust_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Спонсор ID") + " " + user.getSponsorId() + "\n" +
                KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Реферал ID") + " " + user.getInvitedLeftId() + "\n" +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Статус оплаты:") + " " + paidStatus(left, userTelegramId) + "\n" +
                KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Реферал ID") + " " + user.getInvitedRightId() + "\n" +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Статус оплаты:") + " " + paidStatus(right, userTelegramId) + "\n" +
                KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Партнер ID") + " " + leftLeft + "\n" +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Статус оплаты:") + " " + paidStatus(Optional.ofNullable(userRepository.getUserByTelegramId(leftLeft)).orElse(null), userTelegramId) + "\n" +
                KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Партнер ID") + " " + leftRight + "\n" +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Статус оплаты:") + " " + paidStatus(Optional.ofNullable(userRepository.getUserByTelegramId(leftRight)).orElse(null), userTelegramId) + "\n" +
                KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Партнер ID") + " " + rightLeft + "\n" +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Статус оплаты:") + " " + paidStatus(Optional.ofNullable(userRepository.getUserByTelegramId(rightLeft)).orElse(null), userTelegramId) + "\n" +
                KeyboardService.emoji(":busts_in_silhouette:") + "  " + lng.getLng(userTelegramId).get("Партнер ID") + " " + rightRight + "\n" +
                KeyboardService.emoji(":bulb:") + "  " + lng.getLng(userTelegramId).get("Статус оплаты:") + " " + paidStatus(Optional.ofNullable(userRepository.getUserByTelegramId(rightRight)).orElse(null), userTelegramId) + "\n";
    }

    public String sendStat(Long userTelegramId) {
        UserEntity user = userRepository.getUserByTelegramId(userTelegramId);
        return KeyboardService.emoji(":bust_in_silhouette:") + " " + lng.getLng(userTelegramId).get("Аккаунт:") + " " + "\n" +
                KeyboardService.emoji(":bulb:") + " " + lng.getLng(userTelegramId).get("Уровень:") + " " + user.getLevel() + "\n" +
                KeyboardService.emoji(":gift:") + " " + lng.getLng(userTelegramId).get("Новых заказов:") + " " + getCountOfNewOrders(userTelegramId) + "\n" +
                KeyboardService.emoji(":crown:") + " " + lng.getLng(userTelegramId).get("Ваш ID:") + " " + user.getTelegramId() + "\n" +
                KeyboardService.emoji(":moneybag:") + " " + lng.getLng(userTelegramId).get("Баланс:") + " " + user.getBalance() + "\n" +
                KeyboardService.emoji(":credit_card:") + " " + lng.getLng(userTelegramId).get("Номер карты:") + " " + user.getWallet();
    }

    //todo поменять значение sum в зависимости от уровня пользователя
    public void updateBalance(Long userTelegramId, Double sum) {
        UserEntity user = userRepository.getUserByTelegramId(userTelegramId);
        user.setBalance(user.getBalance() + sum);
        userRepository.updateUser(user);
    }

    private Integer getCountOfNewOrders(Long userTelegramId) {
        List<Long> ids = paymentService.getConfirmUsersPaid(userTelegramId);
        return (int) ids.stream().filter(id -> id != 0L)
                .map(id -> userRepository.getUserByTelegramId(id))
                .filter(user -> user.getPaidSponsor().equals(false)).count();
    }

    private String paidStatus(UserEntity user, Long userTelegramId) {
        if (null == user) return "";
        return (user.getPaidSponsor()) ? KeyboardService.emoji(":white_check_mark:") + lng.getLng(userTelegramId).get("Оплатил") : KeyboardService.emoji(":x:") + lng.getLng(userTelegramId).get("Не оплачено");
    }

}
