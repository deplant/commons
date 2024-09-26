package tech.deplant.commons.math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpressionBuilder {
	private final String expression;
	//private final Map<String, Function> userFunctions = new HashMap(4);
	//private final Map<String, Operator> userOperators = new HashMap(4);
	private final Set<String> variableNames = new HashSet(4);
	private boolean implicitMultiplication = true;

	public ExpressionBuilder(String expression) {
		if (expression == null || expression.trim().isEmpty()) {
			throw new IllegalArgumentException("Expression can not be empty");
		}
		this.expression = expression;
	}


}
