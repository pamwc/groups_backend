package edu.groups.server.utils;

import edu.groups.server.entity.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 17.11.2017 at 15:17.
 */
public class CollectionsUtil {
    public static <T extends BaseEntity, R> Optional<List<R>> mapToVisibleValue(Collection<T> elements,
                                                                                Function<T, Optional<R>> mapFun) {
        return ofNullable(elements).map(that -> mapAndGetVisibleAndPresentValue(mapFun, that));

    }

    private static <T extends BaseEntity, R> List<R> mapAndGetVisibleAndPresentValue(Function<T, Optional<R>> mapFun,
                                                                                     Collection<T> elements) {
        return elements.
                stream()
                .filter(BaseEntity::isVisible)
                .map(mapFun)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
