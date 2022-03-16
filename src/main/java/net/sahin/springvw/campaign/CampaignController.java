package net.sahin.springvw.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CampaignController {


    CampaignService service;

    @Autowired
    public CampaignController(CampaignService service) {
        this.service = service;
    }

    @GetMapping("/campaign/{id}")
    public Mono<Campaign> getCampaign(@PathVariable(value = "id") String id){
        return service.getCampaign(id);
    }
}
