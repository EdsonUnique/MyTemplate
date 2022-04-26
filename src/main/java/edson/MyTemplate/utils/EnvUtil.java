package edson.MyTemplate.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvUtil implements EnvironmentAware {

    private static Boolean devEnv=true;
    @Override
    public void setEnvironment(Environment environment) {
        String property = environment.getProperty("spring.profiles.active");
        devEnv="dev".equals(property);

    }

    public static Boolean getDevEnv() {
        return devEnv;
    }
}
