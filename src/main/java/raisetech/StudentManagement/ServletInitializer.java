// ServletInitializer.java
package raisetech.StudentManagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot アプリケーションを Servlet コンテナで実行するための初期化クラス。
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * SpringApplicationBuilder を設定します。
	 *
	 * @param application SpringApplicationBuilder オブジェクト
	 * @return SpringApplicationBuilder オブジェクト
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// StudentManagementApplication クラスをソースとして SpringApplicationBuilder に設定します。
		return application.sources(StudentManagementApplication.class);
	}

}