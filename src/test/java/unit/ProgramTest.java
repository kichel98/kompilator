package unit;

import cup11b.parser;
import org.junit.Test;
import syntax.AddExpression;
import syntax.Program;
import utils.TestUtils;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgramTest {

    @Test
    public void emptyProgram() throws Exception {
        String input = String.join("\n",
                "DECLARE",
                "BEGIN",
                "   2 PLUS 3",
                "END");
        Program expected = new Program(new AddExpression(2, 3));

        parser p = TestUtils.prepareParserTest(input);

        Program actual = (Program) p.parse().value;
        assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
    }

}
