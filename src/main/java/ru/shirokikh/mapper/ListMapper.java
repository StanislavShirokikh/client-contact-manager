package ru.shirokikh.mapper;

import java.util.List;
import java.util.function.Function;

public class ListMapper {
    public static <S, T> List<T> mapToList(List<S> sourceList, Function<S, T> mapper) {
        return sourceList.stream()
                .map(mapper)
                .toList();
    }
}
