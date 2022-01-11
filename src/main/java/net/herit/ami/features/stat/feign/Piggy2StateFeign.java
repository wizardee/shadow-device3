package net.herit.ami.features.stat.feign;

import feign.Response;
import net.herit.ami.features.stat.dto.entity.model.config.DeviceProperty;
import net.herit.ami.features.stat.dto.entity.model.hura.HuraBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "piggy2StateFeign", url = "http://piggybank2.heviton.com:18080")
public interface Piggy2StateFeign {
    @RequestMapping(consumes = "application/json; utf-8", value = "/api/v1/event/status", method = RequestMethod.POST)
    Response requestEgwData(@RequestBody HuraBodyMessage huraBodyMessage);
}
