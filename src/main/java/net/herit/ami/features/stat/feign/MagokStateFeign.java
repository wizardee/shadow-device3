package net.herit.ami.features.stat.feign;

import feign.Response;
import net.herit.ami.features.stat.dto.entity.model.hura.HuraBodyMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "MagokStateFeign", url = "http://210.97.40.115:4040")
public interface MagokStateFeign {
    @RequestMapping(consumes = "application/json; utf-8", value = "/api/v1/event/status", method = RequestMethod.POST)
    Response requestEgwData(@RequestBody HuraBodyMessage huraBodyMessage);
}
