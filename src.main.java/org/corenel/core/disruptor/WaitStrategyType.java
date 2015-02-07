package org.corenel.core.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.LiteBlockingWaitStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;

/**
 * @author Á¤¼ö¿ø
 */
public enum WaitStrategyType {
	
        BLOCKING {
            public WaitStrategy instance() {
                return new BlockingWaitStrategy();
            }
        },

        BUSY_SPIN {
            public WaitStrategy instance() {
                return new BusySpinWaitStrategy();
            }
        },

        LITE_BLOCKING {
            public WaitStrategy instance() {
                return new LiteBlockingWaitStrategy();
            }
        },
        
        SLEEPING_WAIT {
            public WaitStrategy instance() {
                return new SleepingWaitStrategy();
            }
        },
        
        YIELDING {
            public WaitStrategy instance() {
                return new YieldingWaitStrategy();
            }
        };
        
        public abstract WaitStrategy instance();
}
