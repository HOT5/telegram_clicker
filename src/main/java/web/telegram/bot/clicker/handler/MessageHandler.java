package web.telegram.bot.clicker.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import web.telegram.bot.clicker.service.UserService;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageHandler {
    private final UserService userService;
    public String handle(Update update){
        //userService.checkIfUserExists(update.getMessage().getFrom());


        return Optional.of(update.getMessage().getPhoto().get(0).getFileId()).orElse("1");
    }
}
