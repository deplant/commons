package tech.deplant.commons.test.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Objs;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class ObjsTest {

	@Test
	public void nvl_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		Assertions.assertEquals(notNullObject, Objs.notNullElse(notNullObject, defaultObject));
		Assertions.assertEquals(defaultObject, Objs.notNullElse(null, defaultObject));
	}

	@Test
	public void nvl2_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		var replaceObject = "replacementString";
		Assertions.assertEquals(replaceObject,
		                        Objs.notNullReplaceElse(notNullObject, replaceObject, defaultObject));
		Assertions.assertEquals(defaultObject, Objs.notNullReplaceElse(null, replaceObject, defaultObject));
	}

	@Test
	public void nvlLazy_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		Assertions.assertEquals(notNullObject, Objs.notNullElseLazy(notNullObject, () -> defaultObject));
		Assertions.assertEquals(defaultObject, Objs.notNullElseLazy(null, () -> defaultObject));
	}

	@Test
	public void nvl2Lazy_outcomes_should_be_equal_to_inputted() {
		var notNullObject = "NotNull";
		var defaultObject = "defaultString";
		var replaceObject = "replacementString";
		Assertions.assertEquals(replaceObject,
		                        Objs.notNullReplaceElseLazy(notNullObject,
		                                                    () -> replaceObject,
		                                                    () -> defaultObject));
		Assertions.assertEquals(defaultObject,
		                        Objs.notNullReplaceElseLazy(null, () -> replaceObject, () -> defaultObject));
	}

	@Test
	public void isNull_and_isNotNull_shows_correct_booleans() {
		var notNullObject = "NotNull";
		Assertions.assertTrue(Objs.isNull(null));
		Assertions.assertTrue(Objs.isNotNull(notNullObject));
		Assertions.assertFalse(Objs.isNull(notNullObject));
		Assertions.assertFalse(Objs.isNotNull(null));
	}

	@Test
	public void not_null_check_equals_original_obj() {
		var notNullObject = "NotNull";
		Assertions.assertEquals(notNullObject, Objs.notNull(notNullObject));
	}

	@Test
	public void not_null_check_throws_on_null() {
		var notNullObject = "NotNull";
		assertThrows(NullPointerException.class, () -> Objs.notNull(null));
	}

	@Test
	public void check_eq_returns() {
		var notNullObject = "NotNull";
		assertThrows(NullPointerException.class, () -> Objs.notNull(null));
	}

}
