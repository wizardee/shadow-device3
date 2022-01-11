package net.herit.ami.features.stat.service;

import lombok.RequiredArgsConstructor;
import net.herit.ami.commons.logger.call.log.CallLogger;
import net.herit.ami.commons.service.JobService;
import net.herit.ami.features.stat.job.ShadowDeviceJob;
import net.herit.ami.features.stat.property.ShadowDeviceProperty;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ShadowDeviceJobService implements JobService {

    private static final String NAME = "ShadowDevice";
    private static final String GROUP = "TOOL";

    private final ShadowDeviceProperty property;
    private final Scheduler scheduler;
    private final CallLogger call;

    @Override
    public void initBatch() {
        JobDetail jobDetail = buildJobDetail(ShadowDeviceJob.class, NAME, GROUP, new HashMap<>());

        try {
            if(property.isEnable())
                scheduler.scheduleJob(jobDetail, buildJobTrigger(property.getSchedule()));
        } catch (SchedulerException e) {
            call.error("FileReaderService :: scheduler error :: {}", LocalDateTime.now());
        }


    }
}
