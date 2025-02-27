package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

class TestDemoJUnitTest {
private TestDemo testDemo;

@BeforeEach
void setUp() throws Exception {
  testDemo = new TestDemo();
}

  @ParameterizedTest
  @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
  void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
    if (!expectException) {
      assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.addPositive(a, b))
        .isInstanceOf(IllegalArgumentException.class);
    }
  }
  static Stream<Arguments> argumentsForAddPositive() {
	  return Stream.of(
	    arguments(2, 4, 6, false),
	    arguments(0, 4, 0, true),
	    arguments(-1, 4, 0, true),
	    arguments(1, -4, 0, true),
	    arguments(1, 1, 2, false)
	  );
	}

  @Test
  void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
    assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
    assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
    // Can add more cases if need be
  }

  @ParameterizedTest
  @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForMultiplyPositive")
  void assertThatTwoPositiveNumbersAreMultipliedCorrectly(int a, int b, int expected, boolean expectException) {
    if (!expectException) {
      assertThat(testDemo.multiplyPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.multiplyPositive(a, b))
        .isInstanceOf(IllegalArgumentException.class);
    }
  }

  static Stream<Arguments> argumentsForMultiplyPositive() {
    return Stream.of(
      arguments(2, 4, 8, false),
      arguments(0, 4, 0, true),
      arguments(-1, 4, 0, true),
      arguments(1, -4, 0, true),
      arguments(3, 3, 9, false)
    );
  }

  @Test
  void assertThatNumberSquaredIsCorrect() {
    TestDemo mockDemo = spy(testDemo);
    doReturn(5).when(mockDemo).getRandomInt();
    int fiveSquared = mockDemo.randomNumberSquared();
    assertThat(fiveSquared).isEqualTo(25);
  }

  
}
