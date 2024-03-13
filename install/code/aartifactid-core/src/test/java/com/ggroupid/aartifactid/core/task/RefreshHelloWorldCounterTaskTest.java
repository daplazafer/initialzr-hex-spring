package com.ggroupid.aartifactid.core.task;

import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RefreshHelloWorldCounterTaskTest {

    @Mock
    HelloWorldCounterPort helloWorldCounter;

    @InjectMocks
    RefreshHelloWorldCounterTask task;

    @Test
    void whenRefresh_thenResetCounter() {

        task.refresh();

        then(helloWorldCounter).should(never()).increment();
        then(helloWorldCounter).should(times(1)).reset();
    }

}