package tech.deplant.commons.result;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public sealed interface Result<T> {

	record Ok<T>(T result) implements Result<T> {
		@Override
		public boolean isOk() {
			return true;
		}

		@Override
		public boolean isErr() {
			return false;
		}

		@Override
		public T orElse(T defaultResult) {
			return result();
		}

		@Override
		public T orElseLazy(Supplier<T> defaultResultSupplier) {
			return result();
		}

		@Override
		public T orElseThrow() {
			return result();
		}

		@Override
		public T orElseThrow(String errorMessage) {
			return result();
		}

		@Override
		public T orElseThrow(Supplier<Exception> exceptionSupplier) {
			return result();
		}

		@Override
		public T orElseThrow(Supplier<Exception> exceptionSupplier, String errorMessage) {
			return result();
		}
	}
	record Err<T>(Exception error) implements Result<T> {
		@Override
		public boolean isOk() {
			return false;
		}

		@Override
		public boolean isErr() {
			return true;
		}

		@Override
		public T orElse(T defaultResult) {
			return defaultResult;
		}

		@Override
		public T orElseLazy(Supplier<T> defaultResultSupplier) {
			return defaultResultSupplier.get();
		}

		@Override
		public T orElseThrow() {
			throw new RuntimeException(error());
		}

		@Override
		public T orElseThrow(String errorMessage) {
			throw new RuntimeException(errorMessage, error());
		}

		@Override
		public T orElseThrow(Supplier<Exception> exceptionSupplier) {
			throw new RuntimeException(exceptionSupplier.get());
		}

		@Override
		public T orElseThrow(Supplier<Exception> exceptionSupplier, String errorMessage) {
			throw new RuntimeException(errorMessage, exceptionSupplier.get());
		}
	}

	/**
	 * Encapsulates lazy getter as a result object.
	 * Use orElseXXX methods to unwrap result and isOk(), isErr() to check status.
	 *
	 * @param resultSupplier getter for encapsulation
	 * @return wrapped result of execution
	 * @param <T> type of value
	 */
	static <T> Result<T> of(Supplier<T> resultSupplier) {
		try {
			return new Result.Ok<T>(resultSupplier.get());
		} catch (Exception err) {
			return new Result.Err<T>(err);
		}
	}

	/**
	 * Transposing Optional into Result.
	 * Throws NullPointerException on Result.Err.orElseThrow()
	 *
	 * @param optional optional value wrapper
	 * @return result of optional check
	 * @param <T> type of value
	 */
	static <T> Result<T> ofOptional(Optional<T> optional) {
		return ofOptional(optional, () -> new NullPointerException("No optional value provided!"));
	}

	/**
	 * Transposing Optional into Result.
	 * Throws the provided exception.
	 *
	 * @param optional optional value wrapper
	 * @param exceptionSupplier custom exception to throw on Result.Err.orElseThrow()
	 * @return result of optional check
	 * @param <T> type of value
	 */
	static <T> Result<T> ofOptional(Optional<T> optional, Supplier<Exception> exceptionSupplier) {
		if (Objects.isNull(optional)) {
			return new Result.Err<T>(exceptionSupplier.get());
		} else {
			if (optional.isPresent()) {
				return new Result.Ok<T>(optional.get());
			} else {
				return new Result.Err<T>(exceptionSupplier.get());
			}
		}
	}

	boolean isOk();
	boolean isErr();
	T orElse(T defaultResult);
	T orElseLazy(Supplier<T> defaultResultSupplier);
	T orElseThrow();
	T orElseThrow(String errorMessage);
	T orElseThrow(Supplier<Exception> exceptionSupplier);
	T orElseThrow(Supplier<Exception> exceptionSupplier, String errorMessage);

}