package windpark.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import windpark.MOMReceiver;

@RestController
public class WindparkSimulation {
    @RequestMapping(value={"/"},produces={"application/json"})
    public String windengineRequestJSON() {
        return MOMReceiver.getJSON();
    }
}
