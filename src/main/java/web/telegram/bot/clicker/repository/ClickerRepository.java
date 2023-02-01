package web.telegram.bot.clicker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.telegram.bot.clicker.model.Player;

@Repository
public interface ClickerRepository extends MongoRepository<Player, String> {

    Player findByTelegramId(String telegramId);
}
