from arena_pytest.exec.executable_component import BuildTool, ExecutableComponentBuilder


def test_build_tool_bazel_returns_bazel_field_with_target_and_args():
    assert BuildTool.bazel("//foo:bar", ["--config=ci"]) == {
        "bazel": "//foo:bar",
        "args": ["--config=ci"],
    }


def test_build_tool_bazel_no_args_defaults_to_empty_list():
    assert BuildTool.bazel("//foo:bar") == {"bazel": "//foo:bar", "args": []}


def test_with_build_tool_bazel_sets_build_tool_config():
    config = (
        ExecutableComponentBuilder()
        .with_build_tool_bazel("//foo:bar", ["--config=ci"])
        .build()
        ._for_ffi()
    )

    assert config["build_tool"] == {"bazel": "//foo:bar", "args": ["--config=ci"]}
