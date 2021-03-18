package name.katlog.objectAndclass.enums;

import org.junit.Test;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static name.katlog.objectAndclass.enums.TestEnumSet.AlarmPoints.*;

/**
 * Created by fw on 2019/5/30
 */
public class TestEnumSet {
    enum AlarmPoints {
        /** 警报点 */
        STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,
        OFFICE4, BATHROOM, UTILITY, KITCHEN
    }


    @Test
    public void noneOf(){
        EnumSet<AlarmPoints> enumSet = EnumSet.noneOf(AlarmPoints.class);

        assertEquals(0,enumSet.size());
    }

    @Test
    public void of(){
        EnumSet<AlarmPoints> enumSet = EnumSet.of(OFFICE1, OFFICE2, OFFICE3);

        assertEquals(3,enumSet.size());
    }

    @Test
    public void allOf(){
        EnumSet<AlarmPoints> enumSet = EnumSet.allOf(AlarmPoints.class);

        assertEquals(AlarmPoints.class.getEnumConstants().length,enumSet.size());
    }

    @Test
    public void range(){
        EnumSet<AlarmPoints> enumSet = EnumSet.range(OFFICE1, OFFICE3);

        assertEquals(3,enumSet.size());
        assertEquals(EnumSet.of(OFFICE1, OFFICE2, OFFICE3), enumSet);
    }

}
