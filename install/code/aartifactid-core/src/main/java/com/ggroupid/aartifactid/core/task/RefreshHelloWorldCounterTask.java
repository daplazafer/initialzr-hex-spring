package com.ggroupid.aartifactid.core.task;

import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("!test")
public class RefreshHelloWorldCounterTask {

    private final HelloWorldCounterPort helloWorldCounter;

    @Scheduled(fixedRateString = "${aartifactid.task.refresh.hello.world.counter}")
    public void refresh() {

        log.debug("Greetings counter refreshed.");
        helloWorldCounter.reset();
    }

}
