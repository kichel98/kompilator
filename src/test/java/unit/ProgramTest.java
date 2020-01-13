package unit;

import cup11b.parser;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import syntax.*;
import utils.TestUtils;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

public class ProgramTest {

    @Test(expected = Exception.class)
    public void programWithoutCommandsShouldFail() throws Exception {
        String input = String.join("\n",
                "BEGIN",
                "END");

//        final boolean[] wasError = {false};
//        parser p = TestUtils.prepareParserTest(input);
//        parser spy_p = Mockito.spy(p);
//
//        doAnswer(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
//                wasError[0] = true;
//                return null;
//            }
//        }).when(spy_p).report_fatal_error(ArgumentMatchers.anyString(), ArgumentMatchers.anyObject());
//
//        spy_p.parse();
//        assertThat(wasError[0]).isTrue();

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

        Program expected = new Program(new ArrayList<Declaration>(){{add(new VarDeclaration("_zmienna"));}},
                new ArrayList<Command>(){{
                        add(new ReadCommand(new VarIdentifier("_zmienna")));
                        add(new WriteCommand(new IdentValue(new VarIdentifier("_zmienna"))));
                        add(new WriteCommand(new NumValue(1)));
                }});

        parser p = TestUtils.prepareParserTest(input);

        Program actual = (Program) p.parse().value;
        assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
    }

}
