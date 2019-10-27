package com.example;

import com.example.controller.BotController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class Telegrambotnew2Application {

	public static void main(String[] args) {
		SpringApplication.run(Telegrambotnew2Application.class, args);
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new BotController());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		//new ProcessTransactionsThread("ProcessTransactions").start();
		new UserDeletionThread("UserDeletion").start();
	}

}
