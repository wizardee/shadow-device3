package net.herit.ami.features.stat.repository;

import lombok.extern.slf4j.Slf4j;
import net.herit.ami.base.bases.RepositoryBase;
import net.herit.ami.features.stat.dto.entity.IotDeviceStatusRealtimeStatisticEntity;
import net.herit.ami.features.stat.repository.postgresql.IotDeviceStatusRealtimeStatisticRepository;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Disabled
@SpringBootTest
@ActiveProfiles(value = {"local"})
@Slf4j
public class IotDeviceStatusRealtimeStatisticRepositoryTests {
    @Autowired
    IotDeviceStatusRealtimeStatisticRepository realtimeStatisticRepository;

    @Test
    public void selectRealtimeStatistics() {
        List<IotDeviceStatusRealtimeStatisticEntity> list =
                realtimeStatisticRepository.selectRealtimeStatistics(9);

        System.out.println(list);
    }
}
