package dfw.mapstruct.common;

/**
 * Created by fw on 2020/8/6
 */
public class TestMapStruct {
    public static void main(String[] args) {
        //given
        Car car = new Car("Morris", "4make", 13);
        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        //then
        System.out.println(carDto.getSeatCount());
    }
}
