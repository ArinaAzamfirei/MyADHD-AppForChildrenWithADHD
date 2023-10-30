package my.adhd.MyADHD.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "map")
public class ConfigurationProprieties {

    private Map<Integer, Integer> percentage;

}
