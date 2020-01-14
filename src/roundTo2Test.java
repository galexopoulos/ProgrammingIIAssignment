
import static org.junit.jupiter.api.Assertions.*;
import java.lang.*;
import org.junit.jupiter.api.Test;

class roundTo2Test {

	@Test
	void test() {
		double output = gr.aueb.dmst.erp.Hr_Director.roundTo2(20.123);
		assertEquals(20.12, output);
	}

}
