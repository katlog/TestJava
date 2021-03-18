package name.katlog.mapstruct.common;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by fw on 2020/8/6
 */
public interface  CarMapper {
    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);
}
