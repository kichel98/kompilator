package unit;

import cup11b.parser;
import org.junit.Test;
import syntax.*;
import syntax.command.Command;
import syntax.command.ReadCommand;
import syntax.command.WriteCommand;
import syntax.declarations.Declaration;
import syntax.declarations.VarDeclaration;
import syntax.identifier.VarIdentifier;
import syntax.value.IdentValue;
import syntax.value.NumValue;
import utils.TestUtils;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgramTest {

    @Test(expected = Exception.class)
    public void programWithoutCommandsShouldFail() throws Exception {
        String input = String.join("\n",
                "BEGIN",
                "END");

        parser p = TestUtils.prepareParserTest(input);
        p.parse();
    }

    @Test
    public void readWriteProgram() throws Exception {
        String input = String.join("\n",
                "DECLARE",
                "   _zmienna",
                "BEGIN",
                "   READ _zmienna;",
                "   WRITE _zmienna;",
                "   WRITE 1;",
                "END");

        Program expected = new Program(new ArrayList<Declaration>() {{
            add(new VarDeclaration("_zmienna"));
        }},
                new ArrayList<Command>() {{
                    add(new ReadCommand(new VarIdentifier("_zmienna")));
                    add(new WriteCommand(new IdentValue(new VarIdentifier("_zmienna"))));
                    add(new WriteCommand(new NumValue(1L)));
                }});

        parser p = TestUtils.prepareParserTest(input);

        Program actual = (Program) p.parse().value;
        assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
    }

}
