package f2.spring.function;

import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyExclusionFilter  implements AutoConfigurationImportFilter {
    private static final Set<String> SHOULD_SKIP = new HashSet<>(
            Arrays.asList("org.springframework.cloud.function.context.config.KotlinLambdaToFunctionAutoConfiguration")
    );

    @Override
    public boolean[] match(String[] classNames, AutoConfigurationMetadata metadata) {
        boolean[] matches = new boolean[classNames.length];

        for(int i = 0; i< classNames.length; i++) {
            matches[i] = !SHOULD_SKIP.contains(classNames[i]);
        }
        return matches;
    }
}
