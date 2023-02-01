package web.telegram.bot.clicker.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "clicker-db")
@Data
@Builder
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String telegramId;
}
