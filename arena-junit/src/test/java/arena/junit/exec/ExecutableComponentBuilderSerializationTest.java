package arena.junit.exec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

import org.junit.jupiter.api.Test;

final class ExecutableComponentBuilderSerializationTest {

  @Test
  void withBuildToolBazel_targetAndArgs_serializesBazelField() {
    ObjectNode config =
        new ExecutableComponentBuilder("exec")
            .withBuildToolBazel("//foo:bar", List.of("--config=ci"))
            .build()
            .forFfi();

    ObjectNode buildTool = (ObjectNode) config.get("build_tool");
    assertEquals("//foo:bar", buildTool.path("bazel").asText());
    assertEquals(1, buildTool.path("args").size());
    assertEquals("--config=ci", buildTool.path("args").get(0).asText());
  }
}
