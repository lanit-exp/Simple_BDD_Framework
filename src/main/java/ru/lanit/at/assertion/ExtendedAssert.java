package ru.lanit.at.assertion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;
import ru.lanit.at.utils.allure.AllureHelper;

import java.util.Map;


public class ExtendedAssert extends SoftAssert {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExtendedAssert.class);
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
    private Boolean isCritical = false;


    public void setCritical() {
        isCritical = true;
    }


    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            a.doAssert();
            onAssertSuccess(a);
            LOGGER.debug("Успешно проверено: [{}]", a.getActual());
        } catch (AssertionError ex) {
            LOGGER.error(ex.getMessage());
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
            AllureHelper.setStepStatusBroken("SoftAssert:" + ex.getMessage());
            if (isCritical) {
                this.assertAll();
            }
        } finally {
            this.isCritical = false;
            onAfterAssert(a);
        }
    }

    @Override
    public void assertAll() {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae.getKey().getMessage());
                ae.getKey().printStackTrace();
            }
            if (isCritical) sb.append(" [BLOCKER]");
            m_errors.clear();
            throw new AssertionError(sb.toString());
        }
    }

    public void flush() {
        m_errors.clear();
    }
}
