import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public interface _ {

static <T, U, R> Function<T, Function<U, R>> curry(BiFunction<T, U, R> bi) {
  return t -> u -> bi.apply(t, u);
}

static IntFunction<IntUnaryOperator> curry(IntBinaryOperator bi) {
  return l -> r -> bi.applyAsInt(l, r);
}

static <T, U, V, R> Function<T, BiFunction<U, V, R>> curry(TernaryFunction<T, U, V, R> ternaryFunction) {
  return t -> (u, v) -> ternaryFunction.apply(t, u, v);
}

static <T, U, V, W, R> Function<T, TernaryFunction<U, V, W, R>> curry(QuaternaryFunction<T, U, V, W, R> quaternaryFunction) {
  return t -> (u, v, w) -> quaternaryFunction.apply(t, u, v, w);
}

static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> bi, T t) {
  return u -> bi.apply(t, u);
}

static <T, U, V, R> BiFunction<U, V, R> partial(TernaryFunction<T, U, V, R> ternary, T t) {
  return (u, v) -> ternary.apply(t, u, v);
}

static <T, U, V, W, R> TernaryFunction<U, V, W, R> partial(QuaternaryFunction<T, U, V, W, R> quaternary, T t) {
  return (u, v, w) -> quaternary.apply(t, u, v, w);
}

static <CT extends Collection<T>, T, R> List<R> map(Function<T, R> f, CT coll) {
  return coll.stream().map(f).collect(toList());
}

static <CT extends Collection<T>, T, R> Set<R> mapDistinct(Function<T, R> f, CT coll) {
  return coll.stream().map(f).collect(toSet());
}

static <CT extends Collection<T>, T> Optional<T> findFirst(Predicate<T> p, CT coll) {
  return coll.stream().filter(p).findFirst();
}

static <T> Optional<T> findFirst(Predicate<T> p, T[] arr) {
  return Arrays.stream(arr).filter(p).findFirst();
}

static <CT extends Collection<T>, T, K, V> Map<K, V> toMap(Function<T, K> keyMapper, Function<T, V> valueMapper, CT coll) {
  return coll.stream().collect(Collectors.toMap(keyMapper, valueMapper));
}

}
