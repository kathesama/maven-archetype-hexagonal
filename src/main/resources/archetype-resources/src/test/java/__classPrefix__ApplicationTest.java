package ${package};

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ${classPrefix}ApplicationTest {

	@BeforeAll
	public void init() {}

	@Test
	public void contextLoads() {
	}
}
