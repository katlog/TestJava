package name.katlog.common.instant;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZoneId;

/**
 * 2021/9/26
 *  ZoneId类是老版TimeZone的替代品
 */
public class TestZoneId {
    
    @Test
    public void _static_of(){
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println("romeZone = " + romeZone);
        Assert.assertNull(romeZone);

    }
}
