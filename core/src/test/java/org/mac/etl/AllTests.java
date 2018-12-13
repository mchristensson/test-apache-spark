package org.mac.etl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EntityManagerTest.class, ETLContextTest.class, ETLCoreTest.class, ModelMockTest.class })
public class AllTests {

}
