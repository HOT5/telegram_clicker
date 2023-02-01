package web.telegram.bot.clicker.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import web.telegram.bot.clicker.config.TelegramBotProperties;
import web.telegram.bot.clicker.handler.MessageHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotProperties botProperties;
    private final MessageHandler messageHandler;

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        sendMessage(messageHandler.handle(update), update.getMessage().getChatId().toString());

        String response = messageHandler.handle(update);

        if (update.getMessage().hasPhoto()) {

            SendPhoto msg = new SendPhoto();
            msg.setPhoto(new InputFile("AgACAgIAAxkBAAOMY9QQNnCzEHPS2RdwGdvqjx5omo4AAhvGMRtUK6BKEHp-fYt0KcABAAMCAAN4AAMtBA"));
            msg.setChatId(update.getMessage().getChatId().toString());

            execute(msg);
        }

    }

    public void sendMessage(String message, String chatId) {
        SendMessage response = SendMessage.builder()
                .text(message)
                .chatId(chatId)
                .replyMarkup(createMenu())
                .build();
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Error sending telegram message. " + e.getMessage());
        }
    }

    private ReplyKeyboardMarkup createMenu() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row;

        row = new KeyboardRow();
        row.add("Profile");
        row.add("Menu");
        keyboardRowList.add(row);

        row = new KeyboardRow();
        row.add("Stats");
        row.add("Shop");
        keyboardRowList.add(row);

        row = new KeyboardRow();
        row.add("Map");
        keyboardRowList.add(row);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
}
