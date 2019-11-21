package common.study.main;

import common.study.entity.One;
import common.study.entity.Two;
import common.study.option.ParsedEntity;
import common.study.option.ServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainDemo {

    public static void main(String[] args) {
        ServiceResponse<List<ParsedEntity<One, Two>>> listServiceResponse = new ServiceResponse<>();
        ParsedEntity<One, Two> parsedEntity = new ParsedEntity<>();
        List<ParsedEntity<One, Two>> list = new ArrayList<>();
        list.add(parsedEntity);
        listServiceResponse.setData(list);

        parsedEntity.setEntity(new One());

        One orderItemEntity = Optional.ofNullable(listServiceResponse)
                .map(ServiceResponse::getData)
                .map(lt -> lt.get(0))
                .map(ParsedEntity::getEntity)
                .orElse(null);

        System.out.println(orderItemEntity);
    }
}
