package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     *
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] splitByDirection = signatureString.split("[()]");

        String[] splitBySpace = splitByDirection[0].split(" ");
        String
                accessModifier = splitBySpace.length == 3 ? splitBySpace[0] : null,
                returnType = splitBySpace[splitBySpace.length - 2],
                methodName = splitBySpace[splitBySpace.length - 1];

        List<MethodSignature.Argument> args =
                splitByDirection.length > 1
                        ? extractArguments(splitByDirection[1])
                        : new ArrayList<>();

        MethodSignature ans = new MethodSignature(methodName, args);
        ans.setReturnType(returnType);
        ans.setAccessModifier(accessModifier);

        return ans;
    }

    private List<MethodSignature.Argument> extractArguments(String argumentsStr) {
        String[] arguments = argumentsStr.split(",");
        List<MethodSignature.Argument> ans = new ArrayList<>();
        for (String arg : arguments) {
            String[] splitBySpace = arg.trim().split(" ");
            ans.add(new MethodSignature.Argument(splitBySpace[0], splitBySpace[1]));
        }
        return ans;
    }
}
