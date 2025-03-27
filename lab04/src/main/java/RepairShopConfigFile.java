import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.ComputerRepairRequestRepository;
import repository.ComputerRepairedFormRepository;
import repository.file.ComputerRepairRequestFileRepository;
import repository.file.ComputerRepairedFormFileRepository;
import services.ComputerRepairServices;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfigFile {
    @Bean
    Properties getProps() {
        var result = new Properties();
        try (var reader = new FileReader("RepairShop.properties")) {
            result.load(reader);
            System.out.println(result.getProperty("RequestsFile"));
            System.out.println(result.getProperty("RepairedFile"));
            System.out.println(System.getProperty("user.dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
        var props = getProps();
        var file = props.getProperty("RequestsFile", "ComputerRequests.txt");
        return new ComputerRepairRequestFileRepository(file);
    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
       var props = getProps();
       var file = props.getProperty("RepairedFile", "RepairedForms.txt");
       return new ComputerRepairedFormFileRepository(
               file, requestsRepo());

    }

    @Bean
    ComputerRepairServices services(){
        var rr = requestsRepo();
        var fr = formsRepo();
        return new ComputerRepairServices(rr, fr);
    }
}