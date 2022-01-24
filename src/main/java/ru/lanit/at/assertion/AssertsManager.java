package ru.lanit.at.assertion;


import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.utils.web.properties.Configurations;

public class AssertsManager {
    private static final ThreadLocal<AssertsManager> assertsManagerThreadLocal = new ThreadLocal<>();
    private static final Configurations conf = ConfigFactory.create(Configurations.class, System.getProperties(),
            System.getenv());
    private ExtendedAssert asserts;

    public synchronized static AssertsManager getAssertsManager() {
        AssertsManager localInstance = assertsManagerThreadLocal.get();
        if (localInstance == null) {
            synchronized (AssertsManager.class) {
                localInstance = assertsManagerThreadLocal.get();
                if (localInstance == null) {
                    AssertsManager assertsManager = new AssertsManager();
                    assertsManager.setAsserts(new ExtendedAssert());
                    assertsManagerThreadLocal.set(assertsManager);
                    localInstance = assertsManagerThreadLocal.get();
                }
            }
        }
        return localInstance;

    }


    /**
     * Method to get {@link ExtendedAssert} previously marked as critical. So such assert will fail all test.
     *
     * @return Critical {@link ExtendedAssert}.
     */
    public ExtendedAssert criticalAssert() {
        asserts.setCritical();
        return asserts;
    }

    /**
     * Method to get soft {@link ExtendedAssert} that collects assertions.
     *
     * @return Soft {@link ExtendedAssert}.
     */
    public ExtendedAssert softAssert() {
        return asserts;
    }

    /**
     * Recreates instance of {@link ExtendedAssert}.
     */
    public void flushAsserts() {
        asserts.flush();
    }

    private void setAsserts(ExtendedAssert asserts) {
        this.asserts = asserts;
    }


}
