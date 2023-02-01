package web.telegram.bot.clicker.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import web.telegram.bot.clicker.model.Player;

@Component
public class PlayerMapper {

    public Player toPlayer(User user) {
        return Player.builder()
                .telegramId(user.getId().toString())
                .build();
    }
}
