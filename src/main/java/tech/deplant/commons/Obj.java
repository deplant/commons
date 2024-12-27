package tech.deplant.commons;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Obj {

	public static <OUT,IN> OUT decode(IN obj, IN compare, OUT then, OUT orElse) {
		return equals(obj, compare) ? then : orElse;
	}

	public static boolean isNull(Object obj) {
		return null == obj;
	}

	public static boolean isNotNull(Object obj) {
		return obj != null;
	}

	public static boolean equals(Object a, Object b) {
		return (a == b) || (isNotNull(a) && a.equals(b));
	}

	/**
	 * Throws NullPointerException if object is null
	 *
	 * @param obj Object that will be checked
	 * @param <T>
	 * @return passed object
	 */
	public static <T> T notNull(T obj) {
		if (isNull(obj)) {
			throw new NullPointerException();
		}
		return obj;
	}

	/**
	 * Throws NullPointerException with provided message if object is null
	 *
	 * @param obj     Object that will be checked
	 * @param message Message for exception
	 * @param <T>
	 * @return passed object
	 */
	public static <T> T notNull(T obj, String message) {
		if (isNull(obj)) {
			throw new NullPointerException(message);
		}
		return obj;
	}

	/**
	 * Throws custom exception if object is null
	 *
	 * @param obj               Object that will be checked
	 * @param exceptionSupplier Exception provider that will be thrown if object is null
	 * @param <T>
	 * @return passed object
	 */
	public static <X extends Throwable, T> T notNull(T obj, Supplier<? extends X> exceptionSupplier) throws X {
		if (isNull(obj)) {
			throw notNull(exceptionSupplier).get();
		}
		return obj;
	}

	public static <T> void notNullDo(T obj, Consumer<T> action) {
		if (isNotNull(obj)) {
			notNull(action).accept(obj);
		}
	}


	/**
	 * Replaces importing of Objects.requireNonNullElse()
	 * Returns defaultObj if obj is null
	 */
	public static <T> T notNullElse(T obj, T defaultObj) {
		return isNotNull(obj) ? obj : notNull(defaultObj);
	}

	public static <T> T notNullElseLazy(T obj, Supplier<? extends T> supplier) {
		return isNotNull(obj) ? obj : notNull(notNull(supplier).get());
	}

	/**
	 * If not null returns replaceObj, if null returns defaultObj
	 * analogue of NVL2() function in Oracle SQL
	 */
	public static <T, R> R notNullReplaceElse(T obj, R replaceObj, R defaultObj) {
		return isNotNull(obj) ? notNull(replaceObj) : notNull(defaultObj);
	}

	public static <T, R> R notNullReplaceElseLazy(T obj,
	                                              Supplier<? extends R> replaceSupplier,
	                                              Supplier<? extends R> defaultSupplier) {
		return isNotNull(obj) ? notNull(notNull(replaceSupplier).get()) : notNull(notNull(defaultSupplier).get());
	}


}
