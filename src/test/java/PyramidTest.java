/**
 * @author Sylvain Tenier
 *
 * This code is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this code.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PyramidTest {
    static final PrintStream originalOut=System.out;
    // Object for redirecting output to a String for testing
    ByteArrayOutputStream outContent;
    // Test patterns for expected results
    static final HashMap<String, String> pyraPatterns = new HashMap<String, String>() {{
        put("pyra1x1", Character.toString(Pyramid.HOTCUP));
        put("pyra2x3","" + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP);
        put("pyra3x5","" + Pyramid.SPACE +  Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP+ Pyramid.HOTCUP);
        put("pyra6x11","" + Pyramid.SPACE +  Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE+ Pyramid.SPACE
                + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.SPACE +  Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE+ Pyramid.HOTCUP
                + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.SPACE +  Pyramid.SPACE + Pyramid.SPACE + Pyramid.HOTCUP+ Pyramid.SPACE
                + Pyramid.SPACE + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.SPACE +  Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE + Pyramid.SPACE
                + Pyramid.SPACE + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE + Pyramid.SPACE+ Pyramid.SPACE+ Pyramid.SPACE + Pyramid.SPACE
                + Pyramid.SPACE + Pyramid.SPACE + Pyramid.HOTCUP + Pyramid.SPACE
                + System.lineSeparator() + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP
                + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP + Pyramid.HOTCUP);
    }};


    /** Redirect output to a stream instead of screen */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /** Close stream and set output back to standard output (screen) */
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        try {
            outContent.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(originalOut);
    }

    /**
     * Test whether any output is generated according to the provided value
     * Should yield no output if value <=0
     * Should yield some output if value > 0
     * @see Pyramid#generatePyramidFromHeight(int)
     * @see Pyramid#generatePyramidFromWidth(int)
     */
    @RepeatedTest(10)
    void testEmptyOrNOtAccordingToArgument(TestReporter testReporter){
        int simulatedInput=(int)(Math.random()*20)-10;
        Pyramid p = new Pyramid();
        p.generatePyramidFromHeight(simulatedInput);
        String result = outContent.toString().trim();
        if(simulatedInput<=0){
            assertEquals("",result,"Non positive argument yields no output");
        }else{
            assertNotEquals("",result,"Positive argument yields non empty output");
        }
    }

    /** Test different output patterns according to the provided value
     * @see Pyramid#generatePyramidFromHeight(int)
     */
    @DisplayName("Complete check of output pattern from given height")
    @ParameterizedTest
    @MethodSource("heightPatternProvider")
    void testPyramidsPatternsFromHeight(int height, String expected) {
        Pyramid p = new Pyramid();
        p.generatePyramidFromHeight(height);
        String result = outContent.toString().trim();
        assertEquals(expected, result, () -> "Generated pattern from height "+ height);
    }
    /** Provide a stream of inputs with the associated expected pattern */
    static Stream<Arguments> heightPatternProvider() {
        return Stream.of(
                Arguments.of(1, pyraPatterns.get("pyra1x1")),
                Arguments.of(2, pyraPatterns.get("pyra2x3")),
                Arguments.of(3, pyraPatterns.get("pyra3x5")),
                Arguments.of(6, pyraPatterns.get("pyra6x11"))
        );
    }

    /** Test different output patterns according to the provided value
     * @see Pyramid#generatePyramidFromWidth(int)
     */
    @DisplayName("Complete check of output pattern from given width")
    @ParameterizedTest
    @MethodSource("widthPatternProvider")
    void testPyramidsPatternsFromWidth(int width, String expected) {
        Pyramid p = new Pyramid();
        p.generatePyramidFromWidth(width);
        String result = outContent.toString().trim();
        assertEquals(expected, result, () -> "Generated pattern from width "+ width);
    }

    /** Provide a stream of inputs with the associated expected pattern */
    static Stream<Arguments> widthPatternProvider() {
        return Stream.of(
                Arguments.of(1, pyraPatterns.get("pyra1x1")),
                Arguments.of(2, "" + Pyramid.CROSS + Pyramid.CROSS),
                Arguments.of(3, pyraPatterns.get("pyra2x3")),
                Arguments.of(5, pyraPatterns.get("pyra3x5")),
                Arguments.of(8, "" + Pyramid.CROSS + Pyramid.CROSS + Pyramid.CROSS + Pyramid.CROSS + Pyramid.CROSS
                        + Pyramid.CROSS + Pyramid.CROSS + Pyramid.CROSS ),
                Arguments.of(11, pyraPatterns.get("pyra6x11"))
        );
    }

}