import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.ComputerRepairRequestRepository;
import repository.ComputerRepairedFormRepository;
import services.ComputerRepairServices;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfigDb {
    @Bean
    Properties getProps() {
        var result = new Properties();
        try (var reader = RepairShopConfigDb.class.getResourceAsStream("/bd.config")) {
            result.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo() {
        var props = getProps();
        return new repository.jdbc.ComputerRepairRequestJdbcRepository(props);
    }

    @Bean
    ComputerRepairedFormRepository formsRepo() {
        var props = getProps();
        return new repository.jdbc.ComputerRepairedFormJdbcRepository(props);
    }

    @Bean
    ComputerRepairServices services() {
        var rr = requestsRepo();
        var fr = formsRepo();
        return new ComputerRepairServices(rr, fr);
    }
}