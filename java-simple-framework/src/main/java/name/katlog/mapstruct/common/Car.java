package name.katlog.mapstruct.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by fw on 2020/8/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String name;
    private String make;
    private int numberOfSeats;
}
