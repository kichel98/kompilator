package unit;

import org.junit.Ignore;
import org.junit.Test;
import syntax.Program;
import utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class MainVisitorTest {

    @Test
    @Ignore
    public void readAndWrite() throws Exception {
        String input = String.join("\n",
                "DECLARE",
                "   a, b",
                "BEGIN",
                "   READ a;",
                "   READ b;",
                "   WRITE b;",
                "   WRITE a;",
                "END");

        Program result = TestUtils.prepareFullUnitTest(input);
        TestUtils.WriterWithVisitor writerWithVisitor = TestUtils.getVisitorAndWriterInstance();
        result.accept(writerWithVisitor.getVisitor());

        String expected = String.join("\n",
                "GET",
                "STORE 1",
                "GET",
                "STORE 2",
                "LOAD 2",
                "PUT",
                "LOAD 1",
                "PUT",
                "HALT",
                "");

        assertThat(writerWithVisitor.getWriter().toString()).isEqualTo(expected);
    }
}
