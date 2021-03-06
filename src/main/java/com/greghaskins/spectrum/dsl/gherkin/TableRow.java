package com.greghaskins.spectrum.dsl.gherkin;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TableRow<T> {

  private final String description;
  private final Consumer<T> blockRunner;

  TableRow(Consumer<T> blockRunner, Object... arguments) {
    this.blockRunner = blockRunner;
    this.description = describe(arguments);
  }

  void runDeclaration(T block) {
    this.blockRunner.accept(block);
  }

  @Override
  public String toString() {
    return this.description;
  }

  private static String describe(Object[] objects) {
    return Arrays.stream(objects)
        .map(o -> Optional.ofNullable(o)
            .map(Object::toString)
            .orElse("null"))
        .collect(Collectors.joining(" | ", "| ", " |"));
  }

}
