import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts four arguments and produces a result.
 * This is the four-arity specialization of {@link java.util.function.Function}.
 * <p/>
 * <p>This is a functional interface whose
 * functional method is {@link #apply(Object, Object, Object, Object)}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <V> the type of the third argument to the function
 * @param <W> the type of the fourth argument to the function
 * @param <R> the type of the result of the function
 * @see TernaryFunction
 * @see java.util.function.BiFunction
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface QuaternaryFunction<T, U, V, W, R> {

/**
 * Applies this function to the given arguments.
 *
 * @param t the first function argument
 * @param u the second function argument
 * @param v the third function argument
 * @param w the fourth function argument
 * @return the function result
 */
R apply(T t, U u, V v, W w);

/**
 * Returns a composed function that first applies this function to
 * its input, and then applies the {@code after} function to the result.
 * If evaluation of either function throws an exception, it is relayed to
 * the caller of the composed function.
 *
 * @param <X> the type of output of the {@code after} function, and of the
 * composed function
 * @param after the function to apply after this function is applied
 * @return a composed function that first applies this function and then
 *         applies the {@code after} function
 * @throws NullPointerException if after is null
 */
default <X> QuaternaryFunction<T, U, V, W, X> andThen(Function<? super R, ? extends X> after) {
  Objects.requireNonNull(after);
  return (T t, U u, V v, W w) -> after.apply(apply(t, u, v, w));
}
}
