package unit;

import org.junit.Test;
import syntax.Program;
import utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class MainVisitorTest {

    @Test
    public void readAndWrite() throws Exception {
        String input = String.join("\n",
                "DECLARE",
                "   a",
                "BEGIN",
                "   READ a;",
                "   WRITE a;",
                "END");

        Program result = TestUtils.prepareFullUnitTest(input);
        TestUtils.WriterWithVisitor writerWithVisitor = TestUtils.getVisitorAndWriterInstance();
        result.accept(writerWithVisitor.getVisitor());

        String expected = String.join("\n",
                "GET",
                "STORE 1",
                "LOAD 1",
                "PUT",
                "HALT",
                "");

        assertThat(writerWithVisitor.getWriter().toString()).isEqualTo(expected);
    }
}
