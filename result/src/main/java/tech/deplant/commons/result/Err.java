package tech.deplant.commons.result;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Error variant of Result
 *
 * @param error holds Exception that triggered the creation of the Err result.
 * @param <T> type of the Ok result (not used in Err as is)
 */
public record Err<T>(Exception error) implements Result<T> {

	private static final System.Logger LOG = System.getLogger(Result.class.getName());

	@Override
	public boolean isOk() {
		return false;
	}

	@Override
	public boolean isErr() {
		return true;
	}

	@Override
	public <R> Result<R> map(Function<T, R> mapper) {
		return new Err<>(error());
	}

	@Override
	public <R> Result<R> mapResult(Function<T, Result<R>> resultMapper) {
		return new Err<>(error());
	}

	@Override
	public Result<T> mapErr(String exceptionMessage) {
		var ex = error();
		return new Err<>(new RuntimeException(exceptionMessage, ex));
	}

	@Override
	public Result<T> mapErr(Function<Exception,Exception> exceptionFunction) {
		return new Err<>(exceptionFunction.apply(error()));
	}

	@Override
	public Result<T> logErrError(Function<Exception, String> exceptionLogFunction) {
		LOG.log(System.Logger.Level.ERROR, exceptionLogFunction.apply(error()));
		return this;
	}

	@Override
	public Result<T> logErrWarn(Function<Exception, String> exceptionLogFunction) {
		LOG.log(System.Logger.Level.WARNING, exceptionLogFunction.apply(error()));
		return this;
	}

	@Override
	public Result<T> logErrInfo(Function<Exception, String> exceptionLogFunction) {
		LOG.log(System.Logger.Level.INFO, exceptionLogFunction.apply(error()));
		return this;
	}

	@Override
	public Result<T> logErrDebug(Function<Exception, String> exceptionLogFunction) {
		LOG.log(System.Logger.Level.DEBUG, exceptionLogFunction.apply(error()));
		return this;
	}

	@Override
	public Result<T> logErrTrace(Function<Exception, String> exceptionLogFunction) {
		LOG.log(System.Logger.Level.TRACE, exceptionLogFunction.apply(error()));
		return this;
	}

	@Override
	public Optional<Exception> err() {
		return Optional.of(error());
	}

	@Override
	public T orElse(T defaultResult) {
		return defaultResult;
	}

	@Override
	public T orElse(Supplier<T> defaultResultSupplier) {
		return defaultResultSupplier.get();
	}

	@Override
	public Exception errOrThrow(String errorMessage) {
		return error();
	}

	@Override
	public Optional<T> ok() {
		return Optional.empty();
	}

	@Override
	public T orElseThrow() throws Exception {
		throw error();
	}

	@Override
	public T orElseThrow(String errorMessage) throws Exception {
		throw new RuntimeException(errorMessage, error());
	}

	@Override
	public T orElseThrow(Supplier<Exception> exceptionSupplier) throws Exception {
		var ex = exceptionSupplier.get();
		ex.addSuppressed(error());
		throw ex;
	}
}
