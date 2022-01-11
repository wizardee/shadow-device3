package net.herit.ami.features.stat.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.job.QuartzJobBeans;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.logger.oms.aop.LogTracer;
import net.herit.ami.commons.util.RandomUtil;
import net.herit.ami.features.stat.dto.entity.StatisticEntity;
import net.herit.ami.features.stat.dto.entity.model.config.DeviceProperties;
import net.herit.ami.features.stat.dto.entity.model.config.DeviceProperty;
import net.herit.ami.features.stat.dto.entity.model.config.SiteDeviceProperties;
import net.herit.ami.features.stat.dto.entity.model.hura.HuraBodyMessage;
import net.herit.ami.features.stat.dto.entity.model.hura.HuraEntity;
import net.herit.ami.features.stat.dto.entity.model.hura.HuraEntityMessage;
import net.herit.ami.features.stat.feign.MagokStateFeign;
import net.herit.ami.features.stat.feign.Piggy2StateFeign;
import net.herit.ami.features.stat.property.DeviceConfigProperty;
import net.herit.ami.features.stat.repository.mysql.IotDeviceStatusRealtimeLastStatisticRepository;
import org.apache.commons.io.FileUtils;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShadowDeviceJob extends QuartzJobBeans {

    private final CallLogger call;
    private final Piggy2StateFeign piggy2StateFeign;
    private final MagokStateFeign magokStateFeign;
    private final DeviceConfigProperty deviceConfigProperty;
    private final IotDeviceStatusRealtimeLastStatisticRepository repository;
    private final RandomUtil randomUtil;

    @Override
    @LogTracer(svcId = "TLS001")
    protected void initJob(JobExecutionContext context) {
        call.info("initShadowDeviceJob: {}", "");

        try {
            String json = FileUtils.readFileToString(new File(deviceConfigProperty.getJsonpath()), StandardCharsets.UTF_8.toString());
            call.info(json);

            ObjectMapper objectMapper = new ObjectMapper();
            SiteDeviceProperties siteDeviceProperties = objectMapper.readValue(json, SiteDeviceProperties.class);
            call.info(siteDeviceProperties.toString());

            siteDeviceProperties.getDeviceProperties().forEach(deviceProperties -> {
//                Response response =
//                        piggy2StateFeign.requestEgwData(converterPropertyToHuraMessage(deviceProperty));
//                call.info(response.toString());

                processDeviceMessage(deviceProperties);
            });

        } catch (IOException e) {
            e.printStackTrace();
            call.error(e.toString());
        }
    }
    void processDeviceMessage(DeviceProperties deviceProperties) {
        deviceProperties.getDeviceDataConfigs().forEach(deviceProperty -> {
            if(deviceProperty.getUse().equals("y")) {
                HuraBodyMessage message = converterPropertyToHuraMessage(deviceProperty);
                call.info("url={}", deviceProperties.getUrl());
                //piggy2StateFeign.requestEgwData(message);
                magokStateFeign.requestEgwData(message);
            }else {
                call.info("not use. device_id : {}", deviceProperty.getDeviceId());
            }
        });
    }

    /**
     *
     * @param deviceProperty
     * @return
     */
    HuraBodyMessage converterPropertyToHuraMessage(DeviceProperty deviceProperty) {
        HuraBodyMessage huraBodyMessage = new HuraBodyMessage();
        huraBodyMessage.setDeviceId(deviceProperty.getDeviceId());

        String sid = String.valueOf(System.currentTimeMillis());
        huraBodyMessage.setSid(sid);

        HuraEntityMessage huraEntityMessage = new HuraEntityMessage();
        List<HuraEntity> huraEntities = new ArrayList<>();
        String resourceUri = null;
        String cumulateValue = null;

        for(String instanceId : deviceProperty.getInstanceIds()) {
            for (String objectId : deviceProperty.getObjectIds()) {
                resourceUri = "/" + objectId + "/" + instanceId + "/0";

                StatisticEntity entity = null;
                if(objectId.equals("300")) {
                    entity = repository.selectRealtimeLastStatistics(
                            deviceProperty.getVDdeviceId(), resourceUri);
                }else {
                    entity = repository.selectHourLastStatistics(
                            deviceProperty.getVDdeviceId(), resourceUri);
                }
                if(entity == null) {
                    cumulateValue = deviceProperty.getDefaultValue();
                }else {
                    double value =
                            Double.parseDouble(entity.getPreviousValue()) + randomUtil.nextDouble(2, 1);
                    cumulateValue =
                            String.valueOf(
                                    new BigDecimal(value).setScale(2, RoundingMode.HALF_UP));
                }

                huraEntities.add(
                        HuraEntity.builder()
                                .sv(cumulateValue)
                                .resourceUri(resourceUri)
                                .ti(sid)
                                .build()
                );
            }
        }

        huraEntityMessage.setHuraEntities(huraEntities);
        huraBodyMessage.setHuraEntityMessage(huraEntityMessage);
        return huraBodyMessage;
    }
}
