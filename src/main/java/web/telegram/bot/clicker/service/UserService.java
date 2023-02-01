package web.telegram.bot.clicker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import web.telegram.bot.clicker.mapper.PlayerMapper;
import web.telegram.bot.clicker.model.Player;
import web.telegram.bot.clicker.repository.ClickerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final ClickerRepository clickerRepository;
    private final PlayerMapper playerMapper;

    public void checkIfUserExists(User user) {
        if (Optional.ofNullable(clickerRepository.findByTelegramId(user.getId().toString())).isEmpty()) {
            Player player = playerMapper.toPlayer(user);
            clickerRepository.save(player);
        }
    }
}
