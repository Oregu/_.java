import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static java.util.stream.Collectors.toList;

public interface _ {

static <T, U, R> Function<T, Function<U, R>> curry(BiFunction<T, U, R> bi) {
  return t -> u -> bi.apply(t, u);
}

static IntFunction<IntUnaryOperator> curry(IntBinaryOperator bi) {
  return l -> r -> bi.applyAsInt(l, r);
}

static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> bi, T t) {
  return u -> bi.apply(t, u);
}

static <T, U, V, R> Function<T, BiFunction<U, V, R>> curry(TernaryFunction<T, U, V, R> ternaryFunction) {
  return t -> (u, v) -> ternaryFunction.apply(t, u, v);
}

static <T, U, V, R> BiFunction<U, V, R> partial(TernaryFunction<T, U, V, R> ternary, T t) {
  return (u, v) -> ternary.apply(t, u, v);
}

static <CT extends List<T>, T, R> List<R> map(Function<T, R> f, CT coll) {
  return coll.stream().map(f).collect(toList());
}

/**
 * Represents a function that accepts three arguments and produces a result.
 * This is the three-arity specialization of {@link Function}.
 * <p/>
 * <p>This is a functional interface whose
 * functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <V> the type of the third argument to the function
 * @param <R> the type of the result of the function
 * @see BiFunction
 * @see Function
 */
@FunctionalInterface
public interface TernaryFunction<T, U, V, R> {

  /**
   * Applies this function to the given arguments.
   *
   * @param t the first function argument
   * @param u the second function argument
   * @param v the third function argument
   * @return the function result
   */
  R apply(T t, U u, V v);

  /**
   * Returns a composed function that first applies this function to
   * its input, and then applies the {@code after} function to the result.
   * If evaluation of either function throws an exception, it is relayed to
   * the caller of the composed function.
   *
   * @param <W> the type of output of the {@code after} function, and of the
   * composed function
   * @param after the function to apply after this function is applied
   * @return a composed function that first applies this function and then
   *         applies the {@code after} function
   * @throws NullPointerException if after is null
   */
  default <W> TernaryFunction<T, U, V, W> andThen(Function<? super R, ? extends W> after) {
    Objects.requireNonNull(after);
    return (T t, U u, V v) -> after.apply(apply(t, u, v));
  }

}

}
