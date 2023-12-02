package backend.service.impl;

import backend.service.SortingService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SortingServiceImpl implements SortingService {
    public static final String SORT_FIELD_SEPARATOR = ";";
    public static final String DIRECTION_FIELD_SEPARATOR = ":";

    @Override
    public PageRequest getSortedList(Integer count, Integer page, String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(DIRECTION_FIELD_SEPARATOR)) {
            String[] sortingFields = sortBy.split(SORT_FIELD_SEPARATOR);
            for (String field : sortingFields) {
                Sort.Order order;
                if (field.contains(DIRECTION_FIELD_SEPARATOR)) {
                    String[] fieldsAndDirections = field.split(DIRECTION_FIELD_SEPARATOR);
                    order = new Sort.Order(Sort.Direction.valueOf(fieldsAndDirections[1]),
                            fieldsAndDirections[0]);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, field);
                }
                orders.add(order);
            }
        } else {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, sortBy);
            orders.add(order);
        }
        Sort sort = Sort.by(orders);
        return PageRequest.of(page, count, sort);
    }
}
