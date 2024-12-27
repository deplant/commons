package tech.deplant.commons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class ObjTest {

	@Test
	public void nvl_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		Assertions.assertEquals(notNullObject, Obj.notNullElse(notNullObject, defaultObject));
		Assertions.assertEquals(defaultObject, Obj.notNullElse(null, defaultObject));
	}

	@Test
	public void nvl2_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		var replaceObject = "replacementString";
		Assertions.assertEquals(replaceObject,
		                        Obj.notNullReplaceElse(notNullObject, replaceObject, defaultObject));
		Assertions.assertEquals(defaultObject, Obj.notNullReplaceElse(null, replaceObject, defaultObject));
	}

	@Test
	public void nvlLazy_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		Assertions.assertEquals(notNullObject, Obj.notNullElseLazy(notNullObject, () -> defaultObject));
		Assertions.assertEquals(defaultObject, Obj.notNullElseLazy(null, () -> defaultObject));
	}

	@Test
	public void nvl2Lazy_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		var replaceObject = "replacementString";
		Assertions.assertEquals(replaceObject,
		                        Obj.notNullReplaceElseLazy(notNullObject,
		                                                   () -> replaceObject,
		                                                   () -> defaultObject));
		Assertions.assertEquals(defaultObject,
		                        Obj.notNullReplaceElseLazy(null, () -> replaceObject, () -> defaultObject));
	}

	@Test
	public void isNull_and_isNotNull_shows_correct_booleans() {
		var notNullObject = "NotNull";
		Assertions.assertTrue(Obj.isNull(null));
		Assertions.assertTrue(Obj.isNotNull(notNullObject));
		Assertions.assertFalse(Obj.isNull(notNullObject));
		Assertions.assertFalse(Obj.isNotNull(null));
	}

	@Test
	public void not_null_check_equals_original_obj() {
		var notNullObject = "NotNull";
		Assertions.assertEquals(notNullObject, Obj.notNull(notNullObject));
	}

	@Test
	public void not_null_check_throws_on_null() {
		var notNullObject = "NotNull";
		assertThrows(NullPointerException.class, () -> Obj.notNull(null));
	}

	@Test
	public void check_eq_returns() {
		var notNullObject = "NotNull";
		assertThrows(NullPointerException.class, () -> Obj.notNull(null));
	}

}
