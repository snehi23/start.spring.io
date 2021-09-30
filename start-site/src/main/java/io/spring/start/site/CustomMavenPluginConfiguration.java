package io.spring.start.site;

import org.springframework.context.annotation.Bean;
import io.spring.initializr.generator.buildsystem.maven.MavenBuild;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

@ProjectGenerationConfiguration
@ConditionalOnRequestedDependency("custom-maven-plugin")
public class CustomMavenPluginConfiguration {

  @Bean
  public BuildCustomizer<MavenBuild> customPluginConfigurer() {
    return (MavenBuild build) -> {
      build.dependencies().ids().filter(it -> it.equals("custom-maven-plugin")).findFirst()
          .map(r -> build.dependencies().get(r)).map(r -> {
            build.plugins().add(r.getGroupId(), r.getArtifactId(), (plugin) -> plugin
                .execution("my-execution", (first) -> first.goal("scan").configuration((conf) -> {
                  conf.add("failOnSeverity", "MAJOR");
                })));
            return build;
          }).orElse(build);
    };
  }


  @Bean
  public BuildCustomizer<MavenBuild> customPluginDependencyRemoval() {
    return build -> build.dependencies().remove("custom-maven-plugin");
  }
}
